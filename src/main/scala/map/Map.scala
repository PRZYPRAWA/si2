package map

import csp.{Constraint, Csp}
import map.Node.{Color, Node}

import scala.collection.immutable.HashSet

object Map {
  private val rnd = new scala.util.Random()

  def generateProblem(map: List[Node]): Csp[Node, Int] = Csp(map, v => constraints(v))

  def constraints(variables: List[Node]): List[Constraint[Node]] = {
    List(
      Constraint[Node](v =>
        v.value.isDefined &&
          variables
            .filter(vv => v.connectedTo.contains(vv.point))
            .exists(vv => vv.value.isDefined && vv.value.forall(_ == v.value.get))
      )
    )
  }

  def generateMap(numOfNodes: Int, numOfColors: Int, maxX: Int = 100, maxY: Int = 100): List[Node] = {
    assert(numOfNodes > 1, "Number of nodes cannot be less than 1")
    assert(numOfColors > 1, "Number of colors cannot be less than 1")

    val colors: List[Color] = (0 until numOfColors).toList

    val nodes = (0 until numOfNodes).map { n =>
      val p = Point(rnd.nextInt(maxX), rnd.nextInt(maxY))
      Node(n, p, HashSet.empty[Point], colors)
    }.toList

    connectNodes(nodes).distinct
  }

  private def connectNodes(nodes: List[Node]): List[Node] = {
    val shuffled = rnd.shuffle(nodes)

    @scala.annotation.tailrec
    def connect(ns: List[Node], result: List[Node]): List[Node] = {
      ns match {
        case Nil =>
          result
        case h :: t =>
          (t ::: result)
            .sortWith((a, b) => h.point.distanceBetween(a.point) < h.point.distanceBetween(b.point))
            .find { n =>
              if (toSegments(t ::: result).isEmpty)
                h.point != n.point &&
                  !h.connectedTo.contains(n.point)
              else {
                h.point != n.point &&
                  !h.connectedTo.contains(n.point) &&
                  toSegments(t ::: result)
                    .forall { nn =>
                      Segment(h.point, n.point) != nn &&
                        Segment(n.point, h.point) != nn &&
                        !nn.intersect(Segment(n.point, h.point)) &&
                        !nn.intersect(Segment(h.point, n.point))
                    }
              }
            } match {
            case None =>
              connect(t, h :: result)
            case Some(v) =>
              val filtered = t.filterNot(n => n.point == h.point || n.point == v.point)
              connect(filtered :+ h.connect(v) :+ v.connect(h), result)
          }
      }
    }

    connect(shuffled, Nil)
  }

  private def toSegments(ns: List[Node]): List[Segment] =
    ns.flatMap { n =>
      n.connectedTo.map(nn => Segment(n.point, nn))
    }.distinct
}

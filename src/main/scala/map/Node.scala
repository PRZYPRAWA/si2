package map

import csp.Variable

import scala.collection.immutable.HashSet

object Node {
  type Color = Int

  case class Node(
                   number: Int,
                   point: Point,
                   connectedTo: HashSet[Point],
                   domain: List[Color],
                   value: Option[Color] = None
                 ) extends Variable[Color] {

    override def copy(value: Color): Node =
      Node(number, point, connectedTo, domain, Some(value))

    override def removeFromDomain(value: Color): Node =
      Node(number, point, connectedTo, domain.filterNot(_ == value), this.value)

    def connect(n: Node): Node =
      Node(number, point, connectedTo + n.point, domain, value)

    override def withDomain(otherDomain: List[Color]): Variable[Color] =
      Node(number, point, connectedTo, otherDomain, value)

  }

}
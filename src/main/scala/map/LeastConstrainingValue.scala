package map

import csp.SelectValue
import map.Node.{Color, Node}

import scala.collection.immutable.{Map => ImMap}

object LeastConstrainingValue {
  def dsl: SelectValue[Node, Int] = new SelectValue[Node, Int] {
    override def select(variable: Node, variables: List[Node]): List[Int] = {
      (variable :: variables
        .filter(v => variable.connectedTo.contains(v.point) && v.value.isEmpty))
        .flatMap(_.domain)
        .foldLeft(ImMap.empty[Color, Int]) { (acc, value) =>
          acc + (value -> (acc.getOrElse(value, 0) + 1))
        }
        .toList
        .sortWith((a, b) => a._2 < b._2)
        .map(_._1)
        .filter(v => variable.domain.contains(v))
    }
  }
}

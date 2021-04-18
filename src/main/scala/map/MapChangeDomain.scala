package map

import csp.ChangeDomain
import map.Node.Node

object MapChangeDomain {
  implicit val change: ChangeDomain[Node, Int] = new ChangeDomain[Node, Int] {
    override def change(variable: Node, variables: List[Node]): List[Node] = {
      variables.map { v =>
        if (v.connectedTo.contains(variable.point)) {
          v.removeFromDomain(variable.value.get)
        } else {
          v
        }
      }
    }
  }
}

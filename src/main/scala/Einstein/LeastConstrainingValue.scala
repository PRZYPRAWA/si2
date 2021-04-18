package Einstein

import Einstein.values.Property
import csp.SelectValue

object LeastConstrainingValue {
  def dsl: SelectValue[HouseProp, Property[String]] = new SelectValue[HouseProp, Property[String]] {
    override def select(variable: HouseProp, variables: List[HouseProp]): List[Property[String]] = {
      variables
        .filter(v => v.propType == variable.propType && v.value.isEmpty)
        .flatMap(_.domain)
        .foldLeft(Map.empty[Property[String], Int]) { (acc, value) =>
          acc + (value -> (acc.getOrElse(value, 0) + 1))
        }
        .toList
        .sortWith((a, b) => a._2 < b._2)
        .map(_._1)
        .filter(v => variable.domain.contains(v))
    }
  }
}

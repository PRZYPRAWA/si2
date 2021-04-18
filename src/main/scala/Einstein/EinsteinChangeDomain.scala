package Einstein

import Einstein.values.Property
import csp.ChangeDomain

object EinsteinChangeDomain {
  implicit val change: ChangeDomain[HouseProp, Property[String]] = new ChangeDomain[HouseProp, Property[String]] {
    override def change(variable: HouseProp, variables: List[HouseProp]): List[HouseProp] = {
      variables.map { v =>
        if (v.propType == variable.propType) {
          v.removeFromDomain(variable.value.get)
        } else {
          v
        }
      }
    }
  }
}

package Einstein

import Einstein.values.Property.PropType.PropType
import Einstein.values.Property
import csp.Variable

case class HouseProp(
                      number: Int,
                      domain: List[Property[String]],
                      propType: PropType,
                      value: Option[Property[String]] = None
                    ) extends Variable[Property[String]] {

  override def copy(value: Property[String]): HouseProp =
    HouseProp(number, domain, propType, Some(value))

  override def removeFromDomain(value: Property[String]): HouseProp = {
    HouseProp(number, domain.filterNot(d => d.value == value.value), propType, this.value)
  }
}
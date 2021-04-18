package Einstein.values

import Property.PropType

case class Property[V](value: V, propType: PropType.PropType)

object Property {

  object PropType {

    sealed trait PropType

    case object Nationality extends PropType

    case object Color extends PropType

    case object Pet extends PropType

    case object Drink extends PropType

    case object Smoke extends PropType


    val values = List(Nationality, Color, Pet, Drink, Smoke)
  }

  def getDomainOf(propType: PropType.PropType): List[Property[String]] = propType match {
    case PropType.Nationality =>
      Nationality.values
    case PropType.Color =>
      Color.values
    case PropType.Pet =>
      Pet.values
    case PropType.Drink =>
      Drink.values
    case PropType.Smoke =>
      Smoke.values
  }
}
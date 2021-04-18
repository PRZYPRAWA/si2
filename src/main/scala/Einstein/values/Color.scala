package Einstein.values

import Property.PropType

object Color {
  val Red: Property[String] = Property("Red", PropType.Color)
  val Green: Property[String] = Property("Green", PropType.Color)
  val Yellow: Property[String] = Property("Yellow", PropType.Color)
  val Blue: Property[String] = Property("Blue", PropType.Color)
  val White: Property[String] = Property("White", PropType.Color)

  val values = List(
    Red,
    Green,
    Blue,
    Yellow,
    White
  )
}
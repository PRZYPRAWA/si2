package Einstein.values

import Property.PropType

object Drink {
  val Milk: Property[String] = Property("Milk", PropType.Drink)
  val Tea: Property[String] = Property("Tea", PropType.Drink)
  val Water: Property[String] = Property("Water", PropType.Drink)
  val Coffee: Property[String] = Property("Coffee", PropType.Drink)
  val Beer: Property[String] = Property("Beer", PropType.Drink)

  val values = List(
    Milk,
    Tea,
    Water,
    Coffee,
    Beer
  )
}
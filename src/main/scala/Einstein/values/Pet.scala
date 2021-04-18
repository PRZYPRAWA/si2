package Einstein.values

import Property.PropType

object Pet {
  val Dog: Property[String] = Property("Dog", PropType.Pet)
  val Cat: Property[String] = Property("Cat", PropType.Pet)
  val Bird: Property[String] = Property("Bird", PropType.Pet)
  val Fish: Property[String] = Property("Fish", PropType.Pet)
  val Horse: Property[String] = Property("Horse", PropType.Pet)

  val values = List(
    Dog,
    Cat,
    Bird,
    Fish,
    Horse
  )
}
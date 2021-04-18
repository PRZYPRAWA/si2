package Einstein.values

import Property.PropType

object Smoke {
  val Light: Property[String] = Property("Light", PropType.Smoke)
  val Cigar: Property[String] = Property("Cigar", PropType.Smoke)
  val Pipe: Property[String] = Property("Pipe", PropType.Smoke)
  val Unfiltered: Property[String] = Property("Unfiltered", PropType.Smoke)
  val Menthol: Property[String] = Property("Menthol", PropType.Smoke)

  val values = List(
    Light,
    Cigar,
    Pipe,
    Unfiltered,
    Menthol
  )
}
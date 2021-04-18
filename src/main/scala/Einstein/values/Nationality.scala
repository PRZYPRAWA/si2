package Einstein.values

import Property.PropType

object Nationality {
  val Norwegian: Property[String] = Property("Norwegian", PropType.Nationality)
  val English: Property[String] = Property("English", PropType.Nationality)
  val Danish: Property[String] = Property("Danish", PropType.Nationality)
  val Swedish: Property[String] = Property("Swedish", PropType.Nationality)
  val German: Property[String] = Property("German", PropType.Nationality)

  val values = List(
    Norwegian,
    English,
    Danish,
    Swedish,
    German
  )
}
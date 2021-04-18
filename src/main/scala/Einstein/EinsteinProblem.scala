package Einstein

import Einstein.values.{Color, Drink, Nationality, Pet, Property, Smoke}
import csp.{Constraint, Csp}

object EinsteinProblem {
  val numberOfHouses: Int = 5

  def generate: Csp[HouseProp, Property[String]] = Csp(variables, v => constraints(v))

  val variables: List[HouseProp] = {
    (0 until numberOfHouses).flatMap { n =>
      Property.PropType.values.map { p =>
        Einstein.HouseProp(n, Property.getDomainOf(p), p)
      }
    }.toList
  }

  def constraints(variables: List[HouseProp]): List[Constraint[HouseProp]] = List(
    Constraint(v => //0
      v.value.isDefined &&
        variables
          .filter(vv =>
            v.propType == vv.propType &&
              vv.value.isDefined)
          .count(vv => vv.value == v.value) > 1
    ),
    Constraint( //1
      v =>
        v.number == 0 &&
          v.value.isDefined &&
          v.propType == Property.PropType.Nationality &&
          !v.value.contains(Nationality.Norwegian)
    ),
    Constraint(v => //2
      v.value.isDefined &&
        variableIsNot(variables, v, values.Nationality.English, Color.Red)
    ),
    Constraint(v => //3
      v.propType == Property.PropType.Color &&
        v.value.isDefined &&
        v.value.forall(_ == values.Color.White) &&
        leftIsNot(variables, v, values.Color.Green)
    ),
    Constraint(v =>
      v.value.isDefined &&
        variableIsNot(variables, v, values.Nationality.Danish, Drink.Tea)
    ), //4
    Constraint(v => //5
      v.propType == Property.PropType.Smoke &&
        v.value.isDefined &&
        v.value.forall(_ == Smoke.Light) &&
        sideIsNot(variables, v, Pet.Cat)
    ),
    Constraint(v =>
      v.value.isDefined &&
        variableIsNot(variables, v, values.Color.Yellow, values.Smoke.Cigar)
    ), //6
    Constraint(v =>
      v.value.isDefined &&
        variableIsNot(variables, v, values.Nationality.German, values.Smoke.Pipe)
    ), //7
    Constraint(v => //8
      v.number == 2 &&
        v.value.isDefined &&
        v.propType == Property.PropType.Drink &&
        !v.value.contains(values.Drink.Milk)
    ),
    Constraint(v => //9
      v.propType == Property.PropType.Smoke &&
        v.value.isDefined &&
        v.value.forall(_ == values.Smoke.Light) &&
        sideIsNot(variables, v, values.Drink.Water)
    ),
    Constraint(v => //10
      v.value.isDefined &&
        variableIsNot(variables, v, values.Smoke.Unfiltered, values.Pet.Bird)
    ),
    Constraint(v => //11
      v.value.isDefined &&
        variableIsNot(variables, v, values.Nationality.Swedish, values.Pet.Dog)
    ),
    Constraint(v => //12
      v.propType == Property.PropType.Nationality &&
        v.value.isDefined &&
        v.value.contains(values.Nationality.Norwegian) &&
        sideIsNot(variables, v, values.Color.Blue)
    ),
    Constraint(v => //13
      v.propType == Property.PropType.Pet &&
        v.value.isDefined &&
        v.value.forall(_ == values.Pet.Horse) &&
        sideIsNot(variables, v, values.Color.Yellow)
    ),
    Constraint(v => //14
      v.value.isDefined &&
        variableIsNot(variables, v, values.Smoke.Menthol, values.Drink.Beer)
    ),
    Constraint(v => //15
      v.value.isDefined &&
        variableIsNot(variables, v, values.Color.Green, values.Drink.Coffee)
    )
  )

  def arcs(variables: List[HouseProp]): List[Constraint[HouseProp]] = List(
    Constraint( //1
      v =>
        v.number == 0 &&
          v.value.isDefined &&
          v.propType == Property.PropType.Nationality &&
          !v.value.contains(Nationality.Norwegian)
    ),
    Constraint(v => //2
      v.value.isDefined &&
        variableIsNot(variables, v, values.Nationality.English, Color.Red)
    ),
    Constraint(v => //3
      v.propType == Property.PropType.Color &&
        v.value.isDefined &&
        v.value.forall(_ == values.Color.White) &&
        leftIsNot(variables, v, values.Color.Green)
    ),
    Constraint(v =>
      v.value.isDefined &&
        variableIsNot(variables, v, values.Nationality.Danish, Drink.Tea)
    ), //4
    Constraint(v => //5
      v.propType == Property.PropType.Smoke &&
        v.value.isDefined &&
        v.value.forall(_ == Smoke.Light) &&
        sideIsNot(variables, v, Pet.Cat)
    ),
    Constraint(v =>
      v.value.isDefined &&
        variableIsNot(variables, v, values.Color.Yellow, values.Smoke.Cigar)
    ), //6
    Constraint(v =>
      v.value.isDefined &&
        variableIsNot(variables, v, values.Nationality.German, values.Smoke.Pipe)
    ), //7
    Constraint(v => //8
      v.number == 2 &&
        v.value.isDefined &&
        v.propType == Property.PropType.Drink &&
        !v.value.contains(values.Drink.Milk)
    ),
    Constraint(v => //9
      v.propType == Property.PropType.Smoke &&
        v.value.isDefined &&
        v.value.forall(_ == values.Smoke.Light) &&
        sideIsNot(variables, v, values.Drink.Water)
    ),
    Constraint(v => //10
      v.value.isDefined &&
        variableIsNot(variables, v, values.Smoke.Unfiltered, values.Pet.Bird)
    ),
    Constraint(v => //11
      v.value.isDefined &&
        variableIsNot(variables, v, values.Nationality.Swedish, values.Pet.Dog)
    ),
    Constraint(v => //12
      v.propType == Property.PropType.Nationality &&
        v.value.isDefined &&
        v.value.contains(values.Nationality.Norwegian) &&
        sideIsNot(variables, v, values.Color.Blue)
    ),
    Constraint(v => //13
      v.propType == Property.PropType.Pet &&
        v.value.isDefined &&
        v.value.forall(_ == values.Pet.Horse) &&
        sideIsNot(variables, v, values.Color.Yellow)
    ),
    Constraint(v => //14
      v.value.isDefined &&
        variableIsNot(variables, v, values.Smoke.Menthol, values.Drink.Beer)
    ),
    Constraint(v => //15
      v.value.isDefined &&
        variableIsNot(variables, v, values.Color.Green, values.Drink.Coffee)
    )
  )

  private def variableIsNot(
                             variables: List[HouseProp],
                             houseProp: HouseProp,
                             first: Property[String],
                             second: Property[String]): Boolean = {
    (
      houseProp.propType == first.propType &&
        houseProp.value.forall(_ == first) &&
        variables.exists { v =>
          v.value.isDefined &&
            v.number == houseProp.number &&
            v.propType == second.propType && !v.value.contains(second)
        }
      ) || (
      houseProp.propType == second.propType &&
        houseProp.value.forall(_ == second) &&
        variables.exists(v =>
          v.value.isDefined &&
            v.number == houseProp.number &&
            v.propType == first.propType && !v.value.contains(first)
        )
      )
  }

  private def leftIsNot(
                         variables: List[HouseProp],
                         houseProp: HouseProp,
                         property: Property[String]): Boolean = {
    if (houseProp.number == 0) true
    else variables
      .find(v => v.number == houseProp.number - 1 && v.propType == property.propType)
      .forall({ v =>
        v.value.isDefined && !v.value.contains(property)
      })
  }

  private def rightIsNot(
                          variables: List[HouseProp],
                          houseProp: HouseProp,
                          property: Property[String]): Boolean = {
    if (houseProp.number == numberOfHouses - 1) true
    else variables
      .find(v => v.number == houseProp.number + 1 && v.propType == property.propType)
      .forall({ v =>
        v.value.isDefined && !v.value.contains(property)
      })
  }

  private def sideIsNot(
                         variables: List[HouseProp],
                         houseProp: HouseProp,
                         property: Property[String]): Boolean =
    leftIsNot(variables, houseProp, property) && rightIsNot(variables, houseProp, property)
}

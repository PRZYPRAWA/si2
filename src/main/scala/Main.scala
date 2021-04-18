import Einstein.values.Property
import Einstein.{EinsteinProblem, HouseProp, LeastConstrainingValue => EinsteinLCS}
import csp.{Backtracking, ForwardChecking, SelectValue, SelectVariable}
import file.SaveToFile
import map.{LeastConstrainingValue => MapLCS}
import map.Node.Node

object Main extends App {
  println("Started")
  val einstein = EinsteinProblem.generate
  val einsteinSolution1 = Backtracking.solver(
    SelectVariable.default[HouseProp, Property[String]],
    EinsteinLCS.dsl).solve(einstein)
  println(s"Found ${einsteinSolution1.size} solutions")
  println(einsteinSolution1.headOption)

  val einsteinSolution12 = Backtracking.solver(SelectVariable.smallestDomain[HouseProp, Property[String]],
    SelectValue.default).solve(einstein)
  println(s"Found ${einsteinSolution12.size} solutions")
  println(einsteinSolution12.headOption)

  val einsteinSolution2 = ForwardChecking.solver(
    Einstein.EinsteinChangeDomain.change,
    SelectVariable.default[HouseProp, Property[String]],
    SelectValue.default).solve(einstein)
  println(s"Found ${einsteinSolution2.size} solutions")
  println(einsteinSolution2.headOption)

  val einsteinSolution22 = ForwardChecking.solver(
    Einstein.EinsteinChangeDomain.change,
    SelectVariable.smallestDomain[HouseProp, Property[String]],
    SelectValue.default).solve(einstein)
  println(s"Found ${einsteinSolution22.size} solutions")
  println(einsteinSolution22.headOption)

  println("-" * 50)

  val generatedMap = map.Map.generateMap(10, 5, 1000, 1000)
  val problem = map.Map.generateProblem(generatedMap)
  println(generatedMap)

  val mapSolutions1 = Backtracking.solver(SelectVariable.smallestDomain[Node, Int],
    MapLCS.dsl).solve(problem)
  println(s"Found ${mapSolutions1.size} solutions")
  mapSolutions1.headOption.foreach(println)

  val mapSolutions2 = ForwardChecking.solver(map.MapChangeDomain.change,
    SelectVariable.smallestDomain[Node, Int],
    MapLCS.dsl).solve(problem)
  println(s"Found ${mapSolutions2.size} solutions")
  mapSolutions2.headOption.foreach(println)

  SaveToFile.saveMapResults(generatedMap, mapSolutions1)
}

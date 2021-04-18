import Einstein.EinsteinProblem
import csp.{Backtracking, ForwardChecking}
import file.SaveToFile

object Main extends App {
  println("Started")
  val einstein = EinsteinProblem.generate
  val einsteinSolution1 = Backtracking.solver.solve(einstein)
  println(s"Found ${einsteinSolution1.size} solutions")
  println(einsteinSolution1.headOption)

  val einsteinSolution2 = ForwardChecking.solver(Einstein.EinsteinChangeDomain.change).solve(einstein)
  println(s"Found ${einsteinSolution2.size} solutions")
  println(einsteinSolution2.headOption)
  println(einsteinSolution1.headOption == einsteinSolution2.headOption)

  println("-" * 50)

  val generatedMap = map.Map.generateMap(10, 5, 1000, 1000)
  val problem = map.Map.generateProblem(generatedMap)
  val mapSolutions1 = Backtracking.solver.solve(problem)
  println(generatedMap)
  println(s"Found ${mapSolutions1.size} solutions")
  mapSolutions1.headOption.foreach(println)

  val mapSolutions2 = ForwardChecking.solver(map.MapChangeDomain.change).solve(problem)
  println(s"Found ${mapSolutions2.size} solutions")
  mapSolutions2.headOption.foreach(println)

  SaveToFile.saveMapResults(generatedMap, mapSolutions1)
}

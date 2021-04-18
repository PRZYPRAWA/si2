package csp

object Backtracking {
  def solver[V <: Variable[T], T](implicit selectVariable: SelectVariable[V, T],
                                  selectValue: SelectValue[V, T]): Solver[V, T] = new Solver[V, T] {
    override def solve(problem: Csp[V, T]): List[Csp[V, T]] = {
      if (problem.isInvalid) {
        List.empty[Csp[V, T]]
      } else {
        val variable = selectVariable.select(problem.variables)
        variable match {
          case None =>
            List(problem)
          case Some(v) =>
            selectValue.select(v, problem.variables).to(LazyList).foldLeft(List.empty[Csp[V, T]]) { (acc, value) =>
              //              v.domain.to(LazyList).foldLeft(List.empty[Csp[V, T]]) { (acc, value) =>
              val changedValue = v.copy(value)
              val changedProblem: Csp[V, T] = Csp(
                problem.variables.map { vv =>
                  if (vv == v) changedValue.asInstanceOf[V] else vv
                },
                problem.constraints
              )
              acc ::: solve(changedProblem)
            }
        }
      }
    }
  }
}
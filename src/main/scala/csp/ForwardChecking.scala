package csp

object ForwardChecking {
  def solver[V <: Variable[T], T](implicit changeDomain: ChangeDomain[V, T]): Solver[V, T] = new Solver[V, T] {
    override def solve(problem: Csp[V, T]): List[Csp[V, T]] = {
      if (problem.isInvalid) {
        List.empty[Csp[V, T]]
      } else {
        val variable = problem.variables.find(v => v.value.isEmpty)
        variable match {
          case None =>
            List(problem)
          case Some(v) =>
            v.domain.to(LazyList).foldLeft(List.empty[Csp[V, T]]) { (acc, value) =>
              val changedValue = v.copy(value)
              val changedProblem: Csp[V, T] = Csp(
                changeDomain.change(
                  changedValue.asInstanceOf[V],
                  problem.variables.map { vv =>
                    if (vv == v) changedValue.asInstanceOf[V] else vv
                  }
                ),
                problem.constraints
              )
              acc ::: solve(changedProblem)
            }
        }
      }
    }
  }
}

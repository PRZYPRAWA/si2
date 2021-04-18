package csp

trait Solver[V <: Variable[T], T] {
  def solve(problem: Csp[V, T]): List[Csp[V, T]]
}

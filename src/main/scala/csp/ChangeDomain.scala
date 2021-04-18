package csp

trait ChangeDomain[V <: Variable[T], T] {
  def change(variable: V, variables: List[V]): List[V]
}

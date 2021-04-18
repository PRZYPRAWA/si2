package csp

trait SelectValue[V <: Variable[T], T] {
  def select(variable: V, variables: List[V]): List[T]
}

object SelectValue {
  def default[V <: Variable[T], T]: SelectValue[V, T] = new SelectValue[V, T] {
    override def select(variable: V, variables: List[V]): List[T] =
      variable.domain
  }
}
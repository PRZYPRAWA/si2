package csp

trait SelectVariable[V <: Variable[T], T] {
  def select(variables: List[V]): Option[V]
}

object SelectVariable {
  def default[V <: Variable[T], T]: SelectVariable[V, T] = new SelectVariable[V, T] {
    override def select(variables: List[V]): Option[V] = variables.find(_.value.isEmpty)
  }

  def smallestDomain[V <: Variable[T], T]: SelectVariable[V, T] = new SelectVariable[V, T] {
    override def select(variables: List[V]): Option[V] =
      variables.filter(_.value.isEmpty).sortWith((a, b) => a.domain.size < b.domain.size).headOption
  }
}

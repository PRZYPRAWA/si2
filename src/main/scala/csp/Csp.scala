package csp

case class Csp[V <: Variable[T], T](variables: List[V], constraints: List[V] => List[Constraint[V]]) {
  def isInvalid: Boolean = variables.exists { v => constraints(variables).exists(c => c.predicate(v)) }
}

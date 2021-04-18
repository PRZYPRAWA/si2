package csp

case class Constraint[V](predicate: V => Boolean)

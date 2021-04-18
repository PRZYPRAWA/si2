package csp

trait Variable[V] {
  def value: Option[V]

  def domain: List[V]

  def copy(value: V): Variable[V]

  def removeFromDomain(value: V): Variable[V]

  def withDomain(domain: List[V]): Variable[V]
}
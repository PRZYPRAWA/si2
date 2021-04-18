package csp

object ArcConsistency {
  def makeArcConsistent[V <: Variable[T], T](csp: Csp[V, T]): Csp[V, T] = {
    Csp(
      csp.variables.map { v =>
        v.withDomain {
          csp.variables.map { vv =>
            reduceIfInconsistent(v, vv, csp)
          }.foldLeft(List.empty[T]) { (acc, value) =>
            value.domain ::: acc
          }.distinct
        }.asInstanceOf[V]
      },
      csp.constraints
    )
  }

  private def reduceIfInconsistent[V <: Variable[T], T](v1: V, v2: V, csp: Csp[V, T]): V = {
    val pairs = for {
      x <- v1.domain.map(d => v1.copy(d))
      y <- v2.domain.map(d => v2.copy(d))
    } yield (x, y)
    pairs.filter { p =>
      Csp[V, T](
        csp.variables.map { vv =>
          if (vv == v1) p._1.asInstanceOf[V]
          else if (vv == v2) p._2.asInstanceOf[V]
          else vv
        },
        csp.constraints
      ).isInvalid
    }.foldLeft(v1) { (acc, value) =>
      acc.removeFromDomain(value._1.value.get).asInstanceOf[V]
    }
  }
}

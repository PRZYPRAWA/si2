package map

case class Segment(start: Point, end: Point) {

  def intersect(segment: Segment): Boolean = {
    val o1 = orientation(start, end, segment.start)
    val o2 = orientation(start, end, segment.end)
    val o3 = orientation(segment.start, segment.end, start)
    val o4 = orientation(segment.start, segment.end, end)

    if (this == segment || this == Segment(segment.end, segment.start))
      true
    else if (start == segment.start || end == segment.start || start == segment.end || end == segment.end)
      false
    else if (o1 != o2 && o3 != o4)
      true
    else if (o1 == Segment.Collinear && onSegment(start, segment.start, end))
      true
    else if (o2 == Segment.Collinear && onSegment(start, segment.end, end))
      true
    else if (o3 == Segment.Collinear && onSegment(segment.start, start, segment.end))
      true
    else if (o4 == Segment.Collinear && onSegment(segment.start, end, segment.end))
      true
    else
      false
  }

  private def onSegment(p: Point, q: Point, r: Point): Boolean =
    q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
      q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y)

  private def orientation(p: Point, q: Point, r: Point): Segment.Orientation = {
    val value = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y)
    if (value == 0) Segment.Collinear
    else if (value > 0) Segment.Clockwise
    else Segment.Counterclockwise
  }
}

object Segment {

  sealed trait Orientation

  case object Collinear extends Orientation

  case object Clockwise extends Orientation

  case object Counterclockwise extends Orientation

}
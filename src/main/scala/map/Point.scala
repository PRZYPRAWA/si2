package map

case class Point(x: Int, y: Int) {
  def distanceBetween(p: Point): Double = {
    Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2))
  }
}

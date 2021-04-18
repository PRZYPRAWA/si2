package file

import csp.Csp
import map.Node.Node

import java.io.{BufferedWriter, File, FileWriter}
import scala.util.Random


class FWriter {
  def writeFile(filename: String, lines: Seq[String]): Unit = Control.using(
    new BufferedWriter(new FileWriter(new File(filename)))
  ) { bw =>
    for (line <- lines) {
      bw.write(line)
    }
  }
}

object SaveToFile {
  private val colors = List(
    "red",
    "tab:pink",
    "blue",
    "orange",
    "yellow",
    "green",
    "tab:olive",
    "tab:cyan",
    "tab:purple",
    "coral",
    "darkolivegreen",
    "magenta",
    "violet",
    "crimson",
    "salmon",
    "fuchsia",
    "sienna"
  )

  def saveMapResults(nodes: List[Node], csp: List[Csp[Node, Int]]): Unit = {
    val colors = new Random().shuffle(SaveToFile.colors)

    val fw = new FWriter
    val filename = s"solutions/${0}.json"
    val filenameC = s"solutions/${0}c.json"
    fw.writeFile(
      filename,
      Seq("[" + nodes.flatMap { s =>
        s.connectedTo.map(ss => s"[[${s.point.x},${s.point.y}],[${ss.x},${ss.y}]]")
      }.mkString(",")
        + "]")
    )
    fw.writeFile(
      filenameC,
      Seq("[" + nodes.map { s =>
        s"{ ${"\""}node${"\""}   : [${s.point.x},${s.point.y}], ${"\""}color${"\""} : ${"\""}${colors.head}${"\""} }"
      }.mkString(",")
        + "]")
    )

    csp.take(10).zipWithIndex.foreach { v =>
      val filename = s"solutions/${v._2}.json"
      val filenameC = s"solutions/${v._2}c.json"
      fw.writeFile(
        filename,
        Seq("[" + v._1.variables.take(10).flatMap { s =>
          s.connectedTo.map(ss => s"[[${s.point.x},${s.point.y}],[${ss.x},${ss.y}]]")
        }.mkString(",")
          + "]")
      )
      fw.writeFile(
        filenameC,
        Seq("[" + v._1.variables.take(10).map { s =>
          s"{ ${"\""}node${"\""}   : [${s.point.x},${s.point.y}], ${"\""}color${"\""} : ${"\""}${colors(s.value.get)}${"\""} }"
        }.mkString(",")
          + "]")
      )
    }
  }
}

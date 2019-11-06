
package org.wa9nnn.cabrillo

import org.wa9nnn.cabrillo.model.Value
import org.wa9nnn.cabrillo.parsers.{DefaultTagParser, QsoTagParser_WFD}

import scala.io.BufferedSource

class ParseEngine {
  private val lineRegx = """(.*)\s*:\s*(.*)""".r
  private val parsers: Map[String, QsoTagParser_WFD] = Seq(
    QsoTagParser_WFD.tag → new QsoTagParser_WFD
  ).toMap

  def parse(source: BufferedSource): Map[String, Seq[Value]] = {
    val fileAccumulator = new FileAccumulator

    source.getLines().zipWithIndex.foreach { t ⇒
      val (line: String, lineNumber: Int) = t
      val lineRegx(tag, body) = line


      fileAccumulator.accumulate(parsers.getOrElse(tag, DefaultTagParser).parse(lineNumber, tag, body))

    }

    fileAccumulator.result
  }

}

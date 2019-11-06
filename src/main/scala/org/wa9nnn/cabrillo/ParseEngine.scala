
package org.wa9nnn.cabrillo

import org.wa9nnn.cabrillo.model.ParsedCabrillo
import org.wa9nnn.cabrillo.parsers.{DefaultTagParser, QsoTagParser_WFD}

import scala.io.BufferedSource

/**
 * Knows how to parse a Cabrillo file info a form tha can be laer validated.
 */
object ParseEngine {
  private val lineRegx = """(.*)\s*:\s*(.*)""".r
  private val parsers: Map[String, QsoTagParser_WFD] = Seq(
    QsoTagParser_WFD.tag → new QsoTagParser_WFD
  ).toMap

  /**
   *
   * @param source to be processed. e.g.{{ val bufferedSource = Source.fromResource("wfd1.cbr")}}
   * @return map of tag name to values.
   */
  def parse(source: BufferedSource): ParsedCabrillo = {
    val fileAccumulator = new FileAccumulator

    source.getLines().zipWithIndex.foreach { t ⇒
      val (line: String, lineNumber: Int) = t
      val lineRegx(tag, body) = line

      fileAccumulator.accumulate(parsers.getOrElse(tag, DefaultTagParser).parse(lineNumber, tag, body))
    }

    ParsedCabrillo(fileAccumulator.result)
  }
}

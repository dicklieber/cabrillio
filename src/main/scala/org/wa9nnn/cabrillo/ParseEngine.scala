
package org.wa9nnn.cabrillo

import org.wa9nnn.cabrillo.contests.ContestInfoWFD
import org.wa9nnn.cabrillo.model.Cabrillo
import org.wa9nnn.cabrillo.parsers.LineBody
import org.wa9nnn.cabrillo.requirements.RequiredTags

import scala.io.BufferedSource

/**
 * Knows how to parse a Cabrillo file info a form that can be later validated.
 */
class ParseEngine(handlers:RequiredTags) {
  private val lineRegx = """(.*)\s*:\s*(.*)""".r
  //  private val parsers: Map[String, QsoTagParser_WFD] = Seq(
  //    QsoTagParser_WFD.tag → new QsoTagParser_WFD
  //  ).toMap

  /**
   *
   * @param source to be processed. e.g.{{ val bufferedSource = Source.fromResource("wfd1.cbr")}}
   * @return map of tag name to values.
   */
  def parse(source: BufferedSource): Cabrillo = {
    val fileAccumulator = new FileAccumulator

    source.getLines().zipWithIndex.foreach { t ⇒
      val (line: String, lineNumber: Int) = t
      val lineRegx(tag, body) = line

      fileAccumulator.accumulate(handlers.get(tag).parse(LineBody(lineNumber + 1, body)))
    }

    Cabrillo(fileAccumulator.result)
  }
}

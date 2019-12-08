
package org.wa9nnn.cabrillo

import org.wa9nnn.cabrillo.model.CabrilloData
import org.wa9nnn.cabrillo.parsers.LineBody
import org.wa9nnn.cabrillo.requirements.Rules

import scala.io.BufferedSource

/**
 * Knows how to parse a Cabrillo file info a form that can be later validated.
 */
class Parser(handlers:Rules = new Rules()) {
  private val lineRegx = """(.*)\s*:\s*(.*)""".r

  /**
   *
   * @param source to be processed. e.g.{{ val bufferedSource = Source.fromResource("wfd1.cbr")}}
   * @return map of tag name to values.
   */
  def parse(source: BufferedSource): CabrilloData = {
    val accumulator = new CabrilloAccumulator

    source.getLines().zipWithIndex.foreach { t ⇒
      val (line: String, lineNumber: Int) = t
      val lineRegx(tag, body) = line

      accumulator(handlers.get(tag).parse(LineBody(lineNumber + 1, body.trim)))
    }

    CabrilloData(accumulator.result)
  }
}

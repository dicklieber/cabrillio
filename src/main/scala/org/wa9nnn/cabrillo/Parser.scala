
package org.wa9nnn.cabrillo

import org.wa9nnn.cabrillo.model.CabrilloData
import org.wa9nnn.cabrillo.parsers.LineBody
import org.wa9nnn.cabrillo.requirements.Rules

/**
 * Knows how to parse a Cabrillo file info a form, [[CabrilloData]] that can be later validated or processed.
 */
class Parser(handlers:Rules = new Rules()) {
  private val lineRegx = """(.*)\s*:\s*(.*)""".r

  /**
   *
   * @param inputLines file to be processed.
   * @return map of tag name to values.
   */
  def parse(inputLines:Seq[String]): CabrilloData = {
    val accumulator = new CabrilloAccumulator
    inputLines.zipWithIndex.foreach { t ⇒
      val (line: String, lineNumber: Int) = t
      val lineRegx(tag, body) = line
      accumulator(handlers.get(tag).parse(LineBody(lineNumber + 1, body.trim)))
    }
    CabrilloData(accumulator.result)
  }
}

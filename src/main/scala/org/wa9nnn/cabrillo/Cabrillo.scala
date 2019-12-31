
package org.wa9nnn.cabrillo

import java.time.{Duration, Instant}

import org.wa9nnn.cabrillo.contests.ContestInfoWFD
import org.wa9nnn.cabrillo.requirements.{CabrilloError, Rules, RulesEngine}

import scala.io.Source

/**
 * This is the main a[i of the library.
 */
object Cabrillo {
  val rules = new Rules()

  /**
   * Useful for checking and data ingesting.
   *
   * @param bytes of a cabrillo file
   * @return parsed file and errors.
   */
  def apply(bytes: Array[Byte]): ResultWithData = {
    val start = Instant.now
    implicit val constestInfo = new ContestInfoWFD
    val parseEngine = new Parser(rules)

    val inputLines = Source.fromBytes(bytes).getLines().toSeq

    val cabrilloData = parseEngine.parse(inputLines)
    val checkEngine = RulesEngine(rules)

    val (errors: Seq[CabrilloError], unknowns: Seq[CabrilloError]) = checkEngine.check(cabrilloData)

    val qsoCount = cabrilloData.apply("QSO").size

    val duration = Duration.between(start, Instant.now())
    ResultWithData(Result(callSign = cabrilloData("CALLSIGN").headOption.map(_.body), duration = duration, linesInFile = cabrilloData.inLineOrder.size, qsoCount = qsoCount, errors, unknowns),
      cabrilloData)
  }
}

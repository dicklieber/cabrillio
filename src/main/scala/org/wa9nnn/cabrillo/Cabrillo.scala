
package org.wa9nnn.cabrillo

import java.time.{Duration, Instant, LocalDateTime}

import org.wa9nnn.cabrillo.contests.ContestInfoWFD
import org.wa9nnn.cabrillo.model.CabrilloData
import org.wa9nnn.cabrillo.requirements.{CabrilloError, Rules, RulesEngine}

import scala.io.BufferedSource

object Cabrillo {
  val rules = new Rules()

  /**
   * Useful for checking and data ingesting.
   */
  def apply(source: BufferedSource): ResultWithData = {
    val start = Instant.now
    implicit val constestInfo = new ContestInfoWFD
    val parseEngine = new Parser(rules)
    val cabrilloData = parseEngine.parse(source)
    val checkEngine = RulesEngine(rules)

    val (errors: Seq[CabrilloError], unknowns: Seq[CabrilloError]) = checkEngine.check(cabrilloData)

    val qsoCount = cabrilloData.apply("QSO").size

    val duration = Duration.between(start, Instant.now())
    ResultWithData(Result(email = cabrilloData("EMAIL").headOption.map(_.body), duration = duration, linesInFile = cabrilloData.inLineOrder.size, qsoCount = qsoCount, errors, unknowns),
      cabrilloData)
  }

}

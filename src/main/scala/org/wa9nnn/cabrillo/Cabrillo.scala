
package org.wa9nnn.cabrillo

import java.net.URL
import java.time.{Duration, Instant, LocalDateTime}

import org.wa9nnn.cabrillo.contests.ContestInfoWFD
import org.wa9nnn.cabrillo.requirements.{CabrilloError, RulesEngine, Rules}

import scala.io.BufferedSource

object Cabrillo {
  val rules = new Rules()

  def apply(source: BufferedSource, url: URL): Result = {
    val start = Instant.now
    implicit val constestInfo = new ContestInfoWFD
    val parseEngine = new Parser(rules)
    val parsedCabrillo = parseEngine.parse(source)
    val checkEngine = RulesEngine(rules)

    val (errors: Seq[CabrilloError], unknowns: Seq[CabrilloError]) = checkEngine.check(parsedCabrillo)

    val qsoCount = parsedCabrillo.apply("QSO").size

    val duration = Duration.between(start, Instant.now())
    Result(url = url,
      duration = duration,
      linesInFile = parsedCabrillo.inLineOrder.size,
      qsoCount = qsoCount,
      errors, unknowns)
  }
}

/**
 *
 * @param url            where the daa came from.
 * @param duration       how long it took to run the cabrillo checkRule & reporters
 * @param                 linesInFile total lines in file.
 * @param qsoCount       qso tags in file
 * @param tagsWithErrors that were found.
 * @param unknownTags    those that we dont recognize
 * @param reportRunTime  when this checkRule & reporters finished.
 */
case class Result(url: URL,
                  duration: Duration,
                  linesInFile: Int,
                  qsoCount: Int,
                  tagsWithErrors: Seq[CabrilloError],
                  unknownTags: Seq[CabrilloError],
                  reportRunTime: LocalDateTime = LocalDateTime.now())

package org.wa9nnn.cabrillo

import java.net.URL
import java.time.{Duration, Instant, LocalDateTime}

import org.wa9nnn.cabrillo.contests.ContestInfoWFD
import org.wa9nnn.cabrillo.requirements.{CabrilloError, Contest, RequiredTags, StartOfLog, TagHandler}

import scala.io.BufferedSource

object Cabrillo {
  val checkers: Seq[TagHandler] = Seq(
    StartOfLog,
    Contest
  )

  def apply(source: BufferedSource, url: URL): Result = {
    val start = Instant.now
    val parsedCabrillo = new ParseEngine(new RequiredTags()).parse(source)
    implicit val constestInfo = new ContestInfoWFD
    val errors = checkers.flatMap(_.check(parsedCabrillo))
    val duration = Duration.between(start, Instant.now())
    Result(url, duration, errors)
  }
}

/**
 *
 * @param url where the daa came from.
 * @param duration how long it took to run the cavbrillo check & report
 * @param errors that were found.
 * @param reportRunTime when this check & report finished.
 */
case class Result(url: URL, duration: Duration, errors: Seq[CabrilloError], reportRunTime: LocalDateTime = LocalDateTime.now())
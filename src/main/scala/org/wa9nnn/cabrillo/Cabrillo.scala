
package org.wa9nnn.cabrillo

import java.net.URL
import java.time.{Duration, Instant, LocalDateTime}

import org.wa9nnn.cabrillo.contests.ContestInfoWFD
import org.wa9nnn.cabrillo.requirements.{CabrilloError, CheckEngine, Rules}

import scala.io.BufferedSource

object Cabrillo {
  val requiredTags = new Rules()

  def apply(source: BufferedSource, url: URL): Result = {
    val start = Instant.now
    implicit val constestInfo = new ContestInfoWFD
    val parseEngine = new ParseEngine(requiredTags)
    val parsedCabrillo = parseEngine.parse(source)
    val checkEngine =  CheckEngine(requiredTags)

    val errors = checkEngine.check(parsedCabrillo)
    val duration = Duration.between(start, Instant.now())
    Result(url, duration, errors._1, errors._2)
  }
}

/**
 *
 * @param url            where the daa came from.
 * @param duration       how long it took to run the cavbrillo checkRule & report
 * @param tagsWithErrors that were found.
 * @param unknownTags    those that we dont recognize
 * @param reportRunTime  when this checkRule & report finished.
 */
case class Result(url:
                  URL, duration: Duration,
                  tagsWithErrors: Seq[CabrilloError],
                  unknownTags: Seq[CabrilloError],
                  reportRunTime: LocalDateTime = LocalDateTime.now())

package org.wa9nnn.cabrillo

import java.net.URL
import java.time.{Duration, Instant, LocalDateTime}

import org.wa9nnn.cabrillo.requirements.{Begin, CabrilloError, Checker, Contest}

import scala.io.BufferedSource

object Cabrillo {
  val checkers: Seq[Checker] = Seq(
    Begin,
    Contest
  )

  def apply(source: BufferedSource, url: URL): Result = {
    val start = Instant.now
    val parsedCabrillo = ParseEngine.parse(source)

    val errors = checkers.flatMap(_.check(parsedCabrillo))
    val duration = Duration.between(start, Instant.now())
    Result(url, duration, errors)
  }
}

case class Result(url: URL, duration: Duration,  errors: Seq[CabrilloError],  stamp:LocalDateTime = LocalDateTime.now())
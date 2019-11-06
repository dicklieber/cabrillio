
package org.wa9nnn.cabrillo.parsers

import java.time.{ZoneOffset, ZonedDateTime}

import org.wa9nnn.cabrillo.CabrilloException

object DateTimeParser {
  private val parts = """(\d{4})-(\d{2})-(\d{2}) (\d{2})(\d{2})""".r

  def apply(lineNumber: Int, in: String): ZonedDateTime = {
    try {
      val parts(year, month, day, hour, minute) = in
      ZonedDateTime.of(year.toInt, month.toInt, day.toInt, hour.toInt, minute.toInt, 0, 0, ZoneOffset.UTC)
    } catch {
      case x:Exception ⇒
        throw new CabrilloException(lineNumber, "DateTime error", "2017-01-28 1910", in, x)
    }
  }
}

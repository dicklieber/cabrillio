package org.wa9nnn.cabrillo.parsers

import java.time.{ZoneId, ZoneOffset}

import org.specs2.mutable.Specification
import org.wa9nnn.cabrillo.CabrilloException

class DateTimeParserSpec extends Specification {
  "DateTimeParser" >> {
    "ok" >> {
      val zdt = DateTimeParser(42, "2017-01-28 1910")
      zdt.toString must beEqualTo("2017-01-28T19:10:00Z")
    }
    "illformed" >> {
      DateTimeParser(42, "2017-01-28:1910") must throwAn[CabrilloException]


    }
  }
}

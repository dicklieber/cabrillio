package com.wa9nnn.cabrillo

import java.time.{Instant, ZoneId, ZonedDateTime}

import com.wa9nnn.cabrillo.parsers.Exchange_WFD
import org.specs2.mutable.Specification

class QSO_WFDBuilderSpec extends Specification {

  "QSO_WFDBuilder" >> {
    "empty" >> {
      val builder = QSO_WFDBuilder()
      builder.result(42) must throwAn[IllegalStateException]
    }
    "happy" >> {
      val builder = QSO_WFDBuilder()
        .freq("14000")
        .mode("CW")
        .stamp(Instant.EPOCH)
        .sent(Exchange_WFD("wa9nnn", "3O", "IL"))
        .received(Exchange_WFD("w1aw", "1I", "CT"))

      val q = builder.result(42)
      q.toString must beEqualTo ("QSO_WFD(42,20M CW 1970-01-01 0000 wa9nnn 3O IL w1aw 1I CT,20M,CW,1970-01-01T00:00:00Z,wa9nnn 3O IL,w1aw 1I CT)")
    }

  }
}

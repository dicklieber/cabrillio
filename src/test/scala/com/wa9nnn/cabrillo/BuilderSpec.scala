package com.wa9nnn.cabrillo

import java.time.Instant

import com.wa9nnn.cabrillo.parsers.Exchange_WFD
import org.specs2.mutable.Specification

class BuilderSpec extends Specification {

  "Builder" >> {
    "happy" >> {
      val qsoBuilder = QSO_WFDBuilder()
        .freq("14000")
        .mode("CW")
        .stamp(Instant.EPOCH)
        .sent(Exchange_WFD("wa9nnn", "3O", "IL"))
        .received(Exchange_WFD("w1aw", "1I", "CT"))

      val builder = new Builder()
      builder + ("CALLSIGN", "WA9NNN")
      builder + qsoBuilder.result(0)
      val cabrilloData = builder.toCabrilloData

      cabrilloData.inLineOrder must haveLength(4)
      cabrilloData.first.tag must beEqualTo("START-OF-LOG")
      cabrilloData.last.tag must beEqualTo("END-OF-LOG")

      val qsos = cabrilloData("QSO")
      qsos.head.lineNumber must beEqualTo(2)
      qsos must haveLength(1)

      cabrilloData.tags.toString() must beEqualTo ("List(CALLSIGN, END-OF-LOG, QSO, START-OF-LOG)")
    }


  }
}


package com.wa9nnn.cabrillo.model

import java.time.{Instant, ZonedDateTime}

import com.wa9nnn.cabrillo.requirements.{CabrilloError, Frequencies}
import com.wa9nnn.cabrillo.requirements.Frequencies

trait Qso extends TagValue {

  def freq: String

  def mode: String

  def stamp: Instant

  def sent: Exchange

  def received: Exchange

  def checkFreq: Seq[CabrilloError] = {
    try {
     val band =  Frequencies.check(freq)
      Seq.empty
    } catch {
      case _: Exception ⇒
        Seq(CabrilloError(this, s"""Bad band or freq: "$freq""""))
    }
  }
}

trait Exchange {

}
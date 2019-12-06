
package org.wa9nnn.cabrillo.model

import java.time.ZonedDateTime

import org.wa9nnn.cabrillo.requirements.{CabrilloError, Frequencies}

trait Qso extends TagValue {

  def freq: String

  def mode: String

  def stamp: ZonedDateTime

  def sent: Exchange

  def received: Exchange

  def checkFreq: Seq[CabrilloError] = {
    try {
      Frequencies.check(freq)
      Seq.empty
    } catch {
      case _: Exception ⇒
        Seq(CabrilloError(this, s"""Bad band or freq: "$freq""""))
    }
  }
}

trait Exchange {

}
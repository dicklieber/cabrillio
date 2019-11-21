
package org.wa9nnn.cabrillo.model

import java.time.ZonedDateTime

import org.wa9nnn.cabrillo.requirements.{FreqException, Frequencies, CabrilloError}

import scala.util.Try

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
      case ef: FreqException ⇒
        Seq(CabrilloError(this, ef.getMessage))
    }
  }
}

trait Exchange {

}
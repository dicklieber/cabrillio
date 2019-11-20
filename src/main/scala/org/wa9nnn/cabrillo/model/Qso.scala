
package org.wa9nnn.cabrillo.model

import java.time.ZonedDateTime

import org.wa9nnn.cabrillo.requirements.{FreqException, Frequencies, ValueErrorAtLine}

import scala.util.Try

trait Qso extends TagValue {
  def freq: String

  def mode: String

  def stamp: ZonedDateTime

  def sent: Exchange

  def received: Exchange

  def checkFreq: Seq[ValueErrorAtLine] = {
    try {
      Frequencies.check(freq)
      Seq.empty
    } catch {
      case ef: FreqException ⇒
        Seq(new ValueErrorAtLine(this, ef.getMessage))
    }
  }
}

trait Exchange {

}
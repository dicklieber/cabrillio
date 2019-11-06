
package org.wa9nnn.cabrillo.parsers

import java.time.ZonedDateTime

import org.wa9nnn.cabrillo.model._

class QsoTagParser_WFD extends  TagParser {
  val tag: String = QsoTagParser_WFD.tag
  private val mainp = """(\S+)\s+(\S+)\s+([\d\-]+\s+\d+)\s+(\S+\s+\S+\s+\S+)\s++(\S+\s+\S+\s+\S+)""".r()

  override def parse(lineNumber: Int, tag:String, body: String): QSO_WFD = {
      val mainp(sFreq, sMode, sDateTime, sSent, sReceived) = body
      val zdt: ZonedDateTime = DateTimeParser(lineNumber, sDateTime)
      QSO_WFD(tag, lineNumber, body,  sFreq, sMode, zdt, toInfo(sSent), toInfo(sReceived))
  }

  private def toInfo(sInfo: String): Exchange_WFD = {
    val infop = "(\\S+)\\s+(\\S+)\\s+(\\S+)".r
    val infop(callsign, category, section) = sInfo
    Exchange_WFD(callsign, category, section)
  }
}

object QsoTagParser_WFD {
  val tag: String = "QSO"
}

case class Exchange_WFD(callsign: String, category: String, section: String) extends Exchange

case class QSO_WFD(tag:String, lineNumber: Int, body:String, freq: String, mode: String, stamp: ZonedDateTime, sent: Exchange_WFD, received: Exchange_WFD) extends Qso
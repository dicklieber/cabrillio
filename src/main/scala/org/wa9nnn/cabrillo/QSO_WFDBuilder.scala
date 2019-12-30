
package org.wa9nnn.cabrillo

import java.time.{Instant, ZoneId}

import org.wa9nnn.cabrillo.QSO_WFDBuilder._
import org.wa9nnn.cabrillo.parsers.{Exchange_WFD, QSO_WFD}

case class QSO_WFDBuilder(freq: Option[String] = None,
                          mode: Option[String] = None,
                          stamp: Option[Instant] = None,
                          sent: Option[Exchange_WFD] = None,
                          received: Option[Exchange_WFD] = None) {

  def freq(freq: String): QSO_WFDBuilder = {
    copy(freq = Some(freq))
  }

  def mode(freq: String): QSO_WFDBuilder = {
    copy(mode = Some(freq))
  }

  def stamp(stamp: Instant): QSO_WFDBuilder = {
    copy(stamp = Some(stamp))
  }

  def sent(sent: Exchange_WFD): QSO_WFDBuilder = {
    copy(sent = Some(sent))
  }

  def received(received: Exchange_WFD): QSO_WFDBuilder = {
    copy(received = Some(received))
  }


  def result(lineNumber: Int): QSO_WFD = {
    val present: Seq[Object] = (freq ++ mode ++ stamp ++ sent ++ received).toSeq
    if (present.size < 5)
      throw new IllegalStateException(f"Missing ${5 - present.size} values")
    val f = freq.get
    val m = mode.get
    val st = stamp.get
    val s = sent.get
    val r = received.get
    val body = s"$f $m ${fmt.format(st)} $s $r"
    QSO_WFD(lineNumber, body, f, m, st, s, r)
  }
}


object QSO_WFDBuilder {

  import java.time.format.DateTimeFormatter

 val fmt: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm").withZone(ZoneId.of("UTC"))
}
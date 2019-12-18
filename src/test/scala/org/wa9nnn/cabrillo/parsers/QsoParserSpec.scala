package org.wa9nnn.cabrillo.parsers

import org.specs2.mutable.Specification
import org.wa9nnn.cabrillo.contests.ContestInfoWFD
import org.wa9nnn.cabrillo.requirements.{CabrilloError, ContestInfo}

class QsoParserSpec extends Specification {
  implicit val contestInfo = new ContestInfoWFD
  private val qsoParser = new QsoTagHandler_WFD
  "QsoParserSpec" should {
    val tag = "QSO"
    "ok" in {
      val lineNumber = 42
      val handler_WFD = new QsoTagHandler_WFD()
      val body = "14252 PH 2017-01-28 1935 N4KGL         1O  AL      KC0JUO        1I  IA"
      val qsowfd = handler_WFD.parse(LineBody(lineNumber, body))
      qsowfd.tag must beEqualTo(tag)
      qsowfd.body must beEqualTo(body)
      qsowfd.freq must beEqualTo("14252")
      qsowfd.lineNumber must beEqualTo(lineNumber)
      qsowfd.mode must beEqualTo("PH")
      qsowfd.sent must beEqualTo(Exchange_WFD("N4KGL", "1O", "AL"))
      qsowfd.received must beEqualTo(Exchange_WFD("KC0JUO", "1I", "IA"))

      qsowfd.stamp.toString must beEqualTo("2017-01-28T19:35:00Z")
    }
    "bad freq" in {
      val lineNumber = 42
      val handler_WFD = new QsoTagHandler_WFD()
      val body = "crap PH 2017-01-28 1935 N4KGL         1O  AL      KC0JUO        1I  IA"
      val qsowfd = handler_WFD.parse(LineBody(lineNumber, body))
      qsowfd.tag must beEqualTo(tag)
      qsowfd.body must beEqualTo(body)
      qsowfd.freq must beEqualTo("crap")
      qsowfd.lineNumber must beEqualTo(lineNumber)
      qsowfd.mode must beEqualTo("PH")
      qsowfd.sent must beEqualTo(Exchange_WFD("N4KGL", "1O", "AL"))
      qsowfd.received must beEqualTo(Exchange_WFD("KC0JUO", "1I", "IA"))

      qsowfd.stamp.toString must beEqualTo("2017-01-28T19:35:00Z")

      val errors: Seq[CabrilloError] = qsowfd.check()
      errors must haveLength(1)
      errors.head.body mustEqual (body)
      errors.head.tag mustEqual (tag)
      errors.head.lineNumber mustEqual (lineNumber)
      errors.head.cause mustEqual ("""Bad band or freq: "crap"""")

    }
    "bad mode" in {
      val lineNumber = 42
      val handler_WFD = new QsoTagHandler_WFD()
      val body = "LIGHT crappy 2017-01-28 1935 N4KGL         1O  AL      KC0JUO        1I  IA"
      val qsowfd = handler_WFD.parse(LineBody(lineNumber, body))
      qsowfd.tag must beEqualTo(tag)
      qsowfd.body must beEqualTo(body)
      qsowfd.freq must beEqualTo("LIGHT")
      qsowfd.lineNumber must beEqualTo(lineNumber)
      qsowfd.mode must beEqualTo("crappy")
      qsowfd.sent must beEqualTo(Exchange_WFD("N4KGL", "1O", "AL"))
      qsowfd.received must beEqualTo(Exchange_WFD("KC0JUO", "1I", "IA"))

      qsowfd.stamp.toString must beEqualTo("2017-01-28T19:35:00Z")

      val errors: Seq[CabrilloError] = qsowfd.check()
      errors must haveLength(1)
      errors.head.body mustEqual (body)
      errors.head.tag mustEqual (tag)
      errors.head.lineNumber mustEqual (lineNumber)
      errors.head.cause mustEqual ("""Mode CW,DI or PH but: "crappy"""")

    }

    "tag" in {
      qsoParser.tag must beEqualTo(tag)
    }

  }
}

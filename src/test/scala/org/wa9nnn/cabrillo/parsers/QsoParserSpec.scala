package org.wa9nnn.cabrillo.parsers

import org.specs2.mutable.Specification

class QsoParserSpec extends Specification {
  private val qsoParser = new QsoTagParser_WFD
  "QsoParserSpec" should {
    val tag = "QSO"
    "parse" in {
      val lineNumber = 42
      val handler_WFD = new QsoTagParser_WFD()
      val body = "14252 PH 2017-01-28 1935 N4KGL         1O  AL      KC0JUO        1I  IA"
      val qsowfd = handler_WFD.parse(LineBody(lineNumber, body))
      qsowfd.tag must beEqualTo (tag)
      qsowfd.body must beEqualTo (body)
      qsowfd.freq must beEqualTo ("14252")
      qsowfd.lineNumber must beEqualTo (lineNumber)
      qsowfd.mode must beEqualTo ("PH")
      qsowfd.sent must beEqualTo (Exchange_WFD("N4KGL", "1O", "AL"))
      qsowfd.received must beEqualTo (Exchange_WFD("KC0JUO", "1I", "IA"))

      qsowfd.stamp.toString must beEqualTo ("2017-01-28T19:35Z")
    }

    "tag" in {
      qsoParser.tag must beEqualTo (tag)
    }

  }
}

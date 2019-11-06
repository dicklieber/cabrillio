package org.wa9nnn.cabrillo

import org.wa9nnn.cabrillo.model.{ParsedCabrillo, TagValue}

import scala.io.Source

class ParseEngineSpec extends org.specs2.mutable.Specification {
  "Parse Engine" >> {
    val bufferedSource = Source.fromResource("wfd1.cbr")
    val taggedValues: ParsedCabrillo = ParseEngine.parse(bufferedSource)


    val start: Seq[TagValue] = taggedValues("START-OF-LOG")
    start must haveLength(1)
    start.head.tag must beEqualTo("START-OF-LOG")
  }
}

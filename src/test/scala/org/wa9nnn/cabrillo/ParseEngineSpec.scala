package org.wa9nnn.cabrillo

import org.wa9nnn.cabrillo.model.Value

import scala.io.Source

class ParseEngineSpec extends org.specs2.mutable.Specification {
  "Parse Engine" >> {
    val parseEngine = new ParseEngine()
    val bufferedSource = Source.fromResource("wfd1.cbr")
    val taggedValues: Map[String, Seq[Value]] = parseEngine.parse(bufferedSource)


    val start: Seq[Value] = taggedValues("START-OF-LOG")
    start must haveLength(1)
    start.head.tag must beEqualTo("START-OF-LOG")
  }
}

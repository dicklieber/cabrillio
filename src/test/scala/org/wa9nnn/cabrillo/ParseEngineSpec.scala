package org.wa9nnn.cabrillo

import org.wa9nnn.cabrillo.contests.ContestInfoWFD
import org.wa9nnn.cabrillo.model.TagValue
import org.wa9nnn.cabrillo.requirements.{CheckEngine, Rules}

import scala.io.Source

class ParseEngineSpec extends org.specs2.mutable.Specification {
  implicit val contestInfo = new ContestInfoWFD
  private val requiredTags = new Rules()
  "Parse Engine" >> {
    val parseEngine = new ParseEngine(requiredTags)
    val bufferedSource = Source.fromResource("wfd1.cbr")
    val taggedValues = parseEngine.parse(bufferedSource)
    "parse" >> {
      val start: Seq[TagValue] = taggedValues("START-OF-LOG")
      start must haveLength(1)
      start.head.tag must beEqualTo("START-OF-LOG")
    }
    "checkRule" >> {
      val checkEngine = new CheckEngine(requiredTags)
      val errors = checkEngine.check(taggedValues)
      errors._1 must beEmpty
      errors._2 must beEmpty
    }
  }
}

package org.wa9nnn.cabrillo

import org.specs2.mutable.Specification
import org.wa9nnn.cabrillo.requirements.CabrilloError

import scala.io.Source

class CabrilloDataSpec extends Specification {

  "CabrilloDataSpec" should {
    "apply" in {
      val url = getClass.getResource("/wfd1.cbr")
      val bufferedSource = Source.fromURL(url)
      val result = Cabrillo(bufferedSource, url)
      println(s"result: $result")
      result.url must beEqualTo(url)
      result.tagsWithErrors must beEmpty
    }
    "nostart" in {
      val url = getClass.getResource("/wfd-nostart.cbr")
      val bufferedSource = Source.fromURL(url)
      val result = Cabrillo(bufferedSource, url)
      result.url must beEqualTo(url)
      val errors = result.tagsWithErrors
      errors must haveSize(1)
      errors.head must beEqualTo(CabrilloError("START-OF-LOG"))
      val unknownTags = result.unknownTags
      unknownTags must haveSize(1)
      unknownTags.head must beEqualTo(CabrilloError(1, "3.0","START-OF-LOGXYZZY", "Unknown tag: START-OF-LOGXYZZY"))
    }
    "bad start version" in {
      val expectedError: CabrilloError = CabrilloError(1,"2.0","START-OF-LOG","""START-OF-LOG is not "V3.0"""")
      val url = getClass.getResource("/wfd-badstart.cbr")
      val bufferedSource = Source.fromURL(url)
      val result = Cabrillo(bufferedSource, url)
      result.url must beEqualTo(url)
      result.tagsWithErrors must haveSize(1)
      val error: CabrilloError = result.tagsWithErrors.head
      error must beEqualTo(expectedError)
    }

  }
}

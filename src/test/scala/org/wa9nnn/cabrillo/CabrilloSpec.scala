package org.wa9nnn.cabrillo

import org.specs2.mutable.Specification
import org.wa9nnn.cabrillo.requirements.{CabrilloError, MissingTag, ValueErrorAtLine}

import scala.io.Source

class CabrilloSpec extends Specification {

  "CabrilloSpec" should {
    "apply" in {
      val url = getClass.getResource("/wfd1.cbr")
      val bufferedSource = Source.fromURL(url)
      val result = Cabrillo(bufferedSource, url)
      result.url must beEqualTo(url)
      result.errors must beEmpty
    }
    "nostart" in {
      val expectedError: CabrilloError = MissingTag("START-OF-LOG")
      val url = getClass.getResource("/wfd-nostart.cbr")
      val bufferedSource = Source.fromURL(url)
      val result = Cabrillo(bufferedSource, url)
      result.url must beEqualTo(url)
      result.errors must haveSize(1)
      val error: CabrilloError = result.errors.head
      error must beEqualTo(expectedError)
    }
    "bad start version" in {
      val expectedError: CabrilloError = ValueErrorAtLine(1,"2.0","START-OF-LOG","""START-OF-LOG is not "V3.0"""")
      val url = getClass.getResource("/wfd-badstart.cbr")
      val bufferedSource = Source.fromURL(url)
      val result = Cabrillo(bufferedSource, url)
      result.url must beEqualTo(url)
      result.errors must haveSize(1)
      val error: CabrilloError = result.errors.head
      error must beEqualTo(expectedError)
    }

  }
}

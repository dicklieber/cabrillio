package com.wa9nnn.cabrillo

import com.wa9nnn.cabrillo.requirements.CabrilloError
import org.apache.commons.io.IOUtils
import org.specs2.mutable.Specification

class CabrilloDataSpec extends Specification {
  implicit def f(file: String): Array[Byte] = IOUtils.resourceToByteArray(file)

  "CabrilloDataSpec" should {
    "apply" in {
      val result = Cabrillo("/wfd1.cbr").result
      println(s"result: $result")
      result.tagsWithErrors must beEmpty
    }
    "nostart" in {
      val resultWithData = Cabrillo("/wfd-nostart.cbr")
      resultWithData.goodData must beNone
      val result = resultWithData.result
      val errors = result.tagsWithErrors
      errors must haveSize(1)
      errors.head must beEqualTo(requirements.CabrilloError("START-OF-LOG"))
      val unknownTags = result.unknownTags
      unknownTags must haveSize(1)
      unknownTags.head must beEqualTo(requirements.CabrilloError(1, "3.0", "START-OF-LOGXYZZY", "Unknown tag: START-OF-LOGXYZZY"))
    }
    "bad start version" in {
      val expectedError: CabrilloError = requirements.CabrilloError(1, "2.0", "START-OF-LOG", """START-OF-LOG is not "V3.0"""")
      val resultWithData = Cabrillo("/wfd-badstart.cbr")
      resultWithData.goodData must beNone
      val result = resultWithData.result
      result.tagsWithErrors must haveSize(1)
      val error: CabrilloError = result.tagsWithErrors.head
      error must beEqualTo(expectedError)
    }

  }
}

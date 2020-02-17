package com.wa9nnn.cabrillo.requirements

import com.wa9nnn.cabrillo
import com.wa9nnn.cabrillo.MockCabrilloData
import com.wa9nnn.cabrillo.contests.ContestInfoWFD
import com.wa9nnn.cabrillo.parsers.LineBody
import org.specs2.mutable.Specification
import com.wa9nnn.cabrillo.MockCabrilloData
import com.wa9nnn.cabrillo.parsers.LineBody

class ChoicesSpec extends Specification {
  implicit val contestInfo = new ContestInfoWFD

  val tag = ""
  "Choices" >> {
    val tags = Set("alpha", "Beta", "Charlie")

    "ignorecase" >> {
      val choices = Choices(tag, tags)
      "ok exact case" >> {
        choices.tagCheck(MockCabrilloData(tag, LineBody(1, "Beta"))) must haveLength(0)
        choices.tagCheck(cabrillo.MockCabrilloData(tag, LineBody(1, "bETA"))) must haveLength(0)
        choices.tagCheck(cabrillo.MockCabrilloData(tag, LineBody(1, "alpha"))) must haveLength(0)
        val badData = cabrillo.MockCabrilloData(tag, LineBody(1, "crap"))
        val errors = choices.tagCheck(badData)
        errors must haveLength(1)
        errors.head mustEqual CabrilloError(badData.first, "Unexpected value: crap")
      }
    }
    "case sensitive" >> {
      val choices = Choices(tag, tags, ignoreCase = false)
      "ok exact case" >> {
        choices.tagCheck(cabrillo.MockCabrilloData(tag, LineBody(1, "Beta"))) must haveLength(0)
        choices.tagCheck(cabrillo.MockCabrilloData(tag, LineBody(1, "bETA"))).head.cause mustEqual "Unexpected value: bETA"
        choices.tagCheck(cabrillo.MockCabrilloData(tag, LineBody(1, "alpha"))) must haveLength(0)
        val badData = cabrillo.MockCabrilloData(tag, LineBody(1, "crap"))
        val errors = choices.tagCheck(badData)
        errors must haveLength(1)
        errors.head mustEqual CabrilloError(badData.first, "Unexpected value: crap")
      }
    }

  }
}

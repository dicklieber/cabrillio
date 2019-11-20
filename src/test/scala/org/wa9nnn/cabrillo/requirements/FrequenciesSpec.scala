package org.wa9nnn.cabrillo.requirements

import org.specs2.mutable.Specification
import Frequencies.check

class FrequenciesSpec extends Specification {
  "Frequencies" >> {
    "band160M" >> {
      println("Testing freq 160M")
      check("1800")
      check("1999")
      check("2000") must throwA[FreqException]
      ok
    }
    "band1.2G" >> {
      check("1.2G")
      ok
    }
    "warc band" >> {
      check("17001") must throwA[FreqException]

    }

  }
}

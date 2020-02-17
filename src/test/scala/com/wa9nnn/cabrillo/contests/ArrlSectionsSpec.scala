package com.wa9nnn.cabrillo.contests

import org.specs2.mutable.Specification

class ArrlSectionsSpec extends Specification {
  val arrlSections = new ArrlSections
  "ArrlSectionsSpec" should {
    "checkRule" >> {
      "ok" >> {
        arrlSections.check("IL")
        ok
      }
      "ok lowercase" >> {
        arrlSections.check("il")
        ok
      }
      "unknown" >> {
        arrlSections.check("bad") must throwAn(new UnknownSectionCodeException("bad"))

        try {
          arrlSections.check("bad")
          failure
        } catch {
          case u: UnknownSectionCodeException ⇒
            u.getMessage must beEqualTo("Unknown ARRL section: bad")
        }
        ok
      }
    }

    "find" >> {
      arrlSections.find("N").map(_.code).mkString(" ") must beEqualTo("NC ND NE NFL NH NL NLI NM NNJ NNY NT NTX NV")
      arrlSections.find("NL").map(_.code).mkString(" ") must beEqualTo("NL NLI")
      arrlSections.find("nl").map(_.code).mkString(" ") must beEqualTo("NL NLI")
      arrlSections.find("QQ").map(_.code).mkString(" ") must beEmpty
    }

  }
}

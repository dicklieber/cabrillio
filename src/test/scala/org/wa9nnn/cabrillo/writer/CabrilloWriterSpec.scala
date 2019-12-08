package org.wa9nnn.cabrillo.writer

import java.io.{PrintWriter, StringWriter}

import org.specs2.mutable.Specification
import org.wa9nnn.cabrillo.{CabrilloWriter, TestFileLoader}

class CabrilloWriterSpec extends Specification {

  "CabrilloWriterSpec" should {
    "write" in {
      val sw = new StringWriter()
      val printWriter = new PrintWriter(sw)
        CabrilloWriter.write(TestFileLoader.apply(), printWriter)
      sw.toString mustEqual("""START-OF-LOG 3.0
                              |CREATED-BY N3FJP's Winter Field Day Contest Log 1.8
                              |CONTEST WFD
                              |CALLSIGN N4KGL
                              |LOCATION AL
                              |ARRL-SECTION AL
                              |CATEGORY 1O
                              |CATEGORY-OPERATOR MULTI-OP
                              |CATEGORY-STATION FIXED
                              |CATEGORY-TRANSMITTER ONE
                              |CATEGORY-POWER LOW
                              |CATEGORY-ASSISTED NON-ASSISTED
                              |CATEGORY-BAND ALL
                              |CATEGORY-MODE MIXED
                              |SOAPBOX 1,500 points for not using commercial power.
                              |SOAPBOX 1,500 points for setting up outdoors.
                              |SOAPBOX 1,500 points for setting up away from home.
                              |SOAPBOX 1,500 points for satellite QSO(s).
                              |SOAPBOX BONUS 6000`
                              |CLAIMED-SCORE 6120
                              |OPERATORS N4KGL VA3ECO
                              |NAME Greg Lane
                              |ADDRESS 730 Brandeis Ave
                              |ADDRESS-CITY Panama City
                              |ADDRESS-STATE-PROVINCE FL
                              |ADDRESS-POSTALCODE 32405
                              |ADDRESS-COUNTRY USA
                              |EMAIL LaneKG@gmail.com
                              |QSO 7034 CW 2017-01-28 1910 N4KGL         1O  AL      K4W           2O  WCF
                              |QSO 7043 CW 2017-01-28 1914 N4KGL         1O  AL      WA9Z          1O  IA
                              |QSO 7057 CW 2017-01-28 1916 N4KGL         1O  AL      W4GR          8I  GA
                              |QSO 14252 PH 2017-01-28 1935 N4KGL         1O  AL      KC0JUO        1I  IA
                              |QSO 14000 PH 2017-01-28 1940 N4KGL         1O  AL      W8MAI         6H  MI
                              |QSO 14000 PH 2017-01-28 1943 N4KGL         1O  AL      N5KB          1H  IA
                              |QSO 14000 PH 2017-01-28 1953 N4KGL         1O  AL      NC6K          1H  SDG
                              |QSO 14000 CW 2017-01-28 1954 N4KGL         1O  AL      VE2FK         1H  QC
                              |QSO 14000 CW 2017-01-28 2004 N4KGL         1O  AL      W0KY          2I  NE
                              |QSO 14000 CW 2017-01-28 2009 N4KGL         1O  AL      W6GEE         1O  SF
                              |QSO 14000 PH 2017-01-28 2018 N4KGL         1O  AL      K0USA         2O  NE
                              |QSO 14000 PH 2017-01-28 2110 N4KGL         1O  AL      K2GAV         1H  CT
                              |QSO 14000 PH 2017-01-28 2120 N4KGL         1O  AL      WW1USA        1I  MO
                              |QSO 14000 PH 2017-01-28 2122 N4KGL         1O  AL      CG3RB         2O  ONE
                              |END-OF-LOG
                              |""".stripMargin)
    }

  }
}
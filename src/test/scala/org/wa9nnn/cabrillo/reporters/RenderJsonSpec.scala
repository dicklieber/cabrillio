package org.wa9nnn.cabrillo.reporters

import java.io.StringWriter
import java.time.{Duration, LocalDateTime}

import org.specs2.mutable.Specification
import org.wa9nnn.cabrillo.Result
import org.wa9nnn.cabrillo.requirements.CabrilloError

class RenderJsonSpec extends Specification {

  "RenderJsonSpec" >> {
    val result = Result(callSign = Some("WM9W"), duration = Duration.ofMinutes(42), linesInFile = 100, qsoCount = 80, tagsWithErrors = Seq(
            CabrilloError("XYZZY"),
          ), unknownTags = Seq(
            CabrilloError(1, "body one", "SomeTag", "messed up"),
          ), reportRunTime =  LocalDateTime.of(2019, 1, 27,13,0))
    "generate with tagsWithErrors" >> {
      val writer = new StringWriter()
      ReportJson.generate(result, writer)
      val string = writer.toString
      string must beEqualTo ("""{
                               |  "callSign" : "WM9W",
                               |  "duration" : "PT42M",
                               |  "linesInFile" : 100,
                               |  "qsoCount" : 80,
                               |  "tagsWithErrors" : [ {
                               |    "lineNumber" : 0,
                               |    "body" : "",
                               |    "tag" : "XYZZY",
                               |    "cause" : "Missing tag: XYZZY"
                               |  } ],
                               |  "unknownTags" : [ {
                               |    "lineNumber" : 1,
                               |    "body" : "body one",
                               |    "tag" : "SomeTag",
                               |    "cause" : "messed up"
                               |  } ],
                               |  "reportRunTime" : "2019-01-27T13:00:00"
                               |}""".stripMargin)
    }
    "No tagsWithErrors" >> {
      val writer = new StringWriter()
      ReportJson.generate(result.copy(tagsWithErrors = Seq.empty), writer)
      val string = writer.toString
      string must beEqualTo ("""{
                               |  "callSign" : "WM9W",
                               |  "duration" : "PT42M",
                               |  "linesInFile" : 100,
                               |  "qsoCount" : 80,
                               |  "tagsWithErrors" : [ ],
                               |  "unknownTags" : [ {
                               |    "lineNumber" : 1,
                               |    "body" : "body one",
                               |    "tag" : "SomeTag",
                               |    "cause" : "messed up"
                               |  } ],
                               |  "reportRunTime" : "2019-01-27T13:00:00"
                               |}""".stripMargin)

    }

  }
}

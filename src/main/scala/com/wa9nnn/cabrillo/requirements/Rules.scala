
package com.wa9nnn.cabrillo.requirements

import com.wa9nnn.cabrillo.contests.ContestInfoWFD
import com.wa9nnn.cabrillo.parsers.{DefaultTagParser, QsoTagHandler_WFD, TagParser}
import com.wa9nnn.cabrillo.requirements
import com.wa9nnn.cabrillo.contests.ContestInfoWFD
import com.wa9nnn.cabrillo.model.CabrilloTypes.Tag
import com.wa9nnn.cabrillo.parsers.{DefaultTagParser, QsoTagHandler_WFD, TagParser}

class Rules(val contestInfo: ContestInfo = new ContestInfoWFD) {
  def contains(tag: Tag): Boolean = map.contains(tag)

  implicit val ci: ContestInfo = contestInfo

  val handlers: Seq[TagHandler] = Seq(
    StartOfLog,
    requirements.AnyOneValue("CREATED-BY"),
    Contest,
    requirements.AnyOneValue("CALLSIGN"),
    new Location, // LOCATION
    new Section, //ARRL-SECTION
    new Category, //"CATEGORY",
    Choices("CATEGORY-OVERLAY", Set(
      "CLASSIC",
      "ROOKIE",
      "TB-WIRES",
      "BAND-LIMITED",
      "NOVICE-TECH",
      "OVER-50")),
    Choices("CATEGORY-TIME", Set(
      "6-HOURS",
      "12-HOURS",
      "24-HOURS"
    )),
    requirements.AnyOneValue("CATEGORY-OPERATOR"),
    requirements.AnyOneValue("CATEGORY-STATION"),
    requirements.AnyOneValue("CATEGORY-TRANSMITTER"),
    requirements.AnyOneValue("CATEGORY-POWER"),
    requirements.AnyOneValue("CATEGORY-ASSISTED"),
    requirements.AnyOneValue("CATEGORY-BAND"),
    requirements.AnyOneValue("CATEGORY-MODE"),
    AnyValues("SOAPBOX"),
    requirements.AnyOneValue("CLAIMED-SCORE"),
    requirements.AnyOneValue("CLUB"),
    requirements.AnyOneValue("OPERATORS"),
    requirements.AnyOneValue("NAME"),
    requirements.AnyOneValue("ADDRESS"),
    requirements.AnyOneValue("ADDRESS-CITY"),
    requirements.AnyOneValue("ADDRESS-STATE-PROVINCE"),
    requirements.AnyOneValue("ADDRESS-POSTALCODE"),
    requirements.AnyOneValue("ADDRESS-COUNTRY"),
    requirements.AnyOneValue("EMAIL"),
    new QsoTagHandler_WFD, //QSO
    EndOfLog //"END-OF-LOG"
  )
  private val map: Map[Tag, TagHandler] = handlers.map(h => h.tag -> h).toMap

  /**
   *
   * @param tag of interest
   * @return handler
   * @throws IllegalArgumentException if tag is unknown
   */
  def handler(tag: Tag): TagHandler = {
    try {
      map(tag)
    } catch {
      case _: NoSuchElementException ⇒ throw new IllegalArgumentException
    }
  }

  def get(tag: Tag): TagParser = {
    map.getOrElse(tag, new DefaultTagParser(tag))
  }
}


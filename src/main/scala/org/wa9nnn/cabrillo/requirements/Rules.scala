
package org.wa9nnn.cabrillo.requirements

import org.wa9nnn.cabrillo.contests.ContestInfoWFD
import org.wa9nnn.cabrillo.model.Cabrillo
import org.wa9nnn.cabrillo.model.CabrilloTypes.Tag
import org.wa9nnn.cabrillo.parsers.{DefaultTagParser, QsoTagHandler_WFD, TagParser}



class Rules(val contestInfo: ContestInfo = new ContestInfoWFD) {
  def contains(tag: Tag): Boolean = map.contains(tag)

  implicit val ci: ContestInfo = contestInfo

  val handlers: Seq[TagHandler] = Seq(
    StartOfLog,
    AnyOneValue("CREATED-BY"),
    Contest,
    AnyOneValue("CALLSIGN"),
    new Location, // LOCATION
    new Section, //ARRL-SECTION
    new Category, //"CATEGORY",
    AnyOneValue("CATEGORY-OPERATOR"),
    AnyOneValue("CATEGORY-STATION"),
    AnyOneValue("CATEGORY-TRANSMITTER"),
    AnyOneValue("CATEGORY-POWER"),
    AnyOneValue("CATEGORY-ASSISTED"),
    AnyOneValue("CATEGORY-BAND"),
    AnyOneValue("CATEGORY-MODE"),
    OneOrMoreValues("SOAPBOX"),
    AnyOneValue("CLAIMED-SCORE"),
    AnyOneValue("OPERATORS"),
    AnyOneValue("NAME"),
    AnyOneValue("ADDRESS"),
    AnyOneValue("ADDRESS-CITY"),
    AnyOneValue("ADDRESS-STATE-PROVINCE"),
    AnyOneValue("ADDRESS-POSTALCODE"),
    AnyOneValue("ADDRESS-COUNTRY"),
    AnyOneValue("EMAIL"),
    new QsoTagHandler_WFD, //QSO
    EndOfLog //"END-OF-LOG"
  )
  private val map: Map[Tag, TagHandler] = handlers.map(h ⇒ h.tag → h).toMap

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
      case e: NoSuchElementException ⇒ throw new IllegalArgumentException
    }
  }

  def get(tag: Tag): TagParser = {
    map.getOrElse(tag, new DefaultTagParser(tag))
  }
}


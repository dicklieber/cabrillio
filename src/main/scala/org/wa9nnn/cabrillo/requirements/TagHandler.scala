
package org.wa9nnn.cabrillo.requirements

import com.typesafe.scalalogging.LazyLogging
import org.wa9nnn.cabrillo.model.CabrilloTypes._
import org.wa9nnn.cabrillo.model.{Cabrillo, TagValue}
import org.wa9nnn.cabrillo.parsers.{LineBody, SimpleTag, TagParser}

abstract class TagHandler(val tag: Tag, val cardinality: Cardinality = One) extends TagParser with LazyLogging {
  /**
   * Works for tags where the body is simply a string.
   * Handlers for complex tags will override this.
   */
  def parse(lineBody: LineBody): TagValue = {
    new SimpleTag(tag, lineBody)
  }

  /**
   *
   * @param cabrillo the incoming or parsed data.
   * @return empty if no error other wise one or more thing that are evil.
   */
  def check(cabrillo: Cabrillo)(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {
    logger.debug(s"checking $tag")
    val tvs: Tags = cabrillo.apply(tag)

    def checkCardinality(): Seq[CabrilloError] = {
      try {
        cardinality.check(tag, tvs)
      } catch {
        case e: CardinalityException ⇒
          if (tvs.isEmpty)
            Seq(MissingTag(tag))
          e.toErrors
      }
    }

    def checkValue(): Seq[CabrilloError] = {
      tagCheck(cabrillo)
    }

    (checkCardinality() ++ checkValue())
      .toSet.toSeq // deduplicate
  }

  def tagCheck(parsedCabrillo: Cabrillo)(implicit contestInfo: ContestInfo): Seq[CabrilloError]

  def failure(tagValue: TagValue, cause: String): Seq[ValueErrorAtLine] = {
    logger.debug(s"failure: $cause")
    Seq(ValueErrorAtLine(tagValue.lineNumber, tagValue.body, tagValue.tag, cause))
  }

}

case class AnyOneValue(override val tag: Tag) extends TagHandler(tag, One) {

  override def tagCheck(parsedCabrillo: Cabrillo)(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {
    Seq.empty
  }
}

case class AnyValues(override val tag: Tag) extends TagHandler(tag: Tag, Any) {
  override def tagCheck(parsedCabrillo: Cabrillo)(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {
    Seq.empty
  }
}

case class OneOrMoreValues(override val tag: Tag) extends TagHandler(tag: Tag, OneOrMore) {
  override def tagCheck(parsedCabrillo: Cabrillo)(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {
    Seq.empty
  }
}

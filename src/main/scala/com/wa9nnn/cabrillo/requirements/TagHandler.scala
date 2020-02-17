
package com.wa9nnn.cabrillo.requirements

import com.typesafe.scalalogging.LazyLogging
import CabrilloError._
import com.wa9nnn.cabrillo.model.CabrilloTypes.{Tag, TagValues}
import com.wa9nnn.cabrillo.model.{CabrilloData, SimpleTagValue, TagValue}
import com.wa9nnn.cabrillo.parsers.{LineBody, TagParser}
import com.wa9nnn.cabrillo.requirements

/**
 * Tag specific details
 *
 * @param tag         of interest
 * @param cardinality how many of this tag are allowed or required.
 */
abstract class TagHandler(val tag: Tag, val cardinality: Cardinality = One) extends TagParser with LazyLogging {
  /**
   * Works for tags where the body is simply a string.
   * Handlers for complex tags will override this.
   */
  def parse(lineBody: LineBody): TagValue = {
    new SimpleTagValue(tag, lineBody)
  }

  /**
   *
   * @param cabrillo the incoming or parsed data.
   * @return empty if no error otherwise one or more things that are evil.
   */
  final def checkRule(cabrillo: CabrilloData)(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {
    logger.debug(s"checking $tag")
    val tvs: TagValues = cabrillo.apply(tag)

    /**
     * Do we have the right number of instances of this tag.
     *
     * @return
     */
    def checkCardinality(): Seq[CabrilloError] = {
      try {
        cardinality.check(tag, tvs)
      } catch {
        case e: CardinalityException ⇒
          if (tvs.isEmpty)
            Seq(CabrilloError(tag))
          e.toErrors
      }
    }

    (checkCardinality() ++ tagCheck(cabrillo))
      .distinct // deduplicate
  }


  /**
   * The the tag-specific rule checking.
   *
   * @return
   */
  def tagCheck(parsedCabrillo: CabrilloData)(implicit contestInfo: ContestInfo): Seq[CabrilloError]

  def failure(tagValue: TagValue, cause: String): Seq[CabrilloError] = {
    logger.debug(s"failure: $cause")
    Seq(requirements.CabrilloError(tagValue.lineNumber, tagValue.body, tagValue.tag, cause))
  }
}

// These classes are convenience class for tags where this no checking of the body
// For tags that actually have to check content, [[TagHandler]] should be subclassed.

/**
 *
 * @param tag of interest
 */
case class AnyOneValue(override val tag: Tag) extends TagHandler(tag, OneOrNone) {
  override def tagCheck(parsedCabrillo: CabrilloData)(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {
    parsedCabrillo(tag).flatMap { tv =>
      if (tv.body.length == 0)
        Seq(CabrilloError(tv, "Value is required"))
      else
        NoError
    }
  }
}

case class AnyZeroOrOne(override val tag: Tag) extends TagHandler(tag, One) {
  override def tagCheck(parsedCabrillo: CabrilloData)(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {
    NoError
  }
}

/**
 * Zero or more tags that may be empty.
 *
 * @param tag of interest
 */
case class AnyValues(override val tag: Tag) extends TagHandler(tag: Tag, AnyNumber) {
  override def tagCheck(cabrilloData: CabrilloData)(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {
    NoError
  }
}

case class OneOrMoreValues(override val tag: Tag) extends TagHandler(tag: Tag, OneOrMore) {
  override def tagCheck(parsedCabrillo: CabrilloData)(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {
    NoError
  }
}



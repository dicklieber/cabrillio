
package org.wa9nnn.cabrillo.requirements

import com.typesafe.scalalogging.LazyLogging
import org.wa9nnn.cabrillo.model.CabrilloTypes._
import org.wa9nnn.cabrillo.model.{Cabrillo, TagValue}
import org.wa9nnn.cabrillo.parsers.{LineBody, SimpleTag, TagParser}

/**
 * Tag specific details
 * @param tag of interest
 * @param cardinality how many of this tag are allowed or required.
 */
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
   * @return empty if no error otherwise one or more things that are evil.
   */
  final def checkRule(cabrillo: Cabrillo)(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {
    logger.debug(s"checking $tag")
    val tvs: Tags = cabrillo.apply(tag)

    /**
     * Do we have the right number of instances of this tag.
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
      .toSet.toSeq // deduplicate
  }

  /**
   * The the tag-specific rule checking.
   * @return
   */
  def tagCheck(parsedCabrillo: Cabrillo)(implicit contestInfo: ContestInfo): Seq[CabrilloError]

  def failure(tagValue: TagValue, cause: String): Seq[CabrilloError] = {
    logger.debug(s"failure: $cause")
    Seq(CabrilloError(tagValue.lineNumber, tagValue.body, tagValue.tag, cause))
  }
}
// These classes are convenience class for tags where this no checking of the body
// For tags that actually have to check congtent, [[TagHandler]] should be subclassed.
case class AnyOneValue(override val tag: Tag) extends TagHandler(tag, One) {
  override def tagCheck(parsedCabrillo: Cabrillo)(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {
    Seq.empty
  }
}

//case class AnyValues(override val tag: Tag) extends TagHandler(tag: Tag, Any) {
//  override def tagCheck(parsedCabrillo: Cabrillo)(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {
//    Seq.empty
//  }
//}

case class OneOrMoreValues(override val tag: Tag) extends TagHandler(tag: Tag, OneOrMore) {
  override def tagCheck(parsedCabrillo: Cabrillo)(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {
    Seq.empty
  }
}

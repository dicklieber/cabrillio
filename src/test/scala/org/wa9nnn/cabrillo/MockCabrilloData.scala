
package org.wa9nnn.cabrillo

import org.wa9nnn.cabrillo.model.CabrilloTypes.Tag
import org.wa9nnn.cabrillo.model.{CabrilloDataProvider, SimpleTagValue, TagValue}
import org.wa9nnn.cabrillo.parsers.LineBody

object MockCabrilloData {
  /**
   * Creates a new [[CabrilloDataProvider]] with an additional tag.
   * Note if there is already a tag it will be replaced.
   *
   * @param source    an existing [[CabrilloDataProvider]]
   * @param tag       to be added
   * @param bodyLines lines for the tag
   * @return copy
   */
  def addTag(source: CabrilloDataProvider, tag: Tag, bodyLine: LineBody, bodyLines: LineBody*): CabrilloDataProvider = {
    val lines: Seq[LineBody] = bodyLines ++ bodyLines
    CabrilloDataProvider(source.data + makeOneTag(tag, lines))
  }

  def apply(tag: Tag, bodyLines: LineBody*): CabrilloDataProvider = {
    CabrilloDataProvider(Seq(makeOneTag(tag, bodyLines)).toMap)
  }

  private def makeOneTag(tag: Tag, bodyLines: Seq[LineBody]): (Tag, Seq[TagValue]) = {
    tag → bodyLines.map(lb ⇒ new SimpleTagValue(tag, lb))
  }
}
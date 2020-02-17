
package com.wa9nnn.cabrillo

import com.wa9nnn.cabrillo.model.{CabrilloData, SimpleTagValue, TagValue}
import com.wa9nnn.cabrillo.parsers.LineBody
import com.wa9nnn.cabrillo.model.CabrilloTypes.Tag
import com.wa9nnn.cabrillo.model.{CabrilloData, SimpleTagValue, TagValue}

object MockCabrilloData {
  /**
   * Creates a new [[CabrilloData]] with an additional tag.
   * Note if there is already a tag it will be replaced.
   *
   * @param source    an existing [[CabrilloData]]
   * @param tag       to be added
   * @param bodyLines lines for the tag
   * @return copy
   */
  def addTag(source: CabrilloData, tag: Tag, bodyLine: LineBody, bodyLines: LineBody*): CabrilloData = {
    val lines: Seq[LineBody] = bodyLines ++ bodyLines
    CabrilloData(source.data + makeOneTag(tag, lines))
  }

  def apply(tag: Tag, bodyLines: LineBody*): CabrilloData = {
    CabrilloData(Seq(makeOneTag(tag, bodyLines)).toMap)
  }

  private def makeOneTag(tag: Tag, bodyLines: Seq[LineBody]): (Tag, Seq[TagValue]) = {
    tag → bodyLines.map(lb ⇒ new SimpleTagValue(tag, lb))
  }
}
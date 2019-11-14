
package org.wa9nnn.cabrillo.requirements

import org.wa9nnn.cabrillo.model.CabrilloTypes.Tag
import org.wa9nnn.cabrillo.model.TagValue

/**
 *
 * @param tag         to be considered
 * @param cardinality how many are required.
 */
case class Required(tag: String, cardinality: Cardinality = One)

class RequiredTags {
  implicit def ddd(tag: String): Required = Required(tag)

  val tagReqs: Seq[Required] = Seq(
    "START-OF-LOG",
    "CREATED-BY",
    "CONTEST",
    "CALLSIGN",
    "LOCATION",
    "ARRL-SECTION",
    "CATEGORY",
    "CATEGORY-OPERATOR",
    "CATEGORY-STATION",
    "CATEGORY-TRANSMITTER",
    "CATEGORY-POWER",
    "CATEGORY-ASSISTED",
    "CATEGORY-BAND",
    "CATEGORY-MODE",
    Required("SOAPBOX", Any),
    "CLAIMED-SCORE",
    "OPERATORS",
    "NAME",
    "ADDRESS",
    "ADDRESS-CITY",
    "ADDRESS-STATE-PROVINCE",
    "ADDRESS-POSTALCODE",
    "ADDRESS-COUNTRY",
    "EMAIL",
    Required("QSO", Any),
    "END-OF-LOG"
  )
}

sealed trait Cardinality {
  def check(tags: Seq[TagValue]): Boolean
}

case object Optional extends Cardinality {
  override def check(tags: Seq[TagValue]): Boolean = {
    tags.size <= 1
  }
}

case object One extends Cardinality {
  override def check(tags: Seq[TagValue]): Boolean = {
    tags.size == 1
  }
}

case object Any extends Cardinality {
  override def check(tags: Seq[TagValue]): Boolean = {
    tags.nonEmpty
  }
}

case object OneOrMore extends Cardinality {
  override def check(tags: Seq[TagValue]): Boolean = {
    tags.nonEmpty
  }
}


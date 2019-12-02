
package org.wa9nnn.cabrillo.requirements

import org.wa9nnn.cabrillo.model.{ CabrilloDataProvider, TagValue}

/**
 * A reusable tag handler where there is a fixed set of allowable values.
 *
 * @param tag         of interest
 * @param choices     allowed values
 * @param ignoreCase  false for exact case match
 * @param cardinality how many of this tag are allowed or required. Defaults to [[AnyNumber]].
 */
case class Choices(override val tag: String, choices: Set[String], ignoreCase: Boolean = true, override val cardinality: Cardinality = AnyNumber) extends TagHandler(tag, cardinality) {
  private var c: Set[String] = _
  private val contains = {
    if (ignoreCase) {
      c = choices.map(_.toUpperCase())
      tv: TagValue ⇒ {
        ck(tv, tv.body.toUpperCase())
      }
    } else {
      c = choices // use as is
      tv: TagValue ⇒ {
        ck(tv, tv.body)
      }
    }
  }

  private def ck(tv: TagValue, value: String): Seq[CabrilloError] = {
    if (c.contains(value))
      noError
    else
      failure(tv, s"Unexpected value: ${tv.body}")
  }

  /**
   * The the tag-specific rule checking.
   *
   * @return any errors
   */
  override def tagCheck(parsedCabrillo: CabrilloDataProvider)(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {
    parsedCabrillo.apply(tag).flatMap { tv ⇒
      contains(tv)
    }
  }
}

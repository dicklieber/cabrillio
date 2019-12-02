
package org.wa9nnn.cabrillo.requirements

import org.wa9nnn.cabrillo.model.{CabrilloDataProvider, TagValue}

/**
 * Apply the rules
 * @param rules to be checked against.
 * @param contestInfo specific to this contest
 */
case class CheckEngine(rules: Rules)(implicit contestInfo: ContestInfo) {
  /**
   *
   * @param cabrillo data
   * @return (tagswithErrors, unknownTags)
   */
  def check(cabrillo: CabrilloDataProvider): (Seq[CabrilloError],Seq[CabrilloError]) = {

    val requiredErrors = rules.handlers.flatMap { th ⇒
      th.checkRule(cabrillo)
    }
    val unknownTagsErrors = cabrillo.inLineOrder.flatMap { tv: TagValue ⇒
      if (rules.contains(tv.tag))
        Seq.empty
      else
        Seq(CabrilloError(tv, s"Unknown tag: ${tv.tag}"))
    }
    requiredErrors → unknownTagsErrors
  }
}

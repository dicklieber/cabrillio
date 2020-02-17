
package com.wa9nnn.cabrillo.requirements

import com.wa9nnn.cabrillo.model.{CabrilloData, TagValue}
import com.wa9nnn.cabrillo.model.{CabrilloData, TagValue}

/**
 * Apply the rules
 * @param rules to be checked against.
 * @param contestInfo specific to this contest
 */
case class RulesEngine(rules: Rules)(implicit contestInfo: ContestInfo) {
  /**
   *
   * @param cabrillo data
   * @return (ruleErrors, unknownTagsErrors)
   */
  def check(cabrillo: CabrilloData): (Seq[CabrilloError],Seq[CabrilloError]) = {

    val ruleErrors = rules.handlers.flatMap { th ⇒
      th.checkRule(cabrillo)
    }
    val unknownTagsErrors = cabrillo.inLineOrder.flatMap { tv: TagValue ⇒
      if (rules.contains(tv.tag))
        Seq.empty
      else
        Seq(CabrilloError(tv, s"Unknown tag: ${tv.tag}"))
    }
    ruleErrors -> unknownTagsErrors
  }
}

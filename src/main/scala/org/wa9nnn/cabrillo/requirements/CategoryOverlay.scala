
package org.wa9nnn.cabrillo.requirements

import org.wa9nnn.cabrillo.model.CabrilloData

class CategoryOverlay() extends TagHandler("CATEGORY-OVERLAY", OneOrMore) {
  /**
   * The the tag-specific rule checking.
   *
   * @return any errors
   */
  override def tagCheck(parsedCabrillo: CabrilloData)(implicit contestInfo: ContestInfo): Seq[CabrilloError] = {
throw new NotImplementedError()
  }
}

object CategoryOverlay {
  Seq("CLASSIC",
    "ROOKIE",
    "TB-WIRES",
    "BAND-LIMITED",
    "NOVICE-TECH",
    "OVER-50")
}

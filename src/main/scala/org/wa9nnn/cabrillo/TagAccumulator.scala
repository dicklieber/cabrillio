
package org.wa9nnn.cabrillo

import org.wa9nnn.cabrillo.model.CabrilloTypes.Tag
import org.wa9nnn.cabrillo.model.TagValue

class TagAccumulator(tag:Tag) {
  private val builder = Seq.newBuilder[TagValue]

  def apply(item: TagValue): Unit = {
    builder += item
  }

  def collect: Seq[TagValue] = {
    builder.result()
  }
}

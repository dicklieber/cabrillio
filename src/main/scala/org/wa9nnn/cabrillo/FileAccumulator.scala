
package org.wa9nnn.cabrillo

import org.wa9nnn.cabrillo.model.CabrilloTypes.Tag
import org.wa9nnn.cabrillo.model.TagValue

import scala.collection.concurrent.TrieMap

class FileAccumulator {
  private val tagAccumulators = new TrieMap[String, TagAccumulator]()

  def accumulate(value: TagValue): Unit = {
    tagAccumulators
      .getOrElseUpdate(value.tag, new TagAccumulator(value.tag))
      .apply(value)
  }

  def result: Map[String, Seq[TagValue]] = tagAccumulators.values.map(_.collect)
    .map(tv ⇒ tv.head.tag → tv)
    .toMap
}

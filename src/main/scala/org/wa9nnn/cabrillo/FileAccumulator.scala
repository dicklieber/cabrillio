
package org.wa9nnn.cabrillo

import org.wa9nnn.cabrillo.model.Value

import scala.collection.concurrent.TrieMap

class FileAccumulator {
  private val tagAccumulators = new TrieMap[String, TagAccumulator]()

  def accumulate(value: Value): Unit = {
    tagAccumulators
      .getOrElseUpdate(value.tag, new TagAccumulator(value.tag))
      .apply(value)
  }

  def result: Map[String, Seq[Value]] = tagAccumulators.values.map(_.collect)
    .map(tv ⇒ tv.head.tag → tv)
    .toMap
}

case class Values(tag: String, values: Seq[Value])
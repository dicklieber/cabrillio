
package org.wa9nnn.cabrillo.model

import org.wa9nnn.cabrillo.model.CabrilloTypes.{Tag, Tags}

case class Cabrillo(data: Map[Tag, Seq[TagValue]]) {
  val inLineOrder: Seq[TagValue] = (for {
    seq ← data.values
    tv ← seq
  } yield {
    tv
  })
    .toSeq
    .sortBy(_.lineNumber)


  /**
   * get tags by tag name.
   * @param tag name.
   * @return
   */
  def apply(tag: Tag): Tags = {
    data.getOrElse(tag, Seq.empty)
  }

  val first: TagValue = inLineOrder.head

  val last: TagValue = inLineOrder.last

  def lineCount: Int = inLineOrder.size

  def tags:Seq[Tag] = {
    data.keys.toSeq
  }

}

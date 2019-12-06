
package org.wa9nnn.cabrillo.model

import org.wa9nnn.cabrillo.model.CabrilloTypes.{Tag, TagValues}

/**
 * This is all the data from or for a Cabrillo file.
 *
 * @param data tag->Seq[TagValue]
 */
case class CabrilloData(data: Map[Tag, Seq[TagValue]]) {
  val inLineOrder: Seq[TagValue] = (for {
    seq ← data.values
    tv ← seq
  } yield {
    tv
  })
    .toSeq
    .sortBy(_.lineNumber)

  /**
   * Get tags by tag name.
   * Most  [[org.wa9nnn.cabrillo.requirements.TagHandler.tagCheck()]] methods use this.
   *
   * @param tag name.
   * @return
   */
  def apply(tag: Tag): TagValues = {
    data.getOrElse(tag, Seq.empty)
  }

  val first: TagValue = inLineOrder.head

  val last: TagValue = inLineOrder.last

  def lineCount: Int = inLineOrder.size

  /**
   *
   * @return unique [[Tag]]s in file.
   */
  def tags: Seq[Tag] = {
    data.keys.toSeq
  }
}


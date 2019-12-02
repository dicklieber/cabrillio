
package org.wa9nnn.cabrillo.requirements

import org.wa9nnn.cabrillo.model.CabrilloTypes.{Tag, Tags}


/**
 * CSpecifies and hecks that there are the correct number of instances of a tag.
 */
sealed trait Cardinality {
  /**
   *
   * @param tags for a specific tag name.
   * @return the tags if ok, otherwise a [[CardinalityException]].
   */
  def check(tag: Tag, tags: Tags): Seq[CabrilloError] = {
    try {
      doCheck(tag, tags)
      Seq.empty
    } catch {
      case c: CardinalityException ⇒
        c.toErrors
      case x: Exception ⇒
        throw x
    }
  }

  def doCheck(tag: Tag, tags: Tags): Unit
}

case object OneOrNone extends Cardinality {
  override def doCheck(tag: Tag, tags: Tags): Unit = {
    if (tags.size > 1) {
      throw new CardinalityException("Only zero or one of this tag allowed", tag, tags)
    }
  }
}

case object One extends Cardinality {
  override def doCheck(tag: Tag, tags: Tags): Unit = {
    if (tags.size != 1) {
      throw new CardinalityException("Require One tag", tag, tags)
    }
  }
}

case object AnyNumber extends Cardinality {
  override def doCheck(tag: Tag, tags: Tags): Unit = {
    // can't fail
  }
}

case object OneOrMore extends Cardinality {
  override def doCheck(tag: Tag, tags: Tags): Unit = {
    if (tags.isEmpty) {
      throw new CardinalityException("Must have one or more this tag", tag, tags)
    }
  }
}

class CardinalityException(message: String, tag: Tag, tags: Tags) extends Exception(message) {
  def toErrors: Seq[CabrilloError] = {
    if (tags.isEmpty)
      Seq(CabrilloError(tag))
    else
      tags.map { tv ⇒
        CabrilloError(tv.lineNumber, tv.body, tv.tag, message)
      }
  }
}



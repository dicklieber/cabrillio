
package org.wa9nnn.cabrillo

import java.time.{Duration, LocalDateTime}

import org.wa9nnn.cabrillo.model.CabrilloData
import org.wa9nnn.cabrillo.requirements.CabrilloError


/**
 *
 * @param email          from EMAIL tag
 * @param duration       how long it took to run the cabrillo checkRule & reporters
 * @param linesInFile    total lines in file.
 * @param qsoCount       qso tags in file
 * @param tagsWithErrors that were found.
 * @param unknownTags    those that we dont recognize
 * @param reportRunTime  when this checkRule & reporters finished.
 */
case class Result(email: Option[String], duration: Duration, linesInFile: Int, qsoCount: Int, tagsWithErrors: Seq[CabrilloError], unknownTags: Seq[CabrilloError], reportRunTime: LocalDateTime = LocalDateTime.now()) {
  def ok: Boolean = qsoCount > 0 && tagsWithErrors.isEmpty
}


case class ResultWithData(result: Result, cabrilloData: CabrilloData) {
  def goodData: Option[CabrilloData] = Option.when(result.ok) {cabrilloData}
}

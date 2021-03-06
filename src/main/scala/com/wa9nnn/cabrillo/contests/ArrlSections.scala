package com.wa9nnn.cabrillo.contests

import scala.language.postfixOps

class ArrlSections {

  def find(partial: String): Seq[ArrlSection] = {
    arrlSections.filter(_.code.startsWith(partial.toUpperCase()))
  }

  /**
   *
   * @param code section code e.g. "IL"
   * @throws UnknownSectionCodeException on failure
   */
  def check(code: String): Unit = {
    byCode.getOrElse(code.toUpperCase(), throw new UnknownSectionCodeException(code))
  }

  //todo Load from a file
  val arrlSections: Seq[ArrlSection] = Seq(
    ArrlSection("Connecticut", "CT", "1"),
    ArrlSection("Eastern Massachusetts", "EMA", "1"),
    ArrlSection("Maine", "ME", "1"),
    ArrlSection("New Hampshire", "NH", "1"),
    ArrlSection("Rhode Island", "RI", "1"),
    ArrlSection("Vermont", "VT", "1"),
    ArrlSection("Western Massachusetts", "WMA", "1"),
    ArrlSection("Eastern New York", "ENY", "2"),
    ArrlSection("New York City - Long Island", "NLI", "2"),
    ArrlSection("Northern New Jersey", "NNJ", "2"),
    ArrlSection("Northern New York", "NNY", "2"),
    ArrlSection("Southern New Jersey", "SNJ", "2"),
    ArrlSection("Western New York", "WNY", "2"),
    ArrlSection("Delaware", "DE", "3"),
    ArrlSection("Eastern Pennsylvania", "EPA", "3"),
    ArrlSection("Maryland-DC", "MDC", "3"),
    ArrlSection("Western Pennsylvania", "WPA", "3"),
    ArrlSection("Alabama", "AL", "4"),
    ArrlSection("Georgia", "GA", "4"),
    ArrlSection("Kentucky", "KY", "4"),
    ArrlSection("North Carolina", "NC", "4"),
    ArrlSection("Northern Florida", "NFL", "4"),
    ArrlSection("South Carolina", "SC", "4"),
    ArrlSection("Southern Florida", "SFL", "4"),
    ArrlSection("West Central Florida", "WCF", "4"),
    ArrlSection("Tennessee", "TN", "4"),
    ArrlSection("Virginia", "VA", "4"),
    ArrlSection("Puerto Rico", "PR", "4"),
    ArrlSection("Virgin Islands", "VI", "4"),
    ArrlSection("Arkansas", "AR", "5"),
    ArrlSection("Louisiana", "LA", "5"),
    ArrlSection("Mississippi", "MS", "5"),
    ArrlSection("New Mexico", "NM", "5"),
    ArrlSection("North Texas", "NTX", "5"),
    ArrlSection("Oklahoma", "OK", "5"),
    ArrlSection("South Texas", "STX", "5"),
    ArrlSection("West Texas", "WTX", "5"),
    ArrlSection("East Bay", "EB", "6"),
    ArrlSection("Los Angeles", "LAX", "6"),
    ArrlSection("Orange", "ORG", "6"),
    ArrlSection("Santa Barbara", "SB", "6"),
    ArrlSection("Santa Clara Valley", "SCV", "6"),
    ArrlSection("San Diego", "SDG", "6"),
    ArrlSection("San Francisco", "SF", "6"),
    ArrlSection("San Joaquin Valley", "SJV", "6"),
    ArrlSection("Sacramento Valley", "SV", "6"),
    ArrlSection("Pacific", "PAC", "6"),
    ArrlSection("Arizona", "AZ", "7"),
    ArrlSection("Eastern Washington", "EWA", "7"),
    ArrlSection("Idaho", "ID", "7"),
    ArrlSection("Montana", "MT", "7"),
    ArrlSection("Nevada", "NV", "7"),
    ArrlSection("Oregon", "OR", "7"),
    ArrlSection("Utah", "UT", "7"),
    ArrlSection("Western Washington", "WWA", "7"),
    ArrlSection("Wyoming", "WY", "7"),
    ArrlSection("Alaska", "AK", "7"),
    ArrlSection("Michigan", "MI", "8"),
    ArrlSection("Ohio", "OH", "8"),
    ArrlSection("West Virginia", "WV", "8"),
    ArrlSection("Illinois", "IL", "9"),
    ArrlSection("Indiana", "IN", "9"),
    ArrlSection("Wisconsin", "WI", "9"),
    ArrlSection("Colorado", "CO", "0"),
    ArrlSection("Iowa", "IA", "0"),
    ArrlSection("Kansas", "KS", "0"),
    ArrlSection("Minnesota", "MN", "0"),
    ArrlSection("Missouri", "MO", "0"),
    ArrlSection("Nebraska", "NE", "0"),
    ArrlSection("North Dakota", "ND", "0"),
    ArrlSection("South Dakota", "SD", "0"),
    ArrlSection("Maritime", "MAR", "CA"),
    ArrlSection("Newfoundland/Labrador", "NL", "CA"),
    ArrlSection("Quebec", "QC", "CA"),
    ArrlSection("Ontario East", "ONE", "CA"),
    ArrlSection("Ontario North", "ONN", "CA"),
    ArrlSection("Ontario South", "ONS", "CA"),
    ArrlSection("Greater Toronto Area", "GTA", "CA"),
    ArrlSection("Manitoba", "MB", "CA"),
    ArrlSection("Saskatchewan", "SK", "CA"),
    ArrlSection("Alberta", "AB", "CA"),
    ArrlSection("British Columbia", "BC", "CA"),
    ArrlSection("Northern Territories", "NT", "CA"),
    ArrlSection("DX", "DX", "DX")
  ).sorted


  val byCode: Map[String, ArrlSection] = arrlSections.map(s ⇒ s.code → s) toMap
}

class UnknownSectionCodeException(code: String) extends Exception(s"Unknown ARRL section: $code")
package com.interana.eventsim.buildin

import com.interana.eventsim.WeightedRandomThingGenerator

import scala.io.Source

/**
 * Randomly generates locations
 */

object RandomLocationGenerator extends WeightedRandomThingGenerator[String] {
  val s = Source.fromFile("data/CBSA-EST2013-alldata.csv","ISO-8859-1")
  val lines = s.getLines()
  val cbsaRegex = new scala.util.matching.Regex(
    """\d+\,[^\,]*\,[^\,]*\,\"([^\"]+)\"\,M(?:et|ic)ropolitan\ Statistical\ Area\,(\d+)\,.*""",
    "name", "pop")
  val fields = for {l <- lines; m <- cbsaRegex findFirstMatchIn l}
    yield (m.group("name"), m.group("pop").toInt.asInstanceOf[Integer])
  fields.foreach(this.add)
  /*
  val s = Source.fromFile("data/cities15000.txt","UTF-8")
  val lines = s.getLines()
  for (l <- lines) {
    val fields = l.split("\t")
    System.out.println(fields(2), fields(8), fields(14))
    this.add((fields(2), fields(8)), fields(14).toInt)
  }
  */
  s.close()

}

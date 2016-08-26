package com.interana.eventsim.buildin

import com.interana.eventsim.WeightedRandomThingGenerator

import scala.io.Source

/**
 * Data originally from http://www.census.gov/genealogy/www/data/2000surnames/index.html
 */
object RandomHashtagGenerator extends WeightedRandomThingGenerator[String] {

  val s = Source.fromFile("data/twitter_hashtags.csv","ISO-8859-1")
  val lines = s.getLines()
  for (l <- lines) {
    val fields = l.split(",")
    this.add(fields(0), fields(1).toInt)
  }
  s.close()

}

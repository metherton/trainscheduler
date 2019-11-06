package com.martinetherton.trainscheduler

import org.scalatest.{Matchers, WordSpec}

class StationTest extends WordSpec with Matchers {

  "conversion" should {
    "create station from name" in {
      val newStation:Station = "Sheffield"
      newStation shouldEqual new Station("Sheffield")
    }
  }
}

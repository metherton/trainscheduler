package com.martinetherton.trainscheduler

import org.scalatest.{Matchers, WordSpec}

class HopTest  extends WordSpec with Matchers {

  "departureTime" should {
    "be initialized correctly" in {
      val testTrain = new Train( BavarianRegional(), Seq(
        (Time(4, 30), Station("london")),
        (Time(6, 30), Station("leeds")),
        (Time(8, 30), Station("sheffield")),
        (Time(9, 30), Station("birmingham"))))
      val hop = Hop(Station("leeds"), Station("sheffield"), testTrain)
      hop.departureTime shouldEqual Time(6,30)
      hop.arrivalTime shouldEqual Time(8,30)

    }
  }

}

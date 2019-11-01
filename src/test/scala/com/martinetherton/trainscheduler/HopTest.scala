package com.martinetherton.trainscheduler

import org.scalatest.{Matchers, WordSpec}

class HopTest  extends WordSpec with Matchers {

  "departureTime" should {
    "be initialized correctly" in {
      val testTrain = Train( BavarianRegional(), Seq(
        (Time(4, 30), new Station("london")),
        (Time(6, 30), new Station("leeds")),
        (Time(8, 30), new Station("sheffield")),
        (Time(9, 30), new Station("birmingham"))))
      val hop = Hop(new Station("leeds"), new Station("sheffield"), testTrain)
      hop.departureTime shouldEqual Time(6,30)
      hop.arrivalTime shouldEqual Time(8,30)

    }
  }

}

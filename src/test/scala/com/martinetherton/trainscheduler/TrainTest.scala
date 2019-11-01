package com.martinetherton.trainscheduler

import org.scalatest.{Matchers, WordSpec}



class TrainTest extends WordSpec with Matchers {

  "com.martinetherton.trainscheduler.Train.number" should {
    "be initialized correctly" in {
      val testTrain = new Train( BavarianRegional(), Seq((Time(4, 30), new Station("london")), (Time(6, 30), new Station("leeds"))))
      val info = testTrain.info
      info.number shouldBe 125
      info.name shouldBe "Bavarian Regional"
    }
  }

  "stations" should {
    "get initialized correctly" in {
      val train = Train(BavarianRegional(), List((Time(4,30), new Station("London")), (Time(5,45), new Station("Leeds"))))
      train.stations shouldEqual List(new Station("London"), new Station("Leeds"))
    }
  }

  "timeAt" should {
    "give time at Leeds" in {
      val train = Train(BavarianRegional(), List((Time(4,30), new Station("London")), (Time(5,45), new Station("Leeds"))))
      train.timeAt(new Station("Leeds")) shouldEqual Some(Time(5,45))
    }
  }

  "schedule" should {
    "be increasing" in {
      an [IllegalArgumentException] should be thrownBy Train(BavarianRegional(), List((Time(9,30), new Station("London")), (Time(7,45), new Station("Leeds"))))
    }
  }

  "backToBackStations" should {
    "contain pairs" in {
      val testTrain = new Train( BavarianRegional(), Seq(
        (Time(4, 30), new Station("london")),
        (Time(6, 30), new Station("leeds")),
        (Time(8, 30), new Station("sheffield")),
        (Time(9, 30), new Station("birmingham"))))
      testTrain.backToBackStations shouldEqual Seq((new Station("london"), new Station("leeds")),
        (new Station("leeds"), new Station("sheffield")),
        (new Station("sheffield"), new Station("birmingham")))
    }
  }

  "departureTimes" should {
    "map stations to times" in {
      val testTrain = new Train( BavarianRegional(), Seq(
        (Time(4, 30), new Station("london")),
        (Time(6, 30), new Station("leeds")),
        (Time(8, 30), new Station("sheffield")),
        (Time(9, 30), new Station("birmingham"))))
      testTrain.departureTimes shouldEqual Map(new Station("london") -> Time(4, 30), new Station("leeds") -> Time(6, 30), new Station("sheffield") -> Time(8, 30), new Station("birmingham") -> Time(9, 30))
    }
  }

}
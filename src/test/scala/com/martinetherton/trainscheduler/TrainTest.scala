package com.martinetherton.trainscheduler

import org.scalatest.{Matchers, WordSpec}



class TrainTest extends WordSpec with Matchers {

  "com.martinetherton.trainscheduler.Train.number" should {
    "be initialized correctly" in {
      val testTrain = new Train( BavarianRegional(), Seq((Time(4, 30), Station("london")), (Time(6, 30), Station("leeds"))))
      val info = testTrain.info
      info.number shouldBe 125
      info.name shouldBe "Bavarian Regional"
    }
  }

  "stations" should {
    "get initialized correctly" in {
      val train = Train(BavarianRegional(), List((Time(4,30), Station("London")), (Time(5,45), Station("Leeds"))))
      train.stations shouldEqual List(Station("London"), Station("Leeds"))
    }
  }

  "timeAt" should {
    "give time at Leeds" in {
      val train = Train(BavarianRegional(), List((Time(4,30), Station("London")), (Time(5,45), Station("Leeds"))))
      train.timeAt(Station("Leeds")) shouldEqual Some(Time(5,45))
    }
  }

  "schedule" should {
    "be increasing" in {
      an [IllegalArgumentException] should be thrownBy Train(BavarianRegional(), List((Time(9,30), Station("London")), (Time(7,45), Station("Leeds"))))
    }
  }

  "backToBackStations" should {
    "contain pairs" in {
      val testTrain = new Train( BavarianRegional(), Seq(
        (Time(4, 30), Station("london")),
        (Time(6, 30), Station("leeds")),
        (Time(8, 30), Station("sheffield")),
        (Time(9, 30), Station("birmingham"))))
      testTrain.backToBackStations shouldEqual Seq((Station("london"), Station("leeds")),
        (Station("leeds"), Station("sheffield")),
        (Station("sheffield"), Station("birmingham")))
    }
  }

  "departureTimes" should {
    "map stations to times" in {
      val testTrain = new Train( BavarianRegional(), Seq(
        (Time(4, 30), Station("london")),
        (Time(6, 30), Station("leeds")),
        (Time(8, 30), Station("sheffield")),
        (Time(9, 30), Station("birmingham"))))
      testTrain.departureTimes shouldEqual Map(Station("london") -> Time(4, 30), Station("leeds") -> Time(6, 30), Station("sheffield") -> Time(8, 30), Station("birmingham") -> Time(9, 30))
    }
  }

}
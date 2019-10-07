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

}
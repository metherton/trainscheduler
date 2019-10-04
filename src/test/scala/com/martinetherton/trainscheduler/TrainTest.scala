package com.martinetherton.trainscheduler

import org.scalatest.{Matchers, WordSpec}

class TrainTest extends WordSpec with Matchers {

  "com.martinetherton.trainscheduler.Train.number" should {
    "be initialized correctly" in {
      val testTrain = new Train(125, "intercity", Seq((Time(4, 30), Station("london")), (Time(6, 30), Station("leeds"))))
      testTrain.number shouldBe 125
      testTrain.kind shouldBe "intercity"
    }
  }

  "stations" should {
    "get initialized correctly" in {
      val train = Train(125, "intercity", List((Time(4,30), Station("London")), (Time(5,45), Station("Leeds"))))
      train.stations shouldEqual List(Station("London"), Station("Leeds"))
    }
  }

}
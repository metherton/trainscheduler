package com.martinetherton.trainscheduler

import org.scalatest.{Matchers, WordSpec}

class JourneyPlannerTest extends WordSpec with Matchers {

  "list of stations" should {
    "get initialized correctly" in {
      val trains = Set(Train(1, "express", Seq((Time(4, 30), Station("London")), (Time(5, 30), Station("Leeds")))),
        Train(2, "diesel", Seq((Time(6, 30), Station("London")), (Time(8, 30), Station("Sheffield")))))
      new JourneyPlanner(trains).stations shouldEqual Set(Station("Leeds"), Station("London"), Station("Sheffield"))
    }
  }

  "trainsAt" should {
    "return all trains" in {
      val trains = Set(Train(1, "express", Seq((Time(4, 30), Station("London")), (Time(5, 30), Station("Leeds")))),
        Train(2, "diesel", Seq((Time(6, 30), Station("London")), (Time(8, 30), Station("Sheffield")))))
      new JourneyPlanner(trains).trainsAt(Station("London")) shouldEqual trains

    }
  }

  "stopsAt" should {
    "return all (Time, Train) entries that stopAt given station" in {
      val firstTrain = Train(1, "express", Seq((Time(4, 30), Station("London")), (Time(5, 30), Station("Leeds"))))
      val secondTrain = Train(2, "diesel", Seq((Time(6, 30), Station("London")), (Time(8, 30), Station("Sheffield"))))
      val trains = Set(firstTrain, secondTrain)
      new JourneyPlanner(trains).stopsAt(Station("London")) shouldEqual
        Set((Time(4, 30), firstTrain), (Time(6, 30), secondTrain))

    }
  }

}

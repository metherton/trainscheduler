package com.martinetherton.trainscheduler

import org.scalatest.{Matchers, WordSpec}

class JourneyPlannerTest extends WordSpec with Matchers {

  "list of stations" should {
    "get initialized correctly" in {
      val trains = Set(Train(BavarianRegional(), Seq((Time(4, 30), Station("London")), (Time(5, 30), Station("Leeds")))),
        Train(InterCityExpress(), Seq((Time(6, 30), Station("London")), (Time(8, 30), Station("Sheffield")))))
      new JourneyPlanner(trains).stations shouldEqual Set(Station("Leeds"), Station("London"), Station("Sheffield"))
    }
  }

  "trainsAt" should {
    "return all trains" in {
      val trains = Set(Train(BavarianRegional(), Seq((Time(4, 30), Station("London")), (Time(5, 30), Station("Leeds")))),
        Train(InterCityExpress(), Seq((Time(6, 30), Station("London")), (Time(8, 30), Station("Sheffield")))))
      new JourneyPlanner(trains).trainsAt(Station("London")) shouldEqual trains

    }
  }

  "stopsAt" should {
    "return all (Time, Train) entries that stopAt given station" in {
      val firstTrain = Train(BavarianRegional(), Seq((Time(4, 30), Station("London")), (Time(5, 30), Station("Leeds"))))
      val secondTrain = Train(InterCityExpress(), Seq((Time(6, 30), Station("London")), (Time(8, 30), Station("Sheffield"))))
      val trains = Set(firstTrain, secondTrain)
      new JourneyPlanner(trains).stopsAt(Station("London")) shouldEqual
        Set((Time(4, 30), firstTrain), (Time(6, 30), secondTrain))

    }
  }

  "isShortTrip" should {
    "flag London to Sheffield as false" in {
      val firstTrain = Train(BavarianRegional(), Seq((Time(4, 30), Station("London")), (Time(5, 30), Station("Leeds"))))
      val secondTrain = Train(InterCityExpress(), Seq((Time(6, 30), Station("Leeds")), (Time(8, 30), Station("Sheffield"))))
      val trains = Set(firstTrain, secondTrain)
      new JourneyPlanner(trains).isShortTrip(Station("London"), Station("Sheffield")) shouldBe false
     }
  }

  "isShortTrip" should {
    "flag London to Sheffield as true" in {
      val firstTrain = Train(BavarianRegional(), Seq((Time(4, 30), Station("London")), (Time(5, 30), Station("Leeds")), (Time(6, 30), Station("Sheffield"))))
      val secondTrain = Train(InterCityExpress(), Seq((Time(7, 30), Station("Sheffield")), (Time(8, 30), Station("Birmingham"))))
      val trains = Set(firstTrain, secondTrain)
      new JourneyPlanner(trains).isShortTrip(Station("London"), Station("Sheffield")) shouldBe true
    }
  }

}

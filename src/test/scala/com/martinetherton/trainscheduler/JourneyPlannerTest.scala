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

  "hops" should {
    "be initialized correctly" in {
      val firstTrain = Train(BavarianRegional(), Seq((Time(4, 30), Station("London")), (Time(5, 30), Station("Leeds"))))
      val secondTrain = Train(InterCityExpress(), Seq((Time(6, 30), Station("London")), (Time(8, 30), Station("Sheffield"))))
      val trains = Set(firstTrain, secondTrain)
      new JourneyPlanner(trains).hops shouldBe Map(Station("London") -> Set(Hop(Station("London"), Station("Leeds"), firstTrain), Hop(Station("London"), Station("Sheffield"), secondTrain)))
    }
  }


  "connections 1" should {
    "return set of sets of hops" in {
      val firstTrain = Train(BavarianRegional(), Seq((Time(4, 30), Station("London")), (Time(5, 30), Station("Birmingham"))))
      val secondTrain = Train(InterCityExpress(), Seq((Time(6, 30), Station("London")), (Time(9, 10), Station("Leeds"))))
      val thirdTrain = new Train( RegionalExpress(), Seq(
        (Time(5, 30), Station("London")),
        (Time(6, 30), Station("Birmingham")),
        (Time(8, 30), Station("Leeds"))))
      val fourthTrain = Train(BavarianRegional2(), Seq((Time(9, 30), Station("Birmingham")), (Time(10, 30), Station("Sheffield"))))
      val fifthTrain = Train(InterCityExpress2(), Seq((Time(10, 30), Station("Leeds")), (Time(11, 10), Station("Sheffield"))))
      val sixthTrain = new Train( RegionalExpress2(), Seq(
        (Time(6, 30), Station("Leeds")),
        (Time(8, 30), Station("Birmingham")),
        (Time(11, 30), Station("London"))))
      val trains = Set(firstTrain, secondTrain,thirdTrain, fourthTrain, fifthTrain, sixthTrain)
      new JourneyPlanner(trains).connections(Station("Leeds"), Station("Sheffield"), Time(3, 10)) shouldBe
        Set(Seq(Hop(Station("Leeds"), Station("Sheffield"), fifthTrain)), Seq(Hop(Station("Leeds"), Station("Birmingham"), sixthTrain), Hop(Station("Birmingham"), Station("Sheffield"), fourthTrain)))
    }
  }

  "connections 2" should {
    "not use hops starting to early" in {
      val firstTrain = Train(BavarianRegional(), Seq((Time(4, 30), Station("London")), (Time(5, 30), Station("Birmingham"))))
      val secondTrain = Train(InterCityExpress(), Seq((Time(6, 30), Station("London")), (Time(9, 10), Station("Leeds"))))
      val thirdTrain = new Train( RegionalExpress(), Seq(
        (Time(5, 30), Station("London")),
        (Time(6, 30), Station("Birmingham")),
        (Time(8, 30), Station("Leeds"))))
      val fourthTrain = Train(BavarianRegional2(), Seq((Time(9, 30), Station("Birmingham")), (Time(10, 30), Station("Sheffield"))))
      val fifthTrain = Train(InterCityExpress2(), Seq((Time(10, 30), Station("Leeds")), (Time(11, 10), Station("Sheffield"))))
      val sixthTrain = new Train( RegionalExpress2(), Seq(
        (Time(6, 30), Station("Leeds")),
        (Time(8, 30), Station("Birmingham")),
        (Time(11, 30), Station("London"))))
      val trains = Set(firstTrain, secondTrain,thirdTrain, fourthTrain, fifthTrain, sixthTrain)
      new JourneyPlanner(trains).connections(Station("Leeds"), Station("Sheffield"), Time(9, 35)) shouldBe
        Set(Seq(Hop(Station("Leeds"), Station("Sheffield"), fifthTrain)))
    }
  }

}

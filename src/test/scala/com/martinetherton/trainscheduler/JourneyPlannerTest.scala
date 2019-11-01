package com.martinetherton.trainscheduler

import org.scalatest.{Matchers, WordSpec}

class JourneyPlannerTest extends WordSpec with Matchers {

  "list of stations" should {
    "get initialized correctly" in {
      val trains = Set(Train(BavarianRegional(), Seq((Time(4, 30), new Station("London")), (Time(5, 30), new Station("Leeds")))),
        Train(InterCityExpress(), Seq((Time(6, 30), new Station("London")), (Time(8, 30), new Station("Sheffield")))))
      new JourneyPlanner(trains).stations shouldEqual Set(new Station("Leeds"), new Station("London"), new Station("Sheffield"))
    }
  }

  "trainsAt" should {
    "return all trains" in {
      val trains = Set(Train(BavarianRegional(), Seq((Time(4, 30), new Station("London")), (Time(5, 30), new Station("Leeds")))),
        Train(InterCityExpress(), Seq((Time(6, 30), new Station("London")), (Time(8, 30), new Station("Sheffield")))))
      new JourneyPlanner(trains).trainsAt(new Station("London")) shouldEqual trains

    }
  }

  "stopsAt" should {
    "return all (Time, Train) entries that stopAt given station" in {
      val firstTrain = Train(BavarianRegional(), Seq((Time(4, 30), new Station("London")), (Time(5, 30), new Station("Leeds"))))
      val secondTrain = Train(InterCityExpress(), Seq((Time(6, 30), new Station("London")), (Time(8, 30), new Station("Sheffield"))))
      val trains = Set(firstTrain, secondTrain)
      new JourneyPlanner(trains).stopsAt(new Station("London")) shouldEqual
        Set((Time(4, 30), firstTrain), (Time(6, 30), secondTrain))

    }
  }

  "isShortTrip" should {
    "flag London to Sheffield as false" in {
      val firstTrain = Train(BavarianRegional(), Seq((Time(4, 30), new Station("London")), (Time(5, 30), new Station("Leeds"))))
      val secondTrain = Train(InterCityExpress(), Seq((Time(6, 30), new Station("Leeds")), (Time(8, 30), new Station("Sheffield"))))
      val trains = Set(firstTrain, secondTrain)
      new JourneyPlanner(trains).isShortTrip(new Station("London"), new Station("Sheffield")) shouldBe false
     }
  }

  "isShortTrip" should {
    "flag London to Sheffield as true" in {
      val firstTrain = Train(BavarianRegional(), Seq((Time(4, 30), new Station("London")), (Time(5, 30), new Station("Leeds")), (Time(6, 30), new Station("Sheffield"))))
      val secondTrain = Train(InterCityExpress(), Seq((Time(7, 30), new Station("Sheffield")), (Time(8, 30), new Station("Birmingham"))))
      val trains = Set(firstTrain, secondTrain)
      new JourneyPlanner(trains).isShortTrip(new Station("London"), new Station("Sheffield")) shouldBe true
    }
  }

  "hops" should {
    "be initialized correctly" in {
      val firstTrain = Train(BavarianRegional(), Seq((Time(4, 30), new Station("London")), (Time(5, 30), new Station("Leeds"))))
      val secondTrain = Train(InterCityExpress(), Seq((Time(6, 30), new Station("London")), (Time(8, 30), new Station("Sheffield"))))
      val trains = Set(firstTrain, secondTrain)
      new JourneyPlanner(trains).hops shouldBe Map(new Station("London") -> Set(Hop(new Station("London"), new Station("Leeds"), firstTrain), Hop(new Station("London"), new Station("Sheffield"), secondTrain)))
    }
  }


  "connections 1" should {
    "return set of sets of hops" in {
      val firstTrain = Train(BavarianRegional(), Seq((Time(4, 30), new Station("London")), (Time(5, 30), new Station("Birmingham"))))
      val secondTrain = Train(InterCityExpress(), Seq((Time(6, 30), new Station("London")), (Time(9, 10), new Station("Leeds"))))
      val thirdTrain = new Train( RegionalExpress(), Seq(
        (Time(5, 30), new Station("London")),
        (Time(6, 30), new Station("Birmingham")),
        (Time(8, 30), new Station("Leeds"))))
      val fourthTrain = Train(BavarianRegional2(), Seq((Time(9, 30), new Station("Birmingham")), (Time(10, 30), new Station("Sheffield"))))
      val fifthTrain = Train(InterCityExpress2(), Seq((Time(10, 30), new Station("Leeds")), (Time(11, 10), new Station("Sheffield"))))
      val sixthTrain = new Train( RegionalExpress2(), Seq(
        (Time(6, 30), new Station("Leeds")),
        (Time(8, 30), new Station("Birmingham")),
        (Time(11, 30), new Station("London"))))
      val trains = Set(firstTrain, secondTrain,thirdTrain, fourthTrain, fifthTrain, sixthTrain)
      new JourneyPlanner(trains).connections(new Station("Leeds"), new Station("Sheffield"), Time(3, 10)) shouldBe
        Set(Seq(Hop(new Station("Leeds"), new Station("Sheffield"), fifthTrain)), Seq(Hop(new Station("Leeds"), new Station("Birmingham"), sixthTrain), Hop(new Station("Birmingham"), new Station("Sheffield"), fourthTrain)))
    }
  }

  "connections 2" should {
    "not use hops starting to early" in {
      val firstTrain = Train(BavarianRegional(), Seq((Time(4, 30), new Station("London")), (Time(5, 30), new Station("Birmingham"))))
      val secondTrain = Train(InterCityExpress(), Seq((Time(6, 30), new Station("London")), (Time(9, 10), new Station("Leeds"))))
      val thirdTrain = new Train( RegionalExpress(), Seq(
        (Time(5, 30), new Station("London")),
        (Time(6, 30), new Station("Birmingham")),
        (Time(8, 30), new Station("Leeds"))))
      val fourthTrain = Train(BavarianRegional2(), Seq((Time(9, 30), new Station("Birmingham")), (Time(10, 30), new Station("Sheffield"))))
      val fifthTrain = Train(InterCityExpress2(), Seq((Time(10, 30), new Station("Leeds")), (Time(11, 10), new Station("Sheffield"))))
      val sixthTrain = new Train( RegionalExpress2(), Seq(
        (Time(6, 30), new Station("Leeds")),
        (Time(8, 30), new Station("Birmingham")),
        (Time(11, 30), new Station("London"))))
      val trains = Set(firstTrain, secondTrain,thirdTrain, fourthTrain, fifthTrain, sixthTrain)
      new JourneyPlanner(trains).connections(new Station("Leeds"), new Station("Sheffield"), Time(9, 35)) shouldBe
        Set(Seq(Hop(new Station("Leeds"), new Station("Sheffield"), fifthTrain)))
    }
  }

}

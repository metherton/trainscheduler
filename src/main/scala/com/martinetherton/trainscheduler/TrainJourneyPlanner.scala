package com.martinetherton.trainscheduler

object TrainJourneyPlanner extends App {


  val testTrain = new Train( BavarianRegional(), Seq(
    (Time(4, 30), new Station("london")),
    (Time(6, 30), new Station("leeds")),
    (Time(8, 30), new Station("sheffield")),
    (Time(9, 30), new Station("birmingham"))))

  println(testTrain)
}

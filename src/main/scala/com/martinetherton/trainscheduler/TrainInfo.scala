package com.martinetherton.trainscheduler

sealed abstract class TrainInfo {
  def number: Int
  def name: String
}


case class BavarianRegional() extends TrainInfo {
  def number: Int = 125
  def name: String = "Bavarian Regional"
}

case class InterCityExpress(hasWifi: Boolean = false) extends TrainInfo {
  def number: Int = 456
  def name: String = "Inter city express"
}

case class RegionalExpress() extends TrainInfo {
  def number: Int = 789
  def name: String = "Regional Express"
}

package com.martinetherton.trainscheduler

sealed abstract class TrainInfo {
  def number: Int
  def name: String
}


case class BavarianRegional() extends TrainInfo {
  def number: Int = 125
  def name: String = "Bavarian Regional"
}

case class BavarianRegional2() extends TrainInfo {
  def number: Int = 225
  def name: String = "Bavarian Regional 2"
}

case class InterCityExpress(hasWifi: Boolean = false) extends TrainInfo {
  def number: Int = 456
  def name: String = "Inter city express"
}

case class InterCityExpress2(hasWifi: Boolean = false) extends TrainInfo {
  def number: Int = 656
  def name: String = "Inter city express 2"
}

case class RegionalExpress() extends TrainInfo {
  def number: Int = 789
  def name: String = "Regional Express"
}

case class RegionalExpress2() extends TrainInfo {
  def number: Int = 889
  def name: String = "Regional Express 2"
}
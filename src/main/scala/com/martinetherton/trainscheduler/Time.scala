package com.martinetherton.trainscheduler

object Time {

  def fromMinutes(minutes: Int): Time = Time(minutes / 60, minutes % 60)

}

case class Time(hours: Int = 0, minutes: Int = 0) {

  val asMinutes: Int = hours * 60 + minutes

  // TODO: verify hours within 0 and 23
  require(hours >= 0 && hours <= 23, "invalid hours")

  // TODO: verify minutes within 0 and 59
  require(minutes >= 0 && minutes <= 59, "invalid hours")

  def minus(that: Time): Int = asMinutes - that.asMinutes

  def - (that: Time): Int = minus(that)


}

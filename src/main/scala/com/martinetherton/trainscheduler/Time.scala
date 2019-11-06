package com.martinetherton.trainscheduler

import play.api.libs.json
import play.api.libs.json.{JsValue, Json}

import scala.annotation.tailrec
import scala.util.Try

object Time {

  def fromMinutes(minutes: Int): Time = Time(minutes / 60, minutes % 60)

  def fromJson(obj: JsValue): Option[Time] = {
    val tryTime = for {
      hours <- Try((obj \ "hours").as[Int])
      minutes <- Try((obj \ "minutes").as[Int]).recover({ case _: Exception => 0 })
    } yield Time(hours, minutes)
    tryTime.toOption
  }

  @tailrec
  def isIncreasing(times: Seq[Time]): Boolean = times match {
    case Nil => true
    case first :: Nil => true
    case first :: second :: tail => if (first.asMinutes <= second.asMinutes) isIncreasing(second :: tail) else false
  }

  def isIncreasingSliding(times: Seq[Time]): Boolean =
    (times sliding 2).forall((x) => x(0).asMinutes <= x(1).asMinutes)

  implicit def stringToTime(s: String): Time = {
    val time = """(\d{1,2}):(\d{1,2})""".r
    val time(hour, minute) = s
    Time(hour.toInt, minute.toInt)
  }


}

case class Time(hours: Int = 0, minutes: Int = 0) extends Ordered[Time] {

  val asMinutes: Int = hours * 60 + minutes

  // TODO: verify hours within 0 and 23
  require(hours >= 0 && hours <= 23, "invalid hours")

  // TODO: verify minutes within 0 and 59
  require(minutes >= 0 && minutes <= 59, "invalid hours")

  def minus(that: Time): Int = asMinutes - that.asMinutes

  def - (that: Time): Int = minus(that)

  override lazy val toString = f"$hours%02d:$minutes%02d"

  override def compare(that: Time): Int = this - that

  def toJson(): JsValue = Json.obj("hours" -> hours, "minutes" -> minutes)

}

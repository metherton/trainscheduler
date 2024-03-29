package com.martinetherton.trainscheduler

import org.scalatest.{Matchers, WordSpec}
import com.martinetherton.trainscheduler._

class TimeTest extends WordSpec with Matchers {

  "com.martinetherton.trainscheduler.Time" should {
    "be initialized correctly" in {
      val testTime = new Time(5, 25)
      testTime.hours shouldBe 5
      testTime.minutes shouldBe 25
      testTime.asMinutes shouldBe 325
    }
  }

  "Minus" should {
    "return the difference of the time and the new time" in {
      val testTime = new Time(5, 25)
      testTime.minus(new Time(5, 20)) shouldBe 5
    }
  }

  "-" should {
    "return the difference of the time and the new time" in {
      val testTime = new Time(5, 25)
      testTime - new Time(5, 20) shouldBe 5
    }
  }

  "Hours and minus" should {
    "fill in default value of 0 if missing" in {
      new Time(5).asMinutes shouldBe 300
      new Time(minutes = 10).asMinutes shouldBe 10
      new Time(minutes = 10, hours = 1).asMinutes shouldBe 70
    }
  }

  "fromMinutes" should {
    "convert to correct Time" in {
      val newTime: Time = Time.fromMinutes(325)
      newTime.hours shouldBe 5
      newTime.minutes shouldBe 25
    }
  }

  "case class construction" should {
    "work fine" in {
      val newTime = Time(6, 45)
      newTime.hours shouldBe 6
      newTime.minutes shouldBe 45
    }
  }

  "apply construction" should {
    "work fine" in {
      val newTime = Time.apply(7, 55)
      newTime.hours shouldBe 7
      newTime.minutes shouldBe 55
    }
  }

  "toString" should {
    "format hours and minutes" in {
      val newTime = Time(6, 45)
      newTime.toString shouldEqual "06:45"
    }
  }

  "json conversion" should {
    "work" in {
      val convertedTime = Time(3, 15)
      val newTime = Time(3, 15).toJson()
      Time.fromJson(newTime) shouldEqual Some(Time(3,15))
    }
  }

  "isIncreasing" should {
    "be increasing" in {
      val seq = Seq(Time(2, 35), Time(2, 50), Time(3, 40))
      Time.isIncreasing(seq) shouldBe true
    }
  }

  "isIncreasing" should {
    "NOT be increasing" in {
      val seq = Seq(Time(2, 35), Time(5, 50), Time(3, 40))
      Time.isIncreasing(seq) shouldBe false
    }
  }

  "isIncreasingSliding" should {
    "be increasing" in {
      val seq = Seq(Time(2, 35), Time(5, 50), Time(8, 40))
      Time.isIncreasingSliding(seq) shouldBe true
    }
  }

  "isIncreasingSliding" should {
    "NOT be increasing" in {
      val seq = Seq(Time(2, 35), Time(5, 50), Time(3, 40))
      Time.isIncreasingSliding(seq) shouldBe false
    }
  }

  "implicit convert" should {
    "convert string to time" in {
      val newTime: Time = "5:25"
      newTime.asMinutes shouldBe 325
    }
  }

}

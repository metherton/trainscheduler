package com.martinetherton.trainscheduler.misc

import org.scalatest.{Matchers, WordSpec}

class QueueTest extends WordSpec with Matchers {

  "equals" should {
    "equal passed in" in {
      val intQueue = new Queue(List(1,2,3))
      intQueue.equals(new Queue(List(1,2,3))) shouldBe true
    }
  }

  "hashCode" should {
    "hashCode passed in" in {
      val intQueue = new Queue(List(1,2,3))
      intQueue.hashCode() shouldBe 41
    }
  }

  "toString" should {
    "toString passed in" in {
      val intQueue = new Queue(List(1,2,3))
      intQueue.toString() shouldBe "elements are List(1, 2, 3)"
    }
  }

}

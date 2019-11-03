package com.martinetherton.trainscheduler.misc

import org.scalatest.{Matchers, WordSpec}

class QueueTest extends WordSpec with Matchers {

  "equals" should {
    "equal passed in" in {
      val intQueue = Queue(List(1,2,3))
      intQueue.equals(Queue(List(1,2,3))) shouldBe true
    }
  }

  "hashCode" should {
    "hashCode passed in" in {
      val intQueue = Queue.apply(List(1,2,3))
      intQueue.hashCode() shouldBe 41
    }
  }

  "toString" should {
    "toString passed in" in {
      val intQueue = Queue(List(1,2,3))
      intQueue.toString() shouldBe "elements are List(1, 2, 3)"
    }
  }

  "dequeue" should {
    "return first and another queue" in {
      val intQueue = Queue(List(1,2,3))
      val newQueue = intQueue.dequeue()
      newQueue._1 shouldBe 1
      newQueue._2 shouldBe Queue(List(2,3))

    }
  }

}

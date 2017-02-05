package net.wrap_trap.akka_samples.monitor

import akka.actor.{ActorRef, Actor, PoisonPill}

class Child extends Actor {
  def receive = {
    case "poison"  => {
      println("message received: poison")
      self ! PoisonPill
    }
    case "stop" => {
      println("message received: stop")
      context.stop(self)
    }
    case "exception" => throw new RuntimeException("error")
  }
}

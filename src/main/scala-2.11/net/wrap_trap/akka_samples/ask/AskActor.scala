package net.wrap_trap.akka_samples.ask

import akka.actor.{ActorRef, Actor}

/**
  * Created by masayuki on 2016/03/26.
  */
class AskActor extends Actor {
  def receive = {
    case x: String => {
      println("AskActor: receive(String): " + x)
      sender ! x
    }
    case (pid: ActorRef, x: String) => {
      println("AskActor: receive(ActorRef, String): " + pid.toString() + " ," + x)
    }
  }
}

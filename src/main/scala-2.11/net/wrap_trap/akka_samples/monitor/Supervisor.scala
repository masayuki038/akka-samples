package net.wrap_trap.akka_samples.monitor

import akka.actor.{ActorRef, Actor, Terminated, Props}

class Supervisor extends Actor {

  var parent: Option[ActorRef] = None

  override def preStart(): Unit = {
    parent = Option(context.actorOf(Props[Parent]))
  }

  def receive = {
    case Terminated(p) => {
      println("terminated: " + p)
    }
    case args: Array[String] => {
      val p = parent.get
      context.watch(p)
      p ! args
    }
  }
}

package net.wrap_trap.akka_samples.monitor

import akka.actor.{ActorRef, Actor, Terminated, Props}

class Parent extends Actor {

  var child: Option[ActorRef] = None

  override def preStart(): Unit = {
    child = Option(context.actorOf(Props[Child]))
  }

  def receive = {
    case Terminated(c) => {
      println("terminated: " + c)
    }
    case args: Array[String] => {
      val c = child.get 
      if(args.length > 1 && args(1) == "1") {
        context.watch(c)
        println("start to watch: " + child)
      }
      c ! args(0)
    }
  }
}



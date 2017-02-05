package net.wrap_trap.akka_samples.monitor

import akka.actor._

object Monitor {
  def main(args: Array[String]): Unit = {
    val superVisor = ActorSystem("test").actorOf(Props[Supervisor])
    superVisor ! args
  }
}

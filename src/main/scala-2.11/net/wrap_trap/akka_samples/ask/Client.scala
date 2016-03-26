package net.wrap_trap.akka_samples.ask

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

import akka.actor.{ActorRef, Props, ActorSystem}
import akka.pattern.ask
import akka.util.Timeout

import scala.util.{Failure, Success}

object Client {
  def main(args: Array[String]) = {
    val system = ActorSystem("system")
    val actor1 = system.actorOf(Props[AskActor])
    val actor2 = system.actorOf(Props[AskActor])

    implicit val timeout = Timeout(5 seconds)
    replyToSender(actor1)
    replyToOther(actor1, actor2) // actor2 can't receive the message from actor1 since actor2:ActorRef is passed by message
  }

  def replyToSender(actor: ActorRef)(implicit timeout: Timeout) = {
    val reply = actor ? "How are you?"
    reply.onSuccess {
      case msg: String => println("Client: replied from actor: " + msg)
    }
  }

  def replyToOther(actor1: ActorRef, actor2: ActorRef)(implicit timeout: Timeout) = {
    val reply = actor1 ? (actor2, "How are you?")
    reply.onComplete {
      case Success(msg: String) => println("Client: replied from actor: " + msg)
      case Failure(error: Any) => println("Client: error: " + error)
    }
  }
}

package api.user.core

import akka.actor._
import akka.pattern.pipe
import akka.stream.ActorMaterializer
import api.user.core.UserHandler.Register
import api.user.helper.UserHelper
import com.datastax.driver.core.{Cluster, ResultSetFuture, Session}
import spray.json.DefaultJsonProtocol

/**
  * Singleton that defines de session to work with cassandra
  */
object CassRepo {

  def session = Cluster.builder
    .addContactPoint("127.0.0.1").withPort(9042)
    .build.connect()

  implicit val system = ActorSystem()
  implicit val mat = ActorMaterializer()
}


/**
  * Handler for de CRUD operation integrated in the User Rest API usin akka-http
  */
object UserHandler {

  def props (db: Session): Props = Props(new UserHandlerActor(db))

  case class ResultInsert(rs: ResultSetFuture)

  case class User(email: String, name: String, surname: String, password: String)
  case class Register(email: String, name: String, surname: String, password: String)
  case class Update(email: String, details: String)
  case class GetUser(email: String)
  case class DeleteUser(email: String)
  case class UserNotFound(email: String)
  case class UserDeleted(email: String)

}



class UserHandlerActor extends Actor with ActorLogging {

  implicit val ec = context.dispatcher
  implicit  val session = CassRepo.session

  override def receive: Receive = {

    case Register(mail,name,sname, pwd) =>
      UserHelper.resultSetFutureToScala(session.executeAsync(UserHelper.InsertUserStament(UserHandler.User(mail,name,sname, pwd)))) pipeTo  sender
  }

}






trait Protocols extends DefaultJsonProtocol {

  implicit val userLogin = jsonFormat2(RegisterRequest.apply)
}




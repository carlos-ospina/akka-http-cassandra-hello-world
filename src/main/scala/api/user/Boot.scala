import akka.actor.{Actor, ActorSystem, Props}
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import api.user.core.UserHandlerActor
import api.user.utils.Config
import api.user.utils.db.ConfigCassandraCluster

import scala.io.StdIn

object Boot extends App {

  def startApplication() = {
    implicit val system = ActorSystem("senffy-user-system")
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher


    val config = Config.load()
    val session = ConfigCassandraCluster.cluster



  }



//    case class LoginRequest(email: String, password: String)


    val route =
      pathPrefix("api/user")
      path("register") {
        post {

          val actor = system.actorOf(Props[UserHandlerActor])
          onSuccess((actor !  Register()) )

//          entity(as[Register]){
//            u => val register: Future[ResultSet] !
//          }
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
        }
      }

//    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done


  startApplication()

}
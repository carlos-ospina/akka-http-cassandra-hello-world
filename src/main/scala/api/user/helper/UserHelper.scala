package api.user.helper

import com.datastax.driver.core._
import com.datastax.driver.core.querybuilder.QueryBuilder
import api.user.core.UserHandler.User
import com.google.common.util.concurrent.{FutureCallback, Futures}

import scala.concurrent.{Future, Promise}



object UserHelper {

  /**
    * Converts a `ResultSetFuture` into a Scala `Future[ResultSet]`
    * @param f ResultSetFuture to convert
    * @return Converted Future
    */
  implicit def resultSetFutureToScala(f: ResultSetFuture): Future[ResultSet] = {
    val p = Promise[ResultSet]()
    Futures.addCallback(f,
      new FutureCallback[ResultSet] {
        def onSuccess(r: ResultSet) = p success r
        def onFailure(t: Throwable) = p failure t
      })
    p.future
  }


  def InsertUserStament(user: User) : Statement = QueryBuilder.insertInto("senffy1", "USER")
    .value("mail", user.email)
    .value("name", user.name)
    .value("surname", user.surname)
    .value("password", user.surname)
}
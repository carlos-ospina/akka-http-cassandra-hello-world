package api.user.utils

import pureconfig.loadConfig

case class Config(http: HttpConfig, db: DatabaseConfig)

private[utils] case class HttpConfig(host: String, port: Int)
private[utils] case class DatabaseConfig(hosts: List[String], port: Int)

object Config {
  def load() ={
    loadConfig[Config] match {
      case Right(config) => config
      case Left(error) =>
        throw new RuntimeException("Cannot read config file, errors:\n" + error.toList.mkString("\n"))
    }
  }

}
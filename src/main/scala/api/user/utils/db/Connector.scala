package api.user.utils.db

import api.user.utils.Config
import com.datastax.driver.core.{Cluster, ProtocolOptions}


trait CassandraCluster {
  def cluster: Cluster
}

object ConfigCassandraCluster extends CassandraCluster {

  lazy val cluster: Cluster =
    Cluster.builder().
      addContactPoints(hosts: _*).
      withCompression(ProtocolOptions.Compression.SNAPPY).
      withPort(port).
      build()

  private def config = Config.load()

  private val cassandraConfig = config.db
  private val port = cassandraConfig.port
  private val hosts =  cassandraConfig.hosts

}
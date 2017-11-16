package org.hhl.esETL.es
import com.google.gson.GsonBuilder
import io.searchbox.client.JestClientFactory
import io.searchbox.client.config.HttpClientConfig
/**
  * Created by huanghl4 on 2017/11/15.
  */
object esConnFactory extends Serializable{
  @transient private var factory: JestClientFactory = null
  def getESFactory(): JestClientFactory = {
    //设置连接ES
    if (factory == null) {
      factory = new JestClientFactory()
      factory.setHttpClientConfig(new HttpClientConfig.Builder("http://10.120.193.9:9200")
        .addServer("http://10.120.193.10:9200").addServer("http://10.120.193.26:9200")
        .maxTotalConnection(20)//.defaultMaxTotalConnectionPerRoute(10)
        .gson(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss") create())
        .readTimeout(100000)
        .build())
    }
    factory
  }
}

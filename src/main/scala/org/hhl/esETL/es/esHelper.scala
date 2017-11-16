package org.hhl.esETL.es

// 六个以上的引用，请用_
import io.searchbox.indices.settings.UpdateSettings
import io.searchbox.indices.{CreateIndex, DeleteIndex, IndicesExists}


/**
  * Created by huanghl4 on 2017/11/15.
  */
object esHelper {

  @transient var client = esConnFactory.getESFactory().getObject

  def createIndex(index: String) = {
    val settings = "\"settings\" : {\n" +
      "        \"refresh_interval\" : -1,\n" +
     // "        \"number_of_replicas\" : 0\n" +
      "    }\n"
    val createBuilder = new CreateIndex.Builder(index).settings(settings).build()
    val result = client.execute(createBuilder)
    result.isSucceeded match {
      case true => println("create successfully")
      case _ => new Exception("creating index failed")
    }
  }

  def deleteIndex(index: String): Boolean = {
    if (checkIndexExists(index)) {
      val result = client.execute(new DeleteIndex.Builder(index).build())
      result.isSucceeded
    }
    else throw new Exception("Index not exists")
  }

  def createWithDrop(index: String) = {
    if (deleteIndex(index)) createIndex(index) else throw new Exception("delete fail")
  }

  def checkIndexExists(index: String): Boolean = {
    val result = client.execute(new IndicesExists.Builder(index).build())
    result.isSucceeded
//    val flag = result.getResponseCode match {
//      // HTTP 响应码为200时，存在
//      case 200 => true
//      case _ => false // 404 或其它
//    }
//    flag
  }

  def updateIndexSetting(index: String) = {
    val setting = "{ \"index\" : { " +
      "\"refresh_interval\" : " + "1s" +
      "} }"
    val csb = new UpdateSettings.Builder(setting).build()
    val result = client.execute(csb)
    result.isSucceeded match {
      case true => println(s"change $index settings successfully")
      case _ => new Exception(s"change $index settings failed")
    }
  }

// fixme bulkload 之后使用 forceMerge 合并段 es5.4 版本
 // https://www.elastic.co/guide/en/elasticsearch/reference/current/indices-update-settings.html
  // def forceMerge(index:String) = {
//    val fm = new ForceMerge.Builder().maxNumSegments(1).build()
//
//  }

}

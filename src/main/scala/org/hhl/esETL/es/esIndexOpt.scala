package org.hhl.esETL.es

import org.apache.spark.rdd.RDD

/**
  * Created by huanghl4 on 2017/11/16.
  */
object esIndexOpt {

  def init[T](index:String,typeName:String,rdd:RDD[T]) = {
    esHelper.createWithDrop(index)

  }

}

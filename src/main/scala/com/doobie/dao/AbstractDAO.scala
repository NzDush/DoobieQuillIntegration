package com.doobie.dao

import com.doobie.connection.MysqlConnection

trait AbstractDAO[T] extends MysqlConnection{

  def insert(row: List[T]): doobie.ConnectionIO[List[Long]]

  def viewAll: fs2.Stream[doobie.ConnectionIO, T]

  def update(id: Int, data: T): doobie.ConnectionIO[Long]

  def delete(id: Int): doobie.ConnectionIO[Long]

}

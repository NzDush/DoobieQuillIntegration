package com.doobie.connection

//import doobie._
//import doobie.implicits._
import cats.effect.{Blocker, IO}
import doobie.Transactor
import doobie.util.ExecutionContexts
import io.getquill.{ idiom => _, _ }
import doobie.quill.DoobieContext

trait MysqlConnection {
//  val program1 = 42.pure[ConnectionIO]

  implicit val cs = IO.contextShift(ExecutionContexts.synchronous)

  val xa = Transactor.fromDriverManager[IO](
//    "org.mysql.Driver",                       // driver classname
    "com.mysql.cj.jdbc.Driver",                       // driver classname
      "jdbc:mysql://localhost:3306/quill_example", // connect URL (driver-specific)
     "admin",                                   // user
     "password",                                // password
    Blocker.liftExecutionContext(ExecutionContexts.synchronous) // just for testing
  )

  val y = xa.yolo

  val dc = new DoobieContext.MySQL(Literal) // Literal naming scheme

}

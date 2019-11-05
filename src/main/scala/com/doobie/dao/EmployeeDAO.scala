package com.doobie.dao

//import doobie._
import doobie.implicits._
//import doobie.util.ExecutionContexts
//import cats._
//import cats.data._
import cats.effect._
//import cats.implicits._
import com.doobie.models.Employee
import com.doobie.connection.MysqlConnection
import io.getquill.{ idiom => _, _ }

trait EmployeeDAO extends AbstractDAO[Employee] with
                          MysqlConnection {
//  import y._
  import dc._

  def insert(employees: List[Employee]): doobie.ConnectionIO[List[Long]] = {
    val insertQuery = quote{
      liftQuery(employees).foreach(t => query[Employee].insert(t))
    }
    insertQuery
    val runResult = run(insertQuery)
    runResult
  }

  def viewAll: fs2.Stream[doobie.ConnectionIO, Employee] ={
    val viewQuery = quote{
      query[Employee]
    }
    run(viewQuery)
    val streamResult = stream(viewQuery, 16)
    streamResult
  }

  def update(employee_id: Int, newEmployee: Employee): doobie.ConnectionIO[Long] ={
    val updateQuery = quote{
      query[Employee].filter(employee => employee.employee_id == lift(employee_id)).
        update( employee => employee.name -> lift(newEmployee.name),
          employee => employee.address -> lift(newEmployee.address),
          employee => employee.salary -> lift(newEmployee.salary))
    }
    updateQuery
    val runResult = run(updateQuery)
    runResult
  }

  def delete(employee_id: Int): doobie.ConnectionIO[Long] ={
    val deleteQuery = quote{
      query[Employee].filter(employee => employee.employee_id == lift(employee_id)).delete
    }
    deleteQuery
    val runResult = run(deleteQuery)
    runResult
  }

  def groupByAddress: fs2.Stream[doobie.ConnectionIO, (String, Long)] ={
    val groupQuery = quote{
      query[Employee].groupBy(employee => employee.address).map{
        case (address, id) => (address, id.size)
      }
    }
    run(groupQuery)
    val streamResult = stream(groupQuery, 16)
    streamResult
  }

  def sortBySalary: fs2.Stream[doobie.ConnectionIO, Employee] ={
    val sortQuery = quote{
      query[Employee].sortBy(employee => employee.salary)
    }
    run(sortQuery)
    val streamResult = stream(sortQuery, 16)
    streamResult
  }

  def sortBySalaryWithMapping: IO[Vector[Employee]] ={
    val result = sortBySalary.transact(xa).compile.toVector
    result
  }

}

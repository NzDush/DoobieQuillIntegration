package com.doobie.controller

import com.doobie.connection.MysqlConnection
//import com.doobie.models.Employee
//import scala.concurrent.Future
//import doobie._
//import doobie.implicits._
//import doobie.util.ExecutionContexts
//import cats._
//import cats.data._
//import cats.effect._
//import cats.implicits._
import com.doobie.dao.EmployeeDAO
import com.doobie.models.Employee

import io.getquill.{ idiom => _, _ }
//import doobie.quill.DoobieContext

object EmployeeController extends EmployeeDAO with
                                  MysqlConnection{
  import y._
//  import dc._

  def insertEmployees(employees: List[Employee]): Unit ={
    val result = insert(employees).quick.unsafeRunSync
  }

  def viewAllEmployees: Unit ={
    val streamResult = viewAll
    val result = streamResult.quick.unsafeRunSync
  }

  def updateEmployeeViaId(employee_id: Int, newEmployee: Employee): Unit ={
    val result = update(employee_id, newEmployee).quick.unsafeRunSync
  }

  def deleteEmployeeViaId(employee_id: Int): Unit ={
    val result = delete(employee_id).quick.unsafeRunSync
  }

  def groupEmployeesByAddress: Unit ={
    val streamResult = groupByAddress
    val result = streamResult.quick.unsafeRunSync
  }

  def sortEmployeeBySalary: Unit ={
    val result = sortBySalary.quick.unsafeRunSync
  }

    def sortEmployeeNamesBySalaryWithMapping: Unit ={
      val result = sortBySalaryWithMapping.unsafeRunSync.foreach{
        employee => {
          println(employee.name + " : " + employee.salary)
        }
      }
    }

}

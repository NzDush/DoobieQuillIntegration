package com.doobie.main

import com.doobie.connection.MysqlConnection
//import com.doobie.controller.EmployeeController
//import com.doobie.models.Employee

object Main extends App with MysqlConnection{


  import com.doobie.controller.EmployeeController.{ insertEmployees, viewAllEmployees, updateEmployeeViaId, deleteEmployeeViaId,
                                                    groupEmployeesByAddress, sortEmployeeBySalary, sortEmployeeNamesBySalaryWithMapping}

//  insertEmployees(List(Employee(34,"MMM","Galle",86.3), Employee(35,"MMM","Galle",86.3), Employee(36,"MMM","Galle",86.3)))

//  viewAllEmployees
//
//  updateEmployeeViaId(32, Employee(32,"NNN","Nuwara-Eliya",65.2))
//
//  deleteEmployeeViaId(34)
//
//  groupEmployeesByAddress
//
//  sortEmployeeBySalary
//
  sortEmployeeNamesBySalaryWithMapping

}

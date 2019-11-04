name := "DoobieQuillIntegration"

version := "0.1"

//scalaVersion := "2.13.1"

scalacOptions += "-Ypartial-unification" // 2.11.9+

libraryDependencies ++= Seq(
  "org.tpolecat" %% "doobie-core"      % "0.8.4",

  "org.tpolecat" %% "doobie-quill"     % "0.8.4",          // Doobie Quill Integration
  "org.tpolecat" %% "doobie-h2"        % "0.8.4",          // H2 driver 1.4.199 + type mappings.
  "org.tpolecat" %% "doobie-hikari"    % "0.8.4",          // HikariCP transactor.
  //  "org.tpolecat" %% "doobie-postgres"  % "0.8.4",          // Postgres driver 42.2.8 + type mappings.
  "org.tpolecat" %% "doobie-quill"     % "0.8.4",          // Support for Quill 3.4.9
  "org.tpolecat" %% "doobie-specs2"    % "0.8.4" % "test", // Specs2 support for typechecking statements.
  "org.tpolecat" %% "doobie-scalatest" % "0.8.4" % "test", // ScalaTest support for typechecking statements.
  "mysql" % "mysql-connector-java" % "8.0.17"

)

package repo

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver
import slick.driver.JdbcProfile
import scala.concurrent.Future

import repo.ColumnTypeMapper
import models.{Assignment}

import repo.InternRepo


/**
  * Created by knoldus on 8/3/16.
  */
@Singleton
class AssignmentRepo @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)extends AssignmentTable with HasDatabaseConfigProvider[JdbcProfile] {


  import driver.api._

  def insert(sno:Int,name:String,date:String,marks:Int,remarks:String,internId:Int) = {
    val insertStatement = assignmentTableQuery += Assignment(sno,name,date,marks,remarks,internId)
    db.run(insertStatement)
  }

  def getAll()={
    db.run(assignmentTableQuery.to[List].result)
  }
/*
  def getByInternId(email:String)={

    db.run(internTableQuery.join(assignmentTableQuery).on(_.id===_.internId).filter(x=>x._1.email===email).to[List].result)

  }
*/
}

trait AssignmentTable extends InternTable{ self: HasDatabaseConfigProvider[JdbcProfile] =>

  import driver.api._

  val assignmentTableQuery = TableQuery[AssignmentTable]

  class AssignmentTable(tag: Tag) extends Table[Assignment](tag, "assignment") {


    def * = (sno, name, date, marks,remarks,internId) <>(Assignment.tupled, Assignment.unapply)

    def sno= column[Int]("sno",O.AutoInc)

    def name = column[String]("name", O.SqlType("VARCHAR(200"))

    def date = column[String]("date", O.SqlType("VARCHAR(200"))
    def marks = column[Int]("marks")
    def remarks = column[String]("remarks", O.SqlType("VARCHAR(200"))

    def internId=column[Int]("internid")

    def assignmentPk = primaryKey("assignment_pk", (sno, internId))

    def id = foreignKey("internId_fk", internId,internTableQuery)(_.id)


  }
}

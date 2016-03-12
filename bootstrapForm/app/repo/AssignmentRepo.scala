package repo

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver
import slick.driver.JdbcProfile
import scala.concurrent.Future
import models.{Assignment}
import scala.concurrent.ExecutionContext.Implicits.global



/**
  * Created by knoldus on 8/3/16.
  */
@Singleton
class AssignmentRepo @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)extends AssignmentTable with HasDatabaseConfigProvider[JdbcProfile] {


  import driver.api._

  def insert(sno:Int,name:String,date:String,marks:Int,remarks:String,internId:Int) = {
    db.run(assignmentTableQuery += Assignment(sno,name,date,marks,remarks,internId))
  }

  def getAll(email:String)={
    val getList=internTableQuery.filter(_.email===email).to[List].result
    val res=db.run(getList)
    res.flatMap(x=>db.run(assignmentTableQuery.filter(_.internId===x.head.id).to[List].result))
  }

  def getAll()={
    db.run(assignmentTableQuery.to[List].result)
  }


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

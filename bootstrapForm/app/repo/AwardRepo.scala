package repo

import javax.inject.{Inject, Singleton}

import models.{Award, Language}
import play.api.db.slick.{HasDatabaseConfigProvider, DatabaseConfigProvider}
import slick.driver.JdbcProfile
import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by knoldus on 9/3/16.
  */
@Singleton
class AwardRepo @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] with AwardTable{


  import driver.api._

  def insert(sno:Int,name:String,details:String,email:String)={
    val getList=internTableQuery.filter(_.email===email).to[List].result
    val res=db.run(getList)
    res.flatMap(x=>db.run(awardTableQuery+=Award(sno,name,details,x.head.id)))
  }

  def getAll(email:String)={
    val getList=internTableQuery.filter(_.email===email).to[List].result
    val res=db.run(getList)
    res.flatMap(x=>db.run(awardTableQuery.filter(_.internId===x.head.id).to[List].result))
  }

  def delete(id:Int,email:String)={
    val getList=internTableQuery.filter(_.email===email).to[List].result
    val res=db.run(getList)
    res.flatMap(x=>db.run(awardTableQuery.filter(_.internId===x.head.id).filter(_.id===id).delete))

  }


  def getAll()={

    db.run{awardTableQuery.to[List].result}
  }



}


trait AwardTable extends InternTable{ self: HasDatabaseConfigProvider[JdbcProfile] =>

  import driver.api._

  val awardTableQuery = TableQuery[AwardTable]

  class AwardTable(tag: Tag) extends Table[Award](tag, "award") {


    def * = (id,name,details,internId) <>(Award.tupled, Award.unapply)

    def id= column[Int]("id",O.AutoInc)

    def name = column[String]("name", O.SqlType("VARCHAR(200"))

    def details = column[String]("details", O.SqlType("VARCHAR(200"))

    def internId=column[Int]("internid")

    def languagePk = primaryKey("award_pk", (id, internId))

    def idfk = foreignKey("internId_fk", internId,internTableQuery)(_.id)


  }
}

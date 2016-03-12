package repo

import javax.inject.{Inject, Singleton}

import models.{Language, Intern}

import slick.driver
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver.JdbcProfile
import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by knoldus on 8/3/16.
  */
@Singleton
class LanguageRepo @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] with LanguageTable{


  import driver.api._

  def insert(sno:Int,known:String,fluency:String,email:String)={
    val getList=internTableQuery.filter(_.email===email).to[List].result
    val res=db.run(getList)
    res.flatMap(x=>db.run(languageTableQuery+=Language(sno,known,fluency,x.head.id)))
  }

  def delete(sno:Int,email:String)={
    val getList=internTableQuery.filter(_.email===email).to[List].result
    val res=db.run(getList)
    res.flatMap(x=>db.run(languageTableQuery.filter(_.internId===x.head.id).filter(_.sno===sno).delete))
  }


  def getAll(email:String)={
    val getList=internTableQuery.filter(_.email===email).to[List].result
    val res=db.run(getList)
    res.flatMap(x=>db.run(languageTableQuery.filter(_.internId===x.head.id).to[List].result))
  }

  def getAll()={
    db.run(languageTableQuery.to[List].result)
  }


}

trait LanguageTable extends InternTable{ self: HasDatabaseConfigProvider[JdbcProfile] =>

  import driver.api._

   val languageTableQuery = TableQuery[LanguageTable]

  class LanguageTable(tag: Tag) extends Table[Language](tag, "language") {


    def * = (sno,known,fluency,internId) <>(Language.tupled, Language.unapply)

    def sno= column[Int]("sno",O.AutoInc)

    def known = column[String]("known", O.SqlType("VARCHAR(200"))

    def fluency = column[String]("fluency", O.SqlType("VARCHAR(200"))

    def internId=column[Int]("internid")

    def languagePk = primaryKey("language_pk", (sno, internId))

    def iId = foreignKey("internId_fk", internId,internTableQuery)(_.id)


  }
}

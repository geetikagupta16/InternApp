package repo

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver
import slick.driver.JdbcProfile
import scala.concurrent.Future

import models.ProgLanguage
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by knoldus on 9/3/16.
  */

@Singleton
class ProgLanguageRepo @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] with ProgLanguageTable{


  import driver.api._


  def insert(sno:Int,known:String,fluency:String,email:String)={
    val getList=internTableQuery.filter(_.email===email).to[List].result
    val res=db.run(getList)
    res.flatMap(x=>db.run(progLangTableQuery+=ProgLanguage(sno,known,fluency,x.head.id)))
  }

  def getAll(email:String)={
    val getList=internTableQuery.filter(_.email===email).to[List].result
    val res=db.run(getList)
    res.flatMap(x=>db.run(progLangTableQuery.filter(_.internId===x.head.id).to[List].result))
  }

}

trait ProgLanguageTable extends InternTable {self: HasDatabaseConfigProvider[JdbcProfile] =>

  import driver.api._

  val progLangTableQuery = TableQuery[ProgLanguageTable]

  class ProgLanguageTable(tag: Tag) extends Table[ProgLanguage](tag, "proglanguage") {


    def * = (sno,known,fluency,id) <>(ProgLanguage.tupled, ProgLanguage.unapply)

    def sno= column[Int]("sno",O.AutoInc)

    def known = column[String]("known", O.SqlType("VARCHAR(200"))

    def fluency = column[String]("fluency", O.SqlType("VARCHAR(200"))

    def id=column[Int]("internid")

    def languagePk = primaryKey("language_pk", (sno, id))

    def internId = foreignKey("internId_fk", id,internTableQuery)(_.id)


  }
}

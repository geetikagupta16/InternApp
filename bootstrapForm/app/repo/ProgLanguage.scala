package repo

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver
import slick.driver.JdbcProfile
import scala.concurrent.Future

import models.ProgLanguage
import repo.ColumnTypeMapper

/**
  * Created by knoldus on 9/3/16.
  */

@Singleton
class ProgLanguageRepo @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] with ProgLanguageTable{


  import driver.api._

  def insert(sno:Int,known:String,fluency:String,internId:Int)={
    val insert=languageTableQuery+=ProgLanguage(sno,known,fluency,internId)
  }


}

trait ProgLanguageTable extends InternTable {self: HasDatabaseConfigProvider[JdbcProfile] =>

  import driver.api._

  val languageTableQuery = TableQuery[LanguageTable]

  class LanguageTable(tag: Tag) extends Table[ProgLanguage](tag, "proglanguage") {


    def * = (sno,known,fluency,id) <>(ProgLanguage.tupled, ProgLanguage.unapply)

    def sno= column[Int]("sno",O.AutoInc)

    def known = column[String]("known", O.SqlType("VARCHAR(200"))

    def fluency = column[String]("fluency", O.SqlType("VARCHAR(200"))

    def id=column[Int]("id")

    def languagePk = primaryKey("language_pk", (sno, id))

    def internId = foreignKey("internId_fk", id,internTableQuery)(_.id)


  }
}

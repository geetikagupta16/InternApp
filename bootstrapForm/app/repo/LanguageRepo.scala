package repo

import javax.inject.{Inject, Singleton}

import models.{Language, Intern}
import repo.ColumnTypeMapper

import slick.driver
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver.JdbcProfile
import scala.concurrent.Future

/**
  * Created by knoldus on 8/3/16.
  */
@Singleton
class LanguageRepo @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] with LanguageTable{


  import driver.api._

  def insert(sno:Int,known:String,fluency:String,internId:Int)={
    val insert=languageTableQuery+=Language(sno,known,fluency,internId)
  }


}

trait LanguageTable extends InternTable{ self: HasDatabaseConfigProvider[JdbcProfile] =>

  import driver.api._

   val languageTableQuery = TableQuery[LanguageTable]

  class LanguageTable(tag: Tag) extends Table[Language](tag, "language") {


    def * = (sno,known,fluency,id) <>(Language.tupled, Language.unapply)

    def sno= column[Int]("sno",O.AutoInc)

    def known = column[String]("known", O.SqlType("VARCHAR(200"))

    def fluency = column[String]("fluency", O.SqlType("VARCHAR(200"))

    def id=column[Int]("id")

    def languagePk = primaryKey("language_pk", (sno, id))

    def internId = foreignKey("internId_fk", id,internTableQuery)(_.id)


  }
}

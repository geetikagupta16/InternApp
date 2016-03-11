package repo

import javax.inject.{Inject, Singleton}

import models.{Login, Intern}
import play.api.db.slick.{HasDatabaseConfigProvider, DatabaseConfigProvider}
import slick.driver
import slick.driver.JdbcProfile
import models.{Language, Intern}
import scala.concurrent.Future


/**
  * Created by knoldus on 8/3/16.
  */
@Singleton
class InternRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends InternTable
  with HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  def loginAuth(login:Login):Future[List[Intern]]= {
    val find = internTableQuery.filter(_.email === login.email).filter(_.password === login.password).to[List].result
    db.run(find)
  }

  def getAll()={
    db.run(internTableQuery.to[List].result)
  }


}


trait InternTable { self: HasDatabaseConfigProvider[JdbcProfile] =>

  import driver.api._


  class InternInfo(tag: Tag) extends Table[Intern](tag, "intern") {

    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name", O.SqlType("VARCHAR(200"))

    def email = column[String]("email", O.SqlType("VARCHAR(200"))

    def password = column[String]("password", O.SqlType("VARCHAR(200"))

    def * = (id, name,email, password) <>(Intern.tupled, Intern.unapply)


  }

  lazy val internTableQuery = TableQuery[InternInfo]
}


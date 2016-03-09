package repo

import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver
import slick.driver.JdbcProfile

trait ColumnTypeMapper { self: HasDatabaseConfigProvider[JdbcProfile] =>

  import driver.api._

}

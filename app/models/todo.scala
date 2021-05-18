package models

import javax.inject.Inject
import play.api.db.DBApi
import play.api.mvc._
import play.api.{Play, db}
import anorm._
import anorm.SqlParser._
import scala.concurrent.Future



case class Todo (id: Long, label: String)


class TodoList @Inject()(dbApi: DBApi) {

  private val db = dbApi.database("default")

  val todo = {
    get[Long]("id") ~
      get[String]("label") map {
      case id~label => Todo(id, label)
    }
  }

  def all(): List[Todo] = db.withConnection { implicit c =>
    SQL("select * from todos").as(todo.*)
  }

  def create(label: String): Unit = {
    db.withConnection { implicit c =>
      SQL("insert into todos (label) values ({label})").on(
        "label" -> label
      ).executeUpdate()
    }
  }

  def modify(id: Long, label: String): Unit = {
    db.withConnection { implicit c =>
      SQL("update todos set label = {label} where id = {id}").on(
        "id" -> id, "label" -> label
      ).executeUpdate()
    }
  }

  def delete(id: Long): Unit = {
    db.withConnection { implicit c =>
      SQL("delete from todos where id = {id}").on(
        "id" -> id
      ).executeUpdate()
    }
  }
}

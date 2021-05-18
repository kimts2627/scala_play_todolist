package controllers


import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Form
import play.api.data.Forms._
import models._


/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents, todoList: TodoList)
  extends AbstractController(cc) with play.api.i18n.I18nSupport {
  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  val todoForm = Form(
    "label" -> nonEmptyText
  )

  def todo() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index(todoList.all(),todoForm))
  }

  def newTodo() = Action { implicit request =>
    todoForm.bindFromRequest().fold(
      errors => BadRequest(views.html.index(todoList.all(), errors)),
      label => {
        todoList.create(label)
        Redirect(routes.HomeController.todo())
      }
    )
  }

  def modifyTodo(id: Long) = Action { implicit request =>
    todoForm.bindFromRequest().fold(
      errors => BadRequest(views.html.index(todoList.all(), errors)),
      label => {
        todoList.modify(id, label)
        Redirect(routes.HomeController.todo())
      }
    )
  }

  def deleteTodo(id: Long) = Action {
    todoList.delete(id)
    Redirect(routes.HomeController.todo())
  }

}

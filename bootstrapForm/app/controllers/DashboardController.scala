package controllers

import com.google.inject.Inject
import models.{Login, Intern}
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}
import play.api.Play.current
import play.api.i18n.Messages.Implicits._
import repo.InternRepo
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

/**
  * Created by knoldus on 8/3/16.
  */
class DashboardController  @Inject()(internRepo:InternRepo) extends Controller {

  val addAwardForm = Form(
    tuple(
      "id" -> number,
      "name" -> nonEmptyText,
      "details" -> nonEmptyText
    )
  )

  val addLangForm = Form(
    tuple(
      "lang" -> nonEmptyText,
      "fluency" -> nonEmptyText
    )
  )

  val addProgLangForm = Form(
    tuple(
      "langName" -> nonEmptyText,
      "fluency" -> nonEmptyText
    )
  )


  def getDashboard() = Action { implicit request =>
    Ok(views.html.dashboard(addAwardForm, addLangForm, addProgLangForm))
  }

  def getAdminDashboard()=Action{
    implicit request =>
      Ok(views.html.adminDashboard(addAwardForm, addLangForm, addProgLangForm))

  }



  def addAward() = Action { implicit request =>
    Ok("In add award")

  }

  def addLanguage() = Action { implicit request =>
    Ok("In language")

  }

  def addProgLanguage() = Action { implicit request =>
    Ok("In Prog Lang")

  }




}

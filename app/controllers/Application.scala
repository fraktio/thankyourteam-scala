package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import models.Entry

object Application extends Controller {

  val entryForm = Form(
    mapping(
      "id" -> ignored(23L),
      "author" -> text,
      "message" -> text,
      "shortUrl" -> ignored("")
    )(Entry.apply)(Entry.unapply)
  )

  def entries = Action {
    Ok(views.html.index(Entry.all(), entryForm))
  }

  def newEntry = Action { implicit request =>
    entryForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(Entry.all(), errors)),
      entry => {
        val identifier = Entry.create(entry)
        Ok(views.html.view(Entry.findByShortUrl(identifier)))
      }
    )
  }

  def deleteEntry(shortUrl: String) = Action { implicit request =>
    Entry.delete(shortUrl)
    Redirect(routes.Application.entries)
  }

  def viewEntry(shortUrl: String) = Action { implicit request =>
    Ok(views.html.view(Entry.findByShortUrl(shortUrl)))
  }

}
package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current
import scala.util.Random

case class Entry(id: Long, author: String, message: String, shortUrl: String)

object Entry {

  val entry = {
    get[Long]("id") ~
    get[String]("author") ~
    get[String]("message") ~
    get[String]("shortUrl") map {
        case id~author~message~shortUrl => Entry(id, author, message, shortUrl)
    }
  }

  def all(): List[Entry] = DB.withConnection { implicit c =>
    SQL("select * from entry").as(entry *)
  }

  def findByShortUrl(shortUrl: String) = DB.withConnection { implicit c =>
    SQL("select * from entry where shortUrl = {shortUrl}").on(
      'shortUrl -> shortUrl
    ).as(entry *)
  }

  def create(entry: Entry) : String = {

    val shortUrl = Random.alphanumeric.take(7).mkString

    DB.withConnection { implicit c =>
      SQL("insert into entry (author, message, shortUrl) values ({author}, {message}, {shortUrl})").on(
        'author -> entry.author,
        'message -> entry.message,
        'shortUrl -> shortUrl
      ).executeUpdate()
    }

    return shortUrl;
  }

  def delete(shortUrl: String) {
    DB.withConnection { implicit c =>
      SQL("delete from entry where shortUrl = {shortUrl}").on(
        'shortUrl -> shortUrl
      ).executeUpdate()
    }
  }

}
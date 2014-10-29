package models

import java.util.Date

import anorm.SqlParser._
import anorm._
import play.api.Play.current
import play.api.db._

import scala.util.Random

case class Entry(id: Long, author: String, message: String, shortUrl: String, created: Date)

object Entry {

  val entry = {
    get[Long]("id") ~
    get[String]("author") ~
    get[String]("message") ~
    get[String]("shortUrl") ~
    get[Date]("created") map {
        case id~author~message~shortUrl~created => Entry(id, author, message, shortUrl, created)
    }
  }

  def all(): List[Entry] = DB.withConnection { implicit c =>
    SQL("select * from entry").as(entry *)
  }

  def findOneByShortUrl(shortUrl: String): Entry = DB.withConnection { implicit c =>
    SQL("select * from entry where shortUrl = {shortUrl}").on(
      'shortUrl -> shortUrl
    ).as(entry *).head
  }

  def create(entry: Entry) : String = {
    val shortUrl = Random.alphanumeric.take(7).mkString

    DB.withConnection { implicit c =>
      SQL("insert into entry (author, message, shortUrl, created) values ({author}, {message}, {shortUrl}, {created})").on(
        'author -> entry.author,
        'message -> entry.message,
        'shortUrl -> shortUrl,
        'created -> new Date()
      ).executeUpdate()
    }

    return shortUrl;
  }

}
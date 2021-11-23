package pbt

import cats.Show

case class User(id: Long, username: String, email: String)

object User {
  implicit def showUser: Show[User] = Show.fromToString[User]
}

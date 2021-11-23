package pbt.gen

import pbt.User
import org.scalacheck.{Arbitrary, Gen}

object UserGen {

  lazy val userGen: Gen[User] = for {
    id <- Gen.long
    username <- Gen.alphaNumStr
    email <- Gen.alphaNumStr
  } yield User(id, username, email)

  implicit lazy val userArbitrary: Arbitrary[User] = Arbitrary(userGen)

}

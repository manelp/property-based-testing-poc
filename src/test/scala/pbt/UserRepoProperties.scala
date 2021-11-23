package pbt

import pbt.gen.UserGen._

import cats.effect.unsafe.implicits.global
import cats.effect.{IO, Resource}
import org.scalacheck.Prop.{forAll, propBoolean}
import org.scalacheck._

object UserRepoProperties extends Properties("UserRepo") {

  val emptyUserRepo: Resource[IO, UserRepoImpl[IO]] = for {
    userList <- Resource.eval(IO.ref(List.empty[User]))
    userRepo = new UserRepoImpl[IO](userList)
  } yield userRepo

  property("add user") = forAll { (user: User) =>
    val expect = emptyUserRepo
      .use { userRepo =>
        for {
          _ <- userRepo.addUser(user)
          state <- userRepo.listUsers()
        } yield state.contains(user)
      }
    expect.unsafeRunSync()
  }

  property("deleteUser") = forAll { (user: User) =>
    val expect = emptyUserRepo
      .use { userRepo =>
        for {
          _ <- userRepo.addUser(user)
          _ <- userRepo.deleteUser(user.id)
          state <- userRepo.listUsers()
        } yield !state.contains(user)
      }
    expect.unsafeRunSync()
  }

  // We can also add conditional properties
  property("deleteUser doesn't delete other users") = forAll {
    (user: User, x: Long) =>
      (x != user.id) ==> {
        val expect = emptyUserRepo
          .use { userRepo =>
            for {
              _ <- userRepo.addUser(user)
              _ <- userRepo.deleteUser(x)
              state <- userRepo.listUsers()
            } yield state.contains(user)
          }
        expect.unsafeRunSync()
      }
  }

}

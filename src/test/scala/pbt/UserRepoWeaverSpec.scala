package pbt

import cats.effect.{IO, Resource}
import org.scalacheck.rng.Seed
import weaver._
import weaver.scalacheck._
import pbt.gen.UserGen._

object UserRepoWeaverSpec extends SimpleIOSuite with Checkers {

  val emptyUserRepo: Resource[IO, UserRepoImpl[IO]] = for {
    userList <- Resource.eval(IO.ref(List.empty[User]))
    userRepo = new UserRepoImpl[IO](userList)
  } yield userRepo

  test("add user") {
    forall(userGen) { user =>
      emptyUserRepo
        .use { userRepo =>
          for {
            _ <- userRepo.addUser(user)
            state <- userRepo.listUsers()
          } yield expect(state.contains(user))
        }
    }
  }

//  property("deleteUser") = forAll { (user: User) =>
//    val expect = emptyUserRepo
//      .use { userRepo =>
//        for {
//          _ <- userRepo.addUser(user)
//          _ <- userRepo.deleteUser(user.id)
//          state <- userRepo.listUsers()
//        } yield !state.contains(user)
//      }
//    expect.unsafeRunSync()
//  }
//
//  // We can also add conditional properties
//  property("deleteUser doesn't delete other users") = forAll {
//    (user: User, x: Long) =>
//      (x != user.id) ==> {
//        val expect = emptyUserRepo
//          .use { userRepo =>
//            for {
//              _ <- userRepo.addUser(user)
//              _ <- userRepo.deleteUser(x)
//              state <- userRepo.listUsers()
//            } yield state.contains(user)
//          }
//        expect.unsafeRunSync()
//      }
//  }

}

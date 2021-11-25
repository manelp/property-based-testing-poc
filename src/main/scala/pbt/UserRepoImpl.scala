package pbt

import cats.effect.Ref

class UserRepoImpl[F[_]](users: Ref[F, List[User]]) extends UserRepo[F] {

  override def addUser(user: User): F[Unit] = users.update { current =>
    user :: current
  }

  def addUserBuggy(user: User): F[Unit] = users.update { current =>
    if (user.id % 500 == 0) current
    else
      user :: current
  }

  override def listUsers(): F[List[User]] = users.get

  override def deleteUser(userId: Long): F[Unit] = users.update { current =>
    current.filterNot(_.id == userId)
  }
}

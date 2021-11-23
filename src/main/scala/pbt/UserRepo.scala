package pbt

trait UserRepo[F[_]] {

  def addUser(user: User): F[Unit]
  def listUsers(): F[List[User]]
  def deleteUser(userId: Long): F[Unit]

}

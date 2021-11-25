package pbt

trait AddAlg {

  def add(a: Int, b: Int): Int

}

object AddInt extends AddAlg {

  override def add(a: Int, b: Int): Int = a + b
}

package pbt

import org.scalacheck.Prop.forAll
import org.scalacheck.Properties

object AddProperties extends Properties("Add") {

  property("bad usage of property based testing") = forAll { (a: Int, b: Int) =>
    AddInt.add(a, b) == a + b
  }

  property("commutative") = forAll { (a: Int, b: Int) =>
    AddInt.add(a, b) == AddInt.add(b, a)
  }

  property("neutral value") = forAll { (a: Int) =>
    AddInt.add(a, 0) == a
  }

}

# Property based testing notes

## What is it

* As the name says, it relays in properties.
* It checks that a method/program follows defined properties
* It doesn't need to check that the output is the expected but the output follows the expected properties.

I.e

## Using it

In scala:
* [ScalaCheck](https://github.com/typelevel/scalacheck/blob/main/doc/UserGuide.md)
* [Weaver + scalacheck](https://disneystreaming.github.io/weaver-test/docs/scalacheck)
    
Using seeds:
* https://gist.github.com/non/aeef5824b3f681b9cfc141437b16b014

Examples:
* https://github.com/typelevel/cats/tree/main/laws/src/main/scala/cats/laws
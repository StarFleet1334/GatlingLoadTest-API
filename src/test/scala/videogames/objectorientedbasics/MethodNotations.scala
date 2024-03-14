package videogames.objectorientedbasics

import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String,favourite_movie: String,val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favourite_movie

    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    def unary_!(): String = s"${this.name} what a heck!!!"
    def isAlive: Boolean = true

    def apply(): String = s"Hi my name is $name and I like $favourite_movie"

    // Exercises

    def +(nickname: String): Person = new Person(s"${mary.name} ($nickname)",this.favourite_movie)

    def unary_+(): Person = new Person(name,this.favourite_movie,this.age+1)

    def learns(language: String) = s"Mary learns $language"

    def learn_language(): String = learns("Scala")

    def apply(x: Int): String = s"$name watched inception $x times"
  }

  val mary = new Person("Mary","Inception")
//  println(mary.likes("Inception"))
//  println(mary likes "Inception") // Equivalent
//  // This is called infix notation or operator notation ( syntactic sugar)
//  // and only works with method that have only one parameter
//
//  val tom = new Person("Tom","Fight Club")
//  println(mary + tom)
//  println(mary.+(tom))
//
//  println(1 + 2)
//  println(1.+(2))
//
//  // All operators are methods.
//
//  // prefix notation (another form of syntactic sugar)
//  val x = -1 // equivalent with 1.unary_-
//  val y = 1.unary_-
//
//  println(!mary)
//  println(mary.unary_!())
//
//  // Postfix notation
//  println(mary.isAlive)
//  // equivalent way
//  println(mary isAlive)
//
//
//  // apply
//  println(mary.apply())
//  println(mary())

  val newPerson = mary + "the rockstar"
  println(newPerson.name)

  println((+(+mary)).age)

  // postfix notation
  println(mary learn_language)

  // apply
  println(mary(6))

}

package videogames.objectorientedbasics

object Inheritance extends App  {


  // Single class inheritance
  // that means you can only extend one class
  class Animal {
    val breed = "K2"
    protected def eat = println("nomnom")
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  // constructors

  class Person(name: String,age: Int) {
    def this(name: String) = this(name,0)
  }
//  class Adult(name: String,age: Int,idCard: String) extends Person(name,age)
  // second way
  class Adult(name: String,age: Int,idCard: String) extends Person(name)


  // Overriding

  class Dog(override val breed: String) extends Animal {
    override protected def eat: Unit = println("crunch,crunch")
  }

  val x = new Dog("K5")
  println(x.breed)

  // type substitution
  val unknownAnimal: Animal = new Dog("K8")


}

package videogames.objectorientedbasics

object ScalaObjects extends App {

  // This right here is called COMPANIONS
  object Person {
    val N_EYES = 2

    def canFly: Boolean = false

    //factory method
    def apply(mother: Person,father: Person): Person = new Person("Bobbie")
  }

  class Person(val name: String) {
    // instance-level functionality
  }

  println(Person.N_EYES)

  // Scala object = SINGLETON instance
  val mary = new Person("Mary")
  val john = new Person("John")

  val bobbie = Person(mary,john)

  // Scala Applications is only a Scala object with
  // def main(args: Array[String]): Unit 

}

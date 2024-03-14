package videogames.objectorientedbasics

object CaseClasses extends App  {

  case class Person(name: String,age: Int)
  // 1. Class parameters are fields
  val jim = new Person("Jim",34)
  println(jim.name)

  // 2. sensible toString
  println(jim.toString)

  // 3. equals and hashCode implemented OOTB
  val jim2 = new Person("Jim",34)
  println(jim == jim2 )

  // 4. CCs have handy copy method
  val jim3 = jim.copy(age = 45)
  println(jim3)

  // CCs have companion objects
  val thePerson = Person
  val mary = Person("Mary",23)

  // CCs are serializable
  // CCs have extractor patterns means CCs can be used in PATTERN MATCHING

  

}

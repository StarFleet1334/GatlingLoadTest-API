package videogames.objectorientedbasics

import videogames.playground.{Caspian => prince, Cinderella => princess}
//import videogames.playground._
// This syntax imports everything from videogames.playground

object PackagingAndImports extends App {

  // package members are accessible by their simple name
  val writer = new Writer("Danile","RockTheJVM",2018)

  // package objects
  sayHello
  println(SPEED_OF_LIGHT)

  val princess = new prince
  val prince = new princess
  val golem = new videogames.playground.Golem // Fully qualified name
  println(golem)
}

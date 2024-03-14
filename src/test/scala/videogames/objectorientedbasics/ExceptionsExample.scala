package videogames.objectorientedbasics

object ExceptionsExample extends App {

  val x: String = null
//  println(x.length)
  // This would result in exception (NullPointerException)


  // throwing and catching exceptions

  // we can throw exception like this:
  // everything is expression so we can store it
//  val aWeirdValue = throw new NullPointerException()
  // exceptions are instances of classes
  // Exception and Error are the major Throwable sub-classes


//  def getInt(withExceptions: Boolean): Int = {
//    if (withExceptions) throw new RuntimeException("Got RunTime exception!!!")
//    else 42
//  }
//  try {
//   getInt(true)
//  } catch {
//    case e: RuntimeException => println("Caught a RunTime exception")
//  } finally {
//    // Code that will get executed NO MATTER WHAT
//    println("Finally")
//  }


  // How to define your own exceptions
  class MyException extends Exception
  val exception = new MyException
  throw exception



}

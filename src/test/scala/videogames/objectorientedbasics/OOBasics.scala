package videogames.objectorientedbasics

import java.util.Date


object OOBasics extends App{



}

class Writer(firstName: String,surName: String,val year: Int) {

  def fullName(): String = s"${this.firstName} ${this.surName}"
}

class Novel(name: String,year_of_release: Int,author: Writer) {
  def authorAge(): Int = year_of_release - author.year

  def isWrittenBy(author: Writer): Boolean = author.fullName() == this.author.fullName() && author.year == this.author.year

  def copy(new_year_of_release: Int): Novel = new Novel(this.name,new_year_of_release,this.author)
}

class Counter(x: Int = 0) {
  def count = x
  def current_count(): Int = this.x
  def increment = {
    println("Incrementing")
    new Counter(this.x+1)
  }
  def decrement = {
    println("Decrementing")
    new Counter(this.x-1)
  }
  def increment(x: Int): Counter = {
    if (x <= 0) this
    else increment.increment(x-1)
  }
  def decrement(x: Int): Counter = new Counter(this.x-x)
}
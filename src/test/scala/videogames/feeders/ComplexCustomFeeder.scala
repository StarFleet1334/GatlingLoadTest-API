package videogames.feeders

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import scala.util.Random
class ComplexCustomFeeder extends Simulation{

  // Http Configuration
  val httpProtocol = http.baseUrl(url = "https://videogamedb.uk/api")
    .acceptHeader(value = "application/json")
    .contentTypeHeader("application/json")


  def authenticate(): ChainBuilder = {
    exec(http("Authenticate")
      .post("/authenticate")
      .body(StringBody(
        "{\n  \"password\": \"admin\",\n  \"username\": \"admin\"\n}"
      ))
      .check(jsonPath("$.token").saveAs("jwtToken")))
      .pause(5)
  }

  var idNumbers= (1 to 10).iterator

  val rnd = new Random()

  def randomString(length: Int) = {
    rnd.alphanumeric.filter(_.isLetter).take(length).mkString
  }

  val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd")

  def getRandomDate(startdate: LocalDate,random: Random): String = {
    startdate.minusDays(random.nextInt(30)).format(pattern)
  }

  val customFeeder = Iterator.continually(Map(
    "gameId" -> idNumbers.next(),
    "name" -> ("Game-" + randomString(5)),
    "releaseDate" -> getRandomDate(LocalDate.now(),rnd),
    "reviewScore" -> rnd.nextInt(100),
    "category" -> ("Category-" + randomString(6)),
    "rating" -> ("Rating-" + randomString(4))
  ))


  def createNewGame(): ChainBuilder = {
    repeat(10,counterName = "counter") {
      feed(customFeeder)
        .exec(http("Counter #{counter}: Create new game - #{name}")
        .post("/videogame")
        .header("Authorization",s"Bearer #{jwtToken}")
        .body(ElFileBody("bodies/newGameTemplate.json")).asJson
        .check(bodyString.saveAs("responseBody")))
        .exec {session => println(session("responseBody")); session}
        .pause(2)
    }
  }

  val scn = scenario("Complex Custom Feeder")
    .exec(authenticate())
    .exec(createNewGame())


  // Load Scenario
  setUp(
    scn.inject(atOnceUsers(users = 1))
  ).protocols(httpProtocol)

}

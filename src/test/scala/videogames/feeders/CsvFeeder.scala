package videogames.feeders

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

class CsvFeeder extends Simulation {

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


  val csvFeeder = csv("data/gameCsvFile.csv").circular


  def getSpecifcVideoGame(): ChainBuilder = {
    repeat(10,counterName = "counter") {
      feed(csvFeeder)
        .exec(http("Counter #{counter}: Get vide game #{gameName}")
        .get("/videogame/#{gameId}")
        .check(status.is(200))
        .check(jsonPath("$.name").is("#{gameName}")))
        .pause(2)
    }
  }

  val scn = scenario("Csv Feeder Test")
    .exec(authenticate())
    .exec(getSpecifcVideoGame())



  // Load Scenario
  setUp(
    scn.inject(atOnceUsers(users = 1))
  ).protocols(httpProtocol)



}

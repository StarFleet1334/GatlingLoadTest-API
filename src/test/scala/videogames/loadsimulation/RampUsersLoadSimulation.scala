package videogames.loadsimulation

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

class RampUsersLoadSimulation extends Simulation{

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

  def getAllVideoGames(): ChainBuilder = {
    exec(
      http("Get all video games")
        .get("/videogame")
    )
  }

  def getSpecificGame(): ChainBuilder = {
    exec(
      http("Get specific game")
        .get("/videogame/2")
    )
  }

  val scn = scenario("Complex Custom Feeder")
    .exec(authenticate())
    .pause(2)
    .exec(getSpecificGame())
    .pause(2)
    .exec(getAllVideoGames())

  // Load Scenario
  setUp(
    scn.inject(
      nothingFor(5),
      constantUsersPerSec(10).during(10),
      rampUsersPerSec(1).to(5).during(20)
    )
  ).protocols(httpProtocol)

}

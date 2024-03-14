package videogames.scriptfundamentals


import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

class CodeReuse extends Simulation {

  // Http Configuration
  val httpProtocol = http.baseUrl(url = "https://videogamedb.uk/api")
    .acceptHeader(value = "application/json")

  def getAllVideoGames(): ChainBuilder = {
    repeat(3) {
      exec(http("Get all video games")
        .get("/videogame")
        .check(status.is(200)))
    }
  }

  def getSpecificGame(): ChainBuilder = {
    repeat(3,"counter") {
      exec(http("Get Specific game: #{counter}")
        .get("/videogame/1")
        .check(status.in(200 to 210)))
    }
  }

  val scn = scenario("Code reuse")
    .exec(getAllVideoGames())
    .pause(5)
    .exec(getSpecificGame())
    .pause(5)
    .exec(getAllVideoGames())


  // Load Scenario
  setUp(
    scn.inject(atOnceUsers(users = 1))
  ).protocols(httpProtocol)

}

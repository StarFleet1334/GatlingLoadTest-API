package videogames.scriptfundamentals

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class DebugSessionVariables extends Simulation{

  // Http Configuration
  val httpProtocol = http.baseUrl(url = "https://videogamedb.uk/api")
    .acceptHeader(value = "application/json")


  // Scenario Definition
  val scn = scenario(name = "Vide Game DB - 3 calls")
    .exec(http(requestName = "Get all vide games firs time")
      .get("/videogame")
      .check(status.is(200))
      .check(jsonPath("$[1].id").saveAs("videoGameId"))
      .check(jsonPath("$[1].name").saveAs("videoGameName")))
    .exec {session => println(session); session}
    .pause(5)
    .exec(http(requestName = "Video game: #{videoGameName}")
      .get("/videogame/#{videoGameId}")
      .check(status.in(200 to 210))
      .check(bodyString.saveAs("responseBody")))
    .pause(1,10)
    .exec { session => println(session("responseBody").as[String]); session}
    .exec(http(requestName = "Get all vide games second time")
      .get("/videogame")
      .check(status.not(404),status.not(500)))
    .pause(5)


  // Load Scenario
  setUp(
    scn.inject(atOnceUsers(users = 1))
  ).protocols(httpProtocol)

}

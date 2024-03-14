package videogames.scriptfundamentals

import io.gatling.core.Predef._
import io.gatling.http.Predef._


class AddPauseTime extends Simulation{
  // Http Configuration
  val httpProtocol = http.baseUrl(url = "https://videogamedb.uk/api")
    .acceptHeader(value = "application/json")


  // Scenario Definition
  val scn = scenario(name = "Vide Game DB - 3 calls")
    .exec(http(requestName = "Get all vide games firs time")
      .get("/videogame"))
    .pause(5)
    .exec(http(requestName = "Get single video game")
    .get("/videogame/1"))
    .pause(1,10)
    .exec(http(requestName = "Get all vide games second time")
      .get("/videogame"))


  // Load Scenario
  setUp(
    scn.inject(atOnceUsers(users = 1))
  ).protocols(httpProtocol)

}

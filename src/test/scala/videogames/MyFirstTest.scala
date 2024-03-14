package videogames

import io.gatling.core.Predef._
import io.gatling.http.Predef._


class MyFirstTest extends Simulation{

  // Http Configuration
  val httpProtocol = http.baseUrl(url = "https://videogamedb.uk/api")
    .acceptHeader(value = "application/json")


  // Scenario Definition
  val scn = scenario(name = "My First Test")
    .exec(http(requestName = "Get all vide games")
    .get("/videogame"))


  // Load Scenario
  setUp(
    scn.inject(atOnceUsers(users = 1))
  ).protocols(httpProtocol)



}

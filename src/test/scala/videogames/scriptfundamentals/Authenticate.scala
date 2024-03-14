package videogames.scriptfundamentals

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._


class Authenticate extends Simulation{
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

  def createnewGame(): ChainBuilder = {
    exec(http("Create new game")
    .post("/videogame")
      .header("Authorization","Bearer #{jwtToken}")
    .body(StringBody(
      "{\n  \"category\": \"Platform\",\n  \"name\": \"Mario\",\n  \"rating\": \"Mature\",\n  \"releaseDate\": \"2012-05-04\",\n  \"reviewScore\": 85\n}"
    ))
    .check(status.is(200)))
      .pause(5)
  }

  def getAllVideoGames(): ChainBuilder = {
      exec(http("Get all video games")
        .get("/videogame")
        .check(status.is(200))
        .check(bodyString.saveAs("responseBody")))
        .exec {session => println(session("responseBody")); session}
  }


  val scn = scenario("Authenticate")
    .exec(authenticate())
    .exec(createnewGame())
    .exec(getAllVideoGames())




  // Load Scenario
  setUp(
    scn.inject(atOnceUsers(users = 1))
  ).protocols(httpProtocol)


}

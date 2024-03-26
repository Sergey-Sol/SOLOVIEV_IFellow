package steps;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class RAM {
        public Response getMortyCharacter(String baseUri, String postUri, int statusCode, String name) {
        Response mortyResponse = given()
                .baseUri(baseUri)
                .queryParam("name", name)
                .when()
                .get(postUri)
                .then()
                .statusCode(statusCode)
                .body("results[0].name", equalTo("Morty Smith"))
                .extract().response();
        return mortyResponse;
    }
    public Response getLastEpisode(Response mortyResponse, int statusCode){
        String lastEpisodeUrl = mortyResponse.jsonPath().getString("results[0].episode[-1]");
        Response lastEpisodeResponse = given()
                .get(lastEpisodeUrl)
                .then()
                .statusCode(statusCode)
                .body("name", equalTo("Rickmurai Jack"))
                .extract().response();
        return lastEpisodeResponse;
    }

    public Response getLastCharacter(Response lastEpisodeResponse, int statusCode) {
        String lastCharacterUrl = lastEpisodeResponse.jsonPath().getString("characters[-1]");
        Response lastCharacterResponse = given()
                .get(lastCharacterUrl)
                .then()
                .statusCode(statusCode)
                .body("name", equalTo("Young Jerry"))
                .extract().response();
        return lastCharacterResponse;
    }
}



package pages;

import io.restassured.response.Response;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class RestSteps {

    public void сompareСharacters(String baseUri, int statusCode){
        Response mortyResponse = given()
                .baseUri(baseUri)
                .queryParam("name", "Morty Smith")
                .when()
                .get("/character")
                .then()
                .statusCode(statusCode)
                .extract().response();
        String lastEpisodeUrl = mortyResponse.jsonPath().getString("results[0].episode[-1]");

        Response lastEpisodeResponse = given()
                .get(lastEpisodeUrl)
                .then()
                .statusCode(statusCode)
                .extract().response();
        String lastCharacterUrl = lastEpisodeResponse.jsonPath().getString("characters[-1]");

        Response lastCharacterResponse = given()
                .get(lastCharacterUrl)
                .then()
                .statusCode(statusCode)
                .extract().response();
        String lastCharacterLocation = lastCharacterResponse.jsonPath().getString("location.name");
        String lastCharacterRace = lastCharacterResponse.jsonPath().getString("species");

        Assert.assertNotEquals(lastCharacterLocation, mortyResponse.jsonPath().getString("results[0].location.name"));
        Assert.assertEquals(lastCharacterRace, mortyResponse.jsonPath().getString("results[0].species"));
    }
    public void createUser(String baseUri, String postUri, String body, int statusCode){
        given()
                .header("Content-type", "application/json")
                .header("Charset", "UTF-8")
                .baseUri(baseUri)
                .body(body)
                .when()
                .post(postUri)
                .then()
                .statusCode(statusCode)
                .body("name", equalTo("Tomato"))
                .body("job", equalTo("Eat maket"));
    }
}
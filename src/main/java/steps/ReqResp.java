package steps;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class ReqResp {
    public Response createUser(String baseUri, String postUri, String body, int statusCode){
        Response response = given()
                .header("Content-type", "application/json")
                .header("Charset", "UTF-8")
                .baseUri(baseUri)
                .body(body)
                .when()
                .post(postUri)
                .then()
                .statusCode(statusCode)
                .body("name", equalTo("Bob"))
                .body("job", equalTo("Mechanic"))
                .extract().response();
        return response;
    }
}

package testClasses;

import hooks.WebHooks;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import pages.RestSteps;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReqRespTest extends WebHooks{
    @Test
    public void createUserTest() throws IOException {

        JSONObject body = new JSONObject(new String(Files.readAllBytes(Paths.get("src/test/resources/json/paren.json"))));

        new RestSteps()
                .reqRespTest("https://reqres.in", "/api/users", body.toString(), 201);
    }
}
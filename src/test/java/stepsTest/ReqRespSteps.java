package stepsTest;

import hooks.WebHooks;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import readConf.ConfigProvider;
import steps.ReqResp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReqRespSteps extends WebHooks{
    private Response response;
    private final String baseUri = ConfigProvider.baseUrlReqresp;
    private final String postUri = ConfigProvider.postUrlReqresp;
    int statusCode;
    private ReqResp reqResp;


    @Дано("^Базовый URI, путь для создания пользователя, тело запроса, код ответа страницы: (\\d+)$")
    public void setPostRequest(int statusCode) {
        this.statusCode = statusCode;
        this.reqResp = new ReqResp();
    }
    @Когда("^Отправляем POST-запрос$")
    public void sendPostRequest() throws IOException {
        JSONObject body = new JSONObject(new String(Files.readAllBytes(Paths.get("src/test/resources/json/user.json"))));
        response = reqResp.createUser(baseUri, postUri, body.toString(), statusCode);
        }
    @Тогда("^Статус код-ответа для страницы должен быть (\\d+)$")
    public void checkStatusCode(int statusCode) {
        Assertions.assertEquals(statusCode, response.statusCode());
    }

    @И("^Имя пользователя в ответе должно быть (\\w+)$")
    public void checkUserName(String name) {
        Assertions.assertEquals(name, response.jsonPath().getString("name"));
    }

    @И("^Работа пользователя в ответе должна быть (\\w+)$")
    public void checkUserJob(String job) {
        Assertions.assertEquals(job, response.jsonPath().getString("job"));
    }
}
package stepsTest;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import readConf.ConfigProvider;
import steps.RAM;

public class GetMortySteps {
    private Response response;
    private final String baseUri = ConfigProvider.baseUrlRAM;
    private final String postUri = ConfigProvider.postUrlRAM;
    int statusCode;
    String name;
    private RAM ram;

    @Дано("^Базовый URI, путь до страницы с персонажами, код ответа страницы (\\d+), имя персонажа ([\\w\\s]+)$")
    public void setGetRequest(int statusCode, String name) {
        this.name = name;
        this.statusCode = statusCode;
        this.ram = new RAM();
    }
    @Когда("^Отправляем GET-запрос$")
    public void sendGetRequest(){
        response = ram.getMortyCharacter(baseUri, postUri, statusCode, name);
    }
    @Тогда("^Статус код-ответа для персонажа Морти должен быть (\\d+)")
    public void checkStatusCode(int statusCode) {
        Assertions.assertEquals(statusCode, response.statusCode());
    }
    @И("^Имя персонажа в ответе должно быть ([\\w\\s]+)$")
    public void checkCharacterName(String name) {
        Assertions.assertEquals(name, response.jsonPath().getString("results[0].name"));
    }
}

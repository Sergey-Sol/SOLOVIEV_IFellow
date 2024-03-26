package stepsTest;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import readConf.ConfigProvider;
import steps.RAM;

public class GetLastEpisodeSteps {
    private Response response;
    private Response mortyResponse;
    private final String baseUri = ConfigProvider.baseUrlRAM;
    private final String postUri = ConfigProvider.postUrlRAM;
    int statusCode;
    private RAM ram;
    @Дано("^Ссылка на последний эпизод, в котором появлялся Морти, статус-код ([\\w\\s]+)$")
    public void setGetRequest(int statusCode) {
        this.statusCode = statusCode;
        this.ram = new RAM();
        this.mortyResponse = ram.getMortyCharacter(baseUri, postUri, statusCode, "Morty Smith");
    }
    @Когда("^Отправляем GET-запрос на последний эпизод$")
    public void sendGetRequest(){
        response = ram.getLastEpisode(mortyResponse, statusCode);
    }
    @Тогда("^Статус код-ответа для страницы последнего эпизода должен быть (\\d+)")
    public void checkStatusCode(int statusCode) {
        Assertions.assertEquals(statusCode, response.statusCode());
    }
    @И("^Название эпизода должно быть ([\\w\\s]+)$")
    public void checkEpisodeName(String name) {
        Assertions.assertEquals(name, response.jsonPath().getString("name"));
    }
}
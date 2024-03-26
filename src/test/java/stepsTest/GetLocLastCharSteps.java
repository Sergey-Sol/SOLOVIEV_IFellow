package stepsTest;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import readConf.ConfigProvider;
import steps.RAM;

public class GetLocLastCharSteps {
    private Response response;
    private Response mortyResponse;
    private Response lastEpisodeResponse;
    private Response lastCharacterResponse;
    private final String baseUri = ConfigProvider.baseUrlRAM;
    private final String postUri = ConfigProvider.postUrlRAM;
    int statusCode;
    private RAM ram;

    @Дано("^Сылка на локацию последнего персонажа, статус-код ([\\w\\s]+)$")
    public void setGetRequest(int statusCode) {
        this.statusCode = statusCode;
        this.ram = new RAM();
        this.mortyResponse = ram.getMortyCharacter(baseUri, postUri, statusCode, "Morty Smith");
        this.lastEpisodeResponse = ram.getLastEpisode(mortyResponse, statusCode);
        this.lastCharacterResponse = ram.getLastCharacter(lastEpisodeResponse, statusCode);
    }
    @Когда("^Отправляем GET-запрос на локацию последнего персонажа$")
    public void sendGetRequest(){
        response = ram.getLastCharacter(lastEpisodeResponse, statusCode);
    }
    @Тогда("^Статус код-ответа для локации последнего персонажа должен быть (\\d+)")
    public void checkStatusCode(int statusCode) {
        Assertions.assertEquals(statusCode, response.statusCode());
    }
    @И("^Локация последнего персонажа НЕ должна быть, как у Морти")
    public void checkCharactersLocation() {
        Assert.assertNotEquals(lastCharacterResponse.jsonPath().getString("location.name"), mortyResponse.jsonPath().getString("results[0].location.name"));
    }
}

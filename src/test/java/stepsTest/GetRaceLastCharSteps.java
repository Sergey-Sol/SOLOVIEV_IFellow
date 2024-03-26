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

public class GetRaceLastCharSteps {
    private Response response;
    private Response mortyResponse;
    private Response lastEpisodeResponse;
    private Response lastCharacterResponse;
    private final String baseUri = ConfigProvider.baseUrlRAM;
    private final String postUri = ConfigProvider.postUrlRAM;
    int statusCode;
    private RAM ram;

    @Дано("^Ссылка на расу последнего персонажа, статус-код ([\\w\\s]+)$")
    public void setGetRequest(int statusCode) {
        this.statusCode = statusCode;
        this.ram = new RAM();
        this.mortyResponse = ram.getMortyCharacter(baseUri, postUri, statusCode, "Morty Smith");
        this.lastEpisodeResponse = ram.getLastEpisode(mortyResponse, statusCode);
        this.lastCharacterResponse = ram.getLastCharacter(lastEpisodeResponse, statusCode);
    }
    @Когда("^Отправляем GET-запрос на расу последнего персонажа$")
    public void sendGetRequest(){
        response = ram.getLastCharacter(lastEpisodeResponse, statusCode);
    }
    @Тогда("^Статус код-ответа для расы последнего персонажа должен быть (\\d+)")
    public void checkStatusCode(int statusCode) {
        Assertions.assertEquals(statusCode, response.statusCode());
    }
    @И("^Раса последнего персонажа должна быть, как у Морти")
    public void checkCharacterName() {
        Assert.assertEquals(lastCharacterResponse.jsonPath().getString("species"), mortyResponse.jsonPath().getString("results[0].species"));
    }
}

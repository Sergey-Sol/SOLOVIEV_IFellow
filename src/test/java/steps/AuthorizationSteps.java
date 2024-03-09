package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import pages.AuthorizationPage;

public class AuthorizationSteps {

    String username;
    String password;
    private AuthorizationPage auth;

    @Дано("^логин и пароль: (AT\\d{1,3}) и (\\w+)$")
    public void setLogPass(String username, String password) {
        this.username = username;
        this.password = password;
        this.auth = new AuthorizationPage();
    }

    @Когда("^вводим логин и пароль")
    public void inputLogPass() {
        auth.inputLogPass(username, password);
    }

    @И("^нажимаем кнопку авторизации")
    public void clickloginButton() {
        auth.clickloginButton();
    }

    @Тогда("^на открывшейся странице должен отображаться правильный логин (AT\\d{1,3})$")
    public void checkLogin(String username){
        Assertions.assertEquals(username, auth.getActualUserName());
    }
}


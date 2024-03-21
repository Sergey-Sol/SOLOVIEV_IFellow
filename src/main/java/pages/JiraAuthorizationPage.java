package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class JiraAuthorizationPage {

    private final SelenideElement inputUserName = $x("//input[@name='os_username']").as("Поле ввода логина");
    private final SelenideElement inputUserPass = $x("//input[@name='os_password']").as("Поле ввода пароля");
    private final SelenideElement loginButton = $x("//input[contains(@value,'Войти')]").as("Кнопка \"Войти\"");

    @Step("Ввод логина \"{username}\" и пароля \"{password}\"")
    public void login(String username, String password) {
        inputUserName.shouldBe(Condition.visible);
        inputUserName.setValue(username);
        inputUserPass.setValue(password);
        loginButton.click();
    }
}



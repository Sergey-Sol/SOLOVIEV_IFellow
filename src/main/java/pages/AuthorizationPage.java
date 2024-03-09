package pages;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selenide.$x;

public class AuthorizationPage {
    private final SelenideElement inputUserName = $x("//input[@name='os_username']");
    private final SelenideElement inputUserPass = $x("//input[@name='os_password']");
    private final SelenideElement loginButton = $x("//input[contains(@value,'Войти')]");
    private final SelenideElement userAfterAuthorization = $x("//a[contains(@title,'Пользовательский')]");

    public void inputLogPass(String username, String password) {
        inputUserName.shouldBe(Condition.visible);
        inputUserName.setValue(username);
        inputUserPass.setValue(password);
    }

    public void clickloginButton() {
        loginButton.shouldBe(Condition.visible);
        loginButton.click();
    }

    public String getActualUserName() {
        return userAfterAuthorization.getAttribute("data-username");
    }
}

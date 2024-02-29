package pages;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class JiraAuthorizationPage {

    private final SelenideElement inputUserName = $x("//input[@name='os_username']");
    private final SelenideElement inputUserPass = $x("//input[@name='os_password']");
    private final SelenideElement loginButton = $x("//input[contains(@value,'Войти')]");

    public JiraDashboardPage login(String username, String password) {
        inputUserName.shouldBe(Condition.visible);
        inputUserName.setValue(username);
        inputUserPass.setValue(password);
        loginButton.click();
        return page(JiraDashboardPage.class);
    }
}



package pages;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class JiraDashboardPage {
    private final SelenideElement userAfterAuthorization = $x("//a[contains(@title,'Пользовательский')]");
    private final SelenideElement projects = $x("//a[text()='Проекты']");
    private final SelenideElement test = $x("//a[text()='Test (TEST)']");

    public String userIsLoggedInAttribute() {
        return userAfterAuthorization.getAttribute("data-username");
    }

    public JiraTestPage goToProjects() {
        projects.shouldBe(Condition.visible);
        projects.click();
        test.click();
        return page(JiraTestPage.class);
    }
}


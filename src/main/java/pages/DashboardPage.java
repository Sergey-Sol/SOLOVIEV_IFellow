package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {
    private final SelenideElement projects = $x("//a[text()='Проекты']");
    private final SelenideElement test = $x("//a[text()='Test (TEST)']");
    private final SelenideElement testPage = $x("//div/child::a[text()='Test']");

    public void goToProjects() {
        projects.shouldBe(Condition.visible);
        projects.click();
        test.click();
        testPage.shouldBe(Condition.visible);
    }

    public String checkPageTitle() {
        testPage.shouldBe(Condition.visible);
        return testPage.getText();
    }
}


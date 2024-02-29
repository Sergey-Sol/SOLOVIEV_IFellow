package pages;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class JiraTaskPage {

    private final SelenideElement taskPage = $x("//h1[text()='TestSelenium']");
    private final SelenideElement make = $x("//span[text()='Сделать']");
    private final SelenideElement fixInVersions = $x("//strong[@title='Исправить в версиях']/following-sibling::span");

    public JiraTaskPage checkTaskStatus(String expectedText) {
        make.shouldBe(Condition.visible)
                .shouldHave(Condition.text(expectedText));
        return this;
    }

    public String checkTaskStatusAssert() {
        return make.getText();
    }

    public JiraTaskPage checkTaskFixInVersions(String expectedText) {
        fixInVersions.shouldBe(Condition.visible)
                .shouldHave(Condition.text(expectedText));
        return this;
    }

    public String checkTaskFixInVersionsAssert() {
        return fixInVersions.getText();
    }

    public String pageIsTask() {
        taskPage.shouldBe(Condition.visible);
        return taskPage.getText();
    }
}

package pages;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class JiraTaskPage {

    private final SelenideElement taskPage = $x("//h1[text()='TestSelenium']").as("Заголовок задачи \"TestSelenium\"");
    private final SelenideElement make = $x("//span[text()='Сделать']").as("Статус задачи \"Сделать\"");
    private final SelenideElement fixInVersions = $x("//strong[@title='Исправить в версиях']/following-sibling::span").as("Значение поля \"Исправить в версиях\"");

    @Step("Проверить статус задачи")
    public void checkTaskStatus(String expectedText) {
        make.shouldBe(Condition.visible)
                .shouldHave(Condition.text(expectedText));
    }

    @Step("Проверить статус задачи для Assert")
    public SelenideElement checkTaskStatusAssert() {
        return make;
    }

    @Step("Проверить поле \"Исправить в версиях:\"")
    public void checkTaskFixInVersions(String expectedText) {
        fixInVersions.shouldBe(Condition.visible)
                .shouldHave(Condition.text(expectedText));
    }

    @Step("Проверить поле \"Исправить в версиях:\" для Assert")
    public SelenideElement checkTaskFixInVersionsAssert() {
        return fixInVersions;
    }

    @Step("Проверить наименование задачи")
    public SelenideElement pageIsTask() {
        taskPage.shouldBe(Condition.visible);
        return taskPage;
    }
}


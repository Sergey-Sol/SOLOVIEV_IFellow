package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class TaskPage {

    private final SelenideElement taskPage = $x("//h1[text()='TestSelenium']");
    private final SelenideElement make = $x("//span[text()='Сделать']");
    private final SelenideElement fixInVersions = $x("//strong[@title='Исправить в версиях']/following-sibling::span");
    private final SelenideElement projects = $x("//a[text()='Проекты']");
    private final SelenideElement insertSearchValue = $x("//input[@id='quickSearchInput']");
    private final SelenideElement searchResult = $x("//span[@aria-tooltip=\"TestSelenium\"]");

    public void quickSearch (String searchValue) {
        projects.shouldBe(Condition.visible);
        insertSearchValue.shouldBe(Condition.visible);
        insertSearchValue.setValue(searchValue);
        searchResult.click();
    }

    public void checkTaskStatus(String expectedText) {
        make.shouldBe(Condition.visible)
                .shouldHave(Condition.text(expectedText));
    }

    public String checkTaskStatusAssert() {
        return make.getText();
    }

    public void checkTaskFixInVersions(String expectedText) {
        fixInVersions.shouldBe(Condition.visible)
                .shouldHave(Condition.text(expectedText));
    }

    public String checkTaskFixInVersionsAssert() {
        return fixInVersions.getText();
    }

    public String pageIsTask() {
        taskPage.shouldBe(Condition.visible);
        return taskPage.getText();
    }
}


package pages;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;
public class JiraTestPage {

    private final SelenideElement testPage = $x("//h1/descendant::a[text()='Test']").as("Заголовок страницы \"Test\"");
    private final SelenideElement tasksCounter = $x("//div[@class='showing']/child::span").as("Счетчик задач");
    private final SelenideElement insertSearchValue = $x("//input[@id='quickSearchInput']").as("Поле ввода быстрого поиска");
    private final SelenideElement searchResult = $x("//span[@aria-tooltip=\"TestSelenium\"]").as("Первый из результатов поиска по запросу \"TestSelenium\"");
    private final SelenideElement createTask = $x("//a[@title=\"Создать новую задачу ( Нажмите 'c' )\"]").as("Кнопка \"Создать новую задачу\"");
    private final SelenideElement inputTaskTheme = $x("//input[@id='summary']").as("Поле ввода темы задачи");
    private final SelenideElement createButton = $x("//input[@value='Создать']").as("Кнопка \"Создать\"");
    private final SelenideElement inputTaskDescription = $x("//textarea[@name='description']").as("Поле ввода описания задачи");
    private final SelenideElement checkNewTask = $x("//a[@class='issue-created-key issue-link']").as("Ключ создания задачи");
    private final SelenideElement projects = $x("//a[text()='Проекты']").as("Вкладка \"Проекты\"");

    @Step("Проверить title страницы - \"{expectedText}\"")
    public void checkPageTitle(String expectedText) {
        testPage.shouldBe(Condition.visible)
        .shouldHave(Condition.text(expectedText));
    }

    @Step("Осуществить быстрый поиск по значению \"{searchValue}\"")
    public JiraTaskPage quickSearch (String searchValue) {
        projects.shouldBe(Condition.visible);
        insertSearchValue.shouldBe(Condition.visible);
        insertSearchValue.setValue(searchValue);
        searchResult.click();
        return page(JiraTaskPage.class);
    }

    @Step("Проверить title страницы для Assert")
    public SelenideElement checkPageTitleAssert() {
        return testPage;
    }

    @Step("Получить значение счетчика заданий")
    public int getTasksCountValue() {
        String tasksCountText = tasksCounter.getText();
        String[] parts = tasksCountText.split(" ");
        return Integer.parseInt(parts[2]);
    }

    @Step("Создать новое задание с темой \"{taskTheme}\" и описанием \"{description}\"")
    public void createNewTask(String taskTheme, String description) {
        createTask.click();
        inputTaskTheme.shouldBe(Condition.visible);
        inputTaskTheme.setValue(taskTheme);
        inputTaskDescription.setValue(description);
        createButton.click();
        checkNewTask.shouldBe(Condition.visible);
    }
}

package pages;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
public class JiraReportedByMePage {
    private final SelenideElement tasks = $x("//a[text()='Задачи']").as("Вкладка \"Задачи\"");
    private final SelenideElement reportedByMe = $x("//a[text()='Сообщенные мной']").as("Кнопка \"Сообщенные мной\"");
    private final SelenideElement myLatestTask = $x("//li[@class='focused']").as("Ссылка на последнюю задачу");
    private final SelenideElement taskStatus = $x("//span[@data-tooltip]").as("Статус задачи");
    private final SelenideElement atWorkButton = $x("//span[starts-with(text(),'В работе')]").as("Кнопка \"В работе\"");
    private final SelenideElement atWork = $x("//span[@id=\"status-val\"]/child::span[starts-with(text(),'В работе')]").as("Статус \"В работе\"");
    private final SelenideElement buisnessProcessButton = $x("//span[starts-with(text(),'Бизнес-процесс')]").as("Кнопка \"Бизнес-процесс\"");
    private final SelenideElement doneButton = $x("//span[starts-with(text(),'Выполнено')]").as("Кнопка \"Выполнено\"");
    private final SelenideElement titlePage = $x("//h1[text()='Сообщенные мной']").as("Title страницы \"Сообщенные мной\"");
    private final SelenideElement done = $x("//span[@id=\"status-val\"]/child::span[starts-with(text(),'Готово')]").as("Статус \"Готово\"");

    @Step("Переход на страницу созданной мной задачи")
    public void goToMyNewTask() {
        tasks.shouldBe(Condition.visible);
        tasks.click();
        reportedByMe.click();
        myLatestTask.shouldBe(Condition.visible);
        myLatestTask.click();
    }

    @Step("Перевод задачи в статус \"В РАБОТЕ\"")
    public void atWork() {
        titlePage.shouldBe(Condition.visible);
        atWorkButton.click();
        atWork.shouldBe(Condition.visible);
    }

    @Step("Получение статуса задачи")
    public SelenideElement checkStatusTask() {
        return taskStatus;
    }

    @Step("Перевод задачи в статус \"ГОТОВО\"")
    public void done() {
        titlePage.shouldBe(Condition.visible);
        buisnessProcessButton.shouldBe(Condition.visible);
        buisnessProcessButton.click();
        doneButton.shouldBe(Condition.visible);
        doneButton.click();
        done.shouldBe(Condition.visible);
    }
}
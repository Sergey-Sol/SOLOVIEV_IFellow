package pages;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;
public class JiraReportedByMe {
    private final SelenideElement tasks = $x("//a[text()='Задачи']");
    private final SelenideElement reportedByMe = $x("//a[text()='Сообщенные мной']");
    private final SelenideElement myLatestTask = $x("//li[@class='focused']");
    private final SelenideElement taskStatus = $x("//span[@data-tooltip]");
    private final SelenideElement atWorkButton = $x("//span[starts-with(text(),'В работе')]");
    private final SelenideElement atWork = $x("//span[@id=\"status-val\"]/child::span[starts-with(text(),'В работе')]");
    private final SelenideElement buisnessProcessButton = $x("//span[starts-with(text(),'Бизнес-процесс')]");
    private final SelenideElement doneButton = $x("//span[starts-with(text(),'Выполнено')]");
    private final SelenideElement titlePage = $x("//h1[text()='Сообщенные мной']");
    private final SelenideElement done = $x("//span[@id=\"status-val\"]/child::span[starts-with(text(),'Готово')]");

    public void goToMyNewTask() {
        tasks.shouldBe(Condition.visible);
        tasks.click();
        reportedByMe.click();
        myLatestTask.shouldBe(Condition.visible);
        myLatestTask.click();
    }

    public void atWork() {
        titlePage.shouldBe(Condition.visible);
        atWorkButton.click();
        atWork.shouldBe(Condition.visible);
    }

    public String checkStatusTask() {
        return taskStatus.getText();
    }

    public void done() {
        titlePage.shouldBe(Condition.visible);
        buisnessProcessButton.shouldBe(Condition.visible);
        buisnessProcessButton.click();
        doneButton.shouldBe(Condition.visible);
        doneButton.click();
        done.shouldBe(Condition.visible);
    }
}
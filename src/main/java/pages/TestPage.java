package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class TestPage {
    private final SelenideElement tasksCounter = $x("//div[@class='showing']/child::span");
    private final SelenideElement createTask = $x("//a[@title=\"Создать новую задачу ( Нажмите 'c' )\"]");
    private final SelenideElement inputTaskTheme = $x("//input[@id='summary']");
    private final SelenideElement createButton = $x("//input[@value='Создать']");
    private final SelenideElement inputTaskDescription = $x("//textarea[@name='description']");
    private final SelenideElement checkNewTask = $x("//a[@class='issue-created-key issue-link']");

    public int getTasksCountValue() {
        String tasksCountText = tasksCounter.getText();
        String[] parts = tasksCountText.split(" ");
        return Integer.parseInt(parts[2]);
    }

    public void createNewTask(String taskTheme, String description) {
        createTask.click();
        inputTaskTheme.shouldBe(Condition.visible);
        inputTaskTheme.setValue(taskTheme);
        inputTaskDescription.setValue(description);
        createButton.click();
        checkNewTask.shouldBe(Condition.visible);
    }
}


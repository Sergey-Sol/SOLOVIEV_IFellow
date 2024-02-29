package pages;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;
public class JiraTestPage {

    private final SelenideElement testPage = $x("//h1[text()='TEST']");
    private final SelenideElement tasksCounter = $x("//div[@class='showing']/child::span");
    private final SelenideElement insertSearchValue = $x("//input[@id='quickSearchInput']");
    private final SelenideElement searchResult = $x("//span[@aria-tooltip=\"TestSelenium\"]");
    private final SelenideElement createTask = $x("//a[@title=\"Создать новую задачу ( Нажмите 'c' )\"]");
    private final SelenideElement inputTaskTheme = $x("//input[@id='summary']");
    private final SelenideElement createButton = $x("//input[@value='Создать']");
    private final SelenideElement inputTaskDescription = $x("//textarea[@name='description']");
    private final SelenideElement checkNewTask = $x("//a[@class='issue-created-key issue-link']");
    private final SelenideElement projects = $x("//a[text()='Проекты']");

    public void checkPageTitle(String expectedText) {
        testPage.shouldBe(Condition.visible)
        .shouldHave(Condition.text(expectedText));
    }

    public JiraTaskPage quickSearch (String searchValue) {
        projects.shouldBe(Condition.visible);
        insertSearchValue.shouldBe(Condition.visible);
        insertSearchValue.setValue(searchValue);
        searchResult.click();
        return page(JiraTaskPage.class);
    }

    public String checkPageTitleAssert() {
        return testPage.getText();
    }

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

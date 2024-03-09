package steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import pages.AuthorizationPage;
import pages.DashboardPage;
import pages.TestPage;

public class CounterSteps {
    String username;
    String password;
    String taskTheme;
    String taskDescription;
    int initialTasksCount;
    int updatedTasksCount;
    private AuthorizationPage auth;
    private DashboardPage dash;
    private TestPage test;

    @Дано("^данные для авторизации, тема таски, описание таски: (AT\\d{1,3}), (\\w+), ([а-яА-ЯёЁ_]+), ([а-яА-ЯёЁ]+)$")
    public void setLogPass(String username, String password, String taskTheme, String taskDescription) {
        this.username = username;
        this.password = password;
        this.taskTheme = taskTheme;
        this.taskDescription = taskDescription;
        this.auth = new AuthorizationPage();
        this.dash = new DashboardPage();
        this.test = new TestPage();
    }

    @Когда("^Проходим авторизацию, идем на страницу проектов, запоминаем значение счетчика")
    public void authorization() {
        auth.inputLogPass(username, password);
        auth.clickloginButton();
        dash.goToProjects();
        initialTasksCount = test.getTasksCountValue();
    }

    @И("^создаем новый баг и запоминаем новое значение счетчика")
    public void createNewBug() {
        test.createNewTask(taskTheme, taskDescription);
        Selenide.refresh();
        dash.checkPageTitle();
        updatedTasksCount = test.getTasksCountValue();
    }

    @Тогда("^значение счетчика должно увеличиться на 1")
    public void checkCounter(){
        Assertions.assertEquals(initialTasksCount, updatedTasksCount - 1);
    }
}

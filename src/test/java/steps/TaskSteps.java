package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import pages.AuthorizationPage;
import pages.TaskPage;

public class TaskSteps {
    String username;
    String password;
    String taskname;
    private AuthorizationPage auth;
    private TaskPage task;

    @Дано("^логин, пароль, наименование задачи: (AT\\d{1,3}), (\\w+), (\\w+)$")
    public void setLogPass(String username, String password, String taskname) {
        this.username = username;
        this.password = password;
        this.taskname = taskname;
        this.auth = new AuthorizationPage();
        this.task = new TaskPage();
    }

    @Когда("^Проходим авторизацию и переходим на страницу задачи")
    public void authorizationAndGoToTask() {
        auth.inputLogPass(username, password);
        auth.clickloginButton();
        task.quickSearch(taskname);
    }

    @Тогда("^заголовок страницы должен быть (\\w+)$")
    public void checkTitlePage(String taskname){
        Assertions.assertEquals(taskname, task.pageIsTask());
    }
}

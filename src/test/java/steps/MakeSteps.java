package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import pages.AuthorizationPage;
import pages.TaskPage;

public class MakeSteps {
    String username;
    String password;
    String taskname;
    String taskstatus;
    private AuthorizationPage auth;
    private TaskPage task;

    @Дано("^логин, пароль, наименование задачи, статус задачи: (AT\\d{1,3}), (\\w+), (\\w+), ([а-яА-ЯёЁ]+)$")
    public void setLogPass(String username, String password, String taskname, String taskstatus) {
        this.username = username;
        this.password = password;
        this.taskname = taskname;
        this.taskstatus = taskstatus;
        this.auth = new AuthorizationPage();
        this.task = new TaskPage();
    }

    @Когда("^Проходим авторизацию, переходим на страницу задачи и смотрим статус")
    public void checkStatus() {
        auth.inputLogPass(username, password);
        auth.clickloginButton();
        task.quickSearch(taskname);
        task.checkTaskStatus(taskstatus);
    }

    @Тогда("^Статус задачи должен быть ([а-яА-ЯёЁ]+)$")
    public void checkTitlePage(String taskstatus){
        Assertions.assertEquals(taskstatus, task.checkTaskStatusAssert());
    }
}


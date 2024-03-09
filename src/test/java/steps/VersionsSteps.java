package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import pages.AuthorizationPage;
import pages.TaskPage;

public class VersionsSteps {
    String username;
    String password;
    String taskname;
    String taskfield;
    private AuthorizationPage auth;
    private TaskPage task;

    @Дано("^данные для авторизации, наименование задачи, поле задачи (AT\\d{1,3}), (\\w+), (\\w+), ([\\w. ]+)$")
    public void setLogPassforFix(String username, String password, String taskname, String taskfield) {
        this.username = username;
        this.password = password;
        this.taskname = taskname;
        this.taskfield = taskfield;
        this.auth = new AuthorizationPage();
        this.task = new TaskPage();
    }

    @Когда("^проходим авторизацию, переходим на страницу задачи и смотрим значение поля 'Исправить в версиях'")
    public void checkField() {
        auth.inputLogPass(username, password);
        auth.clickloginButton();
        task.quickSearch(taskname);
        task.checkTaskFixInVersions(taskfield);
    }

    @Тогда("^значение поля должно быть ([\\w. ]+)$")
    public void checkFieldFix(String taskfield){
        Assertions.assertEquals(taskfield, task.checkTaskFixInVersionsAssert());
    }
}

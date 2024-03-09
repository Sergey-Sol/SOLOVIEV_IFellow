package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import pages.AuthorizationPage;
import pages.ReportedPage;

public class WorkSteps {
    String username;
    String password;
    private AuthorizationPage auth;
    private ReportedPage rep;

    @Дано("^данные для авторизации, статус задачи: (AT\\d{1,3}), (\\w+)$")
    public void setLogPassStatus(String username, String password) {
        this.username = username;
        this.password = password;
        this.auth = new AuthorizationPage();
        this.rep = new ReportedPage();
    }

    @Когда("^проходим авторизацию, переходим на страницу задачи и меняем статус на 'В работе'")
    public void setStatus() {
        auth.inputLogPass(username, password);
        auth.clickloginButton();
        rep.goToMyNewTask();
        rep.atWork();
    }

    @Тогда("^статус задачи должен быть 'В РАБОТЕ' ([а-яА-ЯёЁ\\s]+)$")
    public void checkTitlePage(String taskstatus){
        Assertions.assertEquals(taskstatus, rep.checkStatusTask());
    }
}

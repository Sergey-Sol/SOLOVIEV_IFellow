package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import pages.AuthorizationPage;
import pages.DashboardPage;

public class DashboardSteps {

    String username;
    String password;
    private AuthorizationPage auth;
    private DashboardPage dash;

    @Дано("^логин, пароль, наименование проекта: (AT\\d{1,3}), (\\w+)$")
    public void setLogPass(String username, String password) {
        this.username = username;
        this.password = password;
        this.auth = new AuthorizationPage();
        this.dash = new DashboardPage();
    }

    @Когда("^Проходим авторизацию")
    public void authorization() {
        auth.inputLogPass(username, password);
        auth.clickloginButton();
    }

    @И("^переходим на страницу проекта")
    public void goToProjects(){
        dash.goToProjects();
    }

    @Тогда("^title страницы должен быть (\\w+)$")
    public void checkPageTitle(String pagename){
        Assertions.assertEquals(pagename, dash.checkPageTitle());
    }
}

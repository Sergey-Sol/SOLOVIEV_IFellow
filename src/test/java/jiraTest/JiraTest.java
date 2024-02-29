package jiraTest;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;

public class JiraTest extends WebHooks {

    private final JiraAuthorizationPage JiraAuthorizationPage = new JiraAuthorizationPage();
    private final JiraDashboardPage JiraDashboardPage = new JiraDashboardPage();
    private final JiraTestPage JiraTestPage = new JiraTestPage();
    private final JiraTaskPage JiraTaskPage = new JiraTaskPage();
    private final JiraReportedByMe JiraReportedByMe = new JiraReportedByMe();

    private int initialTasksCount;
    private int updatedTasksCount;

    @Test
    @DisplayName("Проверка авторизации")
    public void jiraAuthorizationTest() {
        String username = "AT9";
        String password = "Qwerty123";
        JiraAuthorizationPage.login(username, password);
        Assertions.assertEquals(username, JiraDashboardPage.userIsLoggedInAttribute());
    }

    @Test
    @DisplayName("Переход на страницу проекта 'TEST'")
    public void goToProjectTest() {
        String username = "AT9";
        String password = "Qwerty123";
        JiraAuthorizationPage.login(username, password)
                .goToProjects()
                .checkPageTitle("TEST");
        Assertions.assertEquals("TEST", JiraTestPage.checkPageTitleAssert());
    }

    @Test
    @DisplayName("Переход на задачу 'TestSelenium'")
    public void quickSearchTest() {
        String username = "AT9";
        String password = "Qwerty123";
        String searchValue = "TestSelenium";
        JiraAuthorizationPage.login(username, password);
        JiraTestPage.quickSearch(searchValue);
        Assertions.assertEquals(searchValue, JiraTaskPage.pageIsTask());
    }

    @Test
    @DisplayName("Проверка статуса задачи 'TestSelenium': 'СДЕЛАТЬ'")
    public void checkTaskStatusTest() {
        String username = "AT9";
        String password = "Qwerty123";
        String searchValue = "TestSelenium";
        JiraAuthorizationPage.login(username, password);
        JiraTestPage.quickSearch(searchValue)
                .checkTaskStatus("СДЕЛАТЬ");
        Assertions.assertEquals("СДЕЛАТЬ", JiraTaskPage.checkTaskStatusAssert());
    }

    @Test
    @DisplayName("Проверка поля: 'Исправить в версиях' в задаче 'TestSelenium'")
    public void checkTaskFixInVersionsTest() {
        String username = "AT9";
        String password = "Qwerty123";
        String searchValue = "TestSelenium";
        JiraAuthorizationPage.login(username, password);
        JiraTestPage.quickSearch(searchValue)
                .checkTaskFixInVersions("Version 2.0");
        Assertions.assertEquals("Version 2.0", JiraTaskPage.checkTaskFixInVersionsAssert());
    }

    @Test
    @DisplayName("Проверка значения счетчика после создания бага")
    public void checkTasksCountTest() {
        JiraAuthorizationPage.login("AT9", "Qwerty123").goToProjects();
        initialTasksCount = JiraTestPage.getTasksCountValue();
        System.out.println(initialTasksCount);
        JiraTestPage.createNewTask("Тема_Соловьев","Описание");
        Selenide.refresh();
        //Selenide.sleep(2000);
        JiraTestPage.checkPageTitle("TEST");
        updatedTasksCount = JiraTestPage.getTasksCountValue();
        System.out.println(updatedTasksCount);
        Assertions.assertEquals(initialTasksCount, updatedTasksCount - 1);
    }

    @Test
    @DisplayName("Перевод задачи в статус 'В РАБОТЕ'")
    public void atWorkStatusTest() {
        JiraAuthorizationPage.login("AT9", "Qwerty123");
        JiraReportedByMe.goToMyNewTask();
        JiraReportedByMe.atWork();
        Assertions.assertEquals("В РАБОТЕ", JiraReportedByMe.checkStatusTask());
    }

    @Test
    @DisplayName("Перевод задачи в статус 'ГОТОВО'")
    public void doneStatusTest() {
        JiraAuthorizationPage.login("AT9", "Qwerty123");
        JiraReportedByMe.goToMyNewTask();
        JiraReportedByMe.done();
        Assertions.assertEquals("ГОТОВО", JiraReportedByMe.checkStatusTask());
    }
}

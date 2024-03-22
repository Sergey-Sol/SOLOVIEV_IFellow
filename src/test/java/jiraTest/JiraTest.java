package jiraTest;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import hooks.WebHooks;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;
import readConf.ConfigProvider;

@DisplayName("JiraTest")
public class JiraTest extends WebHooks {

    private final JiraDashboardPage JiraDashboardPage = new JiraDashboardPage();
    private final JiraTestPage JiraTestPage = new JiraTestPage();
    private final JiraTaskPage JiraTaskPage = new JiraTaskPage();
    private final JiraReportedByMePage JiraReportedByMePage = new JiraReportedByMePage();
    private final String searchValue = ConfigProvider.SEARCHVALUE;

    @Test
    @DisplayName("Проверка авторизации")
    public void jiraAuthorizationTest() {
        JiraDashboardPage.userIsLoggedInAttribute().shouldHave(Condition.attribute("data-username", "AT9"));
    }

    @Test
    @DisplayName("Переход на страницу проекта 'TEST'")
    public void goToProjectTest() {
                JiraDashboardPage.goToProjects()
                .checkPageTitle("TEST");
        JiraTestPage.checkPageTitleAssert().shouldHave(Condition.text("TEST"));
    }

    @Test
    @Feature("Проверка задачи 'TestSelenium'")
    @DisplayName("Переход на задачу 'TestSelenium'")
    public void quickSearchTest() {
        JiraTestPage.quickSearch(searchValue);
        JiraTaskPage.pageIsTask().shouldHave(Condition.text(searchValue));
    }

    @Test
    @Feature("Проверка задачи 'TestSelenium'")
    @DisplayName("Проверка статуса задачи 'TestSelenium': 'СДЕЛАТЬ'")
    public void checkTaskStatusTest() {
        String taskStatus = "СДЕЛАТЬ";
        JiraTestPage.quickSearch(searchValue)
                .checkTaskStatus(taskStatus);
        JiraTaskPage.checkTaskStatusAssert().shouldHave(Condition.text(taskStatus));
    }

    @Test
    @Feature("Проверка задачи 'TestSelenium'")
    @DisplayName("Проверка поля: 'Исправить в версиях' в задаче 'TestSelenium'")
    public void checkTaskFixInVersionsTest() {
        String versionTask = "Version 2.0";
        JiraTestPage.quickSearch(searchValue)
                .checkTaskFixInVersions(versionTask);
        JiraTaskPage.checkTaskFixInVersionsAssert().shouldHave(Condition.text(versionTask));
    }

    @Test
    @DisplayName("Проверка значения счетчика после создания бага")
    public void checkTasksCountTest() {
        int initialTasksCount;
        int updatedTasksCount;
        String taskTheme = "Тема_Соловьев";
        String taskDescription = "Описание";
        JiraDashboardPage.goToProjects();
        initialTasksCount = JiraTestPage.getTasksCountValue();
        JiraTestPage.createNewTask(taskTheme,taskDescription);
        Selenide.refresh();
        JiraTestPage.checkPageTitle("TEST");
        updatedTasksCount = JiraTestPage.getTasksCountValue();
        Assertions.assertEquals(initialTasksCount, updatedTasksCount - 1);
    }

    @Test
    @Feature("Смена статуса задачи")
    @DisplayName("Перевод задачи в статус 'В РАБОТЕ'")
    public void atWorkStatusTest() {
        JiraReportedByMePage.goToMyNewTask();
        JiraReportedByMePage.atWork();
        JiraReportedByMePage.checkStatusTask().shouldHave(Condition.text("В РАБОТЕ"));
    }

    @Test
    @Feature("Смена статуса задачи")
    @DisplayName("Перевод задачи в статус 'ГОТОВО'")
    public void doneStatusTest() {
        JiraReportedByMePage.goToMyNewTask();
        JiraReportedByMePage.done();
        JiraReportedByMePage.checkStatusTask().shouldHave(Condition.text("ГОТОВО"));
    }
}

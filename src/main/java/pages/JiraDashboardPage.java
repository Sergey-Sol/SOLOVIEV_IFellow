package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class JiraDashboardPage {
    private final SelenideElement userAfterAuthorization = $x("//a[contains(@title,'Пользовательский')]").as("Логин авторизованного пользователя");
    private final SelenideElement projects = $x("//a[text()='Проекты']").as("Вкладка \"Проекты\"");
    private final SelenideElement test = $x("//a[text()='Test (TEST)']").as("Ссылка на страницу проекта \"Test\"");

    @Step("Получение значения атрибута пользователя")
    public SelenideElement userIsLoggedInAttribute() {
        return userAfterAuthorization;
    }

    @Step("Переход на страницу проекта \"Test\"")
    public JiraTestPage goToProjects() {
        projects.shouldBe(Condition.visible);
        projects.click();
        test.click();
        return page(JiraTestPage.class);
    }
}


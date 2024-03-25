package hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.JiraAuthorizationPage;
import readConf.ConfigProvider;

public class WebHooks {
    @BeforeEach
     public void initBrowser() {
        Configuration.browser ="chrome";
        Configuration.timeout = 4000;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized", "--disable-notifications");
        ChromeDriver driver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(driver);

        Selenide.open(ConfigProvider.URL);
        JiraAuthorizationPage authorizationPage = new JiraAuthorizationPage();
        authorizationPage.login(ConfigProvider.USERNAME, ConfigProvider.PASSWORD);

        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
    }
    @AfterEach
    public void tearDown() {
        WebDriverRunner.closeWebDriver();
    }
}

package jiraTest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebHooks {

    @BeforeEach
    public void initBrowser() {
        Configuration.browser ="chrome";
        Configuration.timeout = 10000;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized", "--disable-notifications");
        ChromeDriver driver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(driver);

        Selenide.open("https://edujira.ifellow.ru/secure/Dashboard.jspa");
    }
    @AfterEach
    public void tearDown() {
        WebDriverRunner.closeWebDriver();
    }
}

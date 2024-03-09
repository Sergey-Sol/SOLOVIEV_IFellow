package hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebHooks {

    @Before
    public void initBrowser() {
        Configuration.browser ="chrome";
        Configuration.timeout = 10000;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized", "--disable-notifications");
        ChromeDriver driver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(driver);

        Selenide.open("https://edujira.ifellow.ru/secure/Dashboard.jspa");

    }
    @After
    public void tearDown() {
        WebDriverRunner.closeWebDriver();
    }
}

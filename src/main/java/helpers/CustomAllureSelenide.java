package helpers;


import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.extension.TestWatcher;


public class CustomAllureSelenide extends AllureSelenide implements TestWatcher {
/*
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        try {
            Object instance = context.getRequiredTestInstance();
            WebDriver driver = (WebDriver) instance.getClass().getDeclaredField("driver").get(instance);
            Allure.getLifecycle().addAttachment("screenshot", "image/png", "png", ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
            WebDriverRunner.closeWebDriver();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // Обработка исключения
            e.printStackTrace();
        }

    /*public static void enableAllureSelenide() {
        AllureSelenide allureSelenide = new AllureSelenide();
        allureSelenide.screenshots(true);
        allureSelenide.savePageSource(true);
        allureSelenide.enableLogs(LogType.BROWSER, Level.INFO);
    }*/
}

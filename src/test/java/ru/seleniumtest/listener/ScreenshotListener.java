package ru.seleniumtest.listener;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import ru.seleniumtest.core.Driver;
import io.qameta.allure.Attachment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotListener extends TestListenerAdapter {

    private static final Logger log = LoggerFactory.getLogger(ScreenshotListener.class);

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        try {
            byte[] result = saveScreenshot(iTestResult);
        } catch (Exception ex) {
            log.error("Ошибка при создании скриншота!");
        }
        log.error("Тест завершился с ошибкой");
    }

      private static byte[] saveScreenshot(ITestResult iTestResult) throws IOException {

        final Driver driver = Driver.getDriver();
        final String separator = "\\";

        String testName = iTestResult.getName();
        File screenshot = driver.getScreenshotAs(OutputType.FILE);

        String directory = Paths.get(".").toAbsolutePath().normalize().toString()
                + separator + "target" + separator + "surefire-reports";
        File resultScreen = new File(directory
                + separator
                + "failure_screenshots"
                + separator
                + testName + ".png");

        FileUtils.copyFile(screenshot, resultScreen);
        return Files.readAllBytes(resultScreen.toPath());
    }
}

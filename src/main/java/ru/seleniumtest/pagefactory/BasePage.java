package ru.seleniumtest.pagefactory;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.seleniumtest.core.Driver;
import ru.seleniumtest.util.WaitUtil;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

import java.io.ByteArrayInputStream;
import java.io.File;

import static org.openqa.selenium.OutputType.BYTES;


public abstract class BasePage {

    protected Driver driver = Driver.getDriver();
    public WebDriverWait wait = new WebDriverWait(driver, 10);

    protected BasePage() {
        HtmlElementLoader.populatePageObject(this, driver);
    }


    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }


    public abstract void verifyPageLoaded();

}

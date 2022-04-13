package ru.seleniumtest.core;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Augmentable;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.util.List;

@Augmentable
public class Driver extends ChromeDriver implements TakesScreenshot {//RemoteWebDriver->ChromeDriver

    private static final Logger log = LoggerFactory.getLogger(ru.seleniumtest.core.Driver.class);
    private static final ThreadLocal<Driver> DRIVER_INSTANCE = new ThreadLocal<>();


    private static final String ELEMENT_NOT_FOUND_MESSAGE = "Элемент использующий локатор '%s' не найден!";

    public Driver(String remoteUrl, DesiredCapabilities desiredCapabilities) {
        super(desiredCapabilities);
    }

    public static Driver getDriver() {
        return DRIVER_INSTANCE.get();
    }

    public static synchronized Driver setDriver(Driver driver) {
        if (driver == null) {
            DRIVER_INSTANCE.remove();
            return null;
        } else {
            DRIVER_INSTANCE.set(driver);
            return getDriver();
        }
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) {
        //if ((Boolean) getCapabilities().getCapability(CapabilityType.TAKES_SCREENSHOT)) {
        return target.convertFromBase64Png(execute(DriverCommand.SCREENSHOT).getValue().toString());
    }
    //return null;
    // }

    public static Boolean isElementClickable(WebElement webElement) {
        return webElement.isEnabled() && webElement.isDisplayed();
    }

    public void click(WebElement webElement) {
        try {
            webElement.click();
            Thread.sleep(1500);
        } catch (Exception ex) {
            this.jsClick(webElement);
        }
    }

    private void jsClick(final WebElement webElement) {
        final String JAVA_SCRIPT_CLICK = "arguments[0].click();";
        JavascriptExecutor javascriptExecutor = Driver.getDriver();
        javascriptExecutor.executeScript(JAVA_SCRIPT_CLICK, webElement);
    }

    interface Finder{
        WebElement find(String findBy);
    }

    private WebElement _findElement(String findBy, Finder finder){
        try {
            return finder.find(findBy);
        } catch (NoSuchElementException ex) {
            log.error(String.format(ELEMENT_NOT_FOUND_MESSAGE, findBy));
            throw ex;
        }
    }

    @Override
    public WebElement findElementById(String using) {
        return _findElement(using, super::findElementById);
    }

    @Override
    public WebElement findElementByLinkText(String using) {
        return _findElement(using, super::findElementByLinkText);
    }

    @Override
    public WebElement findElementByPartialLinkText(String using) {
        return _findElement(using, super::findElementByPartialLinkText);
    }

    @Override
    public WebElement findElementByTagName(String using) {
        return _findElement(using, super::findElementByTagName);
    }

    @Override
    public WebElement findElementByName(String using) {
        return _findElement(using, super::findElementByName);
    }

    @Override
    public WebElement findElementByClassName(String using) {
        return _findElement(using, super::findElementByClassName);
    }

    @Override
    public WebElement findElementByCssSelector(String using) {
        return _findElement(using, super::findElementByCssSelector);
    }

    @Override
    public WebElement findElementByXPath(String using) {
        return _findElement(using, super::findElementByXPath);
    }

    @Override
    public List<WebElement> findElementsByXPath(String using) {
        try {
            return super.findElementsByXPath(using);
        } catch (NoSuchElementException ex) {
            log.error(String.format(ELEMENT_NOT_FOUND_MESSAGE, using));
            throw ex;
        }
    }
}

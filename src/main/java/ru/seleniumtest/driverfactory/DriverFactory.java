package ru.seleniumtest.driverfactory;


import com.sun.jna.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.seleniumtest.core.Driver;
import ru.seleniumtest.config.TimeoutConfig;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;




public class DriverFactory {

    private static final Logger log = LoggerFactory.getLogger(DriverFactory.class);

    private static final String CHROME_PROPERTY_NAME = "webdriver.chrome.driver";
    private static final String CHROME_DRIVER_WINDOWS = "src\\main\\resources\\webdrivers\\chromedriver.exe";
    private static final String CHROME_DRIVER_LINUX = "src/main/resources/webdrivers/linux/chromedriver";

    private DriverFactory() {
    }


    private static void createDriver(final BrowserType browserType, String url) {

        Driver driver;
        log.info("URL сервера: " + url);
        switch (browserType) {
            case CHROME:
                setSystemVariable(CHROME_PROPERTY_NAME, CHROME_DRIVER_WINDOWS, CHROME_DRIVER_LINUX);
                driver = new Driver(url, BrowserType.CHROME.getDesiredCapabilities());
                break;
            default:
                throw new IllegalStateException("Неизветсный браузер!");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(TimeoutConfig.PAGE_LOAD_TIMEOUT, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().implicitlyWait(TimeoutConfig.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().setScriptTimeout(TimeoutConfig.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
        Driver.setDriver(driver);


    }


    public static void startBrowser(String browser, String url) {
        BrowserType browserType = BrowserType.valueOf(browser);
        DriverFactory.createDriver(browserType, url);
        log.info("{} браузер открывается.", browserType);
    }


    private static void setSystemVariable(String browser, String windowsDriverPath, String linuxDriverPath) {
        if (Platform.isWindows()) {
            log.info("Тесты запускаются на ОС Windows.");
            System.setProperty(browser, windowsDriverPath);
        } else if (Platform.isLinux()) {
            log.info("Тесты запускаются на ОС Linux.");
            System.setProperty(browser, linuxDriverPath);
        } else {
            throw new IllegalStateException("Неизвестная операционная система!");
        }
    }


    public static void finishBrowser() {
        if (Driver.getDriver() != null) {
            try {
                Driver.getDriver().quit();
            } finally {
                Driver.setDriver(null);
            }
        }
    }
}

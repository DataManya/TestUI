package ru.seleniumtest.driverfactory;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;


public class ChromeBrowser implements BrowserCapabilities {

    public static final String FOLDER_FOR_DOWNLOAD = "C:\\WebDrivers\\Download";

    private ChromeBrowser() {
    }

    private static class LazyHolder {
        private static final ChromeBrowser INSTANCE = new ChromeBrowser();
    }

    public static ChromeBrowser getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        ChromeOptions chromeOptions = new ChromeOptions();
        desiredCapabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        desiredCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", FOLDER_FOR_DOWNLOAD);
        chromePrefs.put("safebrowsing.enabled", "true");
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return desiredCapabilities;
    }
}

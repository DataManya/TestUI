package ru.seleniumtest.driverfactory;

import org.openqa.selenium.remote.DesiredCapabilities;


public enum BrowserType {
    CHROME {
        public DesiredCapabilities getDesiredCapabilities() {
            return ChromeBrowser.getInstance().getDesiredCapabilities();
        }
    };

    public abstract DesiredCapabilities getDesiredCapabilities();
}

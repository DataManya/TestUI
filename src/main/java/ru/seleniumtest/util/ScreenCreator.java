package ru.seleniumtest.util;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import ru.seleniumtest.core.Driver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScreenCreator {



    @Attachment(value = "{0}", type = "image/png")
    public static byte[] saveAllureScreenshot(String name, WebDriver driver) {
        return ((TakesScreenshot) (new Augmenter().augment(driver)))
                .getScreenshotAs(OutputType.BYTES);
    }


}


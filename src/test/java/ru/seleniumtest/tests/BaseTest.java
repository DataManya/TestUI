package ru.seleniumtest.tests;

import io.qameta.allure.Allure;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import ru.seleniumtest.core.Driver;
import ru.seleniumtest.dto.User;
import ru.seleniumtest.pagefactory.BasePage;
import ru.seleniumtest.pagefactory.yandexmarket.*;
import ru.seleniumtest.util.ScreenCreator;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

import static ru.seleniumtest.driverfactory.DriverFactory.finishBrowser;
import static ru.seleniumtest.driverfactory.DriverFactory.startBrowser;


public class BaseTest {

    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);
    private String url ="https://www.citilink.ru/";
    /*================================= Страницы =======================================*/
    protected CitilinkMainPage citilinkMainPage;
    protected CatalogPageObject catalogPageObject;
    protected SuccessfullyLoginPage successfullyLoginPage;
    protected CamerasPage camerasPage;
    protected CameraPage cameraPage;
    protected WishList wishList;
    protected HistoryList historyList;
    protected TabletsPage tabletsPage;
    /*==================================================================================*/

    private int index = 0;


    @BeforeSuite(groups = {"All"})
    public void beforeSuite() {
        log.info("Автотесты запускаются");
    }

    @Parameters({"browser"})
    @BeforeMethod(groups = {"All"})
    public void beforeMethod(@Optional("CHROME") String browser) {
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        startBrowser(browser, url);
        navigateTo(url);
        citilinkMainPage = new CitilinkMainPage();
        User user = new User("89173043826", "DwtpkMan5r4y+4x", "Дарья");
        successfullyLoginPage = citilinkMainPage.authorization(user);
        //ScreenCreator.saveAllureScreenshot("Screen5",driver);
        successfullyLoginPage.isOpen(user);
    }

    @AfterMethod(groups = {"All"})
    public void afterMethod() {
        successfullyLoginPage.logOut();
        // ScreenCreator.saveAllureScreenshot("Screen6",driver);
        finishBrowser();
    }


    private static void navigateTo(String url) {
        Driver.getDriver().navigate().to(url);
    }

    protected void stepPrinter() {
        index++;
        log.info(index < 10 ? "Step-0{}" : "Step-{}", index);
    }


}

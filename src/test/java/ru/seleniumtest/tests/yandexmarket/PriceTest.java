package ru.seleniumtest.tests.yandexmarket;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import ru.seleniumtest.listener.ScreenshotListener;
import ru.seleniumtest.tests.BaseTest;
import io.qameta.allure.Description;
import ru.yandex.qatools.allure.annotations.Title;

@Listeners({ScreenshotListener.class})
public class PriceTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(PriceTest.class);

    @Test (
            groups = {"All"},
            dataProvider = "Prices"
    )
    @Title("Price Filter and catalog Check")
    @Description("Price Filter Check")
    public void priceTest_1(Integer price) {
        catalogPageObject = successfullyLoginPage.clickcatalogButton();
        //catalogPageObject = citilinkMainPage.clickcatalogButton();
        tabletsPage = catalogPageObject.clickTablets();
        tabletsPage = tabletsPage.insertPrice(price);
        tabletsPage.checkPrice(price);
    }

    @DataProvider(name = "Prices")
    public static Object[][] createData() {
        return new Object[][] {
                {10000},
                {15000}
        };

    }

}

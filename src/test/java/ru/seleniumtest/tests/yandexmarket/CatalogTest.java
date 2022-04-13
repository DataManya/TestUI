package ru.seleniumtest.tests.yandexmarket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.seleniumtest.listener.ScreenshotListener;
import ru.seleniumtest.pagefactory.yandexmarket.CatalogPageObject;
import ru.seleniumtest.tests.BaseTest;
import io.qameta.allure.Description;

@Listeners({ScreenshotListener.class})
public class CatalogTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(CatalogTest.class);
    @Test (
            groups = {"All"}
    )
    @Description("Directory check")
    public void catalogTest_1() {
        catalogPageObject = successfullyLoginPage.clickcatalogButton();
        // catalogPageObject = citilinkMainPage.clickcatalogButton();
        camerasPage = catalogPageObject.clickCameras();
        camerasPage.beginSearch();
        cameraPage = camerasPage.clickCamera();
        cameraPage.addToFavorite();
        successfullyLoginPage = cameraPage.comeBacktoMainMenu();
        wishList = successfullyLoginPage.clicWishListButton();
        wishList.isAddToFavorite();
        successfullyLoginPage = wishList.comeBacktoMainMenu();
        historyList = successfullyLoginPage.clicHistoryButton();
        historyList.isAddToHistory();
        successfullyLoginPage = historyList.comeBacktoMainMenu();
        wishList = successfullyLoginPage.clicWishListButton();
        successfullyLoginPage = wishList.removeFromWishlist();
    }

}

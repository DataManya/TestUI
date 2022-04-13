package ru.seleniumtest.pagefactory.yandexmarket;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import ru.seleniumtest.pagefactory.BasePage;
import ru.seleniumtest.util.ScreenCreator;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.annotations.Name;

import static ru.seleniumtest.util.WaitUtil.waitForCondition;

public class WishList extends BasePage {
    private static final Logger log = LoggerFactory.getLogger(WishList.class);

    @Name("Проверка наличия товара в избранном")
    @FindBy(how = How.XPATH, using = ".//*[@alt='Экшн-камера Digma DiCam 520,  серый']")
    private WebElement checkWishList;
    @Name("Вернуться в главное меню")
    @FindBy(how = How.XPATH, using = ".//*[@class='Logo__svg']")
    private WebElement mainMenuButton;
    @Name("Удалить из избранного")
    @FindBy(how = How.XPATH, using = ".//*[@class='js--RemoveFromWishlist ProductListFavourites__wishlist js--RemoveFromWishlist ProductListFavourites__wishlist_desktop ProductCardButton js--ProductCardButton']")
    private WebElement removeWishList;

    @Step("Checking Addition to Favorites")
    public void isAddToFavorite() {
        log.info("Проверка добавления в избранное");
        Assert.assertTrue(waitForCondition(() -> checkWishList.isDisplayed()));
        ScreenCreator.saveAllureScreenshot("Screen3",driver);
    }

    @Step("Back to the main menu")
    public SuccessfullyLoginPage comeBacktoMainMenu() {
        log.info("Возвращаемся в главное меню");
        driver.click(mainMenuButton);

        return new SuccessfullyLoginPage();
    }

    @Step("Remove product from favorites")
    public SuccessfullyLoginPage removeFromWishlist() {
        log.info("Удалить товар из избранного");
        driver.click(removeWishList);
        ScreenCreator.saveAllureScreenshot("Screen5",driver);

        return new SuccessfullyLoginPage();
    }

    @Override
    public void verifyPageLoaded() {

    }
}

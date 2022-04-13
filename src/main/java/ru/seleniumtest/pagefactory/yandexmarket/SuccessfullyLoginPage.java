package ru.seleniumtest.pagefactory.yandexmarket;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import ru.seleniumtest.dto.User;
import ru.seleniumtest.pagefactory.BasePage;
import ru.seleniumtest.util.ScreenCreator;
import ru.yandex.qatools.htmlelements.annotations.Name;

import static ru.seleniumtest.util.WaitUtil.waitForCondition;

public class SuccessfullyLoginPage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(SuccessfullyLoginPage.class);

    @Name("Имя")
    @FindBy(how = How.XPATH, using = ".//*[@class='HeaderUserName__name']")
    private WebElement checkButton;
    @Name("Выйти")
    @FindBy(how = How.XPATH, using = ".//*[@class='js--UserMenu__menu-link_logout UserMenu__menu-link UserMenu__menu-link_logout']")
    private WebElement logoutButton;
    @Name("Каталог")
    @FindBy(how = How.XPATH, using = ".//*[@class='js--PopupCatalogMenu__button-open PopupCatalogMenu__button-open  Button  jsButton Button_theme_primary-transparent Button_size_m Button_with-icon']")
    private WebElement catalogButton;
    @Name("Избранное")
    @FindBy(how = How.XPATH, using = ".//*[@class='HeaderMenu__buttons  HeaderMenu__buttons_wishlist']")
    private WebElement wishlistButton;
    @Name("История")
    @FindBy(how = How.XPATH, using = ".//*[@class='SectionHead__link js--SectionHead__link Main__resently-viewed js--Main__resently-viewed_history-link  Link js--Link Link_arrowed-right Link_type_primary']")
    private WebElement historyButton;

    @Step("Successful login")
    public void isOpen(User user) {
        Assert.assertEquals(checkButton.getText(), user.getName());
    }

    @Override
    public void verifyPageLoaded() {
        log.info("Проверка загрузки страницы.");
        Assert.assertTrue(waitForCondition(() ->checkButton.isDisplayed()));
    }

    @Step("Click on the product catalog")
    public CatalogPageObject clickcatalogButton() {
        log.info("Кликаем на каталог товаров");
        driver.click(catalogButton);

        return new CatalogPageObject();
    }

    @Step("Log out")
    public CitilinkMainPage logOut() {
        driver.click(checkButton);
        driver.click(logoutButton);

        return new CitilinkMainPage();
    }

    @Step("Click to favorites")
    public WishList clicWishListButton() {
        log.info("Перейти в избранное");
        driver.click(wishlistButton);

        return new WishList();
    }

    @Step("Click on browsing history")
    public HistoryList clicHistoryButton() {
        log.info("Кликаем по истории просмоторов");
        driver.click(historyButton);

        return new HistoryList();
    }

}

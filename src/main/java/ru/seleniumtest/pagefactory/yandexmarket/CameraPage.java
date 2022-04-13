package ru.seleniumtest.pagefactory.yandexmarket;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.seleniumtest.pagefactory.BasePage;
import ru.seleniumtest.util.ScreenCreator;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.annotations.Name;

public class CameraPage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(CameraPage.class);

    @Name("Добавить в избранное")
    @FindBy(how = How.XPATH, using = ".//*[@class='ProductHeader__color-sign-item js--ProductHeader__add-to-wishlist IconInRound js--IconInRound']")
    private WebElement addelectButton;
    @Name("Вернуться в главное меню")
    @FindBy(how = How.XPATH, using = ".//*[@class='Logo__svg']")
    private WebElement mainMenuButton;

    @Step("Adding a product to favorites")
    public void addToFavorite() {
        log.info("Добавляем товар в избранное");
        driver.click(addelectButton);
        ScreenCreator.saveAllureScreenshot("Screen2",driver);
    }

    @Step("Back to the main menu")
    public SuccessfullyLoginPage comeBacktoMainMenu() {
        log.info("Возвращаемся в главное меню");
        driver.click(mainMenuButton);

        return new SuccessfullyLoginPage();
    }

    @Override
    public void verifyPageLoaded() {

    }

}

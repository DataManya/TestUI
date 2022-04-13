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

public class HistoryList extends BasePage {
    private static final Logger log = LoggerFactory.getLogger(HistoryList.class);

    @Name("Проверка наличия товара в истории просмотров")
    @FindBy(how = How.XPATH, using = ".//*[@alt='Экшн-камера Digma DiCam 520,  серый']")
    private WebElement checkHistoryList;
    @Name("Вернуться в главное меню")
    @FindBy(how = How.XPATH, using = ".//*[@class='Logo__svg']")
    private WebElement mainMenuButton;

    @Step("Checking the addition to the browsing history")
    public void isAddToHistory() {
        log.info("Проверка добавления в историю просмотров");
        Assert.assertTrue(waitForCondition(() -> checkHistoryList.isDisplayed()));
        ScreenCreator.saveAllureScreenshot("Screen4",driver);
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

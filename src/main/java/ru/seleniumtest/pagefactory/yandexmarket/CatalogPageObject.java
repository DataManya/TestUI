package ru.seleniumtest.pagefactory.yandexmarket;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import ru.seleniumtest.pagefactory.BasePage;
import ru.yandex.qatools.htmlelements.annotations.Name;

import static ru.seleniumtest.util.WaitUtil.waitForCondition;

public class CatalogPageObject extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(CatalogPageObject.class);
    public static String url="https://www.citilink.ru/";
    @Name("Категория 'Экшн камеры'")
    @FindBy(how = How.XPATH, using = ".//*[@data-title='Экшн камеры']")
    private WebElement cameras;
    @Name("Категория 'Планшеты'")
    @FindBy(how = How.XPATH, using = ".//*[@data-title='Недорогие планшеты']")
    private WebElement tablets;

    @Step("Click on action cameras")
    public CamerasPage clickCameras() {
        log.info("Выбираем категорию - 'Экшн камеры'");
        driver.click(cameras);

        return new CamerasPage();
    }

    @Step("Click on tablets")
    public TabletsPage clickTablets() {
        log.info("Выбираем категорию - 'Планшеты'");
        driver.click(tablets);

        return new TabletsPage();
    }

    @Override
    public void verifyPageLoaded() {
        log.info("");
        Assert.assertTrue(waitForCondition(() -> cameras.isDisplayed()));
    }

}

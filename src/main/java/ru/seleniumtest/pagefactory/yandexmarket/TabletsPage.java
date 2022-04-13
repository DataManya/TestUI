package ru.seleniumtest.pagefactory.yandexmarket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import ru.seleniumtest.dto.ProductParams;
import ru.seleniumtest.dto.User;
import ru.seleniumtest.pagefactory.BasePage;
import ru.seleniumtest.util.ScreenCreator;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.htmlelements.annotations.Name;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.List;

import static org.openqa.selenium.OutputType.BYTES;
import static ru.seleniumtest.util.WaitUtil.waitForCondition;

public class TabletsPage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(TabletsPage.class);
    @Name("цена до")
    @FindBy(how = How.XPATH, using = "//*[@id=\"app-filter\"]/div/div/div[2]/div[2]/div/div[3]/div[2]/div[2]/input[2]")
    private WebElement priceField;
    @Name("Список цен")
    @FindBy(how = How.XPATH, using = "//section[@class='ProductGroupList js--ProductGroupList']/div")
    private List<WebElement> fields;
    @Name("Закрыть")
    @FindBy(how = How.XPATH, using = ".//*[@class='euhnv30 css-1oakcsy e14kw8wi0']")
    private WebElement close;

    public TabletsPage() {
    }

    @Step("Enter the price")
    public TabletsPage insertPrice(Integer price) {
        log.info("");
        close.click();
        priceField.click();
        priceField.clear();
        priceField.sendKeys(price.toString());
        priceField.sendKeys(Keys.ENTER);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new TabletsPage();

    }

    @Step("Check Price Filter")
    public void checkPrice(Integer maxPrice){

        List<WebElement> fields = driver.findElementsByXPath("//section[@class='ProductGroupList js--ProductGroupList']/div");
        for (WebElement element :fields) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                ProductParams productParams = objectMapper.readValue(element.getAttribute("data-params").toString(),ProductParams.class);
                Assert.assertTrue(productParams.getPrice()<=maxPrice);
                log.info("");
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        ScreenCreator.saveAllureScreenshot("Screen1",driver);
        //Allure.addAttachment("Attachment", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Override
    public void verifyPageLoaded() {

    }
}

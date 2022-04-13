package ru.seleniumtest.pagefactory.yandexmarket;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.seleniumtest.pagefactory.BasePage;
import ru.yandex.qatools.htmlelements.annotations.Name;

public class CamerasPage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(CamerasPage.class);

    @Name("���� ������ digma")
    @FindBy(how = How.NAME, using = "digma")
    private WebElement digma;
    @Name("���� ������ digma c WI-FI")
    @FindBy(how = How.NAME, using = "7329_718")
    private WebElement wifi;
    @Name("������������ � ������")
    @FindBy(how = How.NAME, using = "available.instore")
    private WebElement available;
    @Name("���� ������ Digma DiCam")
    @FindBy(how = How.XPATH, using = ".//*[@title='����-������ Digma DiCam 520 4K,  WiFi,  ����� [dc520]']")
    private WebElement camera;

    @Step("Choose Action camera digma")
    public void clickDigma() {
        log.info("���� ������ digma");
        driver.click(digma);
    }

    @Step("Choose Action camera digma with WI-FI")
    public void clickDigmaWithWiFi() {
        log.info("���� ������ digma c WI-FI");
        driver.click(wifi);
    }

    @Step("Product present in search results")
    public void clickAvailable() {
        log.info("������������ � ������");
        driver.click(available);
    }

    @Step("Choose your favorite camera")
    public CameraPage clickCamera() {
        log.info("�������� ������������� ������");
        driver.click(camera);
        return new CameraPage();
    }

    @Step("Catalog search")
    public void beginSearch() {
        clickDigma();
        clickDigmaWithWiFi();
        clickAvailable();
    }

    @Override
    public void verifyPageLoaded() {

    }
}

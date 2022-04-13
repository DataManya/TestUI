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
import ru.yandex.qatools.htmlelements.annotations.Name;

import static ru.seleniumtest.util.WaitUtil.waitForCondition;


public class CitilinkMainPage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(CitilinkMainPage.class);
    public static String url="https://www.citilink.ru/";
    @Name("Вход")
    @FindBy(how = How.XPATH, using = ".//*[@class='HeaderMenu__buttons  HeaderMenu__buttons_user']")
    private WebElement loginButton;
    @Name("Поле логина")
    @FindBy(how = How.NAME, using = "login")
    private WebElement loginField;
    @Name("Поле пароля")
    @FindBy(how = How.NAME, using = "pass")
    private WebElement passwordField;
    @Name("Войти")
    @FindBy(how = How.XPATH, using = ".//*[@class='SignIn__button js--SignIn__action_sign-in  Button  jsButton Button_theme_primary Button_size_m Button_full-width']")
    private WebElement submitButton;
    @Name("Каталог товаров")
    @FindBy(how = How.XPATH, using = ".//*[@class='js--PopupCatalogMenu__button-open PopupCatalogMenu__button-open  Button  jsButton Button_theme_primary-transparent Button_size_m Button_with-icon']")
    private WebElement catalogButton;

    public CitilinkMainPage() { this.verifyPageLoaded(); }

    @Step("Click the login button")
    public void clickLoginButton() {
        log.info("Кликаем кнопку - 'Вход'");
        driver.click(loginButton);
    }

    @Step("Enter login")
    public void insertLogin(User user) {
        log.info("Вводим логин");
        driver.click(loginField);
        loginField.clear();
        loginField.sendKeys(user.getLogin());
    }

    @Step("Enter password")
    private void insertPassword(User user) {
        log.info("Вводим пароль");
        driver.click(passwordField);
        passwordField.clear();
        passwordField.sendKeys(user.getPassword());
    }

    @Step("Click the Submit button")
    private void clickSubmitButton() {
        log.info("Кликаем кнопку - 'Войти'");
        driver.click(submitButton);
    }

    @Step("Log in")
    public SuccessfullyLoginPage authorization(User user) {
        clickLoginButton();
        insertLogin(user);
        insertPassword(user);
        clickSubmitButton();

        return new SuccessfullyLoginPage();
    }

    @Step("Click on the product catalog")
    public CatalogPageObject clickcatalogButton() {
        log.info("Кликаем кнопку - 'Каталог товаров'.");
        driver.click(catalogButton);

        return new CatalogPageObject();
    }

    @Override
    public void verifyPageLoaded() {
        log.info("Проверка загрузки страницы авторизации.");
        Assert.assertTrue(waitForCondition(() -> loginButton.isDisplayed()));
    }
}

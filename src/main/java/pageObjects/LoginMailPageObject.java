package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.LoginMailPageUI;

public class LoginMailPageObject extends BasePage {
    WebDriver driver;

    public LoginMailPageObject(WebDriver driver) {
        this.driver = driver;
    }


    public void enterToEmailTextbox(String email) {
        waitForElementVisible(driver, LoginMailPageUI.EMAIL_TEXTBOX_AT_WEBMAIL_PAGE);
        sendkeyToElement(driver, LoginMailPageUI.EMAIL_TEXTBOX_AT_WEBMAIL_PAGE, email);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, LoginMailPageUI.PASSWORD_TEXTBOX_WEBMAIL_PAGE);
        sendkeyToElement(driver, LoginMailPageUI.PASSWORD_TEXTBOX_WEBMAIL_PAGE, password);
    }

    public WebMailPageObject clickToLoginButton() {
        waitForElementClickable(driver, LoginMailPageUI.LOGIN_BUTTON_WEBMAIL_PAGE);
        clickToElement(driver, LoginMailPageUI.LOGIN_BUTTON_WEBMAIL_PAGE);
        sleepInSecond(1);
        return PageGeneratorManager.getWebMailPage(driver);
    }
}

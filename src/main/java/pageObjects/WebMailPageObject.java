package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.WebMailPageUI;

public class WebMailPageObject extends BasePage {
    WebDriver driver;

    public WebMailPageObject(WebDriver driver){
        this.driver = driver;
    }

    public boolean isWebMailDisplayed() {
        waitForElementVisible(driver, WebMailPageUI.WEBMAIL_PAGE_LOADED_SUCESSFULL);
        return isElementDisplayed(driver, WebMailPageUI.WEBMAIL_PAGE_LOADED_SUCESSFULL);
    }

    public void clickToEmailReceviedInMessageList(String emailsubject) {
        waitForElementVisible(driver, WebMailPageUI.NEW_EMAIL_RECEIVED_IN_LIST, emailsubject);
        doubleClickToElement(driver, WebMailPageUI.NEW_EMAIL_RECEIVED_IN_LIST, emailsubject);
    }

    public WebDriver switchToIframe() {
        waitForElementVisible(driver, WebMailPageUI.FRAME);
        return switchToIframeByElement(driver, WebMailPageUI.FRAME);
    }


    public boolean isEmailSubjectAtWebMailDetail() {
        waitForElementVisible(driver, WebMailPageUI.EMAIL_SUBJECT_AT_MESSAGE_HEADER_AT_WEBMAIL);
        return  isElementDisplayed(driver, WebMailPageUI.EMAIL_SUBJECT_AT_MESSAGE_HEADER_AT_WEBMAIL);
    }

    public String areValueForToOrReplyToFieldAtWebMail(String valueField) {
        waitForElementVisible(driver, WebMailPageUI.VALUE_EMAIL_OF_TO_FIELD_AND_REPLY_TO_FIELD_MESSAGE_HEADR_AT_WEBMAIL,valueField);
        return getElementText(driver, WebMailPageUI.VALUE_EMAIL_OF_TO_FIELD_AND_REPLY_TO_FIELD_MESSAGE_HEADR_AT_WEBMAIL,valueField).trim();
    }

    public boolean areMultipleAttachedFileAtWebMailDisplayed(String[] fileNames) {
        boolean status = false;
        for (String fileName : fileNames) {
            waitForElementVisible(driver, WebMailPageUI.LIST_ATTACHED_FILES_AT_WEBMAIL, fileName);
            if (isElementDisplayed(driver, WebMailPageUI.LIST_ATTACHED_FILES_AT_WEBMAIL, fileName)){
                status = true;
            } else {
                return status;
            }
        }
        return status;
    }

    public String isValueForCreateAndExpiryDateFieldAtWebMail(String titleDateField) {
        waitForElementVisible(driver, WebMailPageUI.VALUE_FOR_CREATE_AND_EXPIRY_DATE_AT_WEBMAIL, titleDateField);
        return getElementText(driver, WebMailPageUI.VALUE_FOR_CREATE_AND_EXPIRY_DATE_AT_WEBMAIL, titleDateField);
    }

    public boolean isAttachedFileAtWebMailDisplayed(String fileName) {
        waitForElementVisible(driver, WebMailPageUI.LIST_ATTACHED_FILES_AT_WEBMAIL, fileName);
        return isElementDisplayed(driver, WebMailPageUI.LIST_ATTACHED_FILES_AT_WEBMAIL, fileName);
    }


    public LoginPageObject clickToHyperlinkLinkTextAtWebMail(String linkname) {
        waitForElementClickable(driver, WebMailPageUI.HYPERLINK_TO_SHARED_FILE, linkname);
        clickToElement(driver, WebMailPageUI.HYPERLINK_TO_SHARED_FILE, linkname);
        return PageGeneratorManager.getLoginPage(driver);
    }
}

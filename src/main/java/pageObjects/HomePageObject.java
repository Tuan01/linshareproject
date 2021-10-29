package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage {
    WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

//    public boolean isWelcomeMessagePopupDisplayed() {
//        waitForElementVisible(driver, HomePageUI.WELCOME_MESSAGE_POPUP);
//        return isElementDisplayed(driver, HomePageUI.WELCOME_MESSAGE_POPUP);
//    }

    public boolean isWelcomeMessagePopupDisplayed(){
        boolean status = false;
        waitForElementVisible(driver, HomePageUI.WELCOME_MESSAGE_POPUP);
        if(isElementDisplayed(driver, HomePageUI.WELCOME_MESSAGE_POPUP)){
            status = true;
        } else{
            return status;
        }
        return status;
    }

    public MySpacePageObject clickToHostYourFileButton() {
        waitForElementClickable(driver, HomePageUI.HOST_YOUR_FILE_BUTTON);
        clickToElement(driver, HomePageUI.HOST_YOUR_FILE_BUTTON);
        return PageGeneratorManager.getMySpacePage(driver);
    }

    public SharedSpacePageObject clickToUploadToAGroupButton() {
        waitForElementClickable(driver, HomePageUI.UPLOAD_TO_A_GROUP_BUTTON);
        clickToElement(driver, HomePageUI.UPLOAD_TO_A_GROUP_BUTTON);
        return PageGeneratorManager.getShareSpacePage(driver);
    }

    public UploadRequestPageObject clickToUploadRequestButton() {
        waitForElementClickable(driver, HomePageUI.UPLOAD_REQUEST_BUTTON);
        clickToElement(driver, HomePageUI.UPLOAD_REQUEST_BUTTON);
        return PageGeneratorManager.getUploadRequestPage(driver);
    }


}

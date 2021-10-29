package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.SharedSpacePageUI;
import pageUIs.UploadRequestPageUI;

public class UploadRequestPageObject extends BasePage {
    WebDriver driver;
    public UploadRequestPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isUploadRequestDisplayed() {
        waitForElementVisible(driver, UploadRequestPageUI.TITLE_UPLOAD_REQUEST_PAGE);
        return isElementDisplayed(driver, UploadRequestPageUI.TITLE_UPLOAD_REQUEST_PAGE);
    }
}

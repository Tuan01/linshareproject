package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.ReceivedSharesPageUI;

public class ReceivedSharesPageObject extends BasePage {
    WebDriver driver;

    public ReceivedSharesPageObject(WebDriver driver) {
        this.driver = driver;
    }


    public boolean isRecipientReceivedFileShareSuccessfully(String filename, String expirationdate, String size) {
        waitForElementVisible(driver, ReceivedSharesPageUI.RECIPIENT_RECEIVED_SHARED_FILE_AT_RECEIVED_SHARES_PAGE, filename, expirationdate, size);
        return isElementDisplayed(driver, ReceivedSharesPageUI.RECIPIENT_RECEIVED_SHARED_FILE_AT_RECEIVED_SHARES_PAGE, filename, expirationdate, size);
    }

    public String getNameOfRecipientShareFile(String filename) {
        waitForElementVisible(driver, ReceivedSharesPageUI.NAME_OF_RECIPIENT_SHARE_FILE_AT_RECEIVED_SHARES_PAGE,filename);
        return getElementText(driver, ReceivedSharesPageUI.NAME_OF_RECIPIENT_SHARE_FILE_AT_RECEIVED_SHARES_PAGE,filename);
    }

    public boolean isMessagePopupViewingRestrictedFileselectionDisplayed() {
        waitForElementVisible(driver, ReceivedSharesPageUI.MESSAGE_POPOP_VIEWING_RESTRICTION_UNDER_FILE_SELECTION);
        return isElementDisplayed(driver, ReceivedSharesPageUI.MESSAGE_POPOP_VIEWING_RESTRICTION_UNDER_FILE_SELECTION);
    }

    public void clostTabWindownExceptParentTab(String parentID) {
        closeAllWindowExceptParent(driver, parentID);
    }
}

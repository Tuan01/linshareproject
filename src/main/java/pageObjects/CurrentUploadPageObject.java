package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.BasePageUI;
import pageUIs.CurrentUploadPageUI;


public class CurrentUploadPageObject extends BasePage {
    WebDriver driver;

    public CurrentUploadPageObject(WebDriver driver){
        this.driver = driver;
    }

    public boolean areFileUploadedSuccess(String[] fileNames) {
        boolean status = false;
        for (String fileName : fileNames) {
            waitForElementVisible(driver, BasePageUI.UPLOADED_FILE_NAME, fileName);
            if (isElementDisplayed(driver, BasePageUI.UPLOADED_FILE_NAME, fileName)){
                status = true;
            } else {
                return status;
            }
        }
        return status;
    }

    public boolean areMessageDisplayedSuccessful(String[] fileNames) {
        boolean status = false;
        for (String fileName : fileNames) {
            waitForElementVisible(driver, BasePageUI.MESSAGE_A_FILE_NAME);
            if (isElementDisplayed(driver, BasePageUI.MESSAGE_A_FILE_NAME)){
                status = true;
            } else {
                return status;
            }
        }
        return status;
    }

    public String getSuccessMessageDisplayed() {
        waitForElementVisible(driver, CurrentUploadPageUI.TOAST_MESSSAGE_FOR_UPLOAD_MULTIPLE_FILES);
        return getElementText(driver, CurrentUploadPageUI.TOAST_MESSSAGE_FOR_UPLOAD_MULTIPLE_FILES);
    }

    public boolean AreActionsOfFilesSusscessful(String[] fileNames, String actionName) {
        boolean status = false;
        for(String fileName : fileNames){
            if (isElementUndisplayed(driver, CurrentUploadPageUI.DYNAMIC_ACTIONS_FOR_FILES_BUTTON, fileName, actionName)){
                status = true;
            } else {
                return status;
            }
        }
        return status;
    }


    public void actionsForMultipleFiles(String[] fileNames, String actionName) {
        for (String fileName: fileNames){
            waitForElementClickable(driver,CurrentUploadPageUI.DYNAMIC_ACTIONS_FOR_FILES_BUTTON,fileName, actionName);
            clickToElement(driver,CurrentUploadPageUI.DYNAMIC_ACTIONS_FOR_FILES_BUTTON,fileName, actionName);
            sleepInSecond(1);
        }
    }


    public boolean areFilesClearedSuccessful(String[] fileNames, String actionName) {
        boolean status = false;
        for (String fileName : fileNames){
            if(isElementUndisplayed(driver,CurrentUploadPageUI.DYNAMIC_ACTIONS_FOR_FILES_BUTTON,fileName,actionName)){
                status = true;
            } else {
                return status;
            }
        }
        return status;
    }

    public void actionsForMultipleFileByJS(String[] fileNames, String actionName) {
        for (String fileName: fileNames) {
            waitForElementClickable(driver, CurrentUploadPageUI.DYNAMIC_ACTIONS_FOR_FILES_BUTTON, fileName, actionName);
            clickToElementByJS(driver, CurrentUploadPageUI.DYNAMIC_ACTIONS_FOR_FILES_BUTTON, fileName, actionName);
            sleepInSecond(1);
        }
    }


    public boolean areToastMessageDisplayedSuccessful(String[] fileNames) {
        boolean status = false;
        for (String fileName : fileNames) {
            waitForElementVisible(driver, BasePageUI.MESSAGE_A_FILE_NAME);
            if (isElementDisplayed(driver, BasePageUI.MESSAGE_A_FILE_NAME)){
                status = true;
            } else {
                return status;
            }
        }
        return status;
    }


    public MySpacePageObject clickToViewFileButton(String[] fileNames) {
        for (String fileName : fileNames){
            waitForElementClickable(driver, CurrentUploadPageUI.EYE_ICON_TO_VIEW_FILE_DETAIL, fileName);
            clickToElement(driver, CurrentUploadPageUI.EYE_ICON_TO_VIEW_FILE_DETAIL, fileName);
            sleepInSecond(1);
        }
        return PageGeneratorManager.getMySpacePage(driver);
    }
}

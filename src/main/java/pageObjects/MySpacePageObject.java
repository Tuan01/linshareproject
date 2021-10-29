package pageObjects;

import commons.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.MySpacePageUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MySpacePageObject extends BasePage {
    WebDriver driver;

    public MySpacePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isMySpacePageDisplayed() {
        waitForElementVisible(driver, MySpacePageUI.TITLE_MYSPACE_PAGE);
        return isElementDisplayed(driver, MySpacePageUI.TITLE_MYSPACE_PAGE);
    }

    public boolean ishighlightedFileSuccessful(String[] fileNames) {
        boolean status = false;
        for (String fileName : fileNames){
            if(isElementDisplayed(driver,MySpacePageUI.HINGLIGHT_FILE_MYSPACE_PAGE, fileName)){
                status = true;
            } else {
                return status;
            }
        }
        return status;
    }


    public boolean isFileDeletedSuccessful(String fileName) {
        waitForElementInVisible(driver, MySpacePageUI.DYNAMIC_FILE_NAME_MYSPACE_PAGE, fileName);
        return isElementUndisplayed(driver, MySpacePageUI.DYNAMIC_FILE_NAME_MYSPACE_PAGE, fileName);
    }

    public String getdeletedmessagesuccessfull() {
        waitForElementVisible(driver, MySpacePageUI.DELETE_TOAST_MESSAGE_DISPLAYED_MYSPACE_PAGE);
        return getElementText(driver, MySpacePageUI.DELETE_TOAST_MESSAGE_DISPLAYED_MYSPACE_PAGE);
    }

    public String getDeletedMultipleFileMesssageSuccessfull() {
        waitForElementVisible(driver, MySpacePageUI.MULTIPLE_FILE_DELETE_TOAST_MESSAGE_DISPLAYED_MYSPACE_PAGE);
        return getElementText(driver, MySpacePageUI.MULTIPLE_FILE_DELETE_TOAST_MESSAGE_DISPLAYED_MYSPACE_PAGE);
    }


    public String getRenamedFileSuccessfull() {
        waitForElementVisible(driver, MySpacePageUI.RENAME_FILE_MYSPACE_PAGE);
        return getElementText(driver, MySpacePageUI.RENAME_FILE_MYSPACE_PAGE);
    }

    public String getMessageDuplicateFileSuccessfull() {
        waitForElementVisible(driver, MySpacePageUI.DUPLICATE_TOAST_MESSAGE_MY_SPACE_PAGE);
        return getElementText(driver, MySpacePageUI.DUPLICATE_TOAST_MESSAGE_MY_SPACE_PAGE);
    }

    public String getFullNameOfRecipientAtSharesTab() {
        waitForElementVisible(driver, MySpacePageUI.FULL_NAME_OF_RECIPIENT_AT_SHARES_TAB_MYSPACE_PAGE);
        return getElementText(driver, MySpacePageUI.FULL_NAME_OF_RECIPIENT_AT_SHARES_TAB_MYSPACE_PAGE);
    }

    public void clickToRecipientToViewSharedDetail() {
        waitForElementVisible(driver, MySpacePageUI.FULL_NAME_OF_RECIPIENT_AT_SHARES_TAB_MYSPACE_PAGE);
        clickToElement(driver, MySpacePageUI.FULL_NAME_OF_RECIPIENT_AT_SHARES_TAB_MYSPACE_PAGE);
        sleepInSecond(2);
    }

    public String getEmailRecipientshared() {
        waitForElementVisible(driver, MySpacePageUI.EMAIL_RECIPIENT_SHARED_DETAIL);
        return getElementText(driver, MySpacePageUI.EMAIL_RECIPIENT_SHARED_DETAIL);
    }

    public String getSharedFileNameAtSharedDetail() {
        waitForElementVisible(driver, MySpacePageUI.NAME_SHARED_FILE_IN_SHARED_DETAIL);
        return getElementText(driver, MySpacePageUI.NAME_SHARED_FILE_IN_SHARED_DETAIL);
    }

    public void clickToAcceptAlertButton() {
        try{
            acceptAlert(driver);
        } catch (Exception e){
            System.out.println("Please ingore this");
        }
    }

    public String getSharedDateAtShareDetail() {
        waitForElementVisible(driver, MySpacePageUI.SHARED_DATE_AT_SHARE_DETAIL);
        String a = getElementText(driver, MySpacePageUI.SHARED_DATE_AT_SHARE_DETAIL).trim();
        return a.substring(0, a.length() - 8).trim();
    }

    public void cancelMultipleRecipientAtShareDetail(String[] recipientNames) {
        waitForElementVisible(driver, MySpacePageUI.RECIPIENTS_LIST_SHARING_DETAILS);
        for (String recipient : recipientNames) {
            clickToElementByJS(driver, MySpacePageUI.RECIPIENT_NAME);
            sleepInSecond(1);
            waitForElementClickable(driver, MySpacePageUI.CANCEL_SHARE_BUTTON);
            clickToElementByJS(driver, MySpacePageUI.CANCEL_SHARE_BUTTON);
            sleepInSecond(1);
        }
    }

    public boolean isCancelMultipleSharedFileSuccessfull(String[] fileNames) {
        boolean status = false;
        for (String filename : fileNames){
            if(isElementUndisplayed(driver, MySpacePageUI.SHARED_LINK_IN_FILE_NAME, filename)){
                status = true;
            } else {
                return status;
            }
        }
        return status;
    }

    public boolean isCancelSharedFileSuccessfull(String fileName) {
        boolean status = false;
        if(isElementUndisplayed(driver, MySpacePageUI.SHARED_LINK_IN_FILE_NAME, fileName)){
            status = true;
        } else{
            return status;
        }
        return status;
    }

    public void clickToDownloadAtToolBar() {
        waitForElementClickable(driver, MySpacePageUI.DOWNLOAD_BUTTON_AT_TOOLBAR_SELECTED_MULTIPLE_FILE);
        clickToElementByJS(driver, MySpacePageUI.DOWNLOAD_BUTTON_AT_TOOLBAR_SELECTED_MULTIPLE_FILE);
        sleepInSecond(1);
    }

    public int areFilePresentOnList() {
        ArrayList<String> arrayList = new ArrayList<>();
        List<WebElement> elements = getElements(driver, MySpacePageUI.LIST_OF_NAME_FILE_MY_SPACE);
        for (WebElement element : elements){
            arrayList.add(element.getText().trim());
        }
        return arrayList.size();
    }

    public void clickToSearchTextBox() {
        waitForElementVisible(driver, MySpacePageUI.SEARCH_TEXTBOX_CARD_HEADER);
        clickToElementByJS(driver, MySpacePageUI.SEARCH_TEXTBOX_CARD_HEADER);
    }

//    public void cancelMultipleRecipient(String locator){
//        ArrayList<String> arrayList = new ArrayList<>();
//        List<WebElement> elementList = getElements(driver, MySpacePageUI.RECIPIENT_NAME);
//        for (WebElement element : elementList) {
//            arrayList.add(element.getText().trim());
//        }
//        System.out.println("----------- Du Lieu Tren UI:---------------");
//        for (String name : arrayList) {
//
//            System.out.println(name);
//        }
//    }




//    public boolean isDataStringSortedAscending(WebDriver driver, String locator) {
//        // Khai bao 1 Array List
//        ArrayList<String> arrayList = new ArrayList<>();
//        // Tim tat ca element matching vs dieu kien (Name/Price...)
//        List<WebElement> elementList = driver.findElements(By.xpath(locator));
//
//        // Lay text cua tung element add vao arrayList
//        for (WebElement element : elementList) {
//            arrayList.add(element.getText());
//        }
//
//        System.out.println("----------- Du Lieu Tren UI:---------------");
//        for (String name : arrayList) {
//            System.out.println(name);
//        }
//
//        // Copy qua 1 array list moi de Sort trong code
//
//        ArrayList<String> sortedList = new ArrayList<>();
//        for (String child : arrayList) {
//            sortedList.add(child);
//        }
//
//        // Thuc hien Sort ASC
//        Collections.sort(sortedList);
//
//        System.out.println("--------------Du lieu da SORT ASC trong code:---------------");
//        for (String name : sortedList) {
//            System.out.println(name);
//        }
//
//        // Verify 2 array bang nhau - neu du lieu sort tren UI ko chinh xac thi kq tra ve sai
//
//        return sortedList.equals(arrayList);
//    }


//    public void acceptAlert(WebDriver driver) {
//        alert = waitForAlertPresence(driver);
//        alert = driver.switchTo().alert();
//        alert.accept();
//    }


}

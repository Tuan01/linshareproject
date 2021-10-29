package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.LoginPageObject;
import pageObjects.PageGeneratorManager;
import pageUIs.BasePageUI;
import pageUIs.MySpacePageUI;
import testdata.UserData;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BasePage {

    public static BasePage getBasePage() {
        return new BasePage();
    }

    public String getParrentIDWindowOrTab(WebDriver driver){
        return driver.getWindowHandle();
    }

    public Alert waitForAlertPresence(WebDriver driver) {
        explicitWait = new WebDriverWait(driver, timeout);
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver) {
        alert = waitForAlertPresence(driver);
        alert = driver.switchTo().alert();
        alert.accept();
    }

    public void cancelAlert(WebDriver driver) {
        alert = waitForAlertPresence(driver);
        alert.dismiss();
    }

    public void sendkeyToAlert(WebDriver driver, String value) {
        alert = waitForAlertPresence(driver);
        alert.sendKeys(value);
    }

    public String getAlertText(WebDriver driver) {
        alert = waitForAlertPresence(driver);
        return alert.getText();
    }

    public void switchToWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindowsID = driver.getWindowHandles();
        for (String windowID : allWindowsID) {
            if (!windowID.equals(parentID)) {
                driver.switchTo().window(windowID);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String expectedWindowTitle) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        System.out.println("So luong cua so/ tab dang co:" + allWindowIDs.size());
        for (String windowID : allWindowIDs) {
            driver.switchTo().window(windowID);
            String actualWindowTitle = driver.getTitle();
            if (actualWindowTitle.equals(expectedWindowTitle)) {
                break;
            }
        }
    }

    public void closeAllWindowExceptParent(WebDriver driver, String parentID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String windowID : allWindowIDs) {
            if (!windowID.equals(parentID)) {
                driver.switchTo().window(windowID);
                driver.close();
                sleepInSecond(1);
            }
            if (driver.getWindowHandles().size() == 1) {
                driver.switchTo().window(parentID);
                break;
            }
        }
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(getByXpath(locator));
    }

    public List<WebElement> getElements(WebDriver driver, String locator) {
        return driver.findElements(getByXpath(locator));
    }

    public List<WebElement> getElements(WebDriver driver, String locator, String... values) {
        return driver.findElements(getByXpath(getDynamicLocator(locator,values)));
    }

    public String getDynamicLocator(String locator, String... values) {
        return String.format(locator, (Object[]) values);
    }

    public By getByXpath(String locator) {
        return By.xpath(locator);
    }

    public void clickToElement(WebDriver driver, String locator) {
        getElement(driver, locator).click();
    }

    public void clickToElement(WebDriver driver, String locator, String... values) {
        getElement(driver, getDynamicLocator(locator, values)).click();
    }

    public void sendkeyToElement(WebDriver driver, String locator, String value) {
        getElement(driver, locator).clear();
        getElement(driver, locator).sendKeys(value);
    }

    public void sendkeyToElement(WebDriver driver, String locator, String value, String... values) {
        getElement(driver, getDynamicLocator(locator, values)).clear();
        getElement(driver, getDynamicLocator(locator, values)).sendKeys(value);
    }



    public int getElementSize(WebDriver driver, String locator) {
        return getElements(driver, locator).size();
    }

    public int getElementSize(WebDriver driver, String locator, String... values) {
        return getElements(driver, getDynamicLocator(locator, values)).size();
    }

    public void selectDropdownByText(WebDriver driver, String locator, String itemText) {
        select = new Select(getElement(driver, locator));
        select.deselectByVisibleText(itemText);
    }

    public String getSelectedItemDropdown(WebDriver driver, String locator) {
        select = new Select(getElement(driver, locator));
        return select.getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        select = new Select(getElement(driver, locator));
        return select.isMultiple();
    }

        public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        getElement(driver, parentLocator).click();
        explicitWait = new WebDriverWait(driver, timeout);
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));

        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
//                sleepInSecond(1);
                item.click();
//                sleepInSecond(1);
                break;
            }
        }
    }

    public void closeAlert(WebDriver driver){
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.onbeforeunload = function(e){};");
    }

    public String getElementText(WebDriver driver, String locator) {
        return getElement(driver, locator).getText().trim();
    }

    public String getElementText(WebDriver driver, String locator, String... values) {
        return getElement(driver, getDynamicLocator(locator, values)).getText().trim();
    }

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        return getElement(driver, locator).isDisplayed();
    }

    public boolean isControlDisplayed(WebDriver driver, String locator) {
        boolean status = false;
        try {
            status = getElement(driver, locator).isDisplayed();
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator, String... values) {
        return getElement(driver, getDynamicLocator(locator, values)).isDisplayed();
    }

    public boolean isElementEnabled(WebDriver driver, String locator) {
        return getElement(driver, locator).isEnabled();
    }

    public boolean isElementEnabled(WebDriver driver, String locator, String... values) {
      return getElement(driver,getDynamicLocator(locator,values)).isEnabled();
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getElement(driver, locator).isSelected();
    }

    public boolean isElementSelected(WebDriver driver, String locator, String... values) {
        return getElement(driver, getDynamicLocator(locator, values)).isSelected();
    }


    public WebDriver switchToIframeByElement(WebDriver driver, String locator) {
        return driver.switchTo().frame(getElement(driver, locator));
    }

    public WebDriver switchToDefaultContent(WebDriver driver) {
        return driver.switchTo().defaultContent();
    }

    public void hoverToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.moveToElement(getElement(driver, locator)).perform();
    }

    public void hoverToElement(WebDriver driver, String locator, String... values) {
        action = new Actions(driver);
        action.moveToElement(getElement(driver, getDynamicLocator(locator,values))).perform();
    }

    public void doubleClickToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.doubleClick(getElement(driver, locator)).perform();
    }

    public void doubleClickToElement(WebDriver driver, String locator, String... values) {
        action = new Actions(driver);
        action.doubleClick(getElement(driver, getDynamicLocator(locator, values))).perform();
    }

    public void rightClickToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.contextClick(getElement(driver, locator)).perform();
    }

    public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
        action = new Actions(driver);
        action.dragAndDrop(getElement(driver, sourceLocator), getElement(driver, targetLocator)).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys key) {
        action = new Actions(driver);
        action.sendKeys(getElement(driver, locator), key).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys key, String... values) {
        action = new Actions(driver);
        action.sendKeys(getElement(driver, getDynamicLocator(locator, values)), key).perform();
    }

    public Object executeForBrowser(WebDriver driver, String javaScript) {
        jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
        jsExecutor = (JavascriptExecutor) driver;
        String textActual = (String) jsExecutor
                .executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
    }

    public void clickToElementByJS(WebDriver driver, String locator, String... values) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getElement(driver, getDynamicLocator(locator, values)));
    }

    public void scrollToElement(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
                getElement(driver, locator));
    }

    public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
        explicitWait = new WebDriverWait(driver, timeout);
        jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
                getElement(driver, locator));
        if (status) {
            return true;
        } else {
            return false;
        }
    }

    public void waitForElementVisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, timeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
    }

    public void waitForElementVisible(WebDriver driver, String locator, String... values) {
        explicitWait = new WebDriverWait(driver, timeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(locator, values))));
    }

    public void waitForAllElementVisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, timeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, timeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator, String... values) {
        explicitWait = new WebDriverWait(driver, timeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(locator, values))));
    }

    public void waitForElementInVisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, timeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
    }

    public void waitForElementInVisible(WebDriver driver, String locator, String... values) {
        explicitWait = new WebDriverWait(driver, timeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(getDynamicLocator(locator,values))));
    }

	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		String filePath = System.getProperty("user.dir") + getDirectorySlash("uploadFiles");
		String fullFileName = "";

		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getElement(driver, BasePageUI.UPLOAD_FILE_TYPE).sendKeys(fullFileName);
	}

	public void uploadFolder(WebDriver driver, String...folderNames){
        String filePath = System.getProperty("user.dir") + getDirectorySlash("uploadFiles") + getDirectorySlash("FolderUpload");
        File folder = new File(filePath);
        File[] files = folder.listFiles();
        String filesList = "";
        for(int i = 0; i < files.length;i++){
            filesList += (i != 0 ?"\n":"") + files[i].getAbsolutePath();
        }
        filesList = filesList.trim();
        getElement(driver, BasePageUI.UPLOAD_FILE_TYPE).sendKeys(filesList);
    }

    public String getDirectorySlash(String folderName) {
        String separator = System.getProperty("file.separator");
        return separator + folderName + separator;
    }

    public boolean isElementUndisplayed(WebDriver driver, String locator) {
        System.out.println("Start time = " + new Date().toString());
        overideImplicitTimeout(driver, shortTimeout);
        List<WebElement> elements = getElements(driver, locator);
        overideImplicitTimeout(driver, longTimeout);

        if (elements.size() == 0) {
            System.out.println("Element not in DOM");
            System.out.println("End Time = " + new Date().toString());
            return false;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            System.out.println("Element in DOM but not visible/ displayed");
            System.out.println("End time = " + new Date().toString());
            return false;
        } else {
            System.out.println("Element in DOM and visible");
            return true;
        }
    }

    public boolean isElementUndisplayed(WebDriver driver, String locator, String... values) {
        System.out.println("Start time = " + new Date().toString());
        overideImplicitTimeout(driver, shortTimeout);
        List<WebElement> elements = getElements(driver, getDynamicLocator(locator, values));
        overideImplicitTimeout(driver, longTimeout);

        if (elements.size() == 0) {
            System.out.println("Element not in DOM");
//            System.out.println("End Time = " + new Date().toString());
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            System.out.println("Element in DOM but not visible/ displayed");
//            System.out.println("End time = " + new Date().toString());
            return true;
        } else {
            System.out.println("Element in DOM and visible");
            return false;
        }
    }

    public void overideImplicitTimeout(WebDriver driver, long timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);

    }

    // Linshare
    // Verify search result
    public boolean areRowRecordDisplayed(WebDriver driver, String name, String size) {
        waitForElementVisible(driver, BasePageUI.ROW_VALUE_BY_ALL_FIELD, name, size);
        return isElementDisplayed(driver, BasePageUI.ROW_VALUE_BY_ALL_FIELD, name, size);
    }
    //search file
    public void searchFileByName(WebDriver driver, String fileName){
        waitForElementClickable(driver, BasePageUI.SEARCH_FILE_BY_NAME_CARD_HEADER);
        sendkeyToElement(driver, BasePageUI.SEARCH_FILE_BY_NAME_CARD_HEADER, fileName);
    }

    //  Hover to file name & Function items for file at list

    public void hoverMouseToFileInList(WebDriver driver, String namefile){
        waitForElementVisible(driver, BasePageUI.DYNAMIC_FILE_NAME, namefile);
        hoverToElement(driver, BasePageUI.DYNAMIC_FILE_NAME, namefile);
    }

    // File
    public void fileActionsInList(WebDriver driver, String fileName, String titleAction){
        waitForElementClickable(driver, BasePageUI.DYNAMIC_FUNCTION_ITEMS_OF_FILES_IN_LIST, fileName, titleAction);
        clickToElement(driver, BasePageUI.DYNAMIC_FUNCTION_ITEMS_OF_FILES_IN_LIST, fileName, titleAction);
    }

    // Workgroup/Drive
    public void clickActionsOfWorkgroupAdnDriveInList(WebDriver driver, String nameWRDR, String titleAction){
        waitForElementClickable(driver, BasePageUI.DYNAMIC_FUNCTION_ITEMS_OF_WORKGROUP_OR_DRIVE_LIST, nameWRDR, titleAction);
        clickToElement(driver, BasePageUI.DYNAMIC_FUNCTION_ITEMS_OF_WORKGROUP_OR_DRIVE_LIST, nameWRDR, titleAction);
    }

    public void fileActionsInDropdownMenu(WebDriver driver, String actionValue){
        waitForElementClickable(driver, BasePageUI.DYNAMIC_FUNTION_ITEMS_OF_FILES_IN_DROPDOWN_MENU, actionValue);
        clickToElement(driver, BasePageUI.DYNAMIC_FUNTION_ITEMS_OF_FILES_IN_DROPDOWN_MENU, actionValue);
    }

    // Actions On Toolbar
    public void clickActionsOnToolbar(WebDriver driver, String actionName){
        waitForElementClickable(driver, BasePageUI.DYNAMIC_ACTIONS_TOOLBAR, actionName);
        clickToElementByJS(driver, BasePageUI.DYNAMIC_ACTIONS_TOOLBAR, actionName);
    }

    public void clickToActionsInMoreOptionAtToolbar(WebDriver driver, String actionName){
        waitForElementClickable(driver, BasePageUI.DYNAMIC_ATIONS_IN_MORE_OPTIONS_TOOLBAR, actionName);
        clickToElement(driver, BasePageUI.DYNAMIC_ATIONS_IN_MORE_OPTIONS_TOOLBAR, actionName);
    }
    public void clickToCheckboxtAtToolbar(WebDriver driver){
        waitForElementClickable(driver, BasePageUI.TOGGLE_CHECKBOX_AT_TOOLBAR);
        clickToElement(driver, BasePageUI.TOGGLE_CHECKBOX_AT_TOOLBAR);
    }

    public void ReclickToCheckboxtAtToolbar(WebDriver driver){
        waitForElementClickable(driver, BasePageUI.TOGGLE_CHECKBOX_SELECTED_AT_TOOLBAR);
        clickToElement(driver, BasePageUI.TOGGLE_CHECKBOX_SELECTED_AT_TOOLBAR);
        sleepInSecond(1);
    }


    // Popup confirm: Delete
    public void confirmAcceptFileInPopupDialog(WebDriver driver, String valueAccept){
        waitForElementClickable(driver, BasePageUI.CONFIRM_DELETED_FILE_POPUP_DIALOG, valueAccept);
        clickToElement(driver, BasePageUI.CONFIRM_DELETED_FILE_POPUP_DIALOG, valueAccept);
    }

    // Popup Rename: File/ Workgroup/ Drive/ Upload Request

    public void renamePopupDialog(WebDriver driver, String value){
        waitForElementClickable(driver, BasePageUI.RENAME_POPUP_DIALOG);
        sendkeyToElement(driver, BasePageUI.RENAME_POPUP_DIALOG, value);
        pressKeyToElement(driver, BasePageUI.RENAME_POPUP_DIALOG, Keys.ENTER);
    }

    // Toast message for all actions
    public  String getToastMessageForActions(WebDriver driver, String valuemessage){
        waitForElementVisible(driver, BasePageUI.TOAST_MESSSAGE_FOR_ALL_ACTIONS, valuemessage);
        return getElementText(driver, BasePageUI.TOAST_MESSSAGE_FOR_ALL_ACTIONS, valuemessage);
    }

    // Quick Share
    public void clickToCloseSideBarDetail(WebDriver driver) {
        waitForElementVisible(driver, BasePageUI.CLOSE_ICON_SIDEBAR_AT_SHARE_DETAIL);
        clickToElement(driver, BasePageUI.CLOSE_ICON_SIDEBAR_AT_SHARE_DETAIL);
    }

    public void inputToRecipientField(WebDriver driver, String valueRecipient, String placehoder){
        waitForElementVisible(driver, BasePageUI.ADD_RECIPIENT, placehoder);
        sendkeyToElement(driver, BasePageUI.ADD_RECIPIENT, valueRecipient, placehoder);
    }

    public void selectRecipientFromSuggestionList(WebDriver driver){
        waitForElementVisible(driver, BasePageUI.SUGGESSTION_LIST_RECIPIENT);
        pressKeyToElement(driver, BasePageUI.SUGGESSTION_LIST_RECIPIENT, Keys.ENTER);
    }

    public void inputMultipleRecipientToTextbox(WebDriver driver, String[] recipientNames, String placeholder) {
        for ( String recipient : recipientNames) {
            waitForElementVisible(driver, BasePageUI.ADD_RECIPIENT, placeholder);
            sendkeyToElement(driver, BasePageUI.ADD_RECIPIENT, recipient, placeholder);
            waitForElementVisible(driver, BasePageUI.SUGGESSTION_LIST_RECIPIENT);
            pressKeyToElement(driver, BasePageUI.SUGGESSTION_LIST_RECIPIENT, Keys.ENTER);
        }

    }
    public void inputToFieldsAtTextAreaFormInQuickShare(WebDriver driver, String valuetextarea, String placehoder){
        waitForElementVisible(driver, BasePageUI.EMAIL_SUBJECT_MESSAGE_AND_SHARE_NOTE_TEXTAREA, placehoder);
        sendkeyToElement(driver, BasePageUI.EMAIL_SUBJECT_MESSAGE_AND_SHARE_NOTE_TEXTAREA, valuetextarea, placehoder);
    }

    public void clickToMoreOptionLink(WebDriver driver){
        waitForElementClickable(driver, BasePageUI.MORE_OPTIONS_LINK);
        clickToElement(driver, BasePageUI.MORE_OPTIONS_LINK);
    }

    public void clickToShareOrForwardOrCancelButton(WebDriver driver, String namebutton ){
       waitForElementClickable(driver, BasePageUI.SHARE_FORWARD_CANCEL_BUTTON, namebutton);
       clickToElement(driver, BasePageUI.SHARE_FORWARD_CANCEL_BUTTON, namebutton);
    }
    public void clickToExpirationDateField(WebDriver driver){
        waitForElementClickable(driver, BasePageUI.EXPIRATION_DATE_FIELD);
        clickToElement(driver, BasePageUI.EXPIRATION_DATE_FIELD);
    }

//    public void selectDate(WebDriver driver, String exDay, String extMonth, String exYear){
//        waitForAllElementVisible(driver, BasePageUI.GRID_DATEPICKER);
//        String monthYearVal = getElement(driver, BasePageUI.MONTH_IN_DATAPIKCER).getText().trim();
//        System.out.println("Gia tri:" +monthYearVal);
//        while ( !getMonthYear(monthYearVal)[0].equals(extMonth) && getMonthYear(monthYearVal)[1].equals(exYear)){
//            getElement(driver, BasePageUI.PRE_BUTTON).click();
//        }
//        List<WebElement> dates = getElements(driver, BasePageUI.ALL_DAY);
//        int count = getElements(driver, BasePageUI.ALL_DAY).size();
//        for (int i=0; i < count; i++){
//            String text = getElements(driver, BasePageUI.ALL_DAY).get(i).getText().trim();
//            if(text.equalsIgnoreCase(exDay)){
//                getElements(driver,BasePageUI.ALL_DAY).get(i).click();
//                break;
//            }
//        }
//    }

    public static String[] getMonthYear(String monthYearVal){
        return monthYearVal.split(" ");
    }

    // Shared Detail
    public void clickSharedLinkNextToFileName(WebDriver driver, String filename){
        waitForElementVisible(driver, BasePageUI.SHARED_LINK_NEAR_FILE_NAME, filename);
        clickToElement(driver, BasePageUI.SHARED_LINK_NEAR_FILE_NAME, filename);
    }
    //
    public void openMenuPageByNameAtLeftSideBar(WebDriver driver, String pageName){
        waitForElementClickable(driver, BasePageUI.MENU_PAGE_LINK_BY_NAME, pageName);
        clickToElement(driver, BasePageUI.MENU_PAGE_LINK_BY_NAME, pageName);
        sleepInSecond(2);
    }

    public void setDefaultHomePageByName(WebDriver driver, String pageName){
        hoverToElement(driver, BasePageUI.DEFAULT_HOME_PAGE_LINK_CHECKBOX, pageName);
        waitForElementClickable(driver, BasePageUI.DEFAULT_HOME_PAGE_LINK_CHECKBOX, pageName);
        clickToElement(driver, BasePageUI.DEFAULT_HOME_PAGE_LINK_CHECKBOX, pageName);
    }

    public void clickToAvatarIconAtHeader(WebDriver driver){
        waitForElementClickable(driver, BasePageUI.AVATAR_HUMAN_ICON_AT_MY_FROFILE);
        clickToElement(driver, BasePageUI.AVATAR_HUMAN_ICON_AT_MY_FROFILE);
        sleepInSecond(1);
    }

    public LoginPageObject clickToItemsByNameAtProfile(WebDriver driver, String itemName){
       waitForElementClickable(driver, BasePageUI.DYNAMIC_BUTTON_BY_NAME_DROPDOWN_AT_PROFILE, itemName);
       clickToElement(driver, BasePageUI.DYNAMIC_BUTTON_BY_NAME_DROPDOWN_AT_PROFILE, itemName);
       return PageGeneratorManager.getLoginPage(driver);
    }

//    public Date convertStringToDate(String dateInString) {
//        dateInString = dateInString.replace(",", "");
//        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
//        Date date = null;
//        try {
//            date = formatter.parse(dateInString);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return date;
//    }

    public Date convertStringToDate(String dateInString) {
//        dateInString = dateInString.replace(",", "");
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy HH:mm a");
        Date date = null;
        try {
            date = formatter.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

   public void selectToDate(WebDriver driver, String expectedDate, String expecteDay) throws ParseException {
       String[] dateVal = expectedDate.split("-");
       for ( String va : dateVal) {
           System.out.println(va);
       }
       int monthToSelect = Integer.parseInt(dateVal[1]);
       System.out.println(monthToSelect);

       String selectedMonth = driver.findElement(By.xpath("//div[@class='dp-title ng-binding']")).getText();
       int size = selectedMonth.length() - 5;
       selectedMonth.substring(0,size);
       String selectedMoth02 =  selectedMonth.substring(0,size);
       System.out.println(selectedMoth02);

       // Select the format of Calendar to month text
       SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM");
       Calendar cal = Calendar.getInstance();
       cal.setTime(inputFormat.parse(selectedMoth02));

       // Convert the month name to month number
       SimpleDateFormat outputFormat = new SimpleDateFormat("MM");// 01-12
       System.out.println(outputFormat.format(cal.getTime()));
       int presentMonth = Integer.parseInt(outputFormat.format(cal.getTime()));
       System.out.println(presentMonth);
       // Let write the logic
       if(monthToSelect > presentMonth){
           for (int i =0; i < monthToSelect - presentMonth; i++){
               driver.findElement(By.xpath("//i[@class='zmdi zmdi-long-arrow-right']")).click();
               sleepInSecond(2);
           }
       } else if(monthToSelect < presentMonth){
           for (int i=0; i < presentMonth-monthToSelect; i++){
               driver.findElement(By.xpath("//i[@class='zmdi zmdi-long-arrow-left']")).click();
               sleepInSecond(2);
           }
       }
       // now select the date
       waitForElementClickable(driver, BasePageUI.ALL_DAY, expecteDay);
       clickToElement(driver, BasePageUI.ALL_DAY, expecteDay);

   }

    private Alert alert;
    private WebDriverWait explicitWait;
    private long timeout = 60;
    private long longTimeout = GlobalConstants.LONG_TIMEOUT;
    private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
    private Select select;
    private JavascriptExecutor jsExecutor;
    private Actions action;


}

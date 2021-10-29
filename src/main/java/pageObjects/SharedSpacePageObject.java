package pageObjects;

import commons.BasePage;
import net.bytebuddy.description.NamedElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.BasePageUI;
import pageUIs.MySpacePageUI;
import pageUIs.SharedSpacePageUI;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SharedSpacePageObject extends BasePage {
    WebDriver driver;

    public SharedSpacePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSharedSpaceDisplayed() {
        waitForElementVisible(driver, SharedSpacePageUI.TITLE_SHARED_SPACE_PAGE);
        return isElementDisplayed(driver, SharedSpacePageUI.TITLE_SHARED_SPACE_PAGE);
    }

    public void clickToAddMenuGroupButton() {
        waitForElementClickable(driver, SharedSpacePageUI.ADD_MENU_GROUP_BUTTON);
        clickToElement(driver, SharedSpacePageUI.ADD_MENU_GROUP_BUTTON);
    }

    public void clickToItemsInMenuGroup(String itemvalue) {
        waitForElementClickable(driver,SharedSpacePageUI.DYNAMIC_ITEM_IN_MENU_GROUP_BUTTON, itemvalue);
        clickToElement(driver,SharedSpacePageUI.DYNAMIC_ITEM_IN_MENU_GROUP_BUTTON, itemvalue);
    }

    public boolean isPopupCreateDisplayed(String attributeVal) {
        waitForElementVisible(driver,SharedSpacePageUI.POPUP_CREATE_WR_OR_DRIVE_OR_FOLDER, attributeVal);
        return isElementDisplayed(driver,SharedSpacePageUI.POPUP_CREATE_WR_OR_DRIVE_OR_FOLDER, attributeVal);
    }

    public void pressKeyEnterForUpload() {
        waitForElementVisible(driver, SharedSpacePageUI.DEFAULT_NAME_TEXTBOX);
        pressKeyToElement(driver, SharedSpacePageUI.DEFAULT_NAME_TEXTBOX, Keys.ENTER);
    }


    public String getNameDefaultWorkgroup() {
        waitForElementVisible(driver, SharedSpacePageUI.DEFAULT_NAME_TEXTBOX);
        String a = getElementText(driver, SharedSpacePageUI.DEFAULT_NAME_TEXTBOX).trim();
        System.out.println("Gia tri:" +a);
        return a;
    }

    public void clearTextAtTextboxSearch() {
        waitForElementVisible(driver, SharedSpacePageUI.SEARCH_TEXTBOX);
        getElement(driver, SharedSpacePageUI.SEARCH_TEXTBOX).clear();
    }

    public void enterNameToTextBoxInPopup(String nameVal) {
        waitForElementVisible(driver, SharedSpacePageUI.DEFAULT_NAME_TEXTBOX);
        sendkeyToElement(driver, SharedSpacePageUI.DEFAULT_NAME_TEXTBOX, nameVal);
        pressKeyToElement(driver, SharedSpacePageUI.DEFAULT_NAME_TEXTBOX, Keys.ENTER);
    }

    public String getNameofWorkgroupDriveFolder(String nameval) {
        waitForElementVisible(driver, SharedSpacePageUI.NEW_NAME_WORKGROUP_DRIVE_FOLDER, nameval);
        return getElementText(driver, SharedSpacePageUI.NEW_NAME_WORKGROUP_DRIVE_FOLDER, nameval).trim();
    }

    public void clickToNameWorkgroupORDriveORFolder(String nameval) {
        waitForElementVisible(driver, SharedSpacePageUI.NEW_NAME_WORKGROUP_DRIVE_FOLDER, nameval);
        doubleClickToElement(driver,SharedSpacePageUI.NEW_NAME_WORKGROUP_DRIVE_FOLDER, nameval);
    }

    public boolean isAccessToWorkGroupSuccessfull(String nameVal) {
        waitForElementVisible(driver, SharedSpacePageUI.DETAIL_WORKGROUP, nameVal);
        return isElementDisplayed(driver, SharedSpacePageUI.DETAIL_WORKGROUP, nameVal);
    }

    public boolean arePopupMessageUploadSuccessfullDisplayed(String[] fileNames) {
        boolean status = false;
        for (String fileName : fileNames) {
            waitForElementVisible(driver, SharedSpacePageUI.POPUP_UPLOAD_SUCCESS);
            if (isElementDisplayed(driver, SharedSpacePageUI.POPUP_UPLOAD_SUCCESS)){
                status = true;
            } else {
                return status;
            }
        }
        return status;
    }



    public boolean areListUploadFileDisplayed(String[] fileNames) {
        boolean status = false;
        for (String fileName : fileNames){
            waitForElementVisible(driver, SharedSpacePageUI.UPLOADED_FILE_NAME, fileName);
            if(isElementDisplayed(driver, SharedSpacePageUI.UPLOADED_FILE_NAME, fileName)){
                status = true;
            } else{
                return status;
            }
        }
        return status;
    }

    public boolean isAccessToFolderSuccessfull(String folderVal) {
        waitForElementVisible(driver, SharedSpacePageUI.DETAIL_FOLDER, folderVal);
        return isElementDisplayed(driver, SharedSpacePageUI.DETAIL_FOLDER, folderVal);
    }

    public void selectToRoleMemberInDropdownList(String roleVal) {
        waitForElementClickable(driver, SharedSpacePageUI.DROPDOWN_SELECTE_ROLE_MEMEMBER);
        selectItemInCustomDropdown(driver, SharedSpacePageUI.DROPDOWN_SELECTE_ROLE_MEMEMBER, SharedSpacePageUI.CHIILD_ROLE_OPTION_IN_DROPDOWN, roleVal);
    }

    public boolean viewMemberAndRole(String nameVal, String roleVal) {
        waitForElementVisible(driver, SharedSpacePageUI.ADD_MEMBER_AND_ROLE_SUCCESS, nameVal, roleVal);
        return isElementDisplayed(driver, SharedSpacePageUI.ADD_MEMBER_AND_ROLE_SUCCESS, nameVal, roleVal);
    }

    public boolean isWorkgroupOrDriverDisplayedAtSharedSpace(String nameVal) {
        waitForElementVisible(driver, SharedSpacePageUI.NEW_NAME_WORKGROUP_DRIVE_FOLDER, nameVal);
        return isElementDisplayed(driver, SharedSpacePageUI.NEW_NAME_WORKGROUP_DRIVE_FOLDER, nameVal);
    }

    public void clickToRemoveOrDeleteMemberOutOfWorkgroup(String namVal, String titleVal) {
        waitForElementClickable(driver, SharedSpacePageUI.REMOVE_OR_EDIT_MEMBER_ICON, namVal, titleVal);
        clickToElement(driver, SharedSpacePageUI.REMOVE_OR_EDIT_MEMBER_ICON, namVal, titleVal);
    }

    public boolean isMemberDeletedSuccessfull(String nameVal) {
        waitForElementVisible(driver, SharedSpacePageUI.MESSAGE_REMOVED_MEMEBR_SUCCESS, nameVal);
        return isElementDisplayed(driver, SharedSpacePageUI.MESSAGE_REMOVED_MEMEBR_SUCCESS, nameVal);
    }

    public boolean isWorkgroupOrDriveDeletedSucessfull() {
        waitForElementVisible(driver, SharedSpacePageUI.MESSAGE_DELETED_WORKGROUP_OR_DRIVE_SUCCESS);
        return isElementDisplayed(driver, SharedSpacePageUI.MESSAGE_DELETED_WORKGROUP_OR_DRIVE_SUCCESS);
    }

    public boolean areButtonOptionsDisabled(String itemVal) {
        boolean status = true;
        if(isElementEnabled(driver, SharedSpacePageUI.DYNAMIC_FUNTION_ITEMS_WORKGROUP_OR_DRIVE_IN_DROPDOWN_MENU, itemVal)){
            status = false;
        } else{
            return status;
        }
        return status;
    }

    public boolean areRemoveORDeleteIconDisappearInMemberTab(String namVal, String roleVal) {
        boolean status = false;
        if(isElementUndisplayed(driver, SharedSpacePageUI.REMOVE_OR_EDIT_MEMBER_ICON, namVal, roleVal)){
            status = true;
        } else{
            return status;
        }
        return status;
    }

    public boolean areOptionOfFileDisabled(String optionVal) {
        boolean status = true;
        if(isElementEnabled(driver, SharedSpacePageUI.DYNAMIC_ITEMS_OF_FILE_IN_WORKGROUP_OR_DRIVE, optionVal)){
            status = false;
        } else{
            return status;
        }
        return status;
    }

    public boolean isCopyToOptionDisabled(String itemVal) {
        boolean status = true;
        if(isElementEnabled(driver, SharedSpacePageUI.COPY_TO_OPTION_IN_FOLDER_IN_FILE, itemVal)){
            status = false;
        } else{
            return status;
        }
        return status;
    }

    public boolean areUploadedFileByRecipientDisplayed(String[] fileNames01) {
        boolean status = false;
        for (String fileName : fileNames01){
            waitForElementVisible(driver, SharedSpacePageUI.NEW_NAME_WORKGROUP_DRIVE_FOLDER,fileName);
            if(isElementDisplayed(driver, SharedSpacePageUI.NEW_NAME_WORKGROUP_DRIVE_FOLDER,fileName)){
                status = true;
            } else{
                return status;
            }
        }
        return status;
    }

    public void clickToBackIcon() {
        waitForElementClickable(driver, SharedSpacePageUI.BACK_ICON_IN_WORKGROUP_OR_DRIVE);
        clickToElement(driver, SharedSpacePageUI.BACK_ICON_IN_WORKGROUP_OR_DRIVE);
    }

    public void hoverToCopyOption(String itemVal) {
        waitForElementVisible(driver, SharedSpacePageUI.COPY_TO_OPTION_IN_FOLDER_IN_FILE, itemVal);
        hoverToElement(driver, SharedSpacePageUI.COPY_TO_OPTION_IN_FOLDER_IN_FILE, itemVal);
    }

    public void clickToSharedSpaceAndMySpaceOptionInCopyItem(String itemVal) {
        waitForElementClickable(driver, SharedSpacePageUI.SHARED_SPACE_OPTION_IN_COPY_ITEM, itemVal);
        clickToElement(driver, SharedSpacePageUI.SHARED_SPACE_OPTION_IN_COPY_ITEM, itemVal);
    }

    public boolean isPickTheDestinationPopupDisplayed() {
        waitForElementVisible(driver, SharedSpacePageUI.PICK_DESTINATION_POPUP_COPY);
        return isElementDisplayed(driver, SharedSpacePageUI.PICK_DESTINATION_POPUP_COPY);
    }

    public void clickToCopyHereorCancelButton(String itemVal) {
        waitForElementClickable(driver,SharedSpacePageUI.COPYHERE_OR_CANCEL_BUTTON, itemVal);
        clickToElement(driver, SharedSpacePageUI.COPYHERE_OR_CANCEL_BUTTON, itemVal);
    }

    public boolean isMessageCopyOrMoveSuccessfully(String nameWRVal) {
        waitForElementVisible(driver,SharedSpacePageUI.POPUP_MESSAGE_COPY_OR_MOVE_FILE_SUCCESS, nameWRVal);
        return isElementDisplayed(driver,SharedSpacePageUI.POPUP_MESSAGE_COPY_OR_MOVE_FILE_SUCCESS,nameWRVal);
    }

    public void clickToFolderInsideWorkgroup(String nameFolder) {
        waitForElementClickable(driver,SharedSpacePageUI.FOLDER_AND_FILE_INSIDE_WORKGROUP, nameFolder);
        clickToElement(driver,SharedSpacePageUI.FOLDER_AND_FILE_INSIDE_WORKGROUP, nameFolder);
        waitForElementVisible(driver, SharedSpacePageUI.TITILE_NAME_FOLDER, nameFolder);
    }

    public void clickToViewButtonInMessagePopup() {
        waitForElementClickable(driver,SharedSpacePageUI.VIEW_BUTTON_IN_MESSAGE_POPUP);
        clickToElement(driver,SharedSpacePageUI.VIEW_BUTTON_IN_MESSAGE_POPUP);
    }

    public boolean isFileCopiedSuccessfull(String nameVal) {
        waitForElementVisible(driver,SharedSpacePageUI.FILE_HIGHLIGHTED_COPY_SUCCESS, nameVal);
        return isElementDisplayed(driver,SharedSpacePageUI.FILE_HIGHLIGHTED_COPY_SUCCESS, nameVal);
    }

    public boolean isMessageCopyOrMoveDisappear(String nameWorkgrpoup) {
        waitForElementInVisible(driver,SharedSpacePageUI.POPUP_MESSAGE_COPY_OR_MOVE_FILE_SUCCESS, nameWorkgrpoup);
        return isElementUndisplayed(driver,SharedSpacePageUI.POPUP_MESSAGE_COPY_OR_MOVE_FILE_SUCCESS, nameWorkgrpoup);
    }

    public void clickToAddButtonInPopup() {
        waitForElementClickable(driver,SharedSpacePageUI.ADD_BUTTON_IN_POPUP);
        clickToElement(driver,SharedSpacePageUI.ADD_BUTTON_IN_POPUP);
    }

    public void enterNameFolderInPopup(String nameFolder) {
        waitForElementVisible(driver,SharedSpacePageUI.INPUT_FOLDER_NAME_TEXTBOX_IN_POPUP);
        sendkeyToElement(driver,SharedSpacePageUI.INPUT_FOLDER_NAME_TEXTBOX_IN_POPUP, nameFolder);
    }

    public void clickToAddButtonForFolder() {
        waitForElementClickable(driver,SharedSpacePageUI.CREATE_FOLDER_TICK_ICON);
        clickToElement(driver,SharedSpacePageUI.CREATE_FOLDER_TICK_ICON);
    }

    public boolean isFolderNewlyCreationDisplayed(String nameFolder) {
        waitForElementVisible(driver,SharedSpacePageUI.FOLDER_AND_FILE_INSIDE_WORKGROUP, nameFolder);
        return isElementDisplayed(driver,SharedSpacePageUI.FOLDER_AND_FILE_INSIDE_WORKGROUP, nameFolder);
    }

    public void clickToBackIconInPopup() {
        waitForElementClickable(driver,SharedSpacePageUI.BACK_ICON_IN_POPUP);
        clickToElement(driver,SharedSpacePageUI.BACK_ICON_IN_POPUP);
    }

    public void clickToSearchIconInPopup() {
        waitForElementClickable(driver,SharedSpacePageUI.SEARCH_ICON_IN_POPUP);
        clickToElement(driver,SharedSpacePageUI.SEARCH_ICON_IN_POPUP);
    }

    public void enterNameWorkgroupOrDriveInPopup(String nameW) {
        waitForElementVisible(driver,SharedSpacePageUI.SEARCH_BY_NAME_TEXTBOX_IN_POPUP);
        sendkeyToElement(driver, SharedSpacePageUI.SEARCH_BY_NAME_TEXTBOX_IN_POPUP,nameW);
    }

    public boolean isTitleDisplayed(String titleW) {
        waitForElementVisible(driver,SharedSpacePageUI.BACK_TITLE_NAME_WORKGROUP, titleW);
        return isElementDisplayed(driver,SharedSpacePageUI.BACK_TITLE_NAME_WORKGROUP, titleW);
    }


//    // Khai bao 1 Array List
//    ArrayList<Date> arrayList = new ArrayList<Date>();
//    // Tim tat ca element matching vs dieu kien (Name/Price...)
//    List<WebElement> elementList = driver.findElements(By.xpath(locator));
//
//    // Lay text cua tung element add vao arrayList
//        for (WebElement element : elementList) {

//        arrayList.add(convertStringToDate(element.getText()));
//    }
//
//        System.out.println("----------- Du Lieu Tren UI:---------------");
//        for (Date name : arrayList) {
//        System.out.println(name);
//    }

}

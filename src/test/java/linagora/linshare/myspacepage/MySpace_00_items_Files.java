package linagora.linshare.myspacepage;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;
import testdata.UserData;

public class MySpace_00_items_Files extends BaseTest {
    WebDriver driver;
    LoginPageObject loginPage;
    HomePageObject homePage;
    CurrentUploadPageObject currentUploadPage;
    MySpacePageObject myspacePage;
    ReceivedSharesPageObject receivedsharesPage;

//    String[] fileNames = {"cat.jpg", "dog.jpg", "ETSBC"};

    String[] fileNames = {"cat.jpg", "dog.jpg"};

    @Parameters({"browser","url"})
    @BeforeClass
    public void beforeClass(String browserName, String urlValue){
        driver = getBrowserDriver(browserName,urlValue);
        loginPage = PageGeneratorManager.getLoginPage(driver);

        log.info("Pre-Condition - Step 01: Enter to Email textbox");
        loginPage.enterToEmailTextbox(UserData.Login.EMAIL);

        log.info("Pre-Condition - Step 02: Enter to Password textbox");
        loginPage.enterToPasswordTextbox(UserData.Login.PASSWORD);

        log.info("Pre-Condition - Step 03: Click to Login button");
        homePage = loginPage.clickToLoginButtonWithDefaultHomePage();

        log.info("Pre-Condition - Step 04: Verify the welcome message popup is displayed");
        verifyTrue(homePage.isWelcomeMessagePopupDisplayed());
    }

    @Test
    public void MySpace_01_Download_Uploaded_File(){
        log.info("HomePage - Step 01: Open The Current Upload Page");
        homePage.openMenuPageByNameAtLeftSideBar(driver,"Current uploads");
        currentUploadPage = PageGeneratorManager.getCurrentUploadPage(driver);

        log.info("CurrentUpload - Step 02: Upload Multiple File");
        currentUploadPage.uploadMultipleFiles(driver, fileNames);

        log.info("CurrentUpload - Step 03: Verify uploaded file successfully");
        verifyTrue(currentUploadPage.areFileUploadedSuccess(fileNames));

        log.info("CurrentUpload - Step 04: Verify the toast message displayed successfully ");
        verifyTrue(currentUploadPage.areMessageDisplayedSuccessful(fileNames));

        log.info("MySpace - Step 05: Open the My Space Page");
        currentUploadPage.openMenuPageByNameAtLeftSideBar(driver, "My space");
        myspacePage = PageGeneratorManager.getMySpacePage(driver);

        log.info("MySpace - Step 06: Click on the more options button of the file named ' "+ fileNames[0] + "'");
        myspacePage.fileActionsInList(driver, "cat.jpg", "more actions");

        log.info("MySpace - Step 07: Click on the Delete button of the file named ' "+ fileNames[0] + "'");
        myspacePage.fileActionsInDropdownMenu(driver, "Download");

        log.info("Verify the directory and downloaded file is not empty");
        isDownloadFileSuccessfull(true);
    }

    @Test
    public void MySpace_02_Download_Multiple_Uploaded_File(){
        log.info("MySpace - Step 01: Click to the checkbox icon at toolbar");
        myspacePage.clickToCheckboxtAtToolbar(driver);

        log.info("MySpace - Step 02: Click to the Delete button at toolbar");
        myspacePage.clickToDownloadAtToolBar();

        log.info("MySpace - Step 03: Re-click to checkbox icon at toolbar");
        myspacePage.ReclickToCheckboxtAtToolbar(driver);
    }

    @Test
    public void MySpace_03_User_can_Rename_File(){

        log.info("MySpace - Step 01: Click on the more options button of the file named ' "+ fileNames[0] + "'");
        myspacePage.fileActionsInList(driver, "cat.jpg", "more actions");

        log.info("MySpace - Step 02: Click on the Rename button of the file named ' "+ fileNames[0] + "'");
        myspacePage.fileActionsInDropdownMenu(driver, "Rename");

        log.info("MySpace - Step 03: Rename file named '" + fileNames[0] + "' to file named '"+ UserData.Rename.RENAME_FILE +"'");
        myspacePage.renamePopupDialog(driver, UserData.Rename.RENAME_FILE);

        log.info("MySpace - Step 04: Verify rename file successfull");
        verifyEquals(myspacePage.getRenamedFileSuccessfull(), UserData.Rename.RENAME_FILE);
    }

    @Test
    public void MySpace_04_User_Can_Duplicate_File() {

        log.info("MySpace - Step 01: Click on the more options button");
        myspacePage.fileActionsInList(driver, UserData.Rename.RENAME_FILE, "more actions");

        log.info("MySpace - Step 02: Click on the Duplicate button");
        myspacePage.fileActionsInDropdownMenu(driver, "Duplicate");

        log.info("MySpace - Step 03: Verify the duplicated file successfully");
        verifyEquals(myspacePage.getMessageDuplicateFileSuccessfull(), "The file has been copied successfully.");
    }

    @Test
    public void MySpace_05_User_Can_Duplicate_Multiple_Uploaded_File(){
        log.info("MySpace - Step 01: Click to the checkbox icon at toolbar");
        myspacePage.clickToCheckboxtAtToolbar(driver);

        log.info("MySpace - Step 02: Click to the Delete button at toolbar");
        myspacePage.clickActionsOnToolbar(driver,"more actions");

        log.info("MySpace - Step 03: Click To Duplicate button");
        myspacePage.clickToActionsInMoreOptionAtToolbar(driver, "Duplicate");

        log.info("MySpace - Step 04: Verify the duplicated file successfully");
        verifyEquals(myspacePage.getMessageDuplicateFileSuccessfull(), "The file has been copied successfully.");

        log.info("MySpace - Step 05: Re-click to checkbox icon at toolbar");
        myspacePage.ReclickToCheckboxtAtToolbar(driver);
    }

    @Test
    public void MySpace_06_Delete_Multile_File(){

        log.info("MySpace - Step 01: Click to the checkbox icon at toolbar");
        myspacePage.clickToCheckboxtAtToolbar(driver);

        log.info("MySpace - Step 02: Click to the Delete button at toolbar");
        myspacePage.clickActionsOnToolbar(driver,"Delete");

        log.info("MySpace - Step 03: Confirm delete in alert dialog");
        myspacePage.confirmAcceptFileInPopupDialog(driver, "Yes, delete");

        log.info("MySpace - Step 04: Verify success message displayed with value 'The items were deleted successfully.'");
        verifyEquals(myspacePage.getDeletedMultipleFileMesssageSuccessfull(), "The items were deleted successfully.");
    }

    @Test
    public void MySpace_07_Delete_Uploaded_File(){
        log.info("HomePage - Step 01: Open The Current Upload Page");
        homePage.openMenuPageByNameAtLeftSideBar(driver,"Current uploads");
        currentUploadPage = PageGeneratorManager.getCurrentUploadPage(driver);

        log.info("CurrentUpload - Step 02: Upload Multiple File");
        currentUploadPage.uploadMultipleFiles(driver, fileNames);

        log.info("CurrentUpload - Step 03: Verify uploaded file successfully");
        verifyTrue(currentUploadPage.areFileUploadedSuccess(fileNames));

        log.info("CurrentUpload - Step 04: Verify the toast message displayed successfully ");
        verifyTrue(currentUploadPage.areMessageDisplayedSuccessful(fileNames));

        log.info("MySpace - Step 05: Open the My Space Page");
        currentUploadPage.openMenuPageByNameAtLeftSideBar(driver, "My space");
        myspacePage = PageGeneratorManager.getMySpacePage(driver);

        log.info("MySpace - Step 06: Click on the more options button of the file named ' "+ fileNames[0] + "'");
        myspacePage.fileActionsInList(driver, "cat.jpg", "more actions");

        log.info("MySpace - Step 07: Click on the Delete button of the file named ' "+ fileNames[0] + "'");
        myspacePage.fileActionsInDropdownMenu(driver, "Delete");

        log.info("MySpace - Step 08: Confirm delete in alert dialog");
        myspacePage.confirmAcceptFileInPopupDialog(driver, "Yes, delete");

        log.info("MySpace - Step 09: Verify success message displayed with value 'The item was deleted successfully.'");
        verifyEquals(myspacePage.getdeletedmessagesuccessfull(), "The item was deleted successfully.");

        log.info("MySpace - Step 10: Verify file named '" + fileNames[0] + "' is deleted successfully");
        verifyTrue(myspacePage.isFileDeletedSuccessful(fileNames[0]));

        log.info("MySpace - Step 06: Click on the more options button of the file named ' "+ fileNames[1] + "'");
        myspacePage.fileActionsInList(driver, "dog.jpg", "more actions");

        log.info("MySpace - Step 07: Click on the Delete button of the file named ' "+ fileNames[1] + "'");
        myspacePage.fileActionsInDropdownMenu(driver, "Delete");

        log.info("MySpace - Step 08: Confirm delete in alert dialog");
        myspacePage.confirmAcceptFileInPopupDialog(driver, "Yes, delete");

        log.info("MySpace - Step 09: Verify success message displayed with value 'The item was deleted successfully.'");
        verifyEquals(myspacePage.getdeletedmessagesuccessfull(), "The item was deleted successfully.");

        log.info("MySpace - Step 10: Verify file named '" + fileNames[1] + "' is deleted successfully");
        verifyTrue(myspacePage.isFileDeletedSuccessful(fileNames[1]));

    }

//  @Test
    public void MySpace_01_Seach_files_By_Name(){
        log.info("HomePage - Step 01: Open The Current Upload Page");
        homePage.openMenuPageByNameAtLeftSideBar(driver,"Current uploads");
        currentUploadPage = PageGeneratorManager.getCurrentUploadPage(driver);

        log.info("CurrentUpload - Step 02: Upload Multiple File");
        currentUploadPage.uploadMultipleFiles(driver, fileNames);

        log.info("CurrentUpload - Step 03: Verify uploaded file successfully");
        verifyTrue(currentUploadPage.areFileUploadedSuccess(fileNames));

        log.info("CurrentUpload - Step 04: Verify the toast message displayed successfully ");
        verifyTrue(currentUploadPage.areMessageDisplayedSuccessful(fileNames));

        log.info("MySpace - Step 05: Open the My Space Page");
        currentUploadPage.openMenuPageByNameAtLeftSideBar(driver, "My space");
        myspacePage = PageGeneratorManager.getMySpacePage(driver);

        log.info("MySpace - Step 06: Search for uploaded file named '"+ fileNames[0] + "' ");
        myspacePage.searchFileByName(driver, fileNames[0]);

        log.info("MySpace - Step 07: Verify search results with filename '" + fileNames[0] + "' are returned correctly");
        verifyTrue(myspacePage.areRowRecordDisplayed(driver, "cat.jpg", "1.6 MB"));

        log.info("MySpace - Step 08: Search for uploaded file named '"+ fileNames[1] + "' ");
        myspacePage.searchFileByName(driver, fileNames[1]);

        log.info("MySpace - Step 09: Verify search results with filename '" + fileNames[1] + "' are returned correctly");
        verifyTrue(myspacePage.areRowRecordDisplayed(driver, "dog.jpg", "2.6 MB"));

        log.info("MySpace - Step 10: Search for uploaded file named '"+ fileNames[2] + "' ");
        myspacePage.searchFileByName(driver, fileNames[2]);

        log.info("MySpace - Step 11: Verify search results with filename '" + fileNames[2] + "' are returned correctly");
        verifyTrue(myspacePage.areRowRecordDisplayed(driver, "ETSBC", "9.5 MB"));
    }


    @AfterClass
    public void  afterClass(){
        closeBrowserAndDriver(driver);
        deletefoldercontainFile();
    }

}

package linagora.linshare.currentuploadpage;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;
import testdata.UserData;


public class CurrentUpload_UploadFile_And_ActionFile extends BaseTest {
    WebDriver driver;
    LoginPageObject loginPage;
    HomePageObject homePage;
    CurrentUploadPageObject currentUploadPage;
    MySpacePageObject myspacePage;

    String[] fileNames = {"cat.jpg"};

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
    public void CCurrentUpload_01_Pause_Single_File_During_File_Upload(){
        log.info("HomePage - Step 01: Open The Current Upload Page");
        homePage.openMenuPageByNameAtLeftSideBar(driver,"Current uploads");
        currentUploadPage = PageGeneratorManager.getCurrentUploadPage(driver);

        log.info("CurrentUpload - Step 02: Upload Multiple File");
        currentUploadPage.uploadMultipleFiles(driver, fileNames);

        log.info("CurrentUpload - Step 03: Click On The Pause Button");
        currentUploadPage.actionsForMultipleFiles(fileNames, "Pause");

        log.info("CurrentUpload - Step 04: Verify successfully paused files");
        verifyTrue(currentUploadPage.AreActionsOfFilesSusscessful(fileNames, "Pause"));

        log.info("CurrentUplaod - Step 05: Click on the Refresh page icon");
        currentUploadPage.refreshCurrentPage(driver);

        log.info("CurrentUpload - Step 06: Accept to Reload page");
        currentUploadPage.acceptAlert(driver);
    }

    @Test
    public void CurrentUpload_02_Play_Single_File_In_Paused_File(){
        log.info("CurrentUplaod - Step 01: Upload Multiple files");
        currentUploadPage.uploadMultipleFiles(driver, fileNames);

        log.info("CurrentUpload - Step 02: Click On the Pause Button");
        currentUploadPage.actionsForMultipleFiles(fileNames, "Pause");

        log.info("CurrentUpload - Step 03: Click On The Pay Button");
        currentUploadPage.actionsForMultipleFileByJS(fileNames, "Play");

        log.info("CurrentUpload - Step 04: Verify uploaded file successfully");
        verifyTrue(currentUploadPage.areFileUploadedSuccess(fileNames));

        log.info("CurrentUpload - Step 05: Verify the toast message displayed successfully ");
        verifyTrue(currentUploadPage.areMessageDisplayedSuccessful(fileNames));

        log.info("CurrentUplaod - Step 06: Click on the Refresh page icon");
        currentUploadPage.refreshCurrentPage(driver);
    }

    @Test
    public void CurrentUpload_03_Cancel_Single_File_Uploading(){

        log.info("CurrentUplaod - Step 01: Upload Multiple files");
        currentUploadPage.uploadMultipleFiles(driver, fileNames);

        log.info("CurrentUpload - Step 02:  Click on the Cancel button for All files");
        currentUploadPage.actionsForMultipleFiles(fileNames, "Stop");

        log.info("CurrentUpload - Step 03: Verify the Cancel All Files sucessfully");
        verifyTrue( currentUploadPage.AreActionsOfFilesSusscessful(fileNames, "Stop"));

        log.info("CurrentUplaod - Step 04: Click on the Refresh page icon");
        currentUploadPage.refreshCurrentPage(driver);

        log.info("CurrentUpload - Step 05: Accept to Reload page");
        currentUploadPage.acceptAlert(driver);
    }

    @Test
    public void CurrentUpload_04_Clear_Single_File_Uploaded_Success(){
        log.info("CurrentUpload - Step 01: Upload Multiple Files");
        currentUploadPage.uploadMultipleFiles(driver, fileNames);

        log.info("CurrentUpload - Step 02: Verify uploaded file successfully");
        verifyTrue(currentUploadPage.areFileUploadedSuccess(fileNames));

        log.info("CurrentUpload - Step 03: Verify the toast message displayed successfully ");
        verifyTrue(currentUploadPage.areMessageDisplayedSuccessful(fileNames));

        log.info("CurrentUpload - Step 04: Click the Clear button for All files");
        currentUploadPage.actionsForMultipleFiles(fileNames, "Clear");

        log.info("CurrentUpload - Step 05: Verify all files Are Cleared successfully");
        verifyTrue(currentUploadPage.areFilesClearedSuccessful(fileNames, "Clear"));

        log.info("CurrentUplaod - Step 06 Click on the Refresh page icon");
        currentUploadPage.refreshCurrentPage(driver);

        log.info("CurrentUpload - Step 07: Accept to Reload page");
        currentUploadPage.acceptAlert(driver);
    }

    @Test
    public void CurrentUpload_05_View_Single_File_Uploaded_Success(){
        log.info("CurrentUpload - Step 01: Upload Multiple Files");
        currentUploadPage.uploadMultipleFiles(driver, fileNames);

        log.info("CurrentUpload - Step 02: Verify uploaded file successfully");
        verifyTrue(currentUploadPage.areFileUploadedSuccess(fileNames));

        log.info("CurrentUpload - Step 03: Verify the toast message displayed successfully ");
        verifyTrue(currentUploadPage.areMessageDisplayedSuccessful(fileNames));

        log.info("CurrentUpload - Step 04: Click the Clear button for All files");
        myspacePage = currentUploadPage.clickToViewFileButton(fileNames);

        log.info("MySpace - Step 05: Hinghlighted file successfull");
        verifyTrue(myspacePage.ishighlightedFileSuccessful(fileNames));

    }


    @AfterClass
    public void  afterClass(){
        closeBrowserAndDriver(driver);
    }

}

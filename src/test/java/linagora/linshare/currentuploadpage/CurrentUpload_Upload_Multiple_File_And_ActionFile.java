package linagora.linshare.currentuploadpage;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;
import pageUIs.MySpacePageUI;
import testdata.UserData;


public class CurrentUpload_Upload_Multiple_File_And_ActionFile extends BaseTest {
    WebDriver driver;
    LoginPageObject loginPage;
    HomePageObject homePage;
    CurrentUploadPageObject currentUploadPage;
    MySpacePageObject myspacePage;

    String[] fileNames = {"cat.jpg", "dog.jpg", "ETSBC"};

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
    public void CurrentUpload_01_Upload_Multiple_Files_And_Pause_During_Files_Upload(){

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

//    @Test
    public void CurrentUpload_02_Upload_Multiple_Files_Play_Multiple_Paused_File(){

        log.info("CurrentUplaod - Step 01: Upload Multiple files");
        currentUploadPage.uploadMultipleFiles(driver, fileNames);

        log.info("CurrentUpload - Step 02: Click On the Pause Button");
        currentUploadPage.actionsForMultipleFiles(fileNames, "Pause");

        log.info("CurrentUpload - Step 03: Click On The Pay Button");
        currentUploadPage.actionsForMultipleFiles(fileNames, "Play");

        log.info("CurrentUpload - Step 04: Verify uploaded file successfully");
        verifyTrue(currentUploadPage.areFileUploadedSuccess(fileNames));

        log.info("CurrentUpload - Step 05: Verify the toast message displayed successfully ");
        verifyTrue(currentUploadPage.areMessageDisplayedSuccessful(fileNames));

        log.info("CurrentUplaod - Step 06: Click on the Refresh page icon");
        currentUploadPage.refreshCurrentPage(driver);
    }

//    @Test
    public void CurrentUpload_03_Upload_Multiple_Files_And_Cancel_All_Files(){

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

//    @Test
    public void CurrentUpload_04_Clear_Multiple_Files_Uploaded_Successful(){

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
    }


//    @AfterClass
    public void  afterClass(){
        closeBrowserAndDriver(driver);
    }

}

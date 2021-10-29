package linagora.linshare.myspacepage;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;
import testdata.UserData;

import java.io.File;

public class MySpace_05_items_Files extends BaseTest {
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

    }


//    @AfterClass
    public void  afterClass(){
        closeBrowserAndDriver(driver);
        deletefoldercontainFile();
    }

}

package linagora.linshare.homepage;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;
import testdata.UserData;

public class HomePage_Link_To_Page_And_Default_Page extends BaseTest {
    WebDriver driver;
    LoginPageObject loginPage;
    HomePageObject homePage;
    CurrentUploadPageObject currentUploadPage;
    ReceivedSharesPageObject receivedSharePage;
    MySpacePageObject mySpacePage;
    SharedSpacePageObject shareSpacePage;
    UploadRequestPageObject uploadRequestPage;


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
    public void HomePage_01_Click_Host_Your_File_Module(){
        log.info("Home Page - Step 01: Click to Host Your Files Button");
        mySpacePage = homePage.clickToHostYourFileButton();

        log.info("Myspace Page - Step 02: Verify the Myspace Page Is Displayed");
        verifyTrue(mySpacePage.isMySpacePageDisplayed());

        log.info("Myspace Page - Step 03: Click To Home Page Link");
        mySpacePage.openMenuPageByNameAtLeftSideBar(driver, "Home");
        homePage = PageGeneratorManager.getHomePage(driver);
    }

    @Test
    public void HomePage_02_Click_Upload_To_A_Group_Module(){
        log.info("Home Page - Step 01: Click to Upload To A Group Button");
        shareSpacePage = homePage.clickToUploadToAGroupButton();

        log.info("Myspace Page - Step 02: Verify the Share Space Page Is Displayed");
        verifyTrue(shareSpacePage.isSharedSpaceDisplayed());

        log.info("Myspace Page - Step 03: Click To Home Page Link");
        shareSpacePage.openMenuPageByNameAtLeftSideBar(driver, "Home");
        homePage = PageGeneratorManager.getHomePage(driver);

    }

    @Test
    public void HomePage_03_Click_Upload_Request_Module(){
        log.info("Home Page - Step 01: Click to Upload Request Button");
        uploadRequestPage = homePage.clickToUploadRequestButton();
        uploadRequestPage.sleepInSecond(3);

        log.info("Myspace Page - Step 02: Verify the Upload Request Page Is Displayed");
        verifyTrue(uploadRequestPage.isUploadRequestDisplayed());

        log.info("Myspace Page - Step 03: Click To Home Page Link");
        uploadRequestPage.openMenuPageByNameAtLeftSideBar(driver, "Home");
        homePage = PageGeneratorManager.getHomePage(driver);
        homePage.sleepInSecond(3);
    }

    @Test
    public void HomePage_04_Default_Home_Page_As_Myspace(){
        log.info("Home Page - Step 01: Click To default Home Page Checkbox");
        homePage.sleepInSecond(2);
        homePage.setDefaultHomePageByName(driver, "Host your files");

        log.info("Home Page - Step 02: Click To Avatar Icon At Header Bar ");
        homePage.sleepInSecond(2);
        homePage.clickToAvatarIconAtHeader(driver);

        log.info("Home Page - Step 03: Click To Logout Button");
        loginPage = homePage.clickToItemsByNameAtProfile(driver, "Logout");

        log.info("Login Page - Step 04: Enter to Email Textbox with value: " + UserData.Login.EMAIL);
        loginPage.enterToEmailTextbox(UserData.Login.EMAIL);

        log.info("Login Page - Step 05: Enter to Password Textbox with valye: " + UserData.Login.PASSWORD);
        loginPage.enterToPasswordTextbox(UserData.Login.PASSWORD);

        log.info("Login Page - Step 06: Click To Login Button And Return to Myspace Page");
        mySpacePage = loginPage.clickToLoginButtonWithDefaultMySpacePage();
        mySpacePage.sleepInSecond(3);

        log.info("Myspace Page - Step 07: Verify Myspace Page is Dispalyed ");
        verifyTrue(mySpacePage.isMySpacePageDisplayed());

        log.info("Myspace Page - Step 08: Click To Home Link And Return to Home Page");
        mySpacePage.openMenuPageByNameAtLeftSideBar(driver, "Home");
        homePage = PageGeneratorManager.getHomePage(driver);
        homePage.sleepInSecond(3);
    }

    @Test
    public void HomePage_05_Default_Home_Page_As_Shared_Space(){
        log.info("Home Page - Step 01: Click To default Home Page Checkbox As Shared Space Page");
        homePage.setDefaultHomePageByName(driver, "Upload to a group");

        log.info("Home Page - Step 02: Click To Avatar Icon At Header Bar ");
        homePage.clickToAvatarIconAtHeader(driver);
        homePage.sleepInSecond(2);

        log.info("Home Page - Step 03: Click To Logout Button");
        loginPage = homePage.clickToItemsByNameAtProfile(driver, "Logout");

        log.info("Login Page - Step 04: Enter to Email Textbox with value: " + UserData.Login.EMAIL);
        loginPage.enterToEmailTextbox(UserData.Login.EMAIL);

        log.info("Login Page - Step 05: Enter to Password Textbox with value: " + UserData.Login.PASSWORD);
        loginPage.enterToPasswordTextbox(UserData.Login.PASSWORD);

        log.info("Login Page - Step 06: Click To Login Button And Return to SharedSpace Page");
        shareSpacePage = loginPage.clickToLoginButtonWithDefaultSharedSpace();
        shareSpacePage.sleepInSecond(3);

        log.info("Sharedspace Page - Step 07: Verify Sharedspace Page is Dispalyed ");
        verifyTrue(shareSpacePage.isSharedSpaceDisplayed());

        log.info("Sharedspace Page - Step 08: Click To Home Link And Return to Home Page");
        mySpacePage.openMenuPageByNameAtLeftSideBar(driver, "Home");
        homePage = PageGeneratorManager.getHomePage(driver);
        homePage.sleepInSecond(3);
    }

    @Test
    public void HomePage_06_Default_Home_Page_As_Upload_Request(){
        log.info("Home Page - Step 01: Click To default Home Page Checkbox As Upload Request Page");
        homePage.setDefaultHomePageByName(driver, "Upload request");

        log.info("Home Page - Step 02: Click To Avatar Icon At Header Bar ");
        homePage.clickToAvatarIconAtHeader(driver);
        homePage.sleepInSecond(2);

        log.info("Home Page - Step 03: Click To Logout Button");
        loginPage = homePage.clickToItemsByNameAtProfile(driver, "Logout");

        log.info("Login Page - Step 04: Enter to Email Textbox with value: " + UserData.Login.EMAIL);
        loginPage.enterToEmailTextbox(UserData.Login.EMAIL);

        log.info("Login Page - Step 05: Enter to Password Textbox with value: " + UserData.Login.PASSWORD);
        loginPage.enterToPasswordTextbox(UserData.Login.PASSWORD);

        log.info("Login Page - Step 06: Click To Login Button And Return to SharedSpace Page");
        uploadRequestPage = loginPage.clickToLoginButtonWithDefaultUploadRequest();
        uploadRequestPage.sleepInSecond(3);

        log.info("uploadRequest Page - Step 07: Verify Sharedspace Page is Dispalyed ");
        verifyTrue(uploadRequestPage.isUploadRequestDisplayed());

        log.info("uploadRequest Page - Step 08: Click To Home Link And Return to Home Page");
        uploadRequestPage.openMenuPageByNameAtLeftSideBar(driver, "Home");
        homePage = PageGeneratorManager.getHomePage(driver);
        homePage.sleepInSecond(3);
    }

    @AfterClass
    public void  afterClass(){
        closeBrowserAndDriver(driver);
    }

}

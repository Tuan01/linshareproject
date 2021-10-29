package linagora.linshare.myspacepage;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;
import testdata.UserData;

public class MySpace_03_Multiple_File_And_One_Recipient_Share_File_Funtion extends BaseTest {
    WebDriver driver;
    LoginPageObject loginPage;
    HomePageObject homePage;
    CurrentUploadPageObject currentUploadPage;
    MySpacePageObject myspacePage;
    ReceivedSharesPageObject receivedsharesPage;
    LoginMailPageObject loginMailPage;
    WebMailPageObject webmailPage;

//    String[] fileNames = {"cat.jpg", "dog.jpg", "ETSBC"};

    String[] recipientNames = {"Peter WILSON", "Grant BIG"};
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

        log.info("Pre-Condition - Step 05: Open the My Space Page");
        homePage.openMenuPageByNameAtLeftSideBar(driver, "My space");
        myspacePage = PageGeneratorManager.getMySpacePage(driver);

        log.info("MySpace - Step 06: Click to the checkbox icon at toolbar");
        myspacePage.clickToCheckboxtAtToolbar(driver);

        log.info("MySpace - Step 07: Click to the Delete button at toolbar");
        myspacePage.clickActionsOnToolbar(driver,"Delete");

        log.info("MySpace - Step 08: Confirm delete in alert dialog");
        myspacePage.confirmAcceptFileInPopupDialog(driver, "Yes, delete");

        log.info("MySpace - Step 09: Verify success message displayed with value 'The items were deleted successfully.'");
        verifyEquals(myspacePage.getDeletedMultipleFileMesssageSuccessfull(), "The items were deleted successfully.");
    }


    @Test
    public void MySpace_01_Verify_User_Can_Share_Multiple_File_To_A_Recipient(){

    }

    @Test
    public void MySpace_02_Verify_Recipient_Receive_Shared_File_In_Received_Shares_Menu(){

    }

    @Test
    public void MySpace_03_Verify_User_Received_Shared_File_In_WebMail(){

    }

    @Test
    public void MySpace_04_Verify_User_View_Shared_File_By_Click_Link_In_Email(){

    }


//   @AfterClass
    public void  afterClass(){
       closeBrowserAndDriver(driver);
    }

}

package linagora.linshare.myspacepage;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;
import testdata.UserData;

public class MySpace_02_Multiple_Recipient_And_One_File_Share_File_Funtion extends BaseTest {
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
    public void MySpace_01_User_Can_Share_File_To_Multiple_Recipient(){
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

        log.info("MySpace - Step 06: Hover mouse to file named '" + fileNames[0] + "'");
        myspacePage.hoverMouseToFileInList(driver, "cat.jpg");

        log.info("MySpace - Step 07: Click on the more options button of the file named ' "+ fileNames[0] + "'");
        myspacePage.fileActionsInList(driver, "cat.jpg", "share");

        log.info("MySpace - Step 08: Input multiple recipient to Textbox");
        myspacePage.inputMultipleRecipientToTextbox(driver, recipientNames, "Add People");

        log.info("MySpace - Step 10: Enter the email subject wil value '" + UserData.QucikShare.EMAIL_SUBJECT +"'");
        myspacePage.inputToFieldsAtTextAreaFormInQuickShare(driver, UserData.QucikShare.EMAIL_SUBJECT, "Email subject");

        log.info("MySpace - Step 11: Enter the email message with value '" + UserData.QucikShare.EMAIL_MESSAGE + "'");
        myspacePage.inputToFieldsAtTextAreaFormInQuickShare(driver, UserData.QucikShare.EMAIL_MESSAGE, "Enter your email message");

        log.info("MySpace - Step 12: Click to More option link");
        myspacePage.clickToMoreOptionLink(driver);

        log.info("MySpace - Step 13: Enter the share description textarea with value '" + UserData.QucikShare.NOTE_SHARE +"'");
        myspacePage.inputToFieldsAtTextAreaFormInQuickShare(driver, UserData.QucikShare.NOTE_SHARE, "Enter a sharing note");

        log.info("MySpace - Step 14: Click to the Share Button");
        myspacePage.clickToShareOrForwardOrCancelButton(driver, "Share");

        log.info("MySpace - Step 15: Verify the shared file successfully");
        verifyEquals(myspacePage.getToastMessageForActions(driver, "Your file was successfully sent."), "Your file was successfully sent.");

    }

    @Test
    public void MySpace_03_Verify_The_First_Recipient_Received_Document_Shared_By_Isssuer_In_Received_Shares(){

        log.info("MySpace - Step 01: Click on Human icon on top menu");
        myspacePage.clickToAvatarIconAtHeader(driver);

        log.info("MySpace - Step 02: Click Logout button for account named '" + UserData.Login.EMAIL +"'");
        myspacePage.clickToItemsByNameAtProfile(driver, "Logout");
//        verifyTrue(myspacePage.isAlertDisplayed(driver));

        loginPage = PageGeneratorManager.getLoginPage(driver);

        log.info("LoginPage - Step 03: Recipient shared document- Fill all information and Login");
        loginPage.enterToEmailTextbox("peter.wilson@linshare.org");
        loginPage.enterToPasswordTextbox("secret");
        homePage = loginPage.clickToLoginButtonWithDefaultHomePage();
//        verifyTrue(homePage.isWelcomeMessagePopupDisplayed());

        log.info("HomePage - Step 04: Go to the Received shares menu");
        homePage.openMenuPageByNameAtLeftSideBar(driver, "Received shares");
        receivedsharesPage = PageGeneratorManager.getReceivedSharePage(driver);

        log.info("ReceivedSharePage- Step 05: Verify Recipient Received File Share Successfully ");
        verifyTrue(receivedsharesPage.isRecipientReceivedFileShareSuccessfully(fileNames[0], "in 3 months", "1.6 MB"));
        verifyEquals(receivedsharesPage.getNameOfRecipientShareFile(fileNames[0]),UserData.Login.FULL_NAME_OWNER);
    }

    @Test
    public void MySpace_03_Verify_The_Second_Recipient_Received_Document_Shared_By_Isssuer_In_Received_Shares(){
        log.info("MySpace - Step 01: Click on Human icon on top menu");
        myspacePage.clickToAvatarIconAtHeader(driver);

        log.info("MySpace - Step 02: Click Logout button for account named");
        myspacePage.clickToItemsByNameAtProfile(driver, "Logout");
//        verifyTrue(myspacePage.isAlertDisplayed(driver));
        loginPage = PageGeneratorManager.getLoginPage(driver);

        log.info("LoginPage - Step 03: Recipient shared document- Fill all information and Login");
        loginPage.enterToEmailTextbox("grant.big@linshare.org");
        loginPage.enterToPasswordTextbox("secret");
        homePage = loginPage.clickToLoginButtonWithDefaultHomePage();
//        verifyTrue(homePage.isWelcomeMessagePopupDisplayed());

        log.info("HomePage - Step 04: Go to the Received shares menu");
        homePage.openMenuPageByNameAtLeftSideBar(driver, "Received shares");
        receivedsharesPage = PageGeneratorManager.getReceivedSharePage(driver);

        log.info("ReceivedSharePage- Step 05: Verify Recipient Received File Share Successfully ");
        verifyTrue(receivedsharesPage.isRecipientReceivedFileShareSuccessfully(fileNames[0], "in 3 months", "1.6 MB"));
        verifyEquals(receivedsharesPage.getNameOfRecipientShareFile(fileNames[0]),UserData.Login.FULL_NAME_OWNER);
    }

    @Test
    public void MySpace_04_Owner_Cancel_Share_To_All_Recipient(){
        log.info("MySpace - Step 01: Click on Human icon on top menu");
        myspacePage.clickToAvatarIconAtHeader(driver);

        log.info("MySpace - Step 02: Click Logout button for account named");
        myspacePage.clickToItemsByNameAtProfile(driver, "Logout");
        loginPage = PageGeneratorManager.getLoginPage(driver);

        log.info("LoginPage - Step 03: Recipient shared document- Fill all information and Login");
        loginPage.enterToEmailTextbox("walker.mccallister@linshare.org");
        loginPage.enterToPasswordTextbox("secret");
        homePage = loginPage.clickToLoginButtonWithDefaultHomePage();

        log.info("MySpace - Step 04: Open the My Space Page");
        currentUploadPage.openMenuPageByNameAtLeftSideBar(driver, "My space");
        myspacePage = PageGeneratorManager.getMySpacePage(driver);

        log.info("MySpace - Step 05: Click To Shared Link beside the file named '"+ fileNames[0] +"'");
        myspacePage.clickSharedLinkNextToFileName(driver, "cat.jpg");

        log.info("MySpace - Step 06: Cancel multiple recipient");
        myspacePage.cancelMultipleRecipientAtShareDetail(recipientNames);

        log.info("MySpace - Step 07: Click on the close icon at sidebar sharing detail");
        myspacePage.clickToCloseSideBarDetail(driver);

        log.info("MySpace - Step 08: Verify owner cancel shared file successfully");
        verifyTrue(myspacePage.isCancelSharedFileSuccessfull(fileNames[0]));

    }

    @Test
    public void MySpace_05_Verify_File_Shared_Disappear_out_of_Received_shares_of_Recipients(){

    }

   @AfterClass
    public void  afterClass(){
       closeBrowserAndDriver(driver);
    }

}

package linagora.linshare.myspacepage;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.*;
import testdata.UserData;

public class MySpace_01_One_File_And_One_Recipient_Share_File_Funtion extends BaseTest {
    WebDriver driver;
    LoginPageObject loginPage;
    HomePageObject homePage;
    CurrentUploadPageObject currentUploadPage;
    MySpacePageObject myspacePage;
    ReceivedSharesPageObject receivedsharesPage;
    LoginMailPageObject loginMailPage;
    WebMailPageObject webmailPage;

//    String[] fileNames = {"cat.jpg", "dog.jpg", "ETSBC"};

    String[] fileNames = {"cat.jpg", "dog.jpg", "mouse.jpg"};

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
    public void MySpace_01_User_Can_Share_File(){
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

        log.info("MySpace - Step 08: Input the recipient to Textbox");
        myspacePage.inputToRecipientField(driver, UserData.QucikShare.EMAIL_RECIPIENT1, "Add People");

        log.info("MySpace - Step 09: Select recipient from suggesstion list");
        myspacePage.selectRecipientFromSuggestionList(driver);

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
    public void MySpace_02_User_Can_View_Share_Detail(){

        log.info("MySpace - Step 01: Click To Shared Link beside the file named '"+ fileNames[0] +"'");
        myspacePage.clickSharedLinkNextToFileName(driver, "cat.jpg");

        log.info("MySpace - Step 02: Search by recipients with value '"+ UserData.QucikShare.EMAIL_RECIPIENT1 +"'");
        myspacePage.inputToRecipientField(driver, UserData.QucikShare.EMAIL_RECIPIENT1, "Search by recipients");

        log.info("MySpace - Step 04: Verify the search results recipient named '" + UserData.QucikShare.EMAIL_RECIPIENT1 +"' is dipslayed ");
        verifyEquals(myspacePage.getFullNameOfRecipientAtSharesTab(), UserData.QucikShare.EMAIL_RECIPIENT1);

        log.info("MySpace - Step 05: Click to Recipient to view shared detail ");
        myspacePage.clickToRecipientToViewSharedDetail();

        log.info("MySpace - Step 06: Verify email of shared perrson displayed correctly");
        verifyEquals(myspacePage.getEmailRecipientshared(), "peter.wilson@linshare.org");

        log.info("MySpace - Step 07: Verify the shared file name displayed correctly");
        verifyEquals(myspacePage.getSharedFileNameAtSharedDetail(), fileNames[0]);

        log.info("MySpace - Step 08: Verify the shared file name  '" +myspacePage.getSharedDateAtShareDetail() +"'displayed correctly");
        verifyEquals(myspacePage.getSharedDateAtShareDetail(), UserData.QucikShare.CREATE_SHARED_DATE);

        log.info("MySpace - Step 09: Click on the close icon at sidebar sharing detail");
        myspacePage.clickToCloseSideBarDetail(driver);
    }

    @Test
    public void MySpace_03_Verify_Recipient_Received_Document_Shared_By_Isssuer_In_Received_Shares(){

        log.info("MySpace - Step 01: Click on Human icon on top menu");
        myspacePage.clickToAvatarIconAtHeader(driver);

        log.info("MySpace - Step 02: Click Logout button for account named '" + UserData.Login.EMAIL +"'");
        myspacePage.clickToItemsByNameAtProfile(driver, "Logout");
        myspacePage.acceptAlert(driver);
        loginPage = PageGeneratorManager.getLoginPage(driver);

        log.info("LoginPage - Step 03: Recipient shared document- Fill all information and Login");
        loginPage.enterToEmailTextbox(UserData.Loginecipient.EMAIL_RECIPEINT);
        loginPage.enterToPasswordTextbox(UserData.Loginecipient.PASSWORD_RECIPIENT);
        homePage = loginPage.clickToLoginButtonWithDefaultHomePage();
        verifyTrue(homePage.isWelcomeMessagePopupDisplayed());

        log.info("HomePage - Step 04: Go to the Received shares menu");
        homePage.openMenuPageByNameAtLeftSideBar(driver, "Received shares");
        receivedsharesPage = PageGeneratorManager.getReceivedSharePage(driver);

        log.info("ReceivedSharePage- Step 05: Verify Recipient Received File Share Successfully ");
        verifyTrue(receivedsharesPage.isRecipientReceivedFileShareSuccessfully(fileNames[0], "in 3 months", "1.6 MB"));
        verifyEquals(receivedsharesPage.getNameOfRecipientShareFile(fileNames[0]),UserData.Login.FULL_NAME_OWNER);
        closeBrowserAndDriver(driver);
    }

    @Parameters({"browser","urlmail"})
    @Test
    public void WebMail_04_Verify_Recipient_Received_New_Email_By_Issser_In_Webmail(String browserName, String urlValue){
        driver = getBrowserDriver(browserName,urlValue);
        loginMailPage = PageGeneratorManager.getLoginMailPage(driver);

        log.info("LoginMailPage - Step 01: Enter To Email Textbox with value '"+UserData.WebMail.EMAIL_WEBMAIL_RECIPIENT_01 +"'");
        loginMailPage.enterToEmailTextbox(UserData.WebMail.EMAIL_WEBMAIL_RECIPIENT_01);

        log.info("LoginMailPage - Step 02: Enter To Password Textbox with value '"+UserData.WebMail.PASSWORD_WEBMAIL +"'");
        loginMailPage.enterToPasswordTextbox(UserData.WebMail.PASSWORD_WEBMAIL);

        log.info("LoginMailPage - Step 03: Click To Login Button");
        webmailPage = loginMailPage.clickToLoginButton();

        log.info("WebMailPage - Step 04: Verify Login To WebMail Successfully");
        verifyTrue(webmailPage.isWebMailDisplayed());

        log.info("WebMailPage - Step 05: Click Email Received In Message List");
        webmailPage.clickToEmailReceviedInMessageList("Automation Testing for Linshare");

        log.info("WebMailPage - Step 06: Verify the Email subject displayed corrrectly");
        verifyTrue(webmailPage.isEmailSubjectAtWebMailDetail());

        log.info("WebMailPage - Step 07: Verify field 'To' with value '"+UserData.WebMail.EMAIL_WEBMAIL_RECIPIENT_01+"' ");
        verifyEquals(webmailPage.areValueForToOrReplyToFieldAtWebMail("To"), UserData.WebMail.EMAIL_WEBMAIL_RECIPIENT_01);

        log.info("WebMailPage - Step 08: Verify field 'Reply To' with value '"+UserData.WebMail.EMAIL_WEBMAIL_OWNER +"'");
        verifyEquals(webmailPage.areValueForToOrReplyToFieldAtWebMail("Reply-To"), UserData.WebMail.EMAIL_WEBMAIL_OWNER);

        log.info("WebMailPage - Step 09: Verify field 'Create Date' with value '"+UserData.WebMail.CREATE_DATE+"' ");
        verifyEquals(webmailPage.isValueForCreateAndExpiryDateFieldAtWebMail("Creation date"), UserData.WebMail.CREATE_DATE);

        log.info("WebMailPage - Step 10: Verify field 'Expiry Date' with value '"+UserData.WebMail.EXPIRY_DATE+"' ");
        verifyEquals(webmailPage.isValueForCreateAndExpiryDateFieldAtWebMail("Expiry date"), UserData.WebMail.EXPIRY_DATE);

        log.info("WebMailPage - Step 11: Verify all shared file displayed in the attached file field");
        verifyTrue(webmailPage.isAttachedFileAtWebMailDisplayed(fileNames[0]));

    }

    @Test
    public void WebMail_05_Verify_Recipeint_Can_View_Shared_File_By_Click_On_Name_File_Link_In_Webmail(){

        String parrentID = webmailPage.getParrentIDWindowOrTab(driver);

        log.info("WebMailPage - Step 01: Click To text 'Link' under hyperlink ");
        loginPage = webmailPage.clickToHyperlinkLinkTextAtWebMail("link");

        log.info("LoginPage - Step 02: Switch To Login Page");
        loginPage.switchToLoginLinsharePage("LinShare");

        log.info("LoginPage - Step 03: Enter to Password textbox");
        loginPage.enterToEmailTextbox(UserData.Loginecipient.EMAIL_RECIPEINT);

        log.info("LoginPage - Step 04: Enter to Password textbox");
        loginPage.enterToPasswordTextbox(UserData.Loginecipient.PASSWORD_RECIPIENT);

        log.info("LoginPage - Step 05: Click to Login button");
        receivedsharesPage = loginPage.clickToLoginButtonWithDefaultReceivedShare();

        log.info("ReceiveSharePage- Step 06: Verify the messsage popup with value 'You are currently viewing a restricted file selection.' is displayed");
        verifyTrue(receivedsharesPage.isMessagePopupViewingRestrictedFileselectionDisplayed());

        log.info("ReceivedSharePage - Step 07: Close Tab And keep parent Tab");
        receivedsharesPage.clostTabWindownExceptParentTab(parrentID);

        log.info("WebMailPAge - Step 08: Click To hyperlink as filename");
        loginPage = webmailPage.clickToHyperlinkLinkTextAtWebMail(fileNames[0]);

        log.info("LoginPage - Step 09: Switch To Login Page");
        loginPage.switchToLoginLinsharePage("LinShare");

        log.info("ReceiveSharePage- Step 10: Verify the messsage popup with value 'You are currently viewing a restricted file selection.' is displayed");
        verifyTrue(receivedsharesPage.isMessagePopupViewingRestrictedFileselectionDisplayed());
    }


   @AfterClass
    public void  afterClass(){
       closeBrowserAndDriver(driver);
    }

}

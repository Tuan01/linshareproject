package linagora.linshare.sharedspace;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;
import testdata.UserData;

public class SharedSpace_06_Folder_In_Workgroup extends BaseTest {
    WebDriver driver;
    LoginPageObject loginPage;
    HomePageObject homePage;
    CurrentUploadPageObject currentUploadPage;
    MySpacePageObject myspacePage;
    ReceivedSharesPageObject receivedsharesPage;
    SharedSpacePageObject sharedspacePage;


    String[] fileNames = {"cat.jpg", "dog.jpg"};
    String folderName[] = {"FolderUpload"};

    String defaultnameworkgroup = "New workgroup";

    @Parameters({"browser","url"})
    @BeforeTest
    public void beforeTest(String browserName, String urlValue){
        driver = getBrowserForDriver(browserName,urlValue);
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

//    @Test
    public void SharedSpace_00_User_Can_Create_WorkGroup_With_Name_Default(){
        log.info("HomePage - Step 01: Open The Shared Space Menu");
        homePage.openMenuPageByNameAtLeftSideBar(driver,"Shared space");
        sharedspacePage = PageGeneratorManager.getShareSpacePage(driver);

        log.info("SharedSpace - Step 02: Click On The Add Menu Group Button ");
        sharedspacePage.clickToAddMenuGroupButton();

        log.info("SharedSpace - Step 03: Click On The Workgroup Item");
        sharedspacePage.clickToItemsInMenuGroup("Workgroup");

        log.info("SharedSpace - Step 04: Verify The Popup Displayed");
        verifyTrue(sharedspacePage.isPopupCreateDisplayed("CREATE_NEW_WORKGROUP"));

        log.info("SharedSpace - Step 05: Press Key Enter");
        sharedspacePage.pressKeyEnterForUpload();
    }

    @Test
    public void SharedSpace_01_User_Can_Create_WorkGroup_With_New_Name(){
        log.info("HomePage - Step 01: Open The Shared Space Menu");
        homePage.openMenuPageByNameAtLeftSideBar(driver,"Shared space");
        sharedspacePage = PageGeneratorManager.getShareSpacePage(driver);

        log.info("SharedSpace - Step 02: Click On The Add Menu Group Button ");
        sharedspacePage.clickToAddMenuGroupButton();

        log.info("SharedSpace - Step 03: Click On The Workgroup Item");
        sharedspacePage.clickToItemsInMenuGroup("Workgroup");

        log.info("SharedSpace - Step 04: Verify The Create New Workgroup Popup Displayed");
        verifyTrue(sharedspacePage.isPopupCreateDisplayed("CREATE_NEW_WORKGROUP"));

        log.info("SharedSpace - Step 05: Enter New Name of workgroup with value'"+UserData.WorkgGroup.NAME_WORKGRPOUP+"'");
        sharedspacePage.enterNameToTextBoxInPopup(UserData.WorkgGroup.NAME_WORKGRPOUP);

        log.info("SharedSpace - Step 06: Verify workgroup created successfully");
        verifyEquals(sharedspacePage.getNameofWorkgroupDriveFolder("Automation Testing"), UserData.WorkgGroup.NAME_WORKGRPOUP);
    }

    @Test
    public void SharedSpace_02_User_Can_Create_Folder_In_Workgroup_With_Admin_Right(){
        log.info("SharedSpace - Step 01: Click To Workgroup Named '"+UserData.WorkgGroup.NAME_WORKGRPOUP+"'");
        sharedspacePage.clickToNameWorkgroupORDriveORFolder(UserData.WorkgGroup.NAME_WORKGRPOUP);

        log.info("SharedSpace - Step 02: Verify Detail of Workgroup Dispalayed");
        verifyTrue(sharedspacePage.isAccessToWorkGroupSuccessfull(UserData.WorkgGroup.NAME_WORKGRPOUP));

        log.info("SharedSpace - Step 03: Click On The Add Menu Group Button ");
        sharedspacePage.clickToAddMenuGroupButton();

        log.info("SharedSpace - Step 04: Click On The Folder Item");
        sharedspacePage.clickToItemsInMenuGroup("Folder");

        log.info("SharedSpace - Step 05: Verify The Create New Folder Popup Displayed");
        verifyTrue(sharedspacePage.isPopupCreateDisplayed("CREATE_NEW_FOLDER"));

        log.info("SharedSpace - Step 07: Enter New Name of workgroup with value'"+UserData.Folder.NAME_FOLDER+"'");
        sharedspacePage.enterNameToTextBoxInPopup(UserData.Folder.NAME_FOLDER);

        log.info("SharedSpace - Step 07: Verify Folder created successfully");
        verifyEquals(sharedspacePage.getNameofWorkgroupDriveFolder("AT_New Folder"), UserData.Folder.NAME_FOLDER);
    }

    @Test
    public void SharedSpace_03_User_Can_Upload_File_In_Workgroup(){
        log.info("SharedSpace - Step 01: Upload Mutiple File");
        sharedspacePage.uploadMultipleFiles(driver, fileNames);

        log.info("SharedSpace - Step 02: Verify the toast message displayed successfully ");
        verifyTrue(sharedspacePage.arePopupMessageUploadSuccessfullDisplayed(fileNames));

        log.info("ShareSpace - Step 05: Verify list of file are displayed");
        verifyTrue(sharedspacePage.areListUploadFileDisplayed(fileNames));
    }

    @Test
    public void SharedSpace_04_User_Can_Create_Folder_In_Folder(){
        log.info("SharedSpace - Step 01: Click To Folder Named '"+UserData.Folder.NAME_FOLDER+"'");
        sharedspacePage.clickToNameWorkgroupORDriveORFolder(UserData.Folder.NAME_FOLDER);

        log.info("SharedSpace - Step 02: Verify Detail of Folder Dispalayed");
        verifyTrue(sharedspacePage.isAccessToFolderSuccessfull(UserData.Folder.NAME_FOLDER));

        log.info("SharedSpace - Step 03: Click On The Add Menu Group Button ");
        sharedspacePage.clickToAddMenuGroupButton();

        log.info("SharedSpace - Step 04: Click On The Folder Item");
        sharedspacePage.clickToItemsInMenuGroup("Folder");

        log.info("SharedSpace - Step 05: Verify The Create New Folder Popup Displayed");
        verifyTrue(sharedspacePage.isPopupCreateDisplayed("CREATE_NEW_FOLDER"));

        log.info("SharedSpace - Step 07: Enter New Name of workgroup with value'"+UserData.Folder.NAME_FOLDER_01+"'");
        sharedspacePage.enterNameToTextBoxInPopup(UserData.Folder.NAME_FOLDER_01);

        log.info("SharedSpace - Step 07: Verify Folder created successfully");
        verifyEquals(sharedspacePage.getNameofWorkgroupDriveFolder("AT_New Folder_01"), UserData.Folder.NAME_FOLDER_01);
    }

    @Test
    public void SharedSpace_05_User_Can_Upload_File_In_Folder(){
        log.info("SharedSpace - Step 01: Upload Mutiple File");
        sharedspacePage.uploadMultipleFiles(driver, fileNames);

        log.info("SharedSpace - Step 02: Verify the toast message displayed successfully ");
        verifyTrue(sharedspacePage.arePopupMessageUploadSuccessfullDisplayed(fileNames));

        log.info("ShareSpace - Step 05: Verify list of file are displayed");
        verifyTrue(sharedspacePage.areListUploadFileDisplayed(fileNames));
    }

    @Test
    public void SharedSpace_06_User_Can_Add_Member_With_Admin_Right(){

    }

    @Test
    public void SharedSpace_07_User_Can_Upload_File_In_Folder(){

    }





    @AfterTest
    public void afterTest(){
//        sharedspacePage.searchFileByName(driver, defaultnameworkgroup);
//        sharedspacePage.clickToCheckboxtAtToolbar(driver);
//        sharedspacePage.clickActionsOnToolbar(driver,"Delete");
//        sharedspacePage.confirmAcceptFileInPopupDialog(driver, "Yes, delete");
//        sharedspacePage.clearTextAtTextboxSearch();
//        closeBrowserAndDriver(driver);
    }

}

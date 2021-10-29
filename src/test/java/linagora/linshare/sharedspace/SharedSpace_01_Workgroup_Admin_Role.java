package linagora.linshare.sharedspace;

import commons.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.*;
import pageUIs.SharedSpacePageUI;
import testdata.UserData;

public class SharedSpace_01_Workgroup_Admin_Role extends BaseTest {
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
    @BeforeClass
    public void beforeClass(String browserName, String urlValue){
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
    public void SharedSpace_05_User_Can_Add_Member_With_Admin_Right(){

        log.info("SharedSpace - Step 01: Click On The Add Menu Group Button ");
        sharedspacePage.clickToAddMenuGroupButton();

        log.info("SharedSpace - Step 02: Click On The Add a Member Item");
        sharedspacePage.clickToItemsInMenuGroup("Add a member");

        log.info("SharedSpace - Step 03: Select role 'Admin' for recipient");
        sharedspacePage.selectToRoleMemberInDropdownList("Admin");

        log.info("SharedSpace - Step 04: Enter the recipient");
        sharedspacePage.inputToRecipientField(driver,UserData.WorkgGroup.EMAIL_RECIPIENT1,"Add People");

        log.info("SharedSpace - Step 05: Select the member from seuggesstion");
        sharedspacePage.selectRecipientFromSuggestionList(driver);

        log.info("Shared Space - Step 06: Verify recipient is added successfully ");
        verifyTrue(sharedspacePage.viewMemberAndRole("Peter WILSON", "Admin"));

        log.info("SharedSpace - Step 07: Close slidebar");
        sharedspacePage.clickToCloseSideBarDetail(driver);
    }

    @Test
    public void SharedSpace_06_Invited_User_Receive_Workgroup_With_Admin_Right_In_Shared_Space(){
        log.info("MySpace - Step 01: Click on Human icon on top menu");
        sharedspacePage.clickToAvatarIconAtHeader(driver);

        log.info("MySpace - Step 02: Click Logout button for account named '" + UserData.Login.EMAIL +"'");
        sharedspacePage.clickToItemsByNameAtProfile(driver, "Logout");
        sharedspacePage.closeAlert(driver);
        loginPage = PageGeneratorManager.getLoginPage(driver);

        log.info("LoginPage - Step 03: Invited User fills in some information to log in");
        loginPage.enterToEmailTextbox(UserData.WorkgGroup.EMAIL_RECIPIENT1);
        loginPage.enterToPasswordTextbox("secret");
        homePage = loginPage.clickToLoginButtonWithDefaultHomePage();
//        verifyTrue(homePage.isWelcomeMessagePopupDisplayed());

        log.info("HomePage - Step 05: Open the shared spacw");
        homePage.openMenuPageByNameAtLeftSideBar(driver,"Shared space");
        sharedspacePage = PageGeneratorManager.getShareSpacePage(driver);

        log.info("SharedSpace - Step 06: Verify workgroup named '"+UserData.WorkgGroup.NAME_WORKGRPOUP+"' is displayed");
        verifyTrue(sharedspacePage.isWorkgroupOrDriverDisplayedAtSharedSpace(UserData.WorkgGroup.NAME_WORKGRPOUP));
    }

    @Test
    public void SharedSpace_07_User_Can_Rename_Workgroup_With_Admin_Right(){
        log.info("SharedSpace - Step 01: Click on More Option icon of workgroup named '"+UserData.WorkgGroup.NAME_WORKGRPOUP+"'");
        sharedspacePage.clickActionsOfWorkgroupAdnDriveInList(driver, UserData.WorkgGroup.NAME_WORKGRPOUP, "more actions");

        log.info("SharedSpace - Step 02: Click on Rename Button");
        sharedspacePage.fileActionsInDropdownMenu(driver, "Rename");

        log.info("SharedSpace - Step 03: Verify The Create New Workgroup Popup Displayed");
        verifyTrue(sharedspacePage.isPopupCreateDisplayed("ACTION.RENAME"));

        log.info("SharedSpace - Step 04: Enter New Name of workgroup with value'"+UserData.WorkgGroup.RENAME_WORKGROUP+"'");
        sharedspacePage.enterNameToTextBoxInPopup(UserData.WorkgGroup.RENAME_WORKGROUP);

        log.info("SharedSpace - Step 05: Verify workgroup created successfully");
        verifyEquals(sharedspacePage.getNameofWorkgroupDriveFolder("AT_Automation Testing"), UserData.WorkgGroup.RENAME_WORKGROUP);
    }

    @Test
    public void SharedSpace_08_User_Can_Remove_Member_With_Admin_Right(){
        log.info("SharedSpace - Step 01: Click on More Option icon of workgroup named '"+UserData.WorkgGroup.RENAME_WORKGROUP+"'");
        sharedspacePage.clickActionsOfWorkgroupAdnDriveInList(driver, UserData.WorkgGroup.RENAME_WORKGROUP, "more actions");

        log.info("SharedSpace - Step 02: Click on Rename Button");
        sharedspacePage.fileActionsInDropdownMenu(driver, "Add a member");

        log.info("SharedSpace - Step 03: Remove member named '"+UserData.WorkgGroup.NAME_OWNER+"'");
        sharedspacePage.clickToRemoveOrDeleteMemberOutOfWorkgroup(UserData.WorkgGroup.NAME_OWNER, "Remove team member");

        log.info("SharedSpace - Step 04: Confirm delete in alert dialog");
        sharedspacePage.confirmAcceptFileInPopupDialog(driver, "Yes, delete");

        log.info("SharedSpace - Step 05: Verify the member named '"+UserData.WorkgGroup.NAME_OWNER+"' is moved out of workgroup");
        verifyTrue(sharedspacePage.isMemberDeletedSuccessfull(UserData.WorkgGroup.NAME_OWNER));

        log.info("SharedSpace - Step 06: Close slidebar");
        sharedspacePage.clickToCloseSideBarDetail(driver);

    }

    @Test
    public void SharedSpace_09_User_Can_Detete_Workgroup_With_Admin_Right(){
        log.info("SharedSpace - Step 01: Click on More Option icon of workgroup named '"+UserData.WorkgGroup.RENAME_WORKGROUP+"'");
        sharedspacePage.clickActionsOfWorkgroupAdnDriveInList(driver, UserData.WorkgGroup.RENAME_WORKGROUP, "more actions");

        log.info("SharedSpace - Step 02: Click on Delete Button");
        sharedspacePage.fileActionsInDropdownMenu(driver, "Delete");

        log.info("SharedSpace - Step 04: Confirm delete in alert dialog");
        sharedspacePage.confirmAcceptFileInPopupDialog(driver, "Yes, delete");

        log.info("SharedSpace - Step 05: Verify workgroup named named '"+UserData.WorkgGroup.RENAME_WORKGROUP+"' is deleted successfully");
        verifyTrue(sharedspacePage.isWorkgroupOrDriveDeletedSucessfull());
    }


//    @Test
    public void SharedSpace_10_User_Can_Edit_Member_With_Admin_Right(){
        // TC dang bug nen ignore sau lam
    }

    @AfterClass
    public void afterClass(){
        closeBrowserAndDriver(driver);

    }

}

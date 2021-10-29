package linagora.linshare.sharedspace;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;
import testdata.UserData;

public class SharedSpace_04_Workgroup_Reader_Role extends BaseTest {
    WebDriver driver;
    LoginPageObject loginPage;
    HomePageObject homePage;
    CurrentUploadPageObject currentUploadPage;
    MySpacePageObject myspacePage;
    ReceivedSharesPageObject receivedsharesPage;
    SharedSpacePageObject sharedspacePage;


    String[] fileNames = {"cat.jpg", "dog.jpg"};
    String[] fileNames01 = {"mouse.jpg", "SHALLOW.docx"};
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

        log.info("ShareSpace - Step 03: Verify list of file are displayed");
        verifyTrue(sharedspacePage.areListUploadFileDisplayed(fileNames));
    }

    @Test
    public void SharedSpace_04_User_Can_Add_Member_With_Contribute_Right(){

        log.info("SharedSpace - Step 01: Click On The Add Menu Group Button ");
        sharedspacePage.clickToAddMenuGroupButton();

        log.info("SharedSpace - Step 02: Click On The Add a Member Item");
        sharedspacePage.clickToItemsInMenuGroup("Add a member");

        log.info("SharedSpace - Step 03: Select role 'Contributor' for recipient");
        sharedspacePage.selectToRoleMemberInDropdownList("Contributor");

        log.info("SharedSpace - Step 04: Enter the recipient");
        sharedspacePage.inputToRecipientField(driver,UserData.WorkgGroup.EMAIL_RECIPIENT1,"Add People");

        log.info("SharedSpace - Step 05: Select the member from seuggesstion");
        sharedspacePage.selectRecipientFromSuggestionList(driver);

        log.info("Shared Space - Step 06: Verify recipient is added successfully ");
        verifyTrue(sharedspacePage.viewMemberAndRole("Peter WILSON", "Contributor"));

        log.info("SharedSpace - Step 07: Close slidebar");
        sharedspacePage.clickToCloseSideBarDetail(driver);
    }

    @Test
    public void SharedSpace_05_Invited_User_Receive_Workgroup_With_Contributor_Right_In_Shared_Space(){
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

        log.info("HomePage - Step 05: Open the shared spacw");
        homePage.openMenuPageByNameAtLeftSideBar(driver,"Shared space");
        sharedspacePage = PageGeneratorManager.getShareSpacePage(driver);

        log.info("SharedSpace - Step 06: Verify workgroup named '"+UserData.WorkgGroup.NAME_WORKGRPOUP+"' is displayed");
        verifyTrue(sharedspacePage.isWorkgroupOrDriverDisplayedAtSharedSpace(UserData.WorkgGroup.NAME_WORKGRPOUP));
    }

    @Test
    public void SharedSpace_06_Invited_User_With_Contributor_Right_Can_Not_Add_Memember_And_Delete_And_Rename_Workgroup(){
        log.info("SharedSpace - Step 01: Click on More Option icon of workgroup named '"+UserData.WorkgGroup.NAME_WORKGRPOUP+"'");
        sharedspacePage.clickActionsOfWorkgroupAdnDriveInList(driver, UserData.WorkgGroup.NAME_WORKGRPOUP, "more actions");

        log.info("SharedSpace - Step 02: Verify the option 'Add a member' is disabled");
        verifyFalse(sharedspacePage.areButtonOptionsDisabled("Add a member"));

        log.info("SharedSpace - Step 03: Verify the option 'Delete' is disabled");
        verifyFalse(sharedspacePage.areButtonOptionsDisabled("Delete"));

        log.info("SharedSpace - Step 04: Verify the option 'Rename' is disabled");
        verifyFalse(sharedspacePage.areButtonOptionsDisabled("Rename"));

        log.info("SharedSpace - Step 05: Close the context menu");
        sharedspacePage.clickActionsOfWorkgroupAdnDriveInList(driver, UserData.WorkgGroup.NAME_WORKGRPOUP, "more actions");
    }

    @Test
    public void SharedSpace_07_Invited_User_With_Contributor_Right_Can_Not_Update_Member_Role_And_Remove_Member_Workgroup(){
        log.info("SharedSpace - Step 01: Click on the Members icon");
        sharedspacePage.clickActionsOfWorkgroupAdnDriveInList(driver, UserData.WorkgGroup.NAME_WORKGRPOUP, "Members");

        log.info("SharedSpace - Step 02: Verify the invited user can not update member role");
        verifyTrue(sharedspacePage.areRemoveORDeleteIconDisappearInMemberTab("Peter WILSON", "Edit user rights"));

        log.info("SharedSpace - Step 03: Verify the invited user can not remove member role");
        verifyTrue(sharedspacePage.areRemoveORDeleteIconDisappearInMemberTab("Peter WILSON", "Remove team member"));

        log.info("SharedSpace - Step 04: Verify the owner can not update member role");
        verifyTrue(sharedspacePage.areRemoveORDeleteIconDisappearInMemberTab("Walker MCCALLISTER", "Edit user rights"));

        log.info("SharedSpace - Step 05: Verify the owner can not remove member");
        verifyTrue(sharedspacePage.areRemoveORDeleteIconDisappearInMemberTab("Walker MCCALLISTER", "Remove team member"));
    }

    @Test
    public void SharedSpace_08_Invited_User_With_Contributor_Right_Can_view_Member_And_Role_And_Upload_File_Folder(){
        log.info("SharedSpace - Step 01: Verify the user can view member and role of invited user ");
        verifyTrue(sharedspacePage.viewMemberAndRole("Peter WILSON", "Contributor"));

        log.info("SharedSpace - Step 02: Verify the user can view member and role of owner");
        verifyTrue(sharedspacePage.viewMemberAndRole("Walker MCCALLISTER", "Admin"));

        log.info("SharedSpace - Step 03: Close slidebar");
        sharedspacePage.clickToCloseSideBarDetail(driver);

        log.info("SharedSpace - Step 04: Click to workgroup named '"+ UserData.WorkgGroup.NAME_WORKGRPOUP+"'");
        sharedspacePage.clickToNameWorkgroupORDriveORFolder(UserData.WorkgGroup.NAME_WORKGRPOUP);

        log.info("SharedSpace - Step 05: Invited user upload some file");
        sharedspacePage.uploadMultipleFiles(driver, fileNames01);

        log.info("SharedSpace - Step 06: Verify the toast message displayed successfully ");
        verifyTrue(sharedspacePage.arePopupMessageUploadSuccessfullDisplayed(fileNames01));

        log.info("ShareSpace - Step 07: Verify list of file are displayed");
        verifyTrue(sharedspacePage.areListUploadFileDisplayed(fileNames01));

        log.info("SharedSpace - Step 08: Click On The Add Menu Group Button ");
        sharedspacePage.clickToAddMenuGroupButton();

        log.info("SharedSpace - Step 09: Click On The Folder Item");
        sharedspacePage.clickToItemsInMenuGroup("Folder");

        log.info("SharedSpace - Step 10: Verify The Create New Folder Popup Displayed");
        verifyTrue(sharedspacePage.isPopupCreateDisplayed("CREATE_NEW_FOLDER"));

        log.info("SharedSpace - Step 11: Enter New Name of workgroup with value'"+UserData.Folder.INVITED_USER_NAME_FOLDER+"'");
        sharedspacePage.enterNameToTextBoxInPopup(UserData.Folder.INVITED_USER_NAME_FOLDER);

        log.info("SharedSpace - Step 12: Verify Folder created successfully");
        verifyEquals(sharedspacePage.getNameofWorkgroupDriveFolder("AT_Invited_User_New Folder"), UserData.Folder.INVITED_USER_NAME_FOLDER);
    }

    @Test
    public void SharedSpace_09_Invited_User_With_Contributor_Right_Can_Not_Move_File_And_Delete_In_Workgroup(){
        log.info("SharedSpace - Step 01: Click on the more options button of the file named ' "+ fileNames[0] + "'");
        sharedspacePage.clickActionsOfWorkgroupAdnDriveInList(driver, "cat.jpg", "more actions");

        log.info("SharedSpace - Step 02: Verify option 'Move' of file named ' "+ fileNames[0] + "' is disabled");
        verifyFalse(sharedspacePage.areOptionOfFileDisabled("Move"));

        log.info("SharedSpace - Step 03: Verify option 'Delete' of file named '"+fileNames[0]+"' is disabled ");
        verifyFalse(sharedspacePage.areOptionOfFileDisabled("Delete"));

        log.info("SharedSpace - Step 01: Re-Click on the more options button of the file named ' "+ fileNames[0] + "'");
        sharedspacePage.clickActionsOfWorkgroupAdnDriveInList(driver, "cat.jpg", "more actions");
    }

    @Test
    public void SharedSpace_10_Invited_User_With_Contributor_Right_Can_Not_Move_And_Delete_Copy_Duplicate_Folder_In_Workgroup(){
        log.info("SharedSpace - Step 01: Click on the more options button of the folder named ' "+UserData.Folder.NAME_FOLDER+ "'");
        sharedspacePage.clickActionsOfWorkgroupAdnDriveInList(driver, "AT_New Folder", "more actions");

        log.info("SharedSpace - Step 02: Verify option 'Move' of folder named ' "+ UserData.Folder.NAME_FOLDER + "' is disabled");
        verifyFalse(sharedspacePage.areOptionOfFileDisabled("Move"));

        log.info("SharedSpace - Step 03: Verify option 'Delete' of folder named ' "+UserData.Folder.NAME_FOLDER+ "' is disabled");
        verifyFalse(sharedspacePage.areOptionOfFileDisabled("Delete"));

        log.info("SharedSpace - Step 04: Verify option 'Duplicate' of folder named ' "+UserData.Folder.NAME_FOLDER+ "' is disabled");
        verifyFalse(sharedspacePage.areOptionOfFileDisabled("Duplicate"));

        log.info("SharedSpace - Step 05: Verify option 'Duplicate' of folder named ' "+UserData.Folder.NAME_FOLDER+ "' is disabled");
        verifyFalse(sharedspacePage.areOptionOfFileDisabled("Duplicate"));

        log.info("SharedSpace - Step 06: Verify option 'Copy to' of folder named ' "+UserData.Folder.NAME_FOLDER+ "' is disabled");
        verifyFalse(sharedspacePage.isCopyToOptionDisabled("Copy to"));
    }

    @Test
    public void SharedSpace_11_Verify_Owner_Can_View_Uploaded_File_And_Folder_From_Invited_User(){
        log.info("SharedSpace - Step 01: Click on Human icon on top menu");
        sharedspacePage.clickToAvatarIconAtHeader(driver);

        log.info("MySpace - Step 02: Click Logout button for account named '" +UserData.WorkgGroup.EMAIL_RECIPIENT1+"'");
        sharedspacePage.clickToItemsByNameAtProfile(driver, "Logout");
        sharedspacePage.closeAlert(driver);
        loginPage = PageGeneratorManager.getLoginPage(driver);

        log.info("LoginPage - Step 03: Invited User fills in some information to log in");
        loginPage.enterToEmailTextbox(UserData.WorkgGroup.EMAIL_OWNER);
        loginPage.enterToPasswordTextbox("secret");
        homePage = loginPage.clickToLoginButtonWithDefaultHomePage();

        log.info("HomePage - Step 04: Open the shared spacw");
        homePage.openMenuPageByNameAtLeftSideBar(driver,"Shared space");
        sharedspacePage = PageGeneratorManager.getShareSpacePage(driver);

        log.info("SharedSpace - Step 05: Click to workgroup named '"+ UserData.WorkgGroup.NAME_WORKGRPOUP+"'");
        sharedspacePage.clickToNameWorkgroupORDriveORFolder(UserData.WorkgGroup.NAME_WORKGRPOUP);

        log.info("SharedSpace - Step 06: Verify workgroup named '"+fileNames01+"' is displayed");
        verifyTrue(sharedspacePage.areUploadedFileByRecipientDisplayed(fileNames01));

        log.info("SharedSpace - Step 06: Click on back icon to return to workgroup");
        sharedspacePage.clickToBackIcon();
    }


    @AfterClass
    public void afterClass(){
        sharedspacePage.clickActionsOfWorkgroupAdnDriveInList(driver, UserData.WorkgGroup.NAME_WORKGRPOUP, "more actions");
        sharedspacePage.fileActionsInDropdownMenu(driver, "Delete");
        sharedspacePage.confirmAcceptFileInPopupDialog(driver, "Yes, delete");
        verifyTrue(sharedspacePage.isWorkgroupOrDriveDeletedSucessfull());
        closeBrowserAndDriver(driver);
    }

}

package linagora.linshare.sharedspace;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;
import pageUIs.SharedSpacePageUI;
import testdata.UserData;

public class SharedSpace_05_File_In_Workgroup extends BaseTest {
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

    @Test
    public void SharedSpace_01_User_Can_Create_Mutiple_WorkGroup_With_New_Name(){
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

        log.info("SharedSpace - Step 07: Click On The Add Menu Group Button ");
        sharedspacePage.clickToAddMenuGroupButton();

        log.info("SharedSpace - Step 08: Click On The Workgroup Item");
        sharedspacePage.clickToItemsInMenuGroup("Workgroup");

        log.info("SharedSpace - Step 09: Verify The Create New Workgroup Popup Displayed");
        verifyTrue(sharedspacePage.isPopupCreateDisplayed("CREATE_NEW_WORKGROUP"));

        log.info("SharedSpace - Step 10: Enter New Name of workgroup with value'"+UserData.WorkgGroup.NAME_WORKGRPOUP_01+"'");
        sharedspacePage.enterNameToTextBoxInPopup(UserData.WorkgGroup.NAME_WORKGRPOUP_01);

        log.info("SharedSpace - Step 11: Verify workgroup created successfully");
        verifyEquals(sharedspacePage.getNameofWorkgroupDriveFolder(UserData.WorkgGroup.NAME_WORKGRPOUP_01), UserData.WorkgGroup.NAME_WORKGRPOUP_01);
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

        log.info("SharedSpace - Step 08: Click on back icon to return to workgroup");
        sharedspacePage.clickToBackIcon();

        log.info("SharedSpace - Step 09: Click To Workgroup Named '"+UserData.WorkgGroup.NAME_WORKGRPOUP_01+"'");
        sharedspacePage.clickToNameWorkgroupORDriveORFolder(UserData.WorkgGroup.NAME_WORKGRPOUP_01);

        log.info("SharedSpace - Step 10: Verify Detail of Workgroup Dispalayed");
        verifyTrue(sharedspacePage.isAccessToWorkGroupSuccessfull(UserData.WorkgGroup.NAME_WORKGRPOUP_01));

        log.info("SharedSpace - Step 11: Click On The Add Menu Group Button ");
        sharedspacePage.clickToAddMenuGroupButton();

        log.info("SharedSpace - Step 12: Click On The Folder Item");
        sharedspacePage.clickToItemsInMenuGroup("Folder");

        log.info("SharedSpace - Step 13: Verify The Create New Folder Popup Displayed");
        verifyTrue(sharedspacePage.isPopupCreateDisplayed("CREATE_NEW_FOLDER"));

        log.info("SharedSpace - Step 14: Enter New Name of workgroup with value'"+UserData.Folder.NAME_FOLDER_02+"'");
        sharedspacePage.enterNameToTextBoxInPopup(UserData.Folder.NAME_FOLDER_02);

        log.info("SharedSpace - Step 15: Verify Folder created successfully");
        verifyEquals(sharedspacePage.getNameofWorkgroupDriveFolder(UserData.Folder.NAME_FOLDER_02), UserData.Folder.NAME_FOLDER_02);
    }

    @Test
    public void SharedSpace_03_User_Can_Upload_File_In_Workgroup(){
        log.info("SharedSpace - Step 01: Upload Mutiple File");
        sharedspacePage.uploadMultipleFiles(driver, fileNames);

        log.info("SharedSpace - Step 02: Verify the toast message displayed successfully ");
        verifyTrue(sharedspacePage.arePopupMessageUploadSuccessfullDisplayed(fileNames));

        log.info("ShareSpace - Step 03: Verify list of file are displayed");
        verifyTrue(sharedspacePage.areListUploadFileDisplayed(fileNames));

        log.info("SharedSpace - Step 04: Click on back icon to return to workgroup");
        sharedspacePage.clickToBackIcon();

        log.info("SharedSpace - Step 05: Click To Workgroup Named '"+UserData.WorkgGroup.NAME_WORKGRPOUP+"'");
        sharedspacePage.clickToNameWorkgroupORDriveORFolder(UserData.WorkgGroup.NAME_WORKGRPOUP);

        log.info("SharedSpace - Step 06: Verify Detail of Workgroup Dispalayed");
        verifyTrue(sharedspacePage.isAccessToWorkGroupSuccessfull(UserData.WorkgGroup.NAME_WORKGRPOUP));

        log.info("SharedSpace - Step 01: Upload Mutiple File");
        sharedspacePage.uploadMultipleFiles(driver, fileNames);

        log.info("SharedSpace - Step 02: Verify the toast message displayed successfully ");
        verifyTrue(sharedspacePage.arePopupMessageUploadSuccessfullDisplayed(fileNames));

        log.info("ShareSpace - Step 03: Verify list of file are displayed");
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

        log.info("SharedSpace - Step 08: Click on back icon to return to workgroup");
        sharedspacePage.clickToBackIcon();
    }

    @Test
    public void SharedSpace_05_User_Can_Copy_File_Inside_The_Same_Workgroup(){
        log.info("SharedSpace - Step 01: Click on the more options button of the file named ' "+ fileNames[0] + "'");
        sharedspacePage.clickActionsOfWorkgroupAdnDriveInList(driver, "cat.jpg", "more actions");

        log.info("SharedSpace - Step 02: Hover to 'Copy To' option");
        sharedspacePage.hoverToCopyOption("Copy to");

        log.info("SharedSpace - Step 03: Click To 'Shared space' option");
        sharedspacePage.clickToSharedSpaceAndMySpaceOptionInCopyItem("Shared space");

        log.info("SharedSpace - Step 04: The popup 'Pick the destination' is displayed");
        verifyTrue(sharedspacePage.isPickTheDestinationPopupDisplayed());

        log.info("SharedSpace - Step 05: Click To 'Copy Here' button");
        sharedspacePage.clickToCopyHereorCancelButton("Copy here");

        log.info("SharedSpace - Step 06: Verify copied file to workgroup named '"+UserData.WorkgGroup.NAME_WORKGRPOUP+"' successfully");
        verifyTrue(sharedspacePage.isMessageCopyOrMoveSuccessfully(UserData.WorkgGroup.NAME_WORKGRPOUP));

        log.info("SharedSpace - Step 07: Verify the message");
        verifyTrue(sharedspacePage.isMessageCopyOrMoveDisappear(UserData.WorkgGroup.NAME_WORKGRPOUP));
    }

    @Test
    public void SharedSpace_06_User_Can_Copy_File_To_Folder_Inside_Workgroup(){
        log.info("SharedSpace - Step 01: Click on the more options button of the file named ' "+ fileNames[1] + "'");
        sharedspacePage.clickActionsOfWorkgroupAdnDriveInList(driver, "dog.jpg", "more actions");

        log.info("SharedSpace - Step 02: Hover to 'Copy To' option");
        sharedspacePage.hoverToCopyOption("Copy to");

        log.info("SharedSpace - Step 03: Click To 'Shared space' option");
        sharedspacePage.clickToSharedSpaceAndMySpaceOptionInCopyItem("Shared space");

        log.info("SharedSpace - Step 04: The popup 'Pick the destination' is displayed");
        verifyTrue(sharedspacePage.isPickTheDestinationPopupDisplayed());

        log.info("SharedSpace - Step 05: Click to the folder named '"+UserData.Folder.NAME_FOLDER+"'");
        sharedspacePage.clickToFolderInsideWorkgroup(UserData.Folder.NAME_FOLDER);

        log.info("SharedSpace - Step 06: Click To 'Copy Here' button");
        sharedspacePage.clickToCopyHereorCancelButton("Copy here");

        log.info("SharedSpace - Step 07: Verify copied file to folder named '"+UserData.Folder.NAME_FOLDER+"' successfully");
        verifyTrue(sharedspacePage.isMessageCopyOrMoveSuccessfully(UserData.Folder.NAME_FOLDER));

        log.info("SharedSpace - Step 08: Click To 'VIEW' button in popup message");
        sharedspacePage.clickToViewButtonInMessagePopup();

        log.info("SharedSpace - Step 09: Verife copied file is highlighted in the folder  named '"+UserData.Folder.NAME_FOLDER+"'");
        verifyTrue(sharedspacePage.isFileCopiedSuccessfull(fileNames[1]));

        log.info("SharedSpace - Step 10: Click on back icon to return to workgroup");
        sharedspacePage.clickToBackIcon();
    }

    @Test
    public void SharedSpace_07_User_Can_Create_Folder_Inside_Workgroup_And_Copy_File_To_That_Folder(){
        log.info("SharedSpace - Step 01: Click on the more options button of the file named ' "+ fileNames[0] + "'");
        sharedspacePage.clickActionsOfWorkgroupAdnDriveInList(driver, "cat.jpg", "more actions");

        log.info("SharedSpace - Step 02: Hover to 'Copy To' option");
        sharedspacePage.hoverToCopyOption("Copy to");

        log.info("SharedSpace - Step 03: Click To 'Shared space' option");
        sharedspacePage.clickToSharedSpaceAndMySpaceOptionInCopyItem("Shared space");

        log.info("SharedSpace - Step 04: The popup 'Pick the destination' is displayed");
        verifyTrue(sharedspacePage.isPickTheDestinationPopupDisplayed());

        log.info("SharedSpace - Step 05: Click To Add Button ");
        sharedspacePage.clickToAddButtonInPopup();

        log.info("SharedSpace - Step 06: Enter a folder named '"+UserData.Folder.NAME_FOLDER_03+"'");
        sharedspacePage.enterNameFolderInPopup(UserData.Folder.NAME_FOLDER_03);

        log.info("SharedSpace - Step 07: Click to add button of folder named '"+UserData.Folder.NAME_FOLDER_03+"'");
        sharedspacePage.clickToAddButtonForFolder();

        log.info("SharedSpace - Step 08: Verify the folder named '"+UserData.Folder.NAME_FOLDER_03+"' displayed in popup");
        verifyTrue(sharedspacePage.isFolderNewlyCreationDisplayed(UserData.Folder.NAME_FOLDER_03));

        log.info("SharedSpace - Step 05: Click to the folder named '"+UserData.Folder.NAME_FOLDER_03+"'");
        sharedspacePage.clickToFolderInsideWorkgroup(UserData.Folder.NAME_FOLDER_03);

        log.info("SharedSpace - Step 06: Click To 'Copy Here' button");
        sharedspacePage.clickToCopyHereorCancelButton("Copy here");

        log.info("SharedSpace - Step 07: Verify copied file to folder named '"+UserData.Folder.NAME_FOLDER_03+"' successfully");
        verifyTrue(sharedspacePage.isMessageCopyOrMoveSuccessfully(UserData.Folder.NAME_FOLDER_03));

        log.info("SharedSpace - Step 08: Click To 'VIEW' button in popup message");
        sharedspacePage.clickToViewButtonInMessagePopup();

        log.info("SharedSpace - Step 09: Verife copied file is highlighted in the folder  named '"+UserData.Folder.NAME_FOLDER_03+"'");
        verifyTrue(sharedspacePage.isFileCopiedSuccessfull(fileNames[0]));

        log.info("SharedSpace - Step 10: Click on back icon to return to workgroup");
        sharedspacePage.clickToBackIcon();
    }

    @Test
    public void SharedSpace_08_User_Can_Search_Workgroup_And_Copy_File_To_That_Workgroup(){
        log.info("SharedSpace - Step 01: Click on the more options button of the file named ' "+ fileNames[1] + "'");
        sharedspacePage.clickActionsOfWorkgroupAdnDriveInList(driver, "dog.jpg", "more actions");

        log.info("SharedSpace - Step 02: Hover to 'Copy To' option");
        sharedspacePage.hoverToCopyOption("Copy to");

        log.info("SharedSpace - Step 03: Click To 'Shared space' option");
        sharedspacePage.clickToSharedSpaceAndMySpaceOptionInCopyItem("Shared space");

        log.info("SharedSpace - Step 04: The popup 'Pick the destination' is displayed");
        verifyTrue(sharedspacePage.isPickTheDestinationPopupDisplayed());

        log.info("SharedSpace - Step 05: Click on the back icon in popup");
        sharedspacePage.clickToBackIconInPopup();

        log.info("SharedSpace - Step 06: Click on Search icon in Popup");
        sharedspacePage.clickToSearchIconInPopup();

        log.info("SharedSpace - Step 07: Enter name workgroup or drive in Popup");
        sharedspacePage.enterNameWorkgroupOrDriveInPopup(UserData.WorkgGroup.NAME_WORKGRPOUP_01);

        log.info("SharedSpace - Step 08: Verify Search Result are displayed");
        verifyTrue(sharedspacePage.isFolderNewlyCreationDisplayed(UserData.WorkgGroup.NAME_WORKGRPOUP_01));

        log.info("SharedSpace - Step 05: Click to the workgroup named '"+UserData.WorkgGroup.NAME_WORKGRPOUP_01+"'");
        sharedspacePage.clickToFolderInsideWorkgroup(UserData.WorkgGroup.NAME_WORKGRPOUP_01);

        log.info("SharedSpace - Step 06: Click To 'Copy Here' button");
        sharedspacePage.clickToCopyHereorCancelButton("Copy here");

        log.info("SharedSpace - Step 07: Verify copied file to workgroup named '"+UserData.WorkgGroup.NAME_WORKGRPOUP_01+"' successfully");
        verifyTrue(sharedspacePage.isMessageCopyOrMoveSuccessfully(UserData.WorkgGroup.NAME_WORKGRPOUP_01));

        log.info("SharedSpace - Step 08: Click To 'VIEW' button in popup message");
        sharedspacePage.clickToViewButtonInMessagePopup();

//        log.info("SharedSpace - Step 09: Verife copied file is highlighted in the folder  named '"+UserData.WorkgGroup.NAME_WORKGRPOUP_01+"'");
//        verifyTrue(sharedspacePage.isFileCopiedSuccessfull(fileNames[1]));   // file copy tang len 1  -> chua handle day

        log.info("SharedSpace - Step 10: Click on back icon to return to workgroup");
        sharedspacePage.clickToBackIcon();
    }

//    @AfterTest
    public void afterTest(){
        closeBrowserAndDriver(driver);
    }

}

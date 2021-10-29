package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
    public static LoginPageObject getLoginPage(WebDriver driver){
        return new LoginPageObject(driver);
    }

    public  static HomePageObject getHomePage(WebDriver driver){
        return new HomePageObject(driver);
    }

    public  static CurrentUploadPageObject getCurrentUploadPage(WebDriver driver){
        return new CurrentUploadPageObject(driver);
    }

    public  static ReceivedSharesPageObject getReceivedSharePage(WebDriver driver){
        return new ReceivedSharesPageObject(driver);
    }

    public  static MySpacePageObject getMySpacePage(WebDriver driver){
        return new MySpacePageObject(driver);
    }

    public static SharedSpacePageObject getShareSpacePage(WebDriver driver){
        return new SharedSpacePageObject(driver);
    }

    public static UploadRequestPageObject getUploadRequestPage(WebDriver driver){
        return new UploadRequestPageObject(driver);
    }

    public  static LoginMailPageObject getLoginMailPage(WebDriver driver){
        return new LoginMailPageObject(driver);
    }
    public static WebMailPageObject getWebMailPage(WebDriver driver){
        return  new WebMailPageObject(driver);
    }

}

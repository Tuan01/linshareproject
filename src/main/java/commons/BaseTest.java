package commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.File;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver driver;
    File folder;
    //	private String projectLocation = System.getProperty("user.dir");
    private String osName = System.getProperty("os.name");

    // Define log variable
    protected final Log log;

    // Constructor
    protected BaseTest() {
        log = LogFactory.getLog(getClass());
    }

    protected WebDriver getBrowserForDriver(String browserName, String url) {
        Browser browser = Browser.valueOf(browserName.toUpperCase());
        if (browser == Browser.FIREFOX) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOpt = new FirefoxOptions();
            driver = new FirefoxDriver(firefoxOpt);
        } else if (browser == Browser.CHROME) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOpt = new ChromeOptions();
            chromeOpt.addArguments("--disable-javascript");
            chromeOpt.addArguments("--disable-notifications");
            chromeOpt.addArguments("--disable-popup-blocking");
           driver = new ChromeDriver(chromeOpt);
        } else {
            throw new RuntimeException("Please input the browser name!");
        }
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName, String url) {
        folder = new File(UUID.randomUUID().toString());
        folder.mkdir();
//        String  downloadFilePath = System.getProperty("user.dir") + getDirectorySlash("downloadFiles");
        Browser browser = Browser.valueOf(browserName.toUpperCase());
        if (browser == Browser.FIREFOX) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOpt = new FirefoxOptions();
            firefoxOpt.addPreference("browser.download.dir", folder.getAbsolutePath());
            firefoxOpt.addPreference("browser.download.folderList", 2);
            firefoxOpt.addPreference("browser.helperApps.neverAsk.saveToDisk", "image/jpeg, application/pdf");
            firefoxOpt.addPreference("pdfjs.disabled", true);
            driver = new FirefoxDriver(firefoxOpt);

        } else if (browser == Browser.CHROME) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOpt = new ChromeOptions();
            HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
//            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put( "profile.default_content_setting_values.automatic_downloads", 1 );
//            chromePrefs.put("download.prompt_for_download", false);
            chromePrefs.put("download.default_directory", folder.getAbsolutePath());
            chromeOpt.addArguments("--disable-notifications");
            chromeOpt.addArguments("--disable-popup-blocking");
            chromeOpt.setExperimentalOption("prefs", chromePrefs);
            driver = new ChromeDriver(chromeOpt);
        } else {
            throw new RuntimeException("Please input the browser name!");
        }
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }



//    protected WebDriver getBrowserDriver(String browserName, String url) {
//        Browser browser = Browser.valueOf(browserName.toUpperCase());
//        if (browser == Browser.FIREFOX) {
//            WebDriverManager.firefoxdriver().setup();
//            driver = new FirefoxDriver();
//        } else if (browser == Browser.CHROME) {
//            WebDriverManager.chromedriver().setup();
//            driver = new ChromeDriver();
//        } else {
//            throw new RuntimeException("Please input the browser name!");
//        }
//        driver.get(url);
//        driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
//        return driver;
//    }

    protected WebDriver getBrowserDriver(String browserName) {
        Browser browser = Browser.valueOf(browserName.toUpperCase());
        if (browser == Browser.FIREFOX) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser == Browser.CHROME) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else {
            throw new RuntimeException("Please input the browser name!");
        }
        driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
        return driver;
    }

    protected int getRandomemail() {
        Random rand = new Random();
        return rand.nextInt(9999);
    }

    protected void setBrowserDriverProperty() {
        String browserFolderPath = GlobalConstants.PROJECT_LOCATION + getDirectorySlash("browserDriver");
        if (isWindows()) {
            System.setProperty("webdriver.gecko.driver", browserFolderPath + "geckodriver.exe");
            System.setProperty("webdriver.chrome.driver", browserFolderPath + "chromedriver.exe");
            System.setProperty("webdriver.edge.driver", browserFolderPath + "msedgedriver.exe");
        } else if (isMac()) {
            System.setProperty("webdriver.gecko.driver", browserFolderPath + "geckodriver_mac");
            System.setProperty("webdriver.chrome.driver", browserFolderPath + "chromedriver_mac");
            System.setProperty("webdriver.edge.driver", browserFolderPath + "msedgedriver_mac");
        } else {
            System.setProperty("webdriver.gecko.driver", browserFolderPath + "geckodriver_linux");
            System.setProperty("webdriver.chrome.driver", browserFolderPath + "chromedriver_linux");
        }
    }

    private String getDirectorySlash(String folderName) {
        if (isMac() || isUnix() || isSolaris()) {
            folderName = "/" + folderName + "/";
        } else if (isWindows()) {
            folderName = "\\" + folderName + "\\";
        } else {
            folderName = null;
        }
        return folderName;
    }

    private boolean isWindows() {
        return (osName.toLowerCase().indexOf("win") >= 0);
    }

    private boolean isMac() {
        return (osName.toLowerCase().indexOf("mac") >= 0);
    }

    private boolean isUnix() {
        return (osName.toLowerCase().indexOf("nix") >= 0 || osName.toLowerCase().indexOf("nux") >= 0);
    }

    private boolean isSolaris() {
        return (osName.toLowerCase().indexOf("sunos") >= 0);
    }

    public boolean isDownloadFileSuccessfull(boolean condition) {
        boolean status = false;
        File listofFiles[] = folder.listFiles();
        if(listofFiles.length>0){
            status = true;
        } else{
            return status;
        }
        for(File file:listofFiles){
            if(file.length()>0){
                status = true;
            } else{
                return status;
            }
        }
        return status;
    }

    public void deletefoldercontainFile() {
        for(File file : folder.listFiles()){
            file.delete();
        }
        folder.delete();
    }

    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
        } catch (Throwable e) {
            pass = false;

            // Add lỗi vào ReportNG
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected void closeBrowserAndDriver(WebDriver driver) {
        try {
            // Get ra tên của OS và convert qua chữ thường
            String osName = System.getProperty("os.name").toLowerCase();
            log.info("OS name = " + osName);

            // Khai báo 1 biến command line để thực thi
            String cmd = "";
            if (driver != null) {
                driver.quit();
            }

            // Quit driver executable file in Task Manager
            if (driver.toString().toLowerCase().contains("chrome")) {
                if (osName.toLowerCase().contains("mac")) {
                    cmd = "pkill chromedriver";
                } else if (osName.toLowerCase().contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
                }
            } else if (driver.toString().toLowerCase().contains("internetexplorer")) {
                if (osName.toLowerCase().contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
                }
            } else if (driver.toString().toLowerCase().contains("firefox")) {
                if (osName.toLowerCase().contains("mac")) {
                    cmd = "pkill geckodriver";
                } else if (osName.toLowerCase().contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
                }
            } else if (driver.toString().toLowerCase().contains("edge")) {
                if (osName.toLowerCase().contains("mac")) {
                    cmd = "pkill msedgedriver";
                } else if (osName.toLowerCase().contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
                }
            }

            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();

            log.info("---------- QUIT BROWSER SUCCESS ----------");
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }
}

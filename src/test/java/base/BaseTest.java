package base;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static WebDriver driver;
    public static WebDriverManager driverManager;
    public static String FilePath=System.getProperty("user.dir")+"/Screenshots/";

    @BeforeTest
    @Parameters({"browser_name", "App_Url"})
    public void Launch_App(String browser_name, String App_Url) {

        switch (browser_name.toLowerCase()) {
            case "chrome":

                driverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                driverManager.firefoxdriver().setup();
                driver = new ChromeDriver();
                break;
            case "ie":
                driverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
        }
        driver.get(App_Url);
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();


    }


    public static void takeSnapShot(String fileName){
        TakesScreenshot scrShot =((TakesScreenshot)driver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        String NewFilePath = FilePath+fileName+".jpg";

        File DestFile=new File(NewFilePath);
        try {
            FileUtils.copyFile(SrcFile, DestFile);
            System.out.println(NewFilePath);
        } catch (IOException e) {
            Reporter.log("Failed while copying the Screenshot file"+NewFilePath);
        }
        Reporter.log('<'+"img src=”+FilePath +'/>'");
    }
        @AfterTest
        public void close_App(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}

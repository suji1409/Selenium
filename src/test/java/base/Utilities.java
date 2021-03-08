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
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Utilities {

    public static WebDriver driver;
    public static WebDriverManager driverManager;
    public static String FilePath=System.getProperty("user.dir")+"\\Screenshots\\";


    @BeforeSuite
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

    public WebElement driver_wait(String Locator, String Locator_Value){

        WebElement result_Element = null;
        WebDriverWait wait = new WebDriverWait(driver,20);
        switch (Locator.toLowerCase()){
            case "xpath":
                result_Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locator_Value)));
                if(result_Element==null){
                    System.out.println("Element is not displayed located by xpath");
                }
                break;

            case "classname":
                result_Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(Locator_Value)));
                if(result_Element==null){
                    System.out.println("Element is not displayed located by classname");
                }
                break;

            case "id":
                result_Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Locator_Value)));
                if(result_Element==null){
                    System.out.println("Element is not displayed located by id");
                }
                break;

            case "name":
                result_Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Locator_Value)));
                if(result_Element==null){
                    System.out.println("Element is not displayed located by name");
                }
                break;
        }
        return result_Element;
    }
    public static void takeSnapShot(String fileName) throws Exception{
        TakesScreenshot scrShot =((TakesScreenshot)driver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        FilePath = FilePath+fileName+".png";
        File DestFile=new File(FilePath);
        FileUtils.copyFile(SrcFile, DestFile);
        Reporter.log('<'+"img src=”+FilePath +'/>'");
    }

    @AfterSuite
    public void close_App(){
            driver.quit();
    }

}
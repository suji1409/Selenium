package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void WaitUntilElementIsVisibleBy(String Locator, String Locator_Value){

        WebElement result_Element;
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
    }

    public String getTextFromWebElement(WebElement Element){
        return Element.getText();
    }

    public Integer ConvertPriceTagIntoNumber(String BasePrice){

        return 1;
    }

}

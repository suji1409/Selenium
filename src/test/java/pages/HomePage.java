package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import java.util.ArrayList;

public class HomePage {
    WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(id="keywords")
    WebElement txtSearch_HomePage;

    @FindBy(xpath="//*[contains(@id,'btn-success')]")
    WebElement btnSearch;

    @FindBy(xpath="//*[contains(@class,'product_')]//a")
    ArrayList<WebElement> Search_List;


    public void VerifySearchResults(String SearchForProduct){
        txtSearch_HomePage.sendKeys(SearchForProduct);
        btnSearch.click();
        ArrayList<WebElement> Results = Search_List;
        if(Results.size()!=0){
            for (WebElement result: Results) {
                String hrefResult_Link = result.getAttribute("href").toLowerCase();
                if(!(hrefResult_Link.contains(SearchForProduct))){
                    Reporter.log("Results doesnt have the relevant search");
                }
                else{
                    System.out.println(hrefResult_Link);
                }
            }
        }
        else{
            System.out.println("No Search results found for Product "+SearchForProduct);
        }
    }
}

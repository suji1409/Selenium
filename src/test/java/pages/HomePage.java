package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "keywords")
    WebElement txtSearch_HomePage;

    @FindBy(xpath = "//*[contains(@class,'btn-success')]")
    WebElement btnSearch;

    @FindAll(@FindBy(xpath = "//*[contains(@id,'product_')]//a"))
    List<WebElement> Search_List;

    public Boolean getResultsWithSearchText(String SearchForProduct){

        Boolean isResultsContainSearch = true;
        WaitUntilElementIsVisibleBy("xpath", "//*[contains(@class,'search-results-title')]");
        List<WebElement> Results = new ArrayList<>();
        for (WebElement listItem : Search_List) {
            Results.add(listItem);
        }
        if(Results.size()!=0){
            for (WebElement result: Results) {
                String hrefResult_Link = result.getAttribute("href").toLowerCase();
                if(!(hrefResult_Link.contains(SearchForProduct.toLowerCase()))){
                    isResultsContainSearch = false;
                    break;
                }
                else{
                    isResultsContainSearch = true;
                }
            }
        }
        else{
            isResultsContainSearch = false;
        }
        return isResultsContainSearch;

    }

    public Boolean VerifySearchResults(String SearchForProduct){
        txtSearch_HomePage.clear();
        txtSearch_HomePage.sendKeys(SearchForProduct.toLowerCase());
        btnSearch.click();
        return getResultsWithSearchText(SearchForProduct);
    }
}

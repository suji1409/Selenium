package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import java.lang.reflect.Array;
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

    @FindAll(@FindBy(xpath="//a[@class='list-group-item'"))
    List<WebElement> ShopByCategory;

    @FindAll(@FindBy(xpath="//*[@name='search[price_range_any][]']"))
    List<WebElement>  PriceRanges;

    @FindBy(xpath = "//*[contains(@class,'btn-primary')]")
    WebElement btnSearchByCategory;

    @FindAll(@FindBy(xpath="//*[@itemprop='price']"))
    List<WebElement>  SearchCriteriaResults;


    public void selectShopByCategory(String Category){

        for (WebElement category: ShopByCategory) {
            if(category.getText().equalsIgnoreCase(Category)){
                category.click();
                break;
            }
        }
        WaitUntilElementIsVisibleBy("xpath","//*[contains(@class,'filter-title')]");
    }

    public void selectPriceRange(String Price){
        for (WebElement price: PriceRanges) {
            if(price.getAttribute("value").equalsIgnoreCase(Price)){
                price.click();
                break;
            }
        }
    }

    public void SearchByCategoryAndPrice(String Category,String Price){
        selectShopByCategory(Category);
        selectPriceRange(Price);
        btnSearchByCategory.click();
    }

    public Boolean verifyPriceTagSearchResults(String PriceRange) {

        for (WebElement searchItem : SearchCriteriaResults) {
            String ItemBasePrice = searchItem.getAttribute("content").split(".")[0];
            Integer BasePrice = Integer.parseInt(ItemBasePrice);

            String[] priceSplit = PriceRange.split("$");
            String value = null;
            Integer MinPrice = 0, MaxPrice = 0;

            for (String splitValue : priceSplit) {

                value = value.substring(0, 2);

                try {

                    if (MinPrice == 0) {
                        MinPrice = Integer.parseInt(value);
                    } else {
                        MaxPrice = Integer.parseInt(value);
                    }
                } catch (Exception e) {
                    //Do Nothing
                }

                if(BasePrice>=MinPrice && MinPrice <=MaxPrice){
                    return true;
                }
                else{
                    return false;
                }

                }

            }

            return false;


    }











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

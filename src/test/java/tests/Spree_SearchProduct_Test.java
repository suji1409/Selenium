package tests;

import base.Utilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.util.Locale;

public class Spree_SearchProduct_Test extends Utilities {

    @Test(dataProvider="Search_Value")

    public void LoginToAccount(String SearchTxt){

        HomePage Search_Home = new HomePage(Utilities.driver);
        PageFactory.initElements(driver, Search_Home);
        Search_Home.VerifySearchResults(SearchTxt.toLowerCase());
        WebElement searchResult_Banner = driver_wait("class","search-results-title");
        Assert.assertEquals("Search results for '"+SearchTxt.toLowerCase(),searchResult_Banner.getText().toLowerCase());
    }

    @DataProvider (name = "Search_Value")
    public Object[][] dpMethod(){
        return new Object[][] {{"Ruby"}, {"Apache"}};
    }

}

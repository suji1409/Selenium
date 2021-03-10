package tests;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;

import java.util.Locale;

public class SpreeSearchProductTest extends BaseTest {

    @Test(dataProvider="Search_Value")
    public void LoginToAccount(String SearchTxt){
        HomePage Search_Home = new HomePage(driver);
        Boolean getRelevantResult = Search_Home.VerifySearchResults(SearchTxt);
        Assert.assertEquals(Boolean.TRUE,getRelevantResult );
   }

    @DataProvider (name = "Search_Value")
    public Object[][] dpMethod(){
        return new Object[][] {{"Ruby"},{"Apache"},{"test"}};
    }
}

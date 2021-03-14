package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;

@Listeners(base.ListenerTest.class)
public class SpreeShopByCategoryTest extends BaseTest {

    @Test(dataProvider="SearchCriteria")
    public void ShopByCategory(String Category,String Price){
        HomePage Search_Home = new HomePage(driver);
        Search_Home.SearchByCategoryAndPrice(Category,Price);
       Assert.assertEquals(Boolean.TRUE, Search_Home.verifyPriceTagSearchResults(Price));

        takeSnapShot("SearchResult_For_"+Category + Price);
   }

    @DataProvider (name = "SearchCriteria")
    public Object[][] dpMethod(){
        return new Object[][] {{"Bags","$15.00 - $18.00"}};
    }

}

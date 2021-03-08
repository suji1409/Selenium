package tests;

import base.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;

public class Spree_Login_Test extends Utilities {

    @Test
    @Parameters({"UserName","UserPwd"})
    public void LoginToAccount(String UserName,String UserPwd){
        LoginPage spreeLogin = new LoginPage(Utilities.driver);
        PageFactory.initElements(Utilities.driver, spreeLogin);
        spreeLogin.login(UserName,UserPwd);
        WebElement alert_Banner = driver_wait("xpath","//*[contains(@class,'alert-success')]");
        Assert.assertEquals("Logged in successfully",alert_Banner.getText());
    }

}

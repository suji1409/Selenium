package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import static pages.LoginPage.alertMessage;
@Listeners(base.ListenerTest.class)
public class SpreeLoginTest extends BaseTest {

    @Test
    @Parameters({"UserName","UserPwd"})
    public void LoginToAccount(String UserName,String UserPwd){
        LoginPage spreeLogin = new LoginPage(driver);
        spreeLogin.login(UserName,UserPwd);
        takeSnapShot("Login_Message");
        Assert.assertEquals("Logged in successfully",alertMessage);
    }

}

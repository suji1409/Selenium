package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;

public class LoginPage extends BasePage{

    public static String alertMessage;

    public LoginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id="link-to-login")
    WebElement btnLogin_HomePage;

    @FindBy(id="spree_user_email")
    WebElement txtEmail;

    @FindBy(id="spree_user_password")
    WebElement txtPassword;

    @FindBy(name = "commit")
    WebElement btnLogin_LoginPage;

    @FindBy(xpath = "//*[contains(@class,'alert-success')]")
    WebElement bannerAlert;

    public void waitForAlertBannerToBeVisible(){
        WaitUntilElementIsVisibleBy("xpath","//*[contains(@class,'alert-success')]");
    }

    public String getHomePageAlertMessage(){
        return getTextFromWebElement(bannerAlert);
    }


    public HomePage login(String email, String password){

        btnLogin_HomePage.click();
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnLogin_LoginPage.click();
        waitForAlertBannerToBeVisible();
        alertMessage = getHomePageAlertMessage();
        return new HomePage(driver);

    }

}

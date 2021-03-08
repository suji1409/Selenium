package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.HomePage;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    @FindBy(id="link-to-login")
    WebElement btnLogin_HomePage;

    @FindBy(id="spree_user_email")
    WebElement txtEmail;

    @FindBy(id="spree_user_password")
    WebElement txtPassword;

    @FindBy(name = "commit")
    WebElement btnLogin_LoginPage;


    public HomePage login(String email, String password){
        btnLogin_HomePage.click();
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnLogin_LoginPage.click();
        return new HomePage(driver);
    }

}

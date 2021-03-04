import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Spree_Login {


    public static void main(String[] args) {
        /*WebDriverManager.chromedriver().setup();*/

        WebDriver driver;

        System.setProperty("webdriver.chrome.driver","/Users/suji/Downloads/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://spree-vapasi.herokuapp.com/");
        System.out.println(driver.getTitle());
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("spree_user_email")).sendKeys("sujithalakshmi.sundar@gmail.com");
        driver.findElement(By.id("spree_user_password")).sendKeys("Indiapwd@124");
        driver.findElement(By.name("commit")).click();
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

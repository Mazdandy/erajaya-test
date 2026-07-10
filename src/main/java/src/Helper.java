package src;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import src.Web;

public class Helper {
    public static void Login(WebDriver driver, String Username, String Password){
        Web.waitForElement(driver, "btnAppointment");
        Web.clickElement(driver, "btnAppointment");
        Web.sendKeys(driver, "txtUsername", Username);
        Web.sendKeys(driver, "txtPassword", Password);
        Web.clickElement(driver, "btnLogin");
    }

}

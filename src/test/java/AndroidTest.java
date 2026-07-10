import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.Android;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Android Appium Tests")
public class AndroidTest {
    private AppiumDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        // Configure UiAutomator2 options (for Android)
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("TestDevice")
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setAppPackage("com.erafone.android")
                .setNoReset(true) ;

        // Initialize AndroidDriver (connects to Appium server)
        driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"), // Default Appium server URL
                options
        );
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Login With Invalid Data")
    public void loginWithInvalidData() {
        Android.clickElement(driver, "androidHomeBtnLogin");
        Android.sendKeys(driver,"androidTxtUsername", "email@test.com");
        Android.sendKeys(driver,"androidTxtPassword", "Password");
        Android.clickElement(driver, "androidBtnLogin");
        assertThat(Android.isExist(driver, "androidWrongCredential")).isGreaterThan(0);
    }

    @Test
    @DisplayName("Login With Valid Data")
    public void loginWithValidData() {
        Android.clickElement(driver, "androidHomeBtnLogin");
        Android.sendKeys(driver,"androidTxtUsername", "email@test.com");
        Android.sendKeys(driver,"androidTxtPassword", "Password");
        Android.clickElement(driver, "androidBtnLogin");

        assertThat(Android.isExist(driver, "androidHomeMainBanner")).isGreaterThan(0);
        Android.clickElement(driver, "androidBtnProfile");
        String profileName = Android.getText(driver, "lblProfileName");
        String point = Android.getText(driver, "lblMyPoint");
        assertThat(profileName).isNotNull();
    }
}



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import src.Web;
import src.Helper;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("CURA Healthcare Service Login Tests")
public class WebTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Add preferences to block password saving prompts
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.get("https://katalon-demo-cura.herokuapp.com/");
//        driver.manage().window().fullscreen();

    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Login with Invalid Data: John Does / 123456")
    public void testLoginWithInvalidData() {
        Helper.Login(driver, "John Does", "123456");
        // Verify login was failed by checking error message
        assertThat(Web.getText(driver, "lblErrorMessage")).isEqualTo("Login failed! Please ensure the username and password are valid.");
    }

    @Test
    @DisplayName("Login with Valid Data: John Doe / ThisIsNotAPassword, Make Appointment")
    public void testLoginWithValidData() {
        Helper.Login(driver, "John Doe", "ThisIsNotAPassword");
        // Verify login was successfull by asser the title "Make Appointment" is already there
        assertThat(Web.getText(driver,"lblTitle")).isEqualTo("Make Appointment");
    }
    @Test
    @DisplayName("Make Appointment - without input mandatory field")
    public void testMakeAppointmentWithoutInputMandatoryField() {
        Helper.Login(driver, "John Doe", "ThisIsNotAPassword");
        // Verify login was successfull by asser the title "Make Appointment" is already there
        assertThat(Web.getText(driver,"lblTitle")).isEqualTo("Make Appointment");
        Web.clickElement(driver, "btnBookAppointment");
        // Verify appointment was failed because the title is still "Make Appointment"
        assertThat(Web.getText(driver,"lblTitle")).isEqualTo("Make Appointment");
    }
    @Test
    @DisplayName("Make Appointment - without input mandatory field")
    public void testMakeAppointmentWithInputMandatoryField() {
        String Facility, Program, VisitDate, Comment, Readmission;
        Facility = "Hongkong CURA Healthcare Center";
        Readmission = "yes";
        Program = "Medicaid";
        VisitDate = "21/01/2020";
        Comment = "Test Comment";
        Helper.Login(driver, "John Doe", "ThisIsNotAPassword");

        assertThat(Web.getText(driver,"lblTitle")).isEqualTo("Make Appointment");
        Web.clickElement(driver, "slcFacility");
        Web.clickElement(driver, "optValue", Facility);
        Web.clickElement(driver, "slcFacility");
        if (Readmission.equalsIgnoreCase("Yes")){
            Web.clickElement(driver, "chkHospitalReadmission");
        }
        Web.clickElement(driver, "rbnHealthProgram", Program);
        Web.setDate(driver, "txtVisitDate", VisitDate);
        Web.clickElement(driver, "txtComment");
        Web.sendKeys(driver, "txtComment", Comment);
        Web.clickElement(driver, "btnBookAppointment");
        assertThat(Web.getText(driver, "lblTitle")).isEqualTo("Appointment Confirmation");
        assertThat(Web.getText(driver, "lblFacilityConfirmation")).isEqualTo(Facility);
        assertThat(Web.getText(driver, "lblReadmissionConfirmation")).isEqualToIgnoringCase(Readmission);
        assertThat(Web.getText(driver, "lblProgramConfirmation")).isEqualTo(Program);
        assertThat(Web.getText(driver, "lblVisitDateConfirmation")).isEqualTo(VisitDate);
        assertThat(Web.getText(driver, "lblCommentConfirmation")).isEqualTo(Comment);
    }
}

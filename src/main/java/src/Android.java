package src;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Android {
    
    public static void waitForElement(AppiumDriver driver, By by) {
        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        } while (driver.findElements(by).isEmpty());
    }

    public static void clickElement(AppiumDriver driver, By by) {
        waitForElement(driver, by);
        driver.findElement(by).click();
    }

    public static void sendKeys(AppiumDriver driver, By by, String text) {
        waitForElement(driver, by);
        WebElement element = driver.findElement(by);
        element.clear();
        element.sendKeys(text);
    }

    public static String getText(AppiumDriver driver, By by) {
        waitForElement(driver, by);
        return driver.findElement(by).getText();
    }

    public static void clickElement(AppiumDriver driver, String selectorId) {
        clickElement(driver, SelectorLoader.get(selectorId));
    }

    public static void clickElement(AppiumDriver driver, String selectorId, String value) {
        String selector = String.format(SelectorLoader.get(selectorId).toString(), value);
        driver.findElement(By.xpath((selector.replace("By.xpath:", "").trim()))).click();
    }

    public static void sendKeys(AppiumDriver driver, String selectorId, String text) {
        sendKeys(driver, SelectorLoader.get(selectorId), text);
    }

    public static String getText(AppiumDriver driver, String selectorId) {
        waitForElement(driver, selectorId);
        return driver.findElement(SelectorLoader.get(selectorId)).getText();
    }
    
    public static int isExist(AppiumDriver driver, String selectorId){
        return driver.findElements(SelectorLoader.get(selectorId)).size();
    }

    public static void waitForElement(AppiumDriver driver, String selectorId) {
        waitForElement(driver, SelectorLoader.get(selectorId));
    }
}

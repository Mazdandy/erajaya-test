package src;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Web {
    public static void waitForElement(WebDriver driver, String selectorId) {
        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        } while (driver.findElements(SelectorLoader.get(selectorId)).isEmpty());
    }

    public static void clickElement(WebDriver driver, String selectorId) {
//        waitForElement(driver, selectorId);
        driver.findElement(SelectorLoader.get(selectorId)).click();
    }

    public static void sendKeys(WebDriver driver, String selectorId, String text) {
        waitForElement(driver, selectorId);
        driver.findElement(SelectorLoader.get(selectorId)).clear();
        driver.findElement(SelectorLoader.get(selectorId)).sendKeys(text);
    }
    public static void setDate(WebDriver driver, String selectorId, String text) {
        waitForElement(driver, selectorId);
        driver.findElement(SelectorLoader.get(selectorId)).clear();
        driver.findElement(SelectorLoader.get(selectorId)).sendKeys(text);
        driver.findElement(SelectorLoader.get(selectorId)).sendKeys(Keys.chord(Keys.ENTER));
    }

    public static String getText(WebDriver driver, String selectorId) {
        waitForElement(driver, selectorId);
        return driver.findElement(SelectorLoader.get(selectorId)).getText();
    }

    public static void clickElement(WebDriver driver, String selectorId, String value) {
        String selector = String.format(SelectorLoader.get(selectorId).toString(), value);
        driver.findElement(By.xpath((selector.replace("By.xpath:", "").trim()))).click();
    }

    public static int isExist(WebDriver driver, String selectorId){
        return driver.findElements(SelectorLoader.get(selectorId)).size();
    }

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        
        try {
            driver.get("https://www.google.com");
            System.out.println("Page title is: " + driver.getTitle());
        } finally {
            driver.quit();
        }
    }
}

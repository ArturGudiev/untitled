package selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class FirstExample {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Programming\\Java\\untitled\\drivers\\chromedriver.exe");

        ChromeDriver driver  = new ChromeDriver();
        //Launch the Online Store Website
        driver.get("https://localhost:3030/admin/reports/run");

        // Print a Log In message to the screen
        System.out.println("Successfully opened the website https://localhost:3030/admin/reports/run");

        Thread.sleep(5000);
        WebElement elementByTagName = driver.findElementByTagName("app-form-widget");
//        elementByTagName.sendKeys("administrator");
        Actions actions = new Actions(driver);
        actions.moveToElement(elementByTagName);
        actions.click();
        actions.sendKeys(elementByTagName, "AAA");
//        System.out.println(elementByTagName);
        //Wait for 5 Sec

        // Close the driver
//        driver.quit();

    }
}

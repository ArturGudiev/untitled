package selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstExample {

    public static void login(ChromeDriver driver ) {
        while(true) {
            try {
                driver.findElementByTagName("button").click();
                return ;
            } catch (Exception e) {
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteAllLicenses(ChromeDriver driver){
        while(true) {
            try {
                WebElement checkbox = driver.findElementByTagName("clr-checkbox");

//                WebElement yourChkBox  = driver.findElement(By.xpath("//*[@id='agree_to_terms_join']/parent::label"));

                WebDriverWait wait = new WebDriverWait(driver, 20);
                wait.until(ExpectedConditions.visibilityOf(checkbox));

                Actions act = new Actions(driver);
                act.moveToElement(checkbox).click().build().perform();
                driver.findElementById("manageLicensesDeleteButton").click();
                return;
            }catch (Exception e){

            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Programming\\Java\\untitled\\drivers\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        if(args[0].equals("delete-licenses")){
            driver.get("https://localhost:3030/manage-licenses");
            login(driver);
            deleteAllLicenses(driver);
        }

//        login(driver);
        //Launch the Online Store Website
        // Print a Log In message to the screen

//        Thread.sleep(5000);
//        WebElement elementByTagName = driver.findElementByTagName("app-form-widget");
//        elementByTagName.sendKeys("administrator");
//        Actions actions = new Actions(driver);
//        actions.moveToElement(elementByTagName);
//        actions.click();
//        actions.sendKeys(elementByTagName, "AAA");
//        System.out.println(elementByTagName);
        //Wait for 5 Sec

        // Close the driver
        driver.quit();

    }
}

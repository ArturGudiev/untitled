package selenium.utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.controllers.MyController;

import java.util.Objects;
import java.util.function.Supplier;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class SeleniumUtil {

    static{
        System.setProperty("webdriver.chrome.driver", "C:\\Programming\\Java\\untitled\\drivers\\chromedriver.exe");
    }
    static WebDriver driver = new ChromeDriver();

    public static boolean isPresent(WebElement element) {
        try {
            element.getText();
            Dimension elementSize = element.getSize();
            if ((elementSize.height < 1 && elementSize.width < 1)) {
                return false;
            }
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    public static <T> void shouldBecome(WebElement element, Supplier<T> supplier, T expectedResult) {
        assertTrue("Element is null", element != null);
        getWait().until((ExpectedCondition<Boolean>) d -> supplier.get().equals(expectedResult));
    }

    public static void shouldDisappear(WebElement element) {
        System.out.println("shouldDisappear: " + element);
        shouldBecome(element, () -> isPresent(element), false);
    }

    public static void clickOn(WebElement element){
        getWait().until(elementToBeClickable(element));
        element.click();
    }

    public static void setValue(WebElement element, String value){
        getWait().until(elementToBeClickable(element));
        element.sendKeys(value);
    }

    public static WebDriverWait getWait(){
        WebDriverWait webDriverWait = new WebDriverWait(driver, 15);
        webDriverWait.pollingEvery(java.time.Duration.ofMillis(100));
        return webDriverWait;
    }

    public static <T extends MyController> T initController(Class<T> controllerClass) {
        return PageFactory.initElements(driver, controllerClass);
    }

    public static void navigate(String url) {
        driver.get(url);
    }

    public static void quit() {
        driver.quit();
    }


//    public static MyController initController(Class<T extends MyController> java.lang.Class controllerClass) {
//        return PageFactory.initElements(driver, controllerClass);
//    }
}

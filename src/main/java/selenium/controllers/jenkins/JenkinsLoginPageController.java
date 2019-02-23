package selenium.controllers.jenkins;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.controllers.MyController;

import static selenium.utils.SeleniumUtil.getWait;

public class JenkinsLoginPageController extends MyController {

    @FindBy(name = "j_username")
    private WebElement usernameInput;

    @FindBy(name = "j_password")
    private WebElement passwordInput;

    @FindBy(tagName = "button")
    private WebElement loginButton;

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getUsernameInput() {
        return usernameInput;
    }

    public WebElement getPasswordInput() {
        return passwordInput;
    }

    public void waitForLoad() {
        getWait().until(d -> usernameInput.isDisplayed() && passwordInput.isDisplayed() && loginButton.isDisplayed());
    }

}

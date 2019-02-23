package selenium.controllers.stash;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.controllers.MyController;

import static selenium.utils.SeleniumUtil.getWait;

public class StashLoginPageController extends MyController {
    @FindBy(id = "j_username")
    private WebElement usernameInput;

    @FindBy(id = "j_password")
    private WebElement passwordInput;

    @FindBy(id = "submit")
    private WebElement submitButton;

    public WebElement getUsernameInput() {
        return usernameInput;
    }

    public WebElement getPasswordInput() {
        return passwordInput;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }

    public void waitForLoad() {
        getWait().until(d -> usernameInput.isDisplayed() && passwordInput.isDisplayed() && submitButton.isDisplayed());

    }
}

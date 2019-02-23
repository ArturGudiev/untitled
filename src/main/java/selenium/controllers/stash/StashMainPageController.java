package selenium.controllers.stash;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.controllers.MyController;

import java.util.List;

import static selenium.utils.SeleniumUtil.getWait;

public class StashMainPageController extends MyController {
    @FindBy(id = "repository-selector")
    public WebElement repositoryComboBox;

    @FindBy(className = "spinner")
    public WebElement spinner;

    @FindBy(id = "repository-selector-dialog-search-input")
    public WebElement repositoryInput;

    @FindBy(className = "repository")
    public List<WebElement> resultElements;

    public WebElement getFeatureType() {
        return featureType;
    }

    @FindBy(partialLinkText = "Feature")
    public WebElement featureType;

    public WebElement getBranchTypeComboBox() {
        return branchTypeComboBox;
    }

    @FindBy(id = "branch-type")
    public WebElement branchTypeComboBox;

    public WebElement getBranchNameInput() {
        return branchNameInput;
    }

    @FindBy(id = "branch-name")
    public WebElement branchNameInput;

    public WebElement getRepositoryComboBox() {
        return repositoryComboBox;
    }

    public WebElement getSpinner() {
        return spinner;
    }

    public WebElement getRepositoryInput() {
        return repositoryInput;
    }

    public List<WebElement> getResultElements() {
        return resultElements;
    }

    public void waitForLoad() {
        getWait().until(d -> repositoryComboBox.isDisplayed());
    }
}

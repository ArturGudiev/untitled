package selenium;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import selenium.controllers.jenkins.JenkinsLoginPageController;
import selenium.controllers.stash.StashLoginPageController;
import selenium.controllers.stash.StashMainPageController;
import selenium.utils.SeleniumUtil;

import static selenium.utils.SeleniumUtil.clickOn;
import static selenium.utils.SeleniumUtil.getWait;
import static selenium.utils.SeleniumUtil.initController;
import static selenium.utils.SeleniumUtil.navigate;
import static selenium.utils.SeleniumUtil.setValue;
import static selenium.utils.SeleniumUtil.shouldDisappear;
import static utilities.Util.getDPALogin;
import static utilities.Util.getDPASysPWD;

public class SeleniumSequences {

    static JenkinsLoginPageController loginPage;
    static StashLoginPageController stashLoginPage;
    static StashMainPageController stashMainPage;

    @BeforeClass
    public static void prepare(){
//        loginPage = initController(JenkinsLoginPageController.class);
        stashLoginPage = initController(StashLoginPageController.class);
        stashMainPage = initController(StashMainPageController.class);

    }

    public static final String jenkinsPipelineURL = "https://dpavblxjenkins2.datadomain.com/view/QA%20UI%20Aut/job/QAUI_01_HTML_TESTS_PIPELINE_CUSTOMTESTS/build?delay=0sec";

    @Test
    public void test1() {

        String login = getDPALogin();
        String pwd = getDPASysPWD();

        navigate(jenkinsPipelineURL);

        loginPage.waitForLoad();
        setValue(loginPage.getUsernameInput(), login);
        setValue(loginPage.getPasswordInput(), pwd);
        clickOn(loginPage.getLoginButton());

    }
    
    @Test
    public void createBranchInStash(){
        String login = getDPALogin();
        String pwd = getDPASysPWD();

        navigate("https://dpa-stash.lss.emc.com/plugins/servlet/create-branch");

        stashLoginPage.waitForLoad();
        setValue(stashLoginPage.getUsernameInput(), login);
        setValue(stashLoginPage.getPasswordInput(), pwd);
        clickOn(stashLoginPage.getSubmitButton());


        stashMainPage.waitForLoad();
        clickOn(stashMainPage.getRepositoryComboBox());
        setValue(stashMainPage.getRepositoryInput(), "html-ui-automation");
        shouldDisappear(stashMainPage.getSpinner());
        getWait().until(d -> stashMainPage.getResultElements().size() == 2);
        clickOn(stashMainPage.getResultElements().get(1));


        clickOn(stashMainPage.getBranchTypeComboBox());
        clickOn(stashMainPage.getFeatureType());

        setValue(stashMainPage.getBranchNameInput(), "AAA");
        System.out.println("AAA");
    }

    @AfterClass
    public static void close(){
        SeleniumUtil.quit();
    }
}

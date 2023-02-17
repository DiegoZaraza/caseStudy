package org.test.steps;

import com.github.javafaker.Faker;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.test.pageObject.BasePage;
import org.test.pageObject.IndexPage;
import org.test.pageObject.StepTwoPage;
import org.test.utilities.PropertiesRead;

public class StepDefinitions {
    private static final String PAGE = PropertiesRead.readFromFrameworkConfig("URL");
    private WebDriver webDriver;
    private SoftAssertions softAssertions;
    private Faker faker;
    private IndexPage indexPage;
    private StepTwoPage stepTwoPage;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        webDriver = WebDriverManager.chromedriver().create();
        softAssertions = new SoftAssertions();
        faker = new Faker();
        indexPage = new IndexPage(webDriver);
        stepTwoPage = new StepTwoPage(webDriver);
        webDriver.manage().window().maximize();
        BasePage.setImplicitlyWait();
    }

    @Given("Navigate to alkilautos.com")
    public void navigateToAlkilautosCom() throws InterruptedException {
        BasePage.setImplicitlyWait();
        webDriver.get(PAGE);
        indexPage.setAcceptCookies();

    }

    @After
    public void end() {
        softAssertions.assertAll();
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @When("Put the pick up location {string}")
    public void putThePickupCity(String arg0) throws InterruptedException {
        indexPage.setTxtPickupLocation(arg0);
    }

    @And("Select pick up date")
    public void selectPickUpDate() {
        indexPage.clickTxtFechaRecogida();
    }

    @And("Select drop off date")
    public void selectDropOffDate() {
        indexPage.clickTxtFechaDevolucion();
    }

    @And("Click on button search")
    public void clickOnButtonSearch() {
        indexPage.clickButtonSearch();
    }

    @And("Select car {string}")
    public void selectCar(String arg0) throws InterruptedException {
        stepTwoPage.selectCar(arg0);
        Thread.sleep(20000);
    }

    @Then("Validate founded results")
    public void validateFoundedResults() {
        softAssertions.assertThat(stepTwoPage.getBoxFilter());
    }
}

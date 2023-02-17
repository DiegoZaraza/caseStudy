package org.test.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class StepTwoPage extends BasePage{
    @FindBy(id = "filtros")
    WebElement boxFilters;
    public StepTwoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean getBoxFilter(){
        return isDisplayed(boxFilters, "");
    }
    public void selectCar(String type){
        scrollPage();

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<WebElement> gama = find("//div[@class=\"gama\"]");

        for(WebElement g:gama){
            if(g.findElement(By.className("gama-name")).getText().equals(type)) {
                g.findElement(By.tagName("a")).click();
            }
        }
    }
}

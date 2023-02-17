package org.test.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class IndexPage extends BasePage{
    WebDriver driver;
    public static Calendar today;

    @FindBy(id = "aceptar-cookies")
    WebElement btnCookies;

    @FindBy(id = "ciudadi-str")
    WebElement txtPickupLocation;
    @FindBy(id = "fechai-recogida")
    WebElement txtFechaRecogida;

    @FindBy(id = "fechaf-devolucion")
    WebElement txtFechaDevolucion;

    @FindBy(id = "buscar")
    WebElement btnBuscar;
    public IndexPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void setTxtPickupLocation(String city) throws InterruptedException {
        sendKeys(txtPickupLocation, city, "");
        List<WebElement> columns = find("//span[@class=\"sub-title\"]");
        clickGivenLocation(columns, city);
    }

    public void clickTxtFechaRecogida(){
        click(txtFechaRecogida, "");
        List<WebElement> columns = find("//span[contains(@class, \"flatpickr-day\")]");
        clickGivenDay(columns, getNextSunday());
    }

    public void clickTxtFechaDevolucion(){
        click(txtFechaDevolucion, "");
        List<WebElement> columns = find("//html/body/div[15]/div[2]/div/div[2]/div//span[contains(@class, \"flatpickr-day\")]");
        clickGivenDay(columns, getReturnCar());
    }

    public void clickButtonSearch(){
        click(btnBuscar, "");
    }
    public static void clickGivenDay(List<WebElement> elementList, String day) {
        elementList.stream()
                .filter(element -> element.getAttribute("aria-label").contains(day))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public static void clickGivenLocation(List<WebElement> elementList, String location) {
        elementList.stream()
                .filter(element -> element.getText().contains(location))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public static String getNextSunday() {
        today = Calendar.getInstance();
        while (!today.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, new Locale("ES", "CO")).equals("dom")) {
            today.add(Calendar.DAY_OF_MONTH, 1);
        }
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(today.getTime());
    }

    private static String getReturnCar(){
        today.add(Calendar.DAY_OF_MONTH, 6);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(today.getTime());
    }

    public void setAcceptCookies(){
        click(btnCookies, "");
    }
}

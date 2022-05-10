package pages;

import helper.PurchaseForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PlaceOrderPage extends PageBase{
    By nameBy = By.id("name");
    By countryBy = By.id("country");
    By cityBy = By.id("city");
    By cardBy = By.id("card");
    By monthBy = By.id("month");
    By yearBy = By.id("year");
    By submitBy = By.xpath("//button[text()=\"Purchase\"]");

    public PlaceOrderPage(WebDriver driver) {
        super(driver);
    }

    public void fillPurchaseForm(PurchaseForm form) {
        this.waitAndReturnElement(nameBy).sendKeys(form.getName());
        this.waitAndReturnElement(countryBy).sendKeys(form.getCountry());
        this.waitAndReturnElement(cityBy).sendKeys(form.getCity());
        this.waitAndReturnElement(cardBy).sendKeys(form.getCreditCard());
        this.waitAndReturnElement(monthBy).sendKeys(form.getMonth().toString());
        this.waitAndReturnElement(yearBy).sendKeys(form.getYear().toString());
        this.waitAndReturnElement(submitBy).click();
    }

    public boolean isPurchaseSuccessful() {
        return true;
    }
}

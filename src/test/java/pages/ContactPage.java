package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactPage extends PageBase {
    By contactHeaderTextBy = By.id("exampleModalLabel");
    public ContactPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageValid() {
        return this.waitAndReturnElement(contactHeaderTextBy).getText().contains("New message");
    }
}

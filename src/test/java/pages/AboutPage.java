package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AboutPage extends PageBase {
    By aboutUsHeaderBy = By.id("videoModalLabel");
    public AboutPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageValid() {
        return this.waitAndReturnElement(aboutUsHeaderBy).getText().contains("About us");
    }
}

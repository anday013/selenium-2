package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageBase {
    private By usernameBy = By.id("loginusername");
    private By passwordBy = By.id("loginpassword");
    private By loginButtonBy = By.xpath("//div[@class=\"modal-footer\"]/button[text()=\"Log in\"]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void testLogin(String username, String password) {
        this.waitAndReturnElement(usernameBy).sendKeys(username);
        this.waitAndReturnElement(passwordBy).sendKeys(password + '\n');
        this.waitAndReturnElement(loginButtonBy).click();
    }
}

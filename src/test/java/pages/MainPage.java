package pages;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.By;


public class MainPage extends PageBase {

    private final By footerBy = By.xpath("//footer");
    private final By loginBy = By.xpath("//div/ul/li/a[@id=\"login2\"]");
    private final By logoutBy = By.id("logout2");

    private String productName;

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.demoblaze.com/");
    }

    public String getFooterText() {
        return this.waitAndReturnElement(footerBy).getText();
    }

    public LoginPage openLoginForm() {
        this.waitAndReturnElement(loginBy).click();
        return new LoginPage(driver);
    }

    public boolean isLoggedIn(String username) {
        By usernameSection = By.id("nameofuser");
        return this.waitAndReturnElement(usernameSection).getText().contains("Welcome " + username);
    }

    public void logout() {
        this.waitAndReturnElement(logoutBy).click();
    }

    public boolean isLoggedOut() {
        return this.waitAndReturnElement(loginBy).getText().contains("Log in");
    }

    public ProductPage openProduct(String productName) {
        By samsungProduct = By.xpath("//a[text()=\"" + productName + "\"]");
        this.waitAndReturnElement(samsungProduct).click();
        return new ProductPage(this.driver, productName);
    }

    public CartPage openCart() {
        By cartLink = By.id("cartur");
        this.waitAndReturnElement(cartLink).click();
        return new CartPage(this.driver);
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends PageBase {
    private String productName;
    private By productBodyBy = By.xpath("//h2[1]");
    private By addToCartButton = By.xpath("//a[text()=\"Add to cart\"]");
    private By indexPageButton = By.id("nava");

    public ProductPage(WebDriver driver, String productName) {
        super(driver);
        this.productName = productName;
    }

    public boolean isPageValid() {
        System.out.println(this.waitAndReturnElement(productBodyBy).getText());
        return this.waitAndReturnElement(productBodyBy).getText().contains(productName);
    }

    public MainPage addToCart() {
        this.waitAndReturnElement(addToCartButton).click();
        this.wait.until(ExpectedConditions.alertIsPresent());
        this.driver.switchTo().alert().accept();
        this.waitAndReturnElement(indexPageButton).click();
        return new MainPage(this.driver);
    }
}
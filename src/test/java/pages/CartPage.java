package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends PageBase {
    By placeOrderBtn = By.xpath("//button[text()=\"Place Order\"]");
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductInCart(String productName) {
        By bodyBy = By.xpath("//td[text()=\"" + productName + "\"]");
        return this.waitAndReturnElement(bodyBy).getText().contains(productName);
    }

    public PlaceOrderPage placeOrder() {
        this.waitAndReturnElement(placeOrderBtn).click();
        return new PlaceOrderPage(this.driver);
    }

}

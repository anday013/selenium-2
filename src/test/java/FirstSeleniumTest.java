import helper.PurchaseForm;
import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.CartPage;
import pages.MainPage;
import pages.PlaceOrderPage;
import pages.ProductPage;


public class FirstSeleniumTest {
    public WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testAuthentication() {
        pages.MainPage mainPage = new pages.MainPage(this.driver);
        Assert.assertTrue(mainPage.getFooterText().contains("Copyright © Product Store 2017"));
        Assert.assertTrue(mainPage.getBodyText().contains("Log in"));
        pages.LoginPage loginPage = mainPage.openLoginForm();
        loginPage.testLogin("testtest2000", "testtest2000");
        Assert.assertTrue(mainPage.isLoggedIn("testtest2000"));
        mainPage.logout();
        Assert.assertTrue(mainPage.isLoggedOut());
    }

    @Test
    public void testCart() {
        MainPage mainPage = new MainPage(this.driver);
        String productName = "Samsung galaxy s6";
        ProductPage productPage = mainPage.openProduct(productName);
        Assert.assertTrue(productPage.isPageValid());
        mainPage = productPage.addToCart();
        CartPage cartPage = mainPage.openCart();
        Assert.assertTrue(cartPage.isProductInCart(productName));
        PlaceOrderPage placeOrderPage = cartPage.placeOrder();
        PurchaseForm testForm = new PurchaseForm("Test", "Hungary", "Budapest", "1234567890123456", 1, 2022);
        placeOrderPage.fillPurchaseForm(testForm);
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}

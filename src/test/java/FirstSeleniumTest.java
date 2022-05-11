import helper.PurchaseForm;
import helper.StaticPage;
import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class FirstSeleniumTest {
    public WebDriver driver;
    public JavascriptExecutor js;
    private static String propertyFilePath = System.getProperty("user.dir") +
            "/src/test/resources/config.properties";
    public Properties prop;

    public static String url;
    public static String username;
    public static String password;
    public static StaticPage[] pages;


    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        js = (JavascriptExecutor) driver;
        prop = new Properties();
        //Read configuration.properties file
        try {
            prop.load(new FileInputStream(propertyFilePath));
        } catch (IOException e) {
            System.out.println("Configuration properties file cannot be found");
        }
        url = prop.getProperty("url");
        username = prop.getProperty("username");
        password = prop.getProperty("password");
        driver.get(url);
    }

    @Test
    public void testStaticPage() {
        pages = new StaticPage[]{new StaticPage(this.driver, "Cart", "Total"),
                new StaticPage(this.driver, "Contact", "New message")};
        for (StaticPage page : pages) {
            Assert.assertTrue("Is element exist on static page", page.isElementExist());
        }
    }

    @Test
    public void testAuthentication() {
        MainPage mainPage = new MainPage(this.driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        js.executeScript("window.scrollTo(0, 0);");
        Assert.assertTrue("Test footer static text", mainPage.getFooterText().contains("Copyright © Product Store 2017"));
        Assert.assertTrue("Is no one logged in", mainPage.getBodyText().contains("Log in"));
        pages.LoginPage loginPage = mainPage.openLoginForm();
        loginPage.testLogin(username, password);
        Assert.assertTrue("Is logged in as testtest2000", mainPage.isLoggedIn("testtest2000"));
        mainPage.logout();
        Assert.assertTrue("Is logged out", mainPage.isLoggedOut());
    }

    @Test
    public void testCart() {
        MainPage mainPage = new MainPage(this.driver);
        String productName = "Samsung galaxy s6";
        ProductPage productPage = mainPage.openProduct(productName);
        Assert.assertTrue("Check product page", productPage.isPageValid());
        mainPage = productPage.addToCart();
        CartPage cartPage = mainPage.openCart();
        Assert.assertTrue("Is correct product in cart", cartPage.isProductInCart(productName));
        PlaceOrderPage placeOrderPage = cartPage.placeOrder();
        PurchaseForm testForm = new PurchaseForm("Test", "Hungary", "Budapest", "1234567890123456", 1, 2022);
        placeOrderPage.fillPurchaseForm(testForm);
    }

    @Test
    public void testBackButton() {
        MainPage mainPage = new MainPage(this.driver);
        CartPage cartPage = mainPage.openCart();
        cartPage.goBack(); // Back to Main Page
        Assert.assertTrue("Test footer text", mainPage.getFooterText().contains("Copyright © Product Store 2017"));
    }

    @Test
    public void hoverTest() {
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertEquals("Is hovered text blue", "rgba(33, 232, 203, 1)", mainPage.hoverHome().getCssValue("color"));
    }

    @Test
    public void contactPageTest() {
        MainPage mainPage = new MainPage(this.driver);
        ContactPage contactPage = mainPage.goContactPage();
        Assert.assertTrue("Test Contact Page", contactPage.isPageValid());
    }

    @Test
    public void aboutPageTest() {
        MainPage mainPage = new MainPage(this.driver);
        AboutPage aboutPage = mainPage.goAboutUs();
        Assert.assertTrue("Test About Us Page", aboutPage.isPageValid());
    }

    @Test
    public void staticPageTest() {
        MainPage mainPage = new MainPage(this.driver);

    }


    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}

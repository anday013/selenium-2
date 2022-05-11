package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.PageBase;

public class StaticPage extends PageBase {
    private String name;
    private String elementText;

    public StaticPage(WebDriver driver, String name, String elementText) {
        super(driver);
        this.name = name;
        this.elementText = elementText;
    }

    public boolean isElementExist() {
        By goPageBy = By.xpath("//a[text()=\"" + name + "\"]");
        this.waitAndReturnElement(goPageBy).click();
        By element = By.xpath("//*[text()=\"" + elementText +"\"]");
        boolean res = this.waitAndReturnElement(element).getText().contains(elementText);
        this.goBack();
        return res;
    }
}

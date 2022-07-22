package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.Base;
import utils.Driver;


// page_url = https://demo.guru99.com/V4/manager/Managerhomepage.php
public class HomePage extends Base {
    private Actions actions=new Actions(Driver.getDriver());

    @FindBy(xpath = "//*[text() = 'Manger Id : mngr426799']")
    private WebElement elementTd2;

    @FindBy(css = "td[style~='green']")
    private WebElement elementTd;

    @FindBy(xpath = "//*[text() = 'New Customer']")
    private WebElement elementLink;


    // No page elements added

    public HomePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
    public void verify(String str){
        Assert.assertTrue(elementTd.getText().contains(str),"verification failed Manger Id not matching");
    }
    public void newCustomer() throws InterruptedException {
        waitStatic(3);
        actions.moveByOffset(0,0).click().build().perform();
        elementLink.click();
        waitStatic(3);
        waitForPageToLoad(2);
        actions.moveByOffset(0,0).click().build().perform();
    }
}
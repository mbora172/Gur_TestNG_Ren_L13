package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.Base;
import utils.ConfigurationReader;
import utils.Driver;

import javax.swing.*;


// page_url = https://demo.guru99.com/V4/
public class LoginPage extends Base {
Actions actions=new Actions(Driver.getDriver());
    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(name = "btnLogin")
    private WebElement btnloginInput;

    @FindBy(css = "h2")
    private WebElement baroneH;


    @FindBy(css = "input[name='uid']")
    private WebElement uidInput;

    // No page elements added

    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
    public void verifyHeader(String str){
        Assert.assertEquals(baroneH.getText(),str,"Verification failed bar header not matched");
    }
    public void setUserInfo() throws InterruptedException {
        waitStatic(2);
        uidInput.sendKeys(ConfigurationReader.getProperties("userName"));
        passwordInput.sendKeys(ConfigurationReader.getProperties("password"));
        btnloginInput.click();
        waitStatic(2);
        actions.moveByOffset(0,0).click().build().perform();
        
    }

    
    
}
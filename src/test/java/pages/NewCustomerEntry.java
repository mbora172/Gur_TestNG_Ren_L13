package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.Random;

import org.testng.Assert;
import utils.Base;
import utils.ConfigurationReader;
import utils.Driver;

import javax.swing.*;


// page_url = https://demo.guru99.com/V4/manager/addcustomerpage.php
public class NewCustomerEntry extends Base {
    // No page elements added

    public NewCustomerEntry() {
        PageFactory.initElements(Driver.getDriver(), this);

    }

    public void verifyNewCustomerEntryPage() throws InterruptedException {
        waitStatic(2);
        Assert.assertEquals(heading.getText(),"Add New Customer","verify failed header not matching for Add New Customer");
    }

    @FindBy(css = ".heading3")
    private WebElement heading;

    @FindBy(name = "name")
    private WebElement nameInput;

 

    @FindBy(xpath = "//input[@value='m']")
    private WebElement radInputMale;



    @FindBy(xpath = "//input[@value='f']")
    private WebElement radInputFemale;

    @FindBy(id = "dob")
    private WebElement dobInput;

    @FindBy(name = "addr")
    private WebElement addrTextarea;

    @FindBy(name = "city")
    private WebElement cityInput;

    @FindBy(name = "state")
    private WebElement stateInput;

    @FindBy(name = "pinno")
    private WebElement pinnoInput;

    @FindBy(name = "telephoneno")
    private WebElement telephonenoInput;

    @FindBy(name = "emailid")
    private WebElement emailidInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(name = "sub")
    private WebElement subInput;

    @FindBy(css = ".heading3")
    private WebElement success;

    
    public void fillInfo() throws InterruptedException {
        Random random=new Random();
        Faker faker=new Faker();

        waitStatic(2);
        nameInput.sendKeys("hello");
        radInputMale.click();
        dobInput.sendKeys("01/01/2011");
        addrTextarea.sendKeys("213 ave 41 apt 2  23242");
        cityInput.sendKeys("Brooklyn");
        stateInput.sendKeys("NY");
        pinnoInput.sendKeys("123423");
        telephonenoInput.sendKeys("3332342323");
        emailidInput.sendKeys(faker.name().firstName()+"@bb.com");
        passwordInput.sendKeys(ConfigurationReader.getProperties("password"));
        subInput.click();

        
    }
    public void verifySucces(){
        Assert.assertTrue(success.getText().contains("Successfully"));
    }

    
    
}
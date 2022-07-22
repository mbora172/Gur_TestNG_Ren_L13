package testCases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.NewCustomerEntry;
import utils.Base;
import utils.ConfigurationReader;
import utils.Driver;
/*
Step 1) Go to Guru99 Demo Site http://demo.guru99.com/V4/
Step 2) In Login page check text "Guru99 Bank" is present
Step 3) Login into application with given credentials(you need to enter your email in order to get credentials in advance)
Step 4) Verify that the Home page contains text as "Manger Id: mngr368294"
Step 5) Click on new customer button and verify you are in New Customer Entry Page
Step 6) Fill the information for new customer
Step 7) Verify new customer added sucessfully
 */
public class NgTest extends Base {
   private LoginPage login=new LoginPage();
   private HomePage homePage=new HomePage();
   private NewCustomerEntry newCustomer=new NewCustomerEntry();
    @Test
    public void headerVerification(){

       login.verifyHeader("Guru99 Bank");
    }
    @Test
    public void loginToGuruVerifyManagerId() throws InterruptedException {
        login.setUserInfo();
        homePage.verify("mngr426799");
    }
    @Test
    public void verifyNewCustomerEntryPage() throws InterruptedException {
       // login.setUserInfo();
        homePage.newCustomer();

        newCustomer.verifyNewCustomerEntryPage();

    }
    @Test
    public void verifyNewCostumerAdded() throws InterruptedException {
      //  login.setUserInfo();
        homePage.newCustomer();
       newCustomer.fillInfo();
       newCustomer.verifySucces();
    }


}

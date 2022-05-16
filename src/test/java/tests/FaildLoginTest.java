package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.VerificationPage;
import utilities.PropertyManager;

public class FaildLoginTest extends BaseTest{

     @Test
    public void failedLogin(){
         LoginPage loginPage = new LoginPage(driver);
         VerificationPage verificationPage = new VerificationPage(driver);
         loginPage.login(PropertyManager.getInstance().getLoginEmail(), PropertyManager.getInstance().getBadPassword());

         try {
             verificationPage.verifyFailedLogin("There is 1 error\nAuthentication failed.");
             System.out.println("User is not logged in");
         }catch (Exception e){
             Assert.fail("User is logged in");

         }
     }
}

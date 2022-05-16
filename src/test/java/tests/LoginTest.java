package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.VerificationPage;
import utilities.PropertyManager;

public class LoginTest extends BaseTest{

    @Test
    public void loginTest(){
        LoginPage loginPage = new LoginPage(driver);
        VerificationPage verificationPage = new VerificationPage(driver);

        loginPage.login(PropertyManager.getInstance().getLoginEmail(), PropertyManager.getInstance().getLoginPassword());

        try {
            verificationPage.verifyLogin("Sign out");
            System.out.println("User is logged in");
        }catch (Exception e ){
            Assert.fail("Something went wrong");
        }
    }

}

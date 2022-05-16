package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.RegistrationPage;
import pages.VerificationPage;
import utilities.PropertyManager;

public class RegistrationTest extends BaseTest{

    @Test
    public void registrationTest(){
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        VerificationPage verificationPage = new VerificationPage(driver);


        loginPage.navigateRegistration(PropertyManager.getInstance().getRegEmail());
        registrationPage.register(PropertyManager.getInstance().getFirstName(),PropertyManager.getInstance().getLastName(), PropertyManager.getInstance().getLoginPassword(),PropertyManager.getInstance().getAddress(),PropertyManager.getInstance().getCity(),PropertyManager.getInstance().getPostCode(),PropertyManager.getInstance().getMobPhone());

        try {
            verificationPage.verifyLogin("Sign out");
            System.out.println("User is registered");
        }catch (Exception e ){
            Assert.fail("Something went wrong");
        }
    }
}

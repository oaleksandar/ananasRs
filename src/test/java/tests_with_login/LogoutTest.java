package tests_with_login;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.UserLoggedInPage;
import pages.VerificationPage;

public class LogoutTest extends BaseTestWithLogin{

    @Test
    public void logout(){
        UserLoggedInPage userLoggedInPage = new UserLoggedInPage(driver);
        VerificationPage verificationPage = new VerificationPage(driver);
        userLoggedInPage.logout();

        try {
            verificationPage.verifyLogout("Sign in");
            System.out.println("User is logged out");
        }catch (Exception e ){
            Assert.fail("Something went wrong");
        }
}
}

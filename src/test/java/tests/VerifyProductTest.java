package tests;

import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.VerifyProductPage;
import utilities.PropertyManager;

public class VerifyProductTest extends BaseTest{

    @Test

    public void verifyProductTest() {

        try {
            VerifyProductPage verifyProductPage = new VerifyProductPage(driver);

            verifyProductPage.searchPage(PropertyManager.getInstance().getKljucnaRec());
            verifyProductPage.verifyThreePages(PropertyManager.getInstance().getKljucnaRec());


        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }

}

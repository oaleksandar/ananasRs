package tests;

import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.VerifyProductPage;
import utilities.PropertyManager;

public class VerifyProductTest extends BaseTest{

    @Test
    @Description("Check the first 3 pages of search results and verify that all products have keyword, " +
                 "title, price and image, and logs prices for all products in the console")

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

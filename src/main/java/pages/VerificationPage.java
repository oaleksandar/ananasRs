package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VerificationPage extends BasePage{

    public VerificationPage(WebDriver driver) {
        super(driver);
    }
     By errorNotification = By.xpath("//div[@id='center_column']/div[1]");
     By signOutButton = By.className("logout");

    public void verifyFailedLogin(String expectedText){
        String error = readText(errorNotification);
        assertStringEquals(error,expectedText);
    }

    public void verifyLogin(String expectedText){
        String signOut = readText(signOutButton);
        assertStringEquals(signOut,expectedText);

    }
}

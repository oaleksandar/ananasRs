package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserLoggedInPage extends BasePage{
    public UserLoggedInPage(WebDriver driver) {
        super(driver);
    }

    By signOutButton = By.className("logout");

    public void logout(){
        click(signOutButton);
    }
}

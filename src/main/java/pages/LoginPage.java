package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    By signinButtonBy = By.className("login");
    By emailFeildBy = By.id("email");
    By passwordFeildBy = By.id("passwd");
    By submitLoginBy = By.id("SubmitLogin");
    By regEmailFeildBy = By.id("email_create");
    By createAccountButtonBy = By.id("SubmitCreate");



    public void login(String email, String password){
         click(signinButtonBy);
         writeText(emailFeildBy,email);
         writeText(passwordFeildBy,password);
         click(submitLoginBy);

    }

    public void navigateRegistration (String regEmail){
        click(signinButtonBy);
        writeText(regEmailFeildBy,regEmail);
        click(createAccountButtonBy);
    }

}

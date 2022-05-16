package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage extends BasePage{
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }
    By genderRatioButton = By.id("uniform-id_gender2");
    By firstNameInputFeild = By.id("customer_firstname");
    By lastNameInputFeild = By.id("customer_lastname");
    By emailInputFeild = By.id("email");
    By passwordInputFeild = By.id("passwd");
    By adressInputFeild = By.id("address1");
    By cityInputFiled = By.id("city");
    By stateDropDown = By.id("id_state");
    By postCodeInputField = By.id("postcode");
    By mobileInputField = By.id("phone_mobile");
    By aliasInputField = By.id("alias");
    By registerButtonBy = By.id("submitAccount");

    public void register(String firstName, String lastName,String password, String address, String city, String postCode,String mobPhone) {
        click(genderRatioButton);
        writeText(firstNameInputFeild,firstName);
        writeText(lastNameInputFeild,lastName);
        writeText(passwordInputFeild,password);
        writeText(adressInputFeild,address);
        writeText(cityInputFiled,city);
        selectRandomDropDown(stateDropDown);
        writeText(postCodeInputField,postCode);
        writeText(mobileInputField,mobPhone);
        click(registerButtonBy);
    }
}


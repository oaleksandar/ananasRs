package tests_with_login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import utilities.PropertyManager;

public class BaseTestWithLogin {
    protected WebDriver driver;

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver", PropertyManager.getInstance().getDriverPath());

        driver = new ChromeDriver(new ChromeOptions().addArguments("--disable-notifications").addArguments("--start-maximized"));
//         driver.manage().window().maximize(); -older and slower version
        driver.get(PropertyManager.getInstance().getUrl());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(PropertyManager.getInstance().getLoginEmail(), PropertyManager.getInstance().getLoginPassword());
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}

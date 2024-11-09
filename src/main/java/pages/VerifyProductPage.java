package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class VerifyProductPage extends BasePage {

    public VerifyProductPage(WebDriver driver) {
        super(driver);
    }
    By searchBar = By.xpath("//input[@placeholder='Unesi pojam za pretragu...']");
    By pageLogo = By.xpath("/html//div[@id='__next']/div[4]//h1[@href='/']/a[@title='Link do naslovne stranice']//img[@alt='Ananas E-Commerce']");
    By searchButton = By.xpath("//button[@aria-label='Search']");
    By slazemSe = By.xpath("/html//div[@id='__next']//button[.='Slažem se']");
    By popupClose = By.xpath("//button[@aria-label='zatvori popup']");
    By pagination = By.xpath("/html//div[@id='__next']/div[4]//ul[@class='ais-Pagination-list']/li[1]");
    By nextPage = By.xpath("//li[@class='ais-Pagination-item ais-Pagination-item--nextPage']");



    public void searchPage(String kljucnaRec){
        click(slazemSe);
        handlePopUpIfExists(popupClose); //mostly appears from 14:00h to 22:00h
        waitVisibility(pageLogo);
        click(searchBar);
        writeText(searchBar,kljucnaRec);
        click(searchButton);
        waitVisibility(pagination);
    }
    public void verifyThreePages(String kljucnaRec) {
        List<String> accumulatedBugReport = new ArrayList<>(); // List for bug accumulation across all 3 pages

        // Run verification for each page
        accumulatedBugReport.addAll(verifyProductsOnPage(kljucnaRec)); // First page
        click(nextPage);
        accumulatedBugReport.addAll(verifyProductsOnPage(kljucnaRec)); // Second page
        click(nextPage);
        accumulatedBugReport.addAll(verifyProductsOnPage(kljucnaRec)); // Third page

        // Print the accumulated bug report
        if (accumulatedBugReport.isEmpty()) {
            System.out.println("No products with missing attributes across all pages.");
        } else {
            System.out.println("\nAccumulated Bug Report - Issues found across all pages:");
            for (String issue : accumulatedBugReport) {
                System.out.println(issue);
            }
            Assert.fail("Test failed due to bugs found during verification across pages: " + String.join(", ", accumulatedBugReport));
        }
    }
}

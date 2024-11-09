package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,15);
    }
    public void waitVisibility(By elementBy){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
    }

    public void click(By elementBy){
        waitVisibility(elementBy);
        driver.findElement(elementBy).click();
    }
//    public void checkPageNumberHighlighted(By elementBy, String highlightClass) {
//        // Locate the button element using the provided CSS selector
//        waitVisibility(elementBy);
//        WebElement button = driver.findElement(elementBy);
//        // Check if the button contains the specified highlight class
//        if (button.getAttribute("class").contains(highlightClass)) {
//            System.out.println("The button is highlighted.");
//        } else {
//            System.out.println("The button is not highlighted.");
//        }
//    }

    public void handlePopUpIfExists(By elementBy) {
        try {

            // Wait for the pop-up (e.g., an element like a close button) to be visible
            WebElement popUpCloseButton = wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
            // Check if the close button exists, and click to close it
            if (popUpCloseButton.isDisplayed()) {
                popUpCloseButton.click();
                System.out.println("Pop-up closed successfully.");
            }
        } catch (TimeoutException e) {
            // No pop-up appeared within the given time frame
            System.out.println("No pop-up appeared.");
        } catch (NoSuchElementException e) {
            // Handle if the element is not found or any other issues
            System.out.println("Pop-up close button not found.");
        }
    }

    public void writeText(By elementBy, String text){
        waitVisibility(elementBy);
        driver.findElement(elementBy).clear();
        driver.findElement(elementBy).sendKeys(text);

    }
//    public void verifyProducts(WebDriver driver, String kljucnaRec) {
//// Find all products on the page
//        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class, 'sc-492kdg-6 kuFHaS')]")));
//        System.out.println(products);
//        for (WebElement product : products) {
//// Verify keyword, title, price, and image
//            boolean hasKeyword = product.getText().contains(kljucnaRec);
//            boolean hasTitle = !product.findElement(By.xpath("//h3[contains(@class, 'sc-14no49n-0 sc-492kdg-1 eZmkQR bmZJba')]")).getText().isEmpty();
//            boolean hasPrice = !product.findElement(By.xpath("//span[contains(@class, 'sc-1arj7wv-2 iWtCrL')]")).getText().isEmpty();
//            boolean hasImage = product.findElement(By.xpath("//img[contains(@sizes, '(min-width: 1920px) 388px, (min-width: 1280px) 277px, 277px')]")).getText().isEmpty() ;
//
//            if (hasKeyword && hasTitle && hasPrice && hasImage) {
//// Log the price
//                String price = product.findElement(By.xpath("//span[contains(@class, 'sc-1arj7wv-2 iWtCrL')]")).getText();
//                System.out.println("Price: " + price);
//            } else {
//                System.out.println("Product verification failed for: " + product.getText());
//            }
//        }
//    }
public List<String> verifyProductsOnPage(String kljucnaRec) {
    List<String> bugReport = new ArrayList<>(); // List to collect bug report messages for a single page

    try {
        // Wait for the products to be visible (adjust the locator for your specific case)
        List<WebElement> productList = new ArrayList<>();
        boolean staleElement = true;
        while (staleElement) {
            try {
                // Fetch the product list
                productList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class, 'sc-492kdg-6 kuFHaS')]")));
                staleElement = false; // If no exception is thrown, break the loop
            } catch (StaleElementReferenceException e) {
                System.out.println("Stale element reference encountered. Re-fetching product list...");
            }
        }

        List<String> prices = new ArrayList<>(); // List to collect prices

        // Loop through all the products
        for (WebElement product : productList) {
            try {
                // Verify title
                WebElement titleElement = product.findElement(By.xpath(".//h3[contains(@class, 'sc-14no49n-0 sc-492kdg-1 eZmkQR bmZJba')]")); // Adjust selector
                String title = titleElement.getText();
                if (title.isEmpty()) {
                    System.out.println("Product title is missing.");
                    bugReport.add("Product is missing a title.");
                } else {
                    System.out.println("Product title: " + title);
                }

                // Verify keyword
                boolean hasKeyword = product.getText().toLowerCase().contains(kljucnaRec.toLowerCase());
                if (!hasKeyword) {
                    System.out.println("Product keyword is missing.");
                    bugReport.add("Product with title '" + title + "' is missing the keyword: " + kljucnaRec);
                } else {
                    System.out.println("Product keyword: " + kljucnaRec + " is present");
                }

                // Verify price
                WebElement priceElement = product.findElement(By.xpath(".//span[contains(@class, 'sc-1arj7wv-2 iWtCrL')]")); // Adjust selector
                String price = priceElement.getText();
                if (price.isEmpty()) {
                    System.out.println("Product price is missing.");
                    bugReport.add("Product with title '" + title + "' is missing a price.");
                } else {
                    System.out.println("Product price: " + price);
                    prices.add(price);
                }

                // Verify image
                WebElement imageElement = product.findElement(By.xpath("//img[contains(@sizes, '(min-width: 1920px) 388px, (min-width: 1280px) 277px, 277px')]")); // Adjust selector
                String imageSrc = imageElement.getAttribute("src");
                if (imageSrc == null || imageSrc.isEmpty()) {
                    System.out.println("Product image is missing.");
                    bugReport.add("Product with title '" + title + "' is missing an image.");
                } else {
                    System.out.println("Product image URL: " + imageSrc);
                }

            } catch (NoSuchElementException e) {
                System.out.println("A required element (title, price, image) is missing for this product.");
                bugReport.add("A required element (title, price, or image) is missing for one product.");
            }
        }

        // Print the bug report after verifying all products on this page
        if (bugReport.isEmpty()) {
            System.out.println("No products with missing attributes on this page.");
        } else {
            System.out.println("\nBug Report for Current Page - Issues found:");
            for (String issue : bugReport) {
                System.out.println(issue);
            }
        }

        // Print all collected prices
        if (!prices.isEmpty()) {
            System.out.println("\nAll Prices on this page:");
            for (String price : prices) {
                System.out.println(price);
            }
        } else {
            System.out.println("No prices were found on this page.");
        }

    } catch (Exception e) {
        System.out.println("Error occurred while verifying products: " + e.getMessage());
        bugReport.add("Error occurred: " + e.getMessage());
    }

    return bugReport; // Return the bug report for this page
}
}



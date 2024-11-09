package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
    }

    public void waitVisibility(By elementBy) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
    }

    public void click(By elementBy) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).click();
    }

    public void handlePopUpIfExists(By elementBy) {
        // Pop-Up Add should only appear between 14:00 and 22:00
        LocalTime startTime = LocalTime.of(13, 0); // 13:00 hours
        LocalTime endTime = LocalTime.of(22, 0);   // 22:00 hours
        LocalTime currentTime = LocalTime.now();   // Get the current local time

        // Check if the current time is within active hours
        if (currentTime.isAfter(startTime) && currentTime.isBefore(endTime)) {
            try {
                WebElement popUpCloseButton = wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));

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
        } else {
            // Print message if outside active hours
            System.out.println("Current time is outside of active hours (13:00 to 22:00). Pop-up handling skipped.");
        }
    }

    public void writeText(By elementBy, String text) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).clear();
        driver.findElement(elementBy).sendKeys(text);

    }

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
                        bugReport.add("Product is missing a title.");
                    }
                    // Verify keyword
                    boolean hasKeyword = product.getText().toLowerCase().contains(kljucnaRec.toLowerCase());
                    if (!hasKeyword) {
                        bugReport.add("Product with title '" + title + "' is missing the keyword: " + kljucnaRec);
                    }
                    // Verify price
                    WebElement priceElement = product.findElement(By.xpath(".//span[contains(@class, 'sc-1arj7wv-2 iWtCrL')]")); // Adjust selector
                    String price = priceElement.getText();
                    if (price.isEmpty()) {
                        bugReport.add("Product with title '" + title + "' is missing a price.");
                    } else {
                        prices.add(price);
                    }

                    // Verify image
                    WebElement imageElement = product.findElement(By.xpath("//img[contains(@sizes, '(min-width: 1920px) 388px, (min-width: 1280px) 277px, 277px')]")); // Adjust selector
                    String imageSrc = imageElement.getAttribute("src");
                    if (imageSrc == null || imageSrc.isEmpty()) {
                        bugReport.add("Product with title '" + title + "' is missing an image.");
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
                System.out.println("All Prices on this page:");
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



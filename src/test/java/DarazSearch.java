import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class DarazSearch {
    WebDriver driver;

    @BeforeMethod
    public void login_beforeMethod() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.daraz.lk/#?");
    }



    @Test
//valid product
    public void validProduct() {
        // Store the current window handle
        String oldWindow = driver.getWindowHandle();
        System.out.println("parent window " + oldWindow);

        //  enter a valid product name
        WebElement search = driver.findElement(By.xpath("//input[@id='q']"));
        search.sendKeys("earbuds");

        // click the search button
        WebElement searchButton = driver.findElement(By.xpath("//a[normalize-space()='SEARCH']"));
        searchButton.click();

        // Wait for search results to load
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Locate the search results
        WebElement resultsContainer = driver.findElement(By.xpath("//div[@class='_17mcb']"));
        boolean searchResult = resultsContainer.isDisplayed();

        // Verify if results are displayed
        if (searchResult) {
            System.out.println("Test Pass: Results displayed for the valid product search.");
        } else {
            System.out.println("Test Fail: No results displayed for the valid product search.");
        }

        //  verify specific elements in the results
        List<WebElement> products = resultsContainer.findElements(By.className("Bm3ON"));
        if (!products.isEmpty()) {
            System.out.println("Number of products found: " + products.size());
        } else {
            System.out.println("Test Fail: No products found in the results container.");
        }
    }


    @Test
    //invalid product
    public void invalidProduct() {
        String oldWindow = driver.getWindowHandle();
        System.out.println("parent window " + oldWindow);

        WebElement search = driver.findElement(By.xpath("//input[@id='q']"));
        search.sendKeys("hhjj23");
        WebElement searchButton = driver.findElement(By.xpath("//a[normalize-space()='SEARCH']"));
        searchButton.click();

        WebElement text=driver.findElement(By.xpath("//div[@class='jG0xV']"));
        String noResult=text.getText();
        System.out.println(noResult);
        boolean searchResult=text.isDisplayed();
        if(searchResult==true){
            System.out.println("test pass: Result not displayed for the invalid product search");
        }else{
            System.out.println("test fail: Result displayed for valid product search");
        }


    }
}


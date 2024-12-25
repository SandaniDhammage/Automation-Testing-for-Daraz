import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Set;

public class DarazShopping2 {
    WebDriver driver;

    @BeforeMethod
    public void login_beforeMethod() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.daraz.lk/#?");
    }

    @Test
    public void shopping() {

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            // Login Process
            System.out.println("Logging in...");
            driver.get("https://www.daraz.lk/");
            driver.findElement(By.xpath("//a[normalize-space()='Login']")).click();

            // Wait for login  appear
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@placeholder,'Please enter your Phone Number or Email')]")))
                    .sendKeys(" enter valid phone number or email");
            driver.findElement(By.xpath("//input[contains(@placeholder,'Please enter your password')]"))
                    .sendKeys("enter valid password");
            driver.findElement(By.xpath("//button[normalize-space()='LOG IN']")).click();

            // Confirm login success
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='myAccountTrigger']")));
            System.out.println("Login successful!");

            //  Save Login State (Cookies)
            Set<Cookie> cookies = driver.manage().getCookies();

            //  Search and Add Product to Cart
            System.out.println("Navigating to product page...");
            driver.get("https://www.daraz.lk/");
            for (Cookie cookie : cookies) {
                driver.manage().addCookie(cookie); // Reapply cookies for login state
            }
            driver.navigate().refresh(); // Refresh to apply login state

            // Search for the product
            driver.findElement(By.xpath("//input[@id='q']")).sendKeys("earbuds");
            driver.findElement(By.xpath("//a[normalize-space()='SEARCH']")).click();

            // Wait for product list to load
            WebElement productItem = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@class='_17mcb']/div[1]")));
            productItem.click();

            // Add product to cart
            System.out.println("Adding product to cart...");
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'pdp-button pdp-button_type_text pdp-button_theme_orange pdp-button_size_xl')]"))).click();
            WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='cart-message-text']")));
            String success = msg.getText();
            System.out.println(success);
            driver.navigate().back();

            //remove product from the cart
            WebElement cart = driver.findElement(By.xpath("//span[@class='cart-icon-daraz']"));
            cart.click();
            WebElement delete = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'item_')]//span[@class='lazada lazada-ic-Delete lazada-icon icon delete']")));
            delete.click();

            WebElement remove = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='REMOVE']")));
            remove.click();

            boolean isRemoved = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='item_i82aaf059']")));
            if (isRemoved) {
                System.out.println("Product removed successfully.");
            } else {
                System.out.println("Product is still present in the cart.");
            }

// Check if the product is still present in the cart


        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}







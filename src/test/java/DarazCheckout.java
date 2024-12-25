import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class DarazCheckout {
    WebDriver driver;

    @BeforeMethod
    public void login_beforeMethod() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.daraz.lk/#?");
    }

    @Test
    public void chekOut() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            // Step 1: Login Process
            System.out.println("Logging in...");
            driver.get("https://www.daraz.lk/"); // Replace with your Daraz website URL
            driver.findElement(By.xpath("//a[normalize-space()='Login']")).click();

            // Wait for login modal to appear
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@placeholder,'Please enter your Phone Number or Email')]")))
                    .sendKeys("enter valid phone number or email");
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
                driver.manage().addCookie(cookie);
            }
            driver.navigate().refresh();

//click the cart icon and go to shopping cart
            WebElement cart=driver.findElement(By.xpath("//span[@class='cart-icon-daraz']//*[name()='svg']"));
            cart.click();
            WebElement checkbox = driver.findElement(By.xpath("//label[@class='next-checkbox cart-item-checkbox ']"));
            if (checkbox.isDisplayed() && checkbox.isEnabled()) {
                checkbox.click();
            } else {
                System.out.println("Checkbox is not visible or enabled.");
            }
            WebElement proceedButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='PROCEED TO CHECKOUT(1)']")));
            proceedButton.click();
            WebElement proceedToPayButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='Proceed to Pay']")));
            proceedToPayButton.click();
            WebElement card = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='automation-payment-method-item-101']")));
            card.click();
            //type valid card details

            WebElement creditCardField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='creditCard']")));
            creditCardField.sendKeys("1234 3456 5678 3245");
            WebElement creditCardName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='cardName']")));
            creditCardName.sendKeys("VISA Platinum");
            WebElement creditCardDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='expiryDate']")));
            creditCardDate.sendKeys("03/28");
            WebElement creditCardcv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='cvv']")));
            creditCardcv.sendKeys("789");
            //driver.findElement(By.xpath("//button[normalize-space()='Pay Now']")).click();


        } catch (Exception e) {
            e.printStackTrace();

        }


    }
    @Test
    public void invalidCheckout(){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            // Step 1: Login Process
            System.out.println("Logging in...");
            driver.get("https://www.daraz.lk/");
            driver.findElement(By.xpath("//a[normalize-space()='Login']")).click();

            // Wait for login modal to appear
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@placeholder,'Please enter your Phone Number or Email')]")))
                    .sendKeys("enter valid phone number or email");
            driver.findElement(By.xpath("//input[contains(@placeholder,'Please enter your password')]"))
                    .sendKeys("Enter valid password");
            driver.findElement(By.xpath("//button[normalize-space()='LOG IN']")).click();

            // Confirm login success
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='myAccountTrigger']")));
            System.out.println("Login successful!");

            // Save Login State (Cookies)
            Set<Cookie> cookies = driver.manage().getCookies();

            //  Search and Add Product to Cart
            System.out.println("Navigating to product page...");
            driver.get("https://www.daraz.lk/");
            for (Cookie cookie : cookies) {
                driver.manage().addCookie(cookie);
            }
            driver.navigate().refresh();

            WebElement cart=driver.findElement(By.xpath("//span[@class='cart-icon-daraz']//*[name()='svg']"));
            cart.click();
            WebElement checkbox = driver.findElement(By.xpath("//label[@class='next-checkbox cart-item-checkbox ']"));
            if (checkbox.isDisplayed() && checkbox.isEnabled()) {
                checkbox.click();
            } else {
                System.out.println("Checkbox is not visible or enabled.");
            }
            WebElement proceedButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='PROCEED TO CHECKOUT(1)']")));
            proceedButton.click();
            WebElement proceedToPayButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='Proceed to Pay']")));
            proceedToPayButton.click();
            WebElement card = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='automation-payment-method-item-101']")));
            card.click();
//type invalid card details
            WebElement creditCardField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='creditCard']")));
            creditCardField.sendKeys("1234 3456 5678 3245");
            WebElement creditCardName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='cardName']")));
            creditCardName.sendKeys("VISA Platinum");
            WebElement creditCardDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='expiryDate']")));
            creditCardDate.sendKeys("03/00");
            WebElement creditCardcv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='cvv']")));
            creditCardcv.sendKeys("789");
            driver.findElement(By.xpath("//button[normalize-space()='Pay Now']")).click();


        } catch (Exception e) {
            e.printStackTrace();

        }


    }
}

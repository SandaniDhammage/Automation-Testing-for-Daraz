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


public class DarazLogin {
    WebDriver driver;

    @BeforeMethod
    public void login_beforeMethod() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.daraz.lk/#?");
    }

    //valid credentials
    @Test
    public void loginPage() {
        WebElement linkLogin = driver.findElement(By.xpath("//a[normalize-space()='Login']"));
        linkLogin.click();
        WebElement username = driver.findElement(By.xpath("//div[contains(@class,'iweb-dialog-container-enter')]//input[contains(@placeholder,'Please enter your Phone Number or Email')]"));
        username.sendKeys(" enter valid phone number or email");
        WebElement password = driver.findElement(By.xpath("//div[contains(@class,'iweb-dialog-container-enter')]//input[contains(@placeholder,'Please enter your password')]"));
        password.sendKeys("enter valid password");
        WebElement button = driver.findElement(By.xpath("//div[contains(@class,'iweb-dialog-container-enter')]//button[contains(@type,'button')][normalize-space()='LOG IN']"));
        button.click();


    }

    //invalid username
    @Test
    public void invalidDetails() {
        WebElement linkLogin = driver.findElement(By.xpath("//a[normalize-space()='Login']"));
        linkLogin.click();
        WebElement username = driver.findElement(By.xpath("//div[contains(@class,'iweb-dialog-container-enter')]//input[contains(@placeholder,'Please enter your Phone Number or Email')]"));
        username.sendKeys("enter invalid phone number or email");
        WebElement password = driver.findElement(By.xpath("//div[contains(@class,'iweb-dialog-container-enter')]//input[contains(@placeholder,'Please enter your password')]"));
        password.sendKeys("enter valid password");
        WebElement button = driver.findElement(By.xpath("//div[contains(@class,'iweb-dialog-container-enter')]//button[contains(@type,'button')][normalize-space()='LOG IN']"));
        button.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
// Wait for the element to be present or visible
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'iweb-toast-wrap')]")
        ));
// Retrieve and print the text of the element
        String errorMessageText = errorMessage.getText();
        System.out.println("Error Message: " + errorMessageText);

    }

    //invalid password
    @Test
    public void invalidPassword() {
        WebElement linkLogin = driver.findElement(By.xpath("//a[normalize-space()='Login']"));
        linkLogin.click();
        WebElement username = driver.findElement(By.xpath("//div[contains(@class,'iweb-dialog-container-enter')]//input[contains(@placeholder,'Please enter your Phone Number or Email')]"));
        username.sendKeys("enter valid phone number or email");
        WebElement password = driver.findElement(By.xpath("//div[contains(@class,'iweb-dialog-container-enter')]//input[contains(@placeholder,'Please enter your password')]"));
        password.sendKeys("enter invalid password");
        WebElement button = driver.findElement(By.xpath("//div[contains(@class,'iweb-dialog-container-enter')]//button[contains(@type,'button')][normalize-space()='LOG IN']"));
        button.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
// Wait for the element to be present or visible
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'iweb-toast-wrap')]")
        ));
// Retrieve and print the text of the element
        String errorMessageText = errorMessage.getText();
        System.out.println("Error Message: " + errorMessageText);

    }

    //empty username and password
    @Test
    public void emptydCredentials() {
        WebElement linkLogin = driver.findElement(By.xpath("//a[normalize-space()='Login']"));
        linkLogin.click();
        WebElement username = driver.findElement(By.xpath("//div[contains(@class,'iweb-dialog-container-enter')]//input[contains(@placeholder,'Please enter your Phone Number or Email')]"));
        username.sendKeys("");
        WebElement password = driver.findElement(By.xpath("//div[contains(@class,'iweb-dialog-container-enter')]//input[contains(@placeholder,'Please enter your password')]"));
        password.sendKeys("");
        WebElement button = driver.findElement(By.xpath("//div[contains(@class,'iweb-dialog-container-enter')]//button[contains(@type,'button')][normalize-space()='LOG IN']"));
        button.click();
        // Wait and check if the login modal is still displayed
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        boolean isModalDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'iweb-dialog-container-enter')]"))).isDisplayed();


        if (isModalDisplayed) {
            System.out.println("Test Passed: Login modal remains open for empty credentials.");
        } else {
            System.out.println("Test Failed: Login modal closed unexpectedly for empty credentials.");
        }
    }

    }






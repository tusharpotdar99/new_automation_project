package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC002_LoginTest {
    String url = "https://tutorialsninja.com/demo/";


    String app_url = "http://localhost/opencart/";
    String email_ = "tusharpotdar99@gmail.com";
    String password_ = "TushOC@1999";

    String invalidemail = "something@gmail.com";
    String invalidpassword = "something123";

    private final By myAccount = By.xpath("//span[normalize-space()='My Account']");
    private final By login = By.xpath("//a[normalize-space()='Login']");
    private final By emailTextBox = By.xpath("//input[@id='input-email']");
    private final By passwordTextBox = By.xpath("//input[@id='input-password']");
    private final By warningMessage = By.xpath("//div[contains(@class,'alert-danger')]");
    private final By myAccountHeading = By.xpath("//h1[normalize-space()='My Account']");
    private final By loginButton = By.xpath("//button[normalize-space()='Login']");

    @Test(enabled = false, description = "verify user can login with valid credentials")
    public void verifyValidLogin(){

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try{
            driver.get(app_url);
            driver.manage().window().maximize();

            wait.until(ExpectedConditions.elementToBeClickable(myAccount)).click();
            wait.until(ExpectedConditions.elementToBeClickable(login)).click();

            WebElement user = wait.until(ExpectedConditions.visibilityOfElementLocated(emailTextBox));
            user.clear();
            user.sendKeys(email_);

            WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordTextBox));
            password.clear();
            password.sendKeys(password_);

            wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).click();

            boolean result = wait.until(ExpectedConditions.visibilityOfElementLocated(myAccountHeading)).isDisplayed();
            Assert.assertTrue(result, "My Account page is not displayed after login.");
        }
        finally {
            driver.quit();
        }
    }


}

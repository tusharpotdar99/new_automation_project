package testCases;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class TC006_ProductSearchTest {
    String app_url = "http://localhost/opencart/";
    String email_ = "tusharpotdar99@gmail.com";
    String password_ = "TushOC@1999";

    private final By myAccount = By.xpath("//span[normalize-space()='My Account']");
    private final By login = By.xpath("//a[normalize-space()='Login']");
    private final By emailTextBox = By.xpath("//input[@id='input-email']");
    private final By passwordTextBox = By.xpath("//input[@id='input-password']");
    private final By myAccountHeading = By.xpath("//h1[normalize-space()='My Account']");
    private final By loginButton = By.xpath("//button[normalize-space()='Login']");
    private final By logo = By.xpath("//img[@title='Your Store']");
    private final By productTitles = By.xpath("//div[contains(@class,'product-thumb')]//h4//a");
    private final By productCard = By.xpath("//div[contains(@class,'product-thumb')]");
    private final By searchBox = By.xpath("//input[@placeholder='Search']");
    private final By searchButton = By.xpath("(//button[@type='submit'])[1]");



    @Test(enabled = false, description = "verify user can login with valid credentials")
    public void verifyProductSearchCorrectly(){

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

            wait.until(ExpectedConditions.visibilityOfElementLocated(myAccountHeading));

            WebElement logoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(logo));

            wait.until(
                    ExpectedConditions.refreshed(
                            ExpectedConditions.elementToBeClickable(logo)
                    )).click();

//            new code

            String searchTerm = "iphone";
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)).sendKeys(searchTerm);

            wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();


            List<WebElement> productslst=  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productTitles));
            boolean result = false;
            for(WebElement element:productslst){

                String productName = element.getText();
                if (productName.toLowerCase().contains("iphone")){
                    result = true;
                    System.out.print(productName+",");
                }
            }

//            boolean found = products.stream()
//                    .map(WebElement::getText)
//                    .anyMatch(name -> name.toLowerCase().contains(searchTerm.toLowerCase()));

            Assert.assertTrue(result, "Expected product was not found in search results.");




        } catch (Exception e) {
            Assert.fail("Test failed: " + e.getMessage(), e);
        }
        finally {
            driver.quit();
        }
    }

}

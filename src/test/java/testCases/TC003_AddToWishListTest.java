package testCases;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v143.page.model.Screenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.List;

@Slf4j
public class TC003_AddToWishListTest {
    String app_url = "http://localhost/opencart/";
    String email_ = "tusharpotdar99@gmail.com";
    String password_ = "TushOC@1999";

    private final By myAccount = By.xpath("//span[normalize-space()='My Account']");
    private final By login = By.xpath("//a[normalize-space()='Login']");

    private final By emailTextBox = By.xpath("//input[@id='input-email']");
    private final By passwordTextBox = By.xpath("//input[@id='input-password']");
    private final By loginButton = By.xpath("//button[normalize-space()='Login']");

    private final By logo = By.xpath("//img[@title='Your Store']");
    private final By productTitles = By.xpath("//div[contains(@class,'product-thumb')]//h4//a");

    private final By searchBox = By.xpath("//input[@placeholder='Search']");
    private final By searchButton = By.xpath("(//button[@type='submit'])[1]");

    private final By addToWishListBtn = By.xpath("//button[@title='Add to Wish List']");
    private final By wishListAlert = By.xpath("//div[@id='alert']");
    //div[@class='alert alert-success alert-dismissible']

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));





    @Test(priority = 2, enabled = false, description = "verify user can login with valid credentials.")
    public void verifyAddToWishList(){
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
            WebElement logoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(logo));

            File screenshot = logoElement.getScreenshotAs(OutputType.FILE);

            Files.copy(screenshot.toPath(), Path.of("ScreenShots", "logosc.png"), StandardCopyOption.REPLACE_EXISTING);

            wait.until(
                    ExpectedConditions.refreshed(
                            ExpectedConditions.elementToBeClickable(logo)
                    )
            ).click();



            List<WebElement> productslst=  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productTitles));
            for(WebElement element:productslst){
                String ele = element.getText();
                System.out.print(ele+",");
                if(ele.equalsIgnoreCase("MacBook")){
                    ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].scrollIntoView({block:'center'});",
                            element
                    );
                    wait.until(ExpectedConditions.elementToBeClickable(element));
                    element.click();
                    break;
                }
            }

            wait.until(ExpectedConditions.elementToBeClickable(addToWishListBtn)).click();

            WebElement alert = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(wishListAlert)
            );

            String alertText = alert.getText();
            System.out.println("Alert : "+alertText);
//
//            int productCount = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productTitles)).size();
//            boolean result = productCount > 0 ? true: false;

            Assert.assertTrue(alertText.contains("wish list"), "Wishlist success message was not displayed.");
        } catch (Exception e) {
            Assert.fail("Test failed: " + e.getMessage(), e);
        }
        finally {
            driver.quit();
        }
    }


}

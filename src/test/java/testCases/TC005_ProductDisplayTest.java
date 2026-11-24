package testCases;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
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

public class TC005_ProductDisplayTest {
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

    @Test(enabled = false, description = "verify user can login with valid credentials")
    public void verifyProductListDisplayed(){

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

//            File screenshot = logoElement.getScreenshotAs(OutputType.FILE);

//            Files.copy(screenshot.toPath(), Path.of("ScreenShots", "logosc.png"), StandardCopyOption.REPLACE_EXISTING);

            wait.until(
                    ExpectedConditions.refreshed(
                            ExpectedConditions.elementToBeClickable(logo)
                    )).click();

            List<WebElement> productslst=  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productTitles));
            for(WebElement element:productslst){

                String productName = element.getText();
                System.out.print(productName+",");
            }

            List<WebElement> productCardList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productCard));

            for(int i = 0; i<productCardList.size(); i++){
                By cardXpath = By.xpath("(//div[contains(@class,'product-thumb')])[" + (i + 1) + "]");
                By linkXpath = By.xpath("(//div[contains(@class,'product-thumb')]//h4//a)[" + (i + 1) + "]");
//                By xpth = By.xpath("(//div[contains(@class,'product-thumb')])[" + (i + 1) + "]");
                WebElement cardEle = wait.until(ExpectedConditions.visibilityOfElementLocated(cardXpath));
                File sc = cardEle.getScreenshotAs(OutputType.FILE);
                Files.copy(sc.toPath(), Path.of("ScreenShots", "sc" + (i + 1) + ".png"), StandardCopyOption.REPLACE_EXISTING);
//                wait.until(ExpectedConditions.elementToBeClickable(linkXpath)).click();
//                driver.navigate().back();
            }
            boolean result = !productCardList.isEmpty();

            Assert.assertTrue(result, "Product list is not displayed");





        } catch (Exception e) {
            Assert.fail("Test failed: " + e.getMessage(), e);
        }
        finally {
            driver.quit();
        }
    }

}

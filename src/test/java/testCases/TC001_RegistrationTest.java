package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RegistrationData;
import utilities.TestDataGenerator;

import java.time.Duration;

public class TC001_RegistrationTest {
    String app_url = "http://localhost/opencart/";

    private final By myAccount = By.xpath("//span[normalize-space()='My Account']");
    private final By register = By.xpath("//a[normalize-space()='Register']");

    private final By myAccountHeading = By.xpath("//h1[normalize-space()='My Account']");
    private final By subHeadings = By.cssSelector("#content h2");

    private final By registerPageHeading = By.xpath("//h1[normalize-space()='Register Account']");
    private final By personalDetailsTitle = By.xpath("//legend[normalize-space()='Your Personal Details']");
    private final By redirectedToLogin = By.xpath("//a[normalize-space()='login page']");

    private final By firstNameTextBox = By.xpath("//input[@id='input-firstname']");
    private final By lastNameTextBox = By.xpath("//input[@id='input-lastname']");
    private final By emailTextbox = By.xpath("//input[@id='input-email']");
    private final By passwordTextBox = By.xpath("//input[@id='input-password']");
    private final By newsletterCheckbox = By.xpath("//input[@id='input-newsletter']");
//    private final By newsletterCheckbox = By.xpath("//input[@type ='checkbox' and @id = 'input-newsletter']");

    private final By privacyPolicyCheck = By.xpath("//input[@type ='checkbox' and @name = 'agree']");

    private final By continueButton = By.xpath("//button[normalize-space()='Continue']");

    private final By subscribeYesRadio = By.xpath("//input[@type='radio' and @name='newsletter' and @value='1']");
    private final By subscribeNoRadio = By.xpath("//input[@type='radio' and @name='newsletter' and @value='0']");

    private final By pageHeading =
            By.xpath("//h1[normalize-space()='Your Account Has Been Created!']");

    private final By successMessage =
            By.xpath("//div[@id='content']/p[1]");

    private final By errorFirstName = By.xpath("//div[@id='error-firstname']");
    private final By errorLastName = By.xpath("//div[@id='error-lastname']");
    private final By errorEmail = By.xpath("//div[@id='error-email']");
    private final By errorPassoword = By.xpath("//div[@id='error-password']");
    private final By warningPrivacyPolicyUnchecked = By.xpath("//div[@id='alert']");

    @Test(
            invocationCount = 1,
            enabled = false,
            priority = 1,
            description = "Verify user can register with valid details"
    )
    public void verifyUserRegistrationWithValidData() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try{
            driver.get(app_url);
            driver.manage().window().maximize();
            JavascriptExecutor js = (JavascriptExecutor)driver;

            wait.until(ExpectedConditions.elementToBeClickable(myAccount)).click();
            wait.until(ExpectedConditions.elementToBeClickable(register)).click();

            RegistrationData user = TestDataGenerator.generateUser();

            WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameTextBox));
            firstName.clear();
            firstName.sendKeys(user.getFirstName());

            WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameTextBox));
            lastName.clear();
            lastName.sendKeys(user.getLastName());

            WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(emailTextbox));
            email.clear();
            email.sendKeys(user.getEmail());

            WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordTextBox));
            password.clear();
            password.sendKeys(user.getPassword());

            System.out.println("Before scroll: " +  js.executeScript("return window.scrollY;"));

            js.executeScript( "window.scrollTo(0, document.documentElement.scrollHeight);");

            System.out.println("After scroll: " + js.executeScript("return window.scrollY;"));

            WebElement newslettercheck = wait.until(ExpectedConditions.elementToBeClickable(newsletterCheckbox));
//            js.executeScript("window.scrollBy(0, 2000)");
//            js.executeScript(    "arguments[0].scrollIntoView({block:'center'});",newslettercheck
//                    );
            if(!newslettercheck.isSelected()){
                newslettercheck.click();
            }

            WebElement privacyPolicy = wait.until(ExpectedConditions.elementToBeClickable(privacyPolicyCheck));
            if(!privacyPolicy.isSelected()){
                privacyPolicy.click();
            }

            WebElement continues = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
            continues.click();

            WebElement pageHeadings = wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeading));
            pageHeadings.isDisplayed();


            boolean result = pageHeadings.getText().contains("Your Account Has Been Created!");
            Assert.assertTrue(result, "Account is not created.");
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
        }
        finally {
            driver.quit();
        }

    }

    @Test(
            invocationCount = 1,
            enabled = true,
            priority = 1,
            description = "Verify user cannot register with missing mandatory fields"
    )
    public void verifyUserRegistrationWithMissingMandatoryFields() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try{
            driver.get(app_url);
            driver.manage().window().maximize();
            JavascriptExecutor js = (JavascriptExecutor)driver;

            wait.until(ExpectedConditions.elementToBeClickable(myAccount)).click();
            wait.until(ExpectedConditions.elementToBeClickable(register)).click();

            RegistrationData user = TestDataGenerator.generateUser();

            WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameTextBox));
            firstName.clear();
            firstName.sendKeys("");

            WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameTextBox));
            lastName.clear();
            lastName.sendKeys("");

            WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(emailTextbox));
            email.clear();
            email.sendKeys("");

            WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordTextBox));
            password.clear();
            password.sendKeys("");

            System.out.println("Before scroll: " +  js.executeScript("return window.scrollY;"));

            js.executeScript( "window.scrollTo(0, document.documentElement.scrollHeight);");

            System.out.println("After scroll: " + js.executeScript("return window.scrollY;"));

            WebElement newslettercheck = wait.until(ExpectedConditions.elementToBeClickable(newsletterCheckbox));
            js.executeScript("window.scrollBy(0, 2000)");
//            js.executeScript(    "arguments[0].scrollIntoView({block:'center'});",newslettercheck
//                    );


            if(!newslettercheck.isSelected()){
                newslettercheck.click();
            }

            WebElement privacyPolicy = wait.until(ExpectedConditions.elementToBeClickable(privacyPolicyCheck));
            if(!privacyPolicy.isSelected()){
                privacyPolicy.click();
            }

            WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
            js.executeScript("arguments[0].click();", continueBtn);

            boolean result = false;
            String fnameError = wait.until(ExpectedConditions.visibilityOfElementLocated(errorFirstName)).getText();
            String lnameError = wait.until(ExpectedConditions.visibilityOfElementLocated(errorLastName)).getText();
            String emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(errorEmail)).getText();
            String passwordError = wait.until(ExpectedConditions.visibilityOfElementLocated(errorPassoword)).getText();

            System.out.println(fnameError+"\n"+lnameError+"\n"+emailError+"\n"+passwordError);

            if(!fnameError.isEmpty() && !lnameError.isEmpty() && !emailError.isEmpty() && !passwordError.isEmpty()){
                result = true;
            }

            Assert.assertTrue(result, "Error messages are not displayed.");
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
        }
        finally {
            driver.quit();
        }

    }


}

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SignUpTest {


    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        //options.addArguments("headless"); //тесты запускаются без отображения окна браузера
        driver = new ChromeDriver(options);
        //driver.manage().window().setSize(new Dimension(1280,768));
        //driver.manage().addCookie(new Cookie("SSID","oashdoiahsdoih")); //если зайти на сайт с этими куки, то определиться сразу их владелец
        //driver.manage().timeouts().setScriptTimeout();
        //driver.manage().timeouts().pageLoadTimeout();//кружочек на вкладке сайта
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//20 секунд максимум
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void validZipCodeShouldBeAccepted() {
        //Open https://www.sharelane.com/cgi-bin/register.py
        //Enter 11111 into ZIP Code
        //Click on [Continue]
        //Check that [Register] button exists

        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("11111");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        boolean isPageOpened = driver.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        assertTrue(isPageOpened, "Sign Up page was not opened");
    }

    @Test
    public void zipCodeShouldBeRequired() {
        //Open https://www.sharelane.com/cgi-bin/register.py
        //Click on [Continue]
        //Check that error with specific text exists

        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error, "Oops, error on page. ZIP code should have 5 digits", "Error text is not correct OR is not displayed");
    }

    @Test
    public void zipCodeWithLengthMoreThanFiveCharsShouldBeRejected() {
        //Open https://www.sharelane.com/cgi-bin/register.py
        //Enter 111111 into ZIP Code
        //Click on [Continue]
        //Check that error with specific text exists

        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("111111");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error, "Oops, error on page. ZIP code should have 5 digits", "ZIP code: Error text is not correct");
    }

    @Test
    public void firstNameShouldBeMandatory() {
        //Open https://www.sharelane.com/cgi-bin/register.py
        //Enter 11111 into ZIP Code
        //Click on [Continue]
        //Enter as@gmail.com into Email field
        //Enter 12345 into Password field
        //Enter 12345 into Confirm Password field
        //Click on [Register]
        //Check that error message with specific text exists

        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("111111");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        driver.findElement(By.name("email")).sendKeys("as@gmail.com");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String errorMessage = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(errorMessage, "Oops, error on page. Some of your fields have invalid data or email was previously used", "First name: Error text is not correct");
    }

    @Test
    public void emailShouldBeMandatory() {
        //Open https://www.sharelane.com/cgi-bin/register.py
        //Enter 11111 into ZIP Code
        //Click on [Continue]
        //Enter Nastya into First Name field
        //Enter 12345 into Password field
        //Enter 12345 into Confirm Password field
        //Click on [Register]
        //Check that error message with specific text exists

        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("111111");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        driver.findElement(By.name("first_name")).sendKeys("Nastya");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String errorMessage = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(errorMessage, "Oops, error on page. Some of your fields have invalid data or email was previously used", "Email: Error text is not correct");
    }

    @Test
    public void emailShouldBeFormattedAsEmail() {
        //Open https://www.sharelane.com/cgi-bin/register.py
        //Enter 11111 into ZIP Code
        //Click on [Continue]
        //Enter Nastya into First Name field
        //Enter asgmail.com into Email field
        //Enter 12345 into Password field
        //Enter 12345 into Confirm Password field
        //Click on [Register]
        //Check that error message with specific text exists

        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("111111");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        driver.findElement(By.name("first_name")).sendKeys("Nastya");
        driver.findElement(By.name("email")).sendKeys("asgmail.com");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String errorMessage = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(errorMessage, "Oops, error on page. Some of your fields have invalid data or email was previously used", "Email: Error text is not correct");
    }

    @Test
    public void emailShouldBeNotAlreadyUsed() {
        //Open https://www.sharelane.com/cgi-bin/register.py
        //Enter 11111 into ZIP Code
        //Click on [Continue]
        //Enter Nastya into First Name field
        //Enter as@gmail.com into Email field
        //Enter 12345 into Password field
        //Enter 12345 into Confirm Password field
        //Click on [Register]
        //Click on the 'here' link;
        //Repeat filling fields and clicking on [Register] button;
        //Check that error message with specific text exists

        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("111111");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        driver.findElement(By.name("first_name")).sendKeys("Nastya");
        driver.findElement(By.name("email")).sendKeys("as@gmail.com");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        driver.findElement(By.cssSelector("a[href*='./main.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./register.py']")).click();
        driver.findElement(By.name("zip_code")).sendKeys("111111");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        driver.findElement(By.name("first_name")).sendKeys("Nastya");
        driver.findElement(By.name("email")).sendKeys("as@gmail.com");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String errorMessage = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(errorMessage, "Oops, error on page. Some of your fields have invalid data or email was previously used", "Already registered user's email is accepted");
    }

    @Test
    public void passwordShouldBeMandatory() {
        //Open https://www.sharelane.com/cgi-bin/register.py
        //Enter 11111 into ZIP Code
        //Click on [Continue]
        //Enter Nastya into First Name field
        //Enter as@gmail.com into Email field
        //Enter 12345 into Confirm Password field
        //Click on [Register]
        //Check that error message with specific text exists

        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("111111");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        driver.findElement(By.name("first_name")).sendKeys("Nastya");
        driver.findElement(By.name("email")).sendKeys("as@gmail.com");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String errorMessage = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(errorMessage, "Oops, error on page. Some of your fields have invalid data or email was previously used", "Password: Error text is not correct");
    }

    @Test
    public void confirmPasswordShouldBeMandatory() {
        //Open https://www.sharelane.com/cgi-bin/register.py
        //Enter 11111 into ZIP Code
        //Click on [Continue]
        //Enter Nastya into First Name field
        //Enter as@gmail.com into Email field
        //Enter 12345 into Password field
        //Click on [Register]
        //Check that error message with specific text exists

        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("111111");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        driver.findElement(By.name("first_name")).sendKeys("Nastya");
        driver.findElement(By.name("email")).sendKeys("as@gmail.com");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String errorMessage = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(errorMessage, "Oops, error on page. Some of your fields have invalid data or email was previously used", "Confirm password: Error text is not correct");
    }

    @Test
    public void confirmPasswordShouldBeMatchedToPassword() {
        //Open https://www.sharelane.com/cgi-bin/register.py
        //Enter 11111 into ZIP Code
        //Click on [Continue]
        //Enter Nastya into First Name field
        //Enter as@gmail.com into Email field
        //Enter 12345 into Password field
        //Enter 123456 into Confirm Password field
        //Click on [Register]
        //Check that error message with specific text exists

        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("111111");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        driver.findElement(By.name("first_name")).sendKeys("Nastya");
        driver.findElement(By.name("email")).sendKeys("as@gmail.com");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("123456");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String errorMessage = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(errorMessage, "Oops, error on page. Some of your fields have invalid data or email was previously used", "Confirm password: not match password is accepted");
    }

    @Test
    public void hereLinkShouldNavigateToLoginForm() {
        //Open https://www.sharelane.com/cgi-bin/register.py
        //Enter 11111 into ZIP Code
        //Click on [Continue]
        //Enter Nastya into First Name field
        //Enter as@gmail.com into Email field
        //Enter 12345 into Password field
        //Enter 12345 into Confirm Password field
        //Click on [Register]
        //Click on the 'here' link;
        //Check that [Login] button exists

        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("111111");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        driver.findElement(By.name("first_name")).sendKeys("Nastya");
        driver.findElement(By.name("email")).sendKeys("as@gmail.com");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        driver.findElement(By.cssSelector("a[href*='./main.py']")).click();
        boolean loginButton = driver.findElement(By.cssSelector("[value=Login]")).isDisplayed();
        assertTrue(loginButton, "Login fields are not opened");
    }

    @Test
    public void successfulRegistrationShouldBePerformed() {
        //Open https://www.sharelane.com/cgi-bin/register.py
        //Enter 11111 into ZIP Code
        //Click on [Continue]
        //Enter Nastya into First Name field
        //Enter as@gmail.com into Email field
        //Enter 12345 into Password field
        //Enter 12345 into Confirm Password field
        //Click on [Register]
        //Check that successful message with specific text exists

        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("111111");
        //driver.findElement(By.name("zip_code")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        driver.findElement(By.name("first_name")).sendKeys("Nastya");
        driver.findElement(By.name("email")).sendKeys("as@gmail.com");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String successfulMessage = driver.findElement(By.cssSelector("[class=confirmation_message]")).getText();
        assertEquals(successfulMessage, "Account is created!", "Registration failed");
    }

//    @Test
//    public void passwordShouldBeHiddenWhenEntered() {
//
//        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
//        driver.get("https://www.sharelane.com/cgi-bin/register.py");
//        driver.findElement(By.name("password1")).getAttribute("value");
//    }
}

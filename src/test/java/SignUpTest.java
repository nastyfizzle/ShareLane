import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SignUpTest {

    @Test
    public void validZipCodeShouldBeAccepted() {
        //Open https://www.sharelane.com/cgi-bin/register.py
        //Enter 11111 into ZIP Code
        //Click on [Continue]
        //Check that [Register] button exists

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("11111");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        boolean isPageOpened = driver.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        assertTrue(isPageOpened, "Sign Up page was not opened");
        driver.quit();
    }

    @Test
    public void zipCodeShouldBeRequired() {
        //Open https://www.sharelane.com/cgi-bin/register.py
        //Click on [Continue]
        //Check that error with specific text exists

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error, "Oops, error on page. ZIP code should have 5 digits", "Error text is not correct OR is not displayed");
        driver.quit();
    }

    @Test
    public void zipCodeWithLengthMoreThanFiveCharsShouldBeRejected() {
        //Open https://www.sharelane.com/cgi-bin/register.py
        //Enter 111111 into ZIP Code
        //Click on [Continue]
        //Check that error with specific text exists

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("111111");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error, "Oops, error on page. ZIP code should have 5 digits", "ZIP code: Error text is not correct");
        driver.quit();
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

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("111111");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        driver.findElement(By.name("email")).sendKeys("as@gmail.com");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String errorMessage = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(errorMessage, "Oops, error on page. Some of your fields have invalid data or email was previously used", "First name: Error text is not correct");
        driver.quit();
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

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("111111");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        driver.findElement(By.name("first_name")).sendKeys("Nastya");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String errorMessage = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(errorMessage, "Oops, error on page. Some of your fields have invalid data or email was previously used", "Email: Error text is not correct");
        driver.quit();
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

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
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
        driver.quit();
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

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
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
        driver.quit();
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

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("111111");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        driver.findElement(By.name("first_name")).sendKeys("Nastya");
        driver.findElement(By.name("email")).sendKeys("as@gmail.com");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String errorMessage = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(errorMessage, "Oops, error on page. Some of your fields have invalid data or email was previously used", "Password: Error text is not correct");
        driver.quit();
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

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("111111");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        driver.findElement(By.name("first_name")).sendKeys("Nastya");
        driver.findElement(By.name("email")).sendKeys("as@gmail.com");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String errorMessage = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(errorMessage, "Oops, error on page. Some of your fields have invalid data or email was previously used", "Confirm password: Error text is not correct");
        driver.quit();
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

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
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
        driver.quit();
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

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
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
        driver.quit();
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

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("111111");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        driver.findElement(By.name("first_name")).sendKeys("Nastya");
        driver.findElement(By.name("email")).sendKeys("as@gmail.com");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String successfulMessage = driver.findElement(By.cssSelector("[class=confirmation_message]")).getText();
        assertEquals(successfulMessage, "Account is created!", "Registration failed");
        driver.quit();
    }
}

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class DiscountTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Register -> Login -> Add a book to cart ->
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("11111");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        driver.findElement(By.name("first_name")).sendKeys("Nastya");
        driver.findElement(By.name("email")).sendKeys("as@gmail.com");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String email = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/b")).getText();
        //todo: it's not correct locator, I tried to use this locator "//table//tbody/tr[2]/td[1] but it's not correct too
        String password = driver.findElement(By.xpath("//table//tbody/tr[2]/td[2]")).getText();
        driver.findElement(By.cssSelector("a[href*='./main.py']")).click();
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("[value=Login]")).click();
    }

    //Check discount 0% (enter 1)
    @Test
    public void zeroPercentDiscountShouldBeForMinimumAmount() {
        driver.findElement(By.cssSelector("a[href*='./show_book.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./add_to_cart.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./shopping_cart.py']")).click();
        String discount = driver.findElement(By.xpath("//table//tbody/tr[5]//tbody/tr[2]/td[5]")).getText();
        assertEquals(discount, "0", "Amount of discount is incorrect");
    }

    //Check a discount 0% (enter 19)
    @Test
    public void zeroPercentDiscountShouldBeForMaximumAmount() {
        driver.findElement(By.cssSelector("a[href*='./show_book.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./add_to_cart.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./shopping_cart.py']")).click();
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("19");
        driver.findElement(By.cssSelector("[value=Update]")).click();
        String discount = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText(); //todo: issues with locator
        assertEquals(discount, "0", "Amount of discount is incorrect");
    }

    //Check a discount 2% (enter 20)
    @Test
    public void twoPercentDiscountShouldBeForMinimumAmount() {
        driver.findElement(By.cssSelector("a[href*='./show_book.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./add_to_cart.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./shopping_cart.py']")).click();
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("20");
        driver.findElement(By.cssSelector("[value=Update]")).click();
        String discount = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText(); //todo: issues with locator
        assertEquals(discount, "2", "Amount of discount is incorrect");
    }

    //Check a discount 2% (enter 49)
    @Test
    public void twoPercentDiscountShouldBeForMaximumAmount() {
        driver.findElement(By.cssSelector("a[href*='./show_book.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./add_to_cart.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./shopping_cart.py']")).click();
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("49");
        driver.findElement(By.cssSelector("[value=Update]")).click();
        String discount = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText(); //todo: issues with locator
        assertEquals(discount, "2", "Amount of discount is incorrect");
    }

    //Check a discount 3% (enter 50)
    @Test
    public void threePercentDiscountShouldBeForMinimumAmount() {
        driver.findElement(By.cssSelector("a[href*='./show_book.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./add_to_cart.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./shopping_cart.py']")).click();
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("50");
        driver.findElement(By.cssSelector("[value=Update]")).click();
        String discount = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText(); //todo: issues with locator
        assertEquals(discount, "3", "Amount of discount is incorrect");
    }

    //Check a discount 3% (enter 99)
    @Test
    public void threePercentDiscountShouldBeForMaximumAmount() {
        driver.findElement(By.cssSelector("a[href*='./show_book.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./add_to_cart.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./shopping_cart.py']")).click();
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("99");
        driver.findElement(By.cssSelector("[value=Update]")).click();
        String discount = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText(); //todo: issues with locator
        assertEquals(discount, "3", "Amount of discount is incorrect");
    }

    //Check a discount 4% (enter 100)
    @Test
    public void fourPercentDiscountShouldBeForMinimumAmount() {
        driver.findElement(By.cssSelector("a[href*='./show_book.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./add_to_cart.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./shopping_cart.py']")).click();
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("100");
        driver.findElement(By.cssSelector("[value=Update]")).click();
        String discount = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText(); //todo: issues with locator
        assertEquals(discount, "4", "Amount of discount is incorrect");
    }

    //Check a discount 4% (enter 499)
    @Test
    public void fourPercentDiscountShouldBeForMaximumAmount() {
        driver.findElement(By.cssSelector("a[href*='./show_book.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./add_to_cart.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./shopping_cart.py']")).click();
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("499");
        driver.findElement(By.cssSelector("[value=Update]")).click();
        String discount = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText(); //todo: issues with locator
        assertEquals(discount, "4", "Amount of discount is incorrect");
    }


    //Check a discount 5% (enter 500)
    @Test
    public void fivePercentDiscountShouldBeForMinimumAmount() {
        driver.findElement(By.cssSelector("a[href*='./show_book.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./add_to_cart.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./shopping_cart.py']")).click();
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("500");
        driver.findElement(By.cssSelector("[value=Update]")).click();
        String discount = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText(); //todo: issues with locator
        assertEquals(discount, "5", "Amount of discount is incorrect");
    }

    //Check a discount 5% (enter 999)
    @Test
    public void fivePercentDiscountShouldBeForMaximumAmount() {
        driver.findElement(By.cssSelector("a[href*='./show_book.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./add_to_cart.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./shopping_cart.py']")).click();
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("999");
        driver.findElement(By.cssSelector("[value=Update]")).click();
        String discount = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText(); //todo: issues with locator
        assertEquals(discount, "5", "Amount of discount is incorrect");
    }

    //Check a discount 6% (enter 1000)
    @Test
    public void sixPercentDiscountShouldBeForMinimumAmount() {
        driver.findElement(By.cssSelector("a[href*='./show_book.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./add_to_cart.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./shopping_cart.py']")).click();
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("1000");
        driver.findElement(By.cssSelector("[value=Update]")).click();
        String discount = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText(); //todo: issues with locator
        assertEquals(discount, "6", "Amount of discount is incorrect");
    }

    //Check a discount 6% (enter 4999)
    @Test
    public void sixPercentDiscountShouldBeForMaximumAmount() {
        driver.findElement(By.cssSelector("a[href*='./show_book.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./add_to_cart.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./shopping_cart.py']")).click();
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("4999");
        driver.findElement(By.cssSelector("[value=Update]")).click();
        String discount = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText(); //todo: issues with locator
        assertEquals(discount, "6", "Amount of discount is incorrect");
    }

    //Check a discount 7% (enter 5000)
    @Test
    public void sevenPercentDiscountShouldBeForMinimumAmount() {
        driver.findElement(By.cssSelector("a[href*='./show_book.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./add_to_cart.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./shopping_cart.py']")).click();
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("5000");
        driver.findElement(By.cssSelector("[value=Update]")).click();
        String discount = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText(); //todo: issues with locator
        assertEquals(discount, "7", "Amount of discount is incorrect");
    }

    //Check a discount 7% (enter 9999)
    @Test
    public void sevenPercentDiscountShouldBeForMaximumAmount() {
        driver.findElement(By.cssSelector("a[href*='./show_book.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./add_to_cart.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./shopping_cart.py']")).click();
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("9999");
        driver.findElement(By.cssSelector("[value=Update]")).click();
        String discount = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText(); //todo: issues with locator
        assertEquals(discount, "7", "Amount of discount is incorrect");
    }

    //Check a discount 8% (enter 100000)
    @Test
    public void eightPercentDiscountShouldBeForMinimumAmount() {
        driver.findElement(By.cssSelector("a[href*='./show_book.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./add_to_cart.py']")).click();
        driver.findElement(By.cssSelector("a[href*='./shopping_cart.py']")).click();
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("100000");
        driver.findElement(By.cssSelector("[value=Update]")).click();
        String discount = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText(); //todo: issues with locator
        assertEquals(discount, "8", "Amount of discount is incorrect");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

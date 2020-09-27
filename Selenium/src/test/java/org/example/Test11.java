package org.example;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test11 {
    private WebDriverWait wait;

    @Test
    public void test11() throws InterruptedException {
        int myRandomNumber = 0;

        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("mainpage"));

        driver.findElement(By.linkText("New customers click here")).click();
        TimeUnit.SECONDS.sleep(1);
        WebElement someText = driver.findElement(By.cssSelector("[name=firstname]"));
        someText.sendKeys("user");
        TimeUnit.SECONDS.sleep(1);
        someText = driver.findElement(By.cssSelector("[name=lastname]"));
        someText.sendKeys("user");
        TimeUnit.SECONDS.sleep(1);
        someText = driver.findElement(By.name("address1"));
        someText.sendKeys("address1");
        TimeUnit.SECONDS.sleep(1);
        someText = driver.findElement(By.cssSelector("[name=postcode]"));
        someText.sendKeys("11111");
        TimeUnit.SECONDS.sleep(1);
        someText = driver.findElement(By.cssSelector("[name=city]"));
        someText.sendKeys("city");
        TimeUnit.SECONDS.sleep(1);
        WebElement country = driver.findElement(By.tagName("select"));
        Select select = new Select(country);
        select.selectByVisibleText("United States");
        TimeUnit.SECONDS.sleep(1);
        someText = driver.findElement(By.cssSelector("[name=email]"));
        myRandomNumber=random();
        someText.sendKeys("user" + myRandomNumber + "@mail.ru");
        TimeUnit.SECONDS.sleep(1);
        someText = driver.findElement(By.cssSelector("[name=phone]"));
        someText.sendKeys("+" + random());
        TimeUnit.SECONDS.sleep(1);
        someText = driver.findElement(By.cssSelector("[name=password]"));
        someText.sendKeys("admin");
        TimeUnit.SECONDS.sleep(1);
        someText = driver.findElement(By.cssSelector("[name=confirmed_password]"));
        someText.sendKeys("admin");
        TimeUnit.SECONDS.sleep(1);
        someText = driver.findElement(By.cssSelector("[name=create_account]"));
        someText.click();
        someText = driver.findElement(By.cssSelector("[name=password]"));
        someText.sendKeys("admin");
        TimeUnit.SECONDS.sleep(2);
        someText = driver.findElement(By.cssSelector("[name=confirmed_password]"));
        someText.sendKeys("admin");
        TimeUnit.SECONDS.sleep(2);
        someText = driver.findElement(By.cssSelector("[name=create_account]"));
        someText.click();
        TimeUnit.SECONDS.sleep(3);

        driver.findElement(By.linkText("Logout")).click();
        TimeUnit.SECONDS.sleep(3);

        someText = driver.findElement(By.cssSelector("[name=email]"));
        someText.sendKeys("user" + myRandomNumber + "@mail.ru");
        TimeUnit.SECONDS.sleep(3);
        someText = driver.findElement(By.cssSelector("[name=password]"));
        someText.sendKeys("admin");
        TimeUnit.SECONDS.sleep(3);
        driver.findElement(By.name("login")).click();
        wait = new WebDriverWait(driver,10);

        driver.findElement(By.linkText("Logout")).click();
        TimeUnit.SECONDS.sleep(3);

        TimeUnit.SECONDS.sleep(3);
        driver.quit();
    }

    public  int random()
    {
        int randomInt = 0;
        int a = 0;
        int b = 10000;

        int randomNumber = a + (int) (Math.random() * b);

        return randomNumber;
    }
}

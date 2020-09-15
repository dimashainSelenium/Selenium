package org.example;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class main {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("loginpage"));
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");
        WebElement login = driver.findElement(By.name("login"));
        login.click();
        TimeUnit.SECONDS.sleep(5);
        driver.quit();
    }
}

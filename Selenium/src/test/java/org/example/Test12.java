package org.example;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Test12 {
    private CharSequence ProdName;
    private CharSequence prefix;
    private CharSequence validFrom;
    private CharSequence validTo;
    private WebDriverWait wait;

    @Test
    public void test12() throws InterruptedException {
        validFrom = "28.09.2020";
        validTo = "01.10.2020";
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
        TimeUnit.SECONDS.sleep(1);

        driver.findElement(By.linkText("Catalog")).click();
        driver.findElement(By.name("categories[1]")).click();
        driver.findElement(By.cssSelector("[class=button][href*=product]")).click();

        driver.findElement(By.name("status")).click();
        driver.findElement(By.name("name[en]")).clear();
        driver.findElement(By.name("name[en]")).sendKeys(ProdName);
        driver.findElement(By.name("code")).sendKeys(prefix);
        driver.findElement(By.cssSelector("[name*=categories][data-name*=Rubber]")).click();
        driver.findElement(By.cssSelector("[name*=product_groups][value*='3']")).click();
        driver.findElement(By.name("quantity")).sendKeys("1");

        File file = new File("1.png");
        String absolutePath = file.getAbsolutePath();
        WebElement chooseFile = driver.findElement(By.name("new_images[]"));
        chooseFile.sendKeys(absolutePath);
        
        driver.findElement(By.name("date_valid_from")).sendKeys(validFrom);
        driver.findElement(By.name("date_valid_to")).sendKeys(validTo);

        driver.findElement(By.linkText("Information")).click();
        wait = new WebDriverWait(driver,10);
        new Select(driver.findElement(By.name("manufacturer_id"))).selectByVisibleText("ACME Corp.");
        driver.findElement(By.name("keywords")).sendKeys("Duck");
        driver.findElement(By.name("short_description[en]")).sendKeys("Duck");
        driver.findElement(By.name("description[en]")).sendKeys(ProdName+" autoSelenium!");
        driver.findElement(By.name("head_title[en]")).sendKeys(ProdName);
        driver.findElement(By.name("meta_description[en]")).sendKeys("123");
        driver.findElement(By.linkText("Data")).click();

        driver.findElement(By.linkText("Prices")).click();
        wait = new WebDriverWait(driver,10);
        driver.findElement(By.name("purchase_price")).sendKeys("13");
        new Select(driver.findElement(By.name("purchase_price_currency_code"))).selectByVisibleText("Euros");
        driver.findElement(By.name("gross_prices[USD]")).sendKeys("20");

        driver.findElement(By.name("save")).click();
        wait = new WebDriverWait(driver,10);

        TimeUnit.SECONDS.sleep(3);
        driver.quit();

    }
}

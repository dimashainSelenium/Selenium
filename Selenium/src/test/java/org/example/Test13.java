package org.example;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


public class Test13 {

    WebDriverWait wait;
    WebElement box;
    WebElement cart;
    private List<WebElement> selector, items;

    @Test
    public void test13() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("mainpage"));
        selector = null;

        for(int i=0; i<3;i++) {
            box = driver.findElement(By.cssSelector("#box-most-popular"));
            box.findElement(By.cssSelector("a")).click();
            selector = driver.findElements(By.cssSelector("[name*=options]"));

            if (selector.isEmpty()){
                driver.findElement(By.name("add_cart_product")).click();
            }
            else {
                Select size = new Select(driver.findElement(By.cssSelector("[name*=options]")));
                size.selectByVisibleText("Small");
                driver.findElement(By.name("add_cart_product")).click();
                selector.clear();
            }
            cart = driver.findElement(By.cssSelector("span.quantity"));
            wait.until(ExpectedConditions.textToBePresentInElement(cart, "" + (i + 1)));
            driver.get(ConfProperties.getProperty("mainpage"));
        }

        driver.findElement(By.linkText("Checkout »")).click();
        for(int i=0; i<3;i++) {
            items = driver.findElements(By.cssSelector("li.shortcut"));
            if (items.isEmpty()){
                driver.findElement(By.cssSelector("[name=remove_cart_item]")).click();
            }
            else {
                driver.findElement(By.cssSelector("li.shortcut")).click();
                driver.findElement(By.cssSelector("[name=remove_cart_item]")).click();
            }

            WebElement element = wait.until(presenceOfElementLocated(By.id("order_confirmation-wrapper")));
            ExpectedConditions.stalenessOf(element);
        }

        TimeUnit.SECONDS.sleep(3);
        driver.quit();
    }
}

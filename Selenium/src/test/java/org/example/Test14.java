package org.example;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Test14 {
    List<WebElement> countryRows, listLinks;
    WebDriverWait wait;
    String originalWindow, newWindow;
    Set<String> existingWindows;

    @Test
    public void test14() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("loginpage"));
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");
        WebElement login = driver.findElement(By.name("login"));
        login.click();
        wait.until(titleIs("My Store"));

        driver.findElement(By.linkText("Countries")).click();

        countryRows = driver.findElements(By.cssSelector("[name=countries_form] .row"));
        countryRows.get(1).findElement(By.cssSelector("a")).click();
        wait.until(titleContains("Edit Country"));

        listLinks = driver.findElements(By.cssSelector("form .fa-external-link"));

        for (int i=0; i<listLinks.size(); i++) {
            originalWindow=driver.getWindowHandle();
            existingWindows=driver.getWindowHandles();
            listLinks.get(i).click();
            newWindow=wait.until(anyWindowOtherThan(existingWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(originalWindow);
        }

        TimeUnit.SECONDS.sleep(3);
        driver.quit();
    }

    public ExpectedCondition<String> anyWindowOtherThan(final Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
                public String apply(WebDriver driver) {
                Set<String> handles=driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size()>0 ? handles.iterator().next():null;
            }
        };
    }
}

package org.example;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test7 {
    int menu, submenu;
    WebDriverWait wait;

    @Test
    public void test7() throws InterruptedException {
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
        List<WebElement> menuPoints = driver.findElements(By.id("app-"));
        List<WebElement> submenuPoints;
        WebElement menuPoint, submenuPoint;
        menu =menuPoints.size();

        for (int i = 0; i< menu; i++  ) {
            menuPoints = driver.findElements(By.id("app-"));
            menuPoint= menuPoints.get(i);
            wait = new WebDriverWait(driver,10);
            menuPoint.click();

            menuPoints = driver.findElements(By.id("app-"));
            menuPoint= menuPoints.get(i);
            submenuPoints = menuPoint.findElements(By.cssSelector("[id^=doc-]"));
            submenu =submenuPoints.size();

            if(submenu >0) { //подменю есть
                for (int j = 0; j< submenu; j++) {
                    menuPoints = driver.findElements(By.id("app-"));
                    menuPoint= menuPoints.get(i);
                    submenuPoints = menuPoint.findElements(By.cssSelector("[id^=doc-]"));
                    submenuPoint = submenuPoints.get(j);
                    submenuPoint.click();
                    driver.findElement(By.cssSelector("h1"));
                }
            } else {   // подменю нет
                driver.findElement(By.cssSelector("h1"));
            }
        }
        TimeUnit.SECONDS.sleep(5);
        driver.quit();
    }
    }


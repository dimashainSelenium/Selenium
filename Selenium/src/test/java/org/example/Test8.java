package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test8 {
    private List<WebElement> prodList;
    private List<WebElement> stickerList;
    private int prodCount;
    private int stickerCount;

    @Test
    public void test8() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("mainpage"));

        prodList = driver.findElements(By.cssSelector("[class^=product"));
        prodCount = prodList.size();
        stickerList = driver.findElements(By.cssSelector("li.product .sticker"));
        stickerCount = stickerList.size();
        Assert.assertTrue(stickerCount == prodCount);
        TimeUnit.SECONDS.sleep(1);
        driver.quit();
    }
}

package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class Test10 {

    WebElement productUnit;
    String[] regularPrice1, regularPrice2, discountPrice1, discountPrice2;
    String productName1, productName2;
    float size1, size2;
    WebDriverWait wait;

    @Test
    public void test10() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("mainpage"));

        productUnit = driver.findElement(By.cssSelector("[id=box-campaigns] li.product"));

        productName1 = productUnit.findElement(By.cssSelector(".name")).getText();

        regularPrice1 = new String[5];
        discountPrice1 = new String[5];

        regularPrice1[0] = productUnit.findElement(By.cssSelector(".regular-price")).getText();
        regularPrice1[1]= productUnit.findElement(By.cssSelector(".regular-price")).getCssValue("color");
        regularPrice1[2]= productUnit.findElement(By.cssSelector(".regular-price")).getCssValue("font-weight");
        regularPrice1[3]= productUnit.findElement(By.cssSelector(".regular-price")).getCssValue("text-decoration");
        regularPrice1[4]= productUnit.findElement(By.cssSelector(".regular-price")).getCssValue("font-size");

        size1 = Float.parseFloat(regularPrice1[4].replaceAll("px",""));

        discountPrice1[0]= productUnit.findElement(By.cssSelector(".campaign-price")).getText();
        discountPrice1[1]= productUnit.findElement(By.cssSelector(".campaign-price")).getCssValue("color");
        discountPrice1[2]= productUnit.findElement(By.cssSelector(".campaign-price")).getCssValue("font-weight");
        discountPrice1[3]= productUnit.findElement(By.cssSelector(".campaign-price")).getCssValue("text-decoration");
        discountPrice1[4]= productUnit.findElement(By.cssSelector(".campaign-price")).getCssValue("font-size");

        size2=Float.parseFloat(discountPrice1[4].replaceAll("px",""));

        Assert.assertTrue(size1<size2);

        productUnit.click();
        wait = new WebDriverWait(driver,10);

        productUnit = driver.findElement(By.cssSelector("[id=box-product]"));

        productName2 = productUnit.findElement(By.cssSelector("[itemprop=name]")).getText();

        Assert.assertTrue(productName1.compareTo(productName2)==0);

        regularPrice2 = new String[5];
        discountPrice2 = new String[5];

        regularPrice2[0]= productUnit.findElement(By.cssSelector(".regular-price")).getText();
        regularPrice2[1]= productUnit.findElement(By.cssSelector(".regular-price")).getCssValue("color");
        regularPrice2[2]= productUnit.findElement(By.cssSelector(".regular-price")).getCssValue("font-weight");
        regularPrice2[3]= productUnit.findElement(By.cssSelector(".regular-price")).getCssValue("text-decoration");
        regularPrice2[4]= productUnit.findElement(By.cssSelector(".regular-price")).getCssValue("font-size");

        size1 = Float.parseFloat(regularPrice2[4].replaceAll("px",""));

        discountPrice2[0]= productUnit.findElement(By.cssSelector(".campaign-price")).getText();
        discountPrice2[1]= productUnit.findElement(By.cssSelector(".campaign-price")).getCssValue("color");
        discountPrice2[2]= productUnit.findElement(By.cssSelector(".campaign-price")).getCssValue("font-weight");
        discountPrice2[3]= productUnit.findElement(By.cssSelector(".campaign-price")).getCssValue("text-decoration");
        discountPrice2[4]= productUnit.findElement(By.cssSelector(".campaign-price")).getCssValue("font-size");

        size2=Float.parseFloat(discountPrice2[4].replaceAll("px",""));

        Assert.assertTrue(size1<size2);

        for(int i=0; i<5;i++) {
            Assert.assertTrue(regularPrice1[i].compareTo(regularPrice2[i])==0);
            Assert.assertTrue(discountPrice1[i].compareTo(discountPrice2[i])==0);
        }

        Assert.assertTrue(size1<size2);
        TimeUnit.SECONDS.sleep(1);
        driver.quit();
    }
}

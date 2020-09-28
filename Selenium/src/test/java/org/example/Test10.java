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
        regularPrice1[1]= productUnit.findElement(By.cssSelector(".regular-price")).getCssValue("font-weight");
        regularPrice1[2]= productUnit.findElement(By.cssSelector(".regular-price")).getCssValue("text-decoration");
        regularPrice1[3]= productUnit.findElement(By.cssSelector(".regular-price")).getCssValue("font-size");
        regularPrice1[4]= productUnit.findElement(By.cssSelector(".regular-price")).getCssValue("color");
        greyCheck(regularPrice1[4]);

        size1 = Float.parseFloat(regularPrice1[3].replaceAll("px",""));

        discountPrice1[0]= productUnit.findElement(By.cssSelector(".campaign-price")).getText();
        discountPrice1[1]= productUnit.findElement(By.cssSelector(".campaign-price")).getCssValue("font-weight");
        discountPrice1[2]= productUnit.findElement(By.cssSelector(".campaign-price")).getCssValue("text-decoration");
        discountPrice1[3]= productUnit.findElement(By.cssSelector(".campaign-price")).getCssValue("font-size");
        discountPrice1[4]= productUnit.findElement(By.cssSelector(".campaign-price")).getCssValue("color");
        redCheck(discountPrice1[4]);

        size2=Float.parseFloat(discountPrice1[3].replaceAll("px",""));

        Assert.assertTrue(size1<size2);

        productUnit.click();
        wait = new WebDriverWait(driver,10);

        productUnit = driver.findElement(By.cssSelector("[id=box-product]"));

        productName2 = productUnit.findElement(By.cssSelector("[itemprop=name]")).getText();

        Assert.assertTrue(productName1.compareTo(productName2)==0);

        regularPrice2 = new String[5];
        discountPrice2 = new String[5];

        regularPrice2[0]= productUnit.findElement(By.cssSelector(".regular-price")).getText();
        regularPrice2[1]= productUnit.findElement(By.cssSelector(".regular-price")).getCssValue("font-weight");
        regularPrice2[2]= productUnit.findElement(By.cssSelector(".regular-price")).getCssValue("text-decoration");
        regularPrice2[3]= productUnit.findElement(By.cssSelector(".regular-price")).getCssValue("font-size");
        regularPrice2[4]= productUnit.findElement(By.cssSelector(".regular-price")).getCssValue("color");
        greyCheck(regularPrice2[4]);

        size1 = Float.parseFloat(regularPrice2[3].replaceAll("px",""));

        discountPrice2[0]= productUnit.findElement(By.cssSelector(".campaign-price")).getText();
        discountPrice2[1]= productUnit.findElement(By.cssSelector(".campaign-price")).getCssValue("font-weight");
        discountPrice2[2]= productUnit.findElement(By.cssSelector(".campaign-price")).getCssValue("text-decoration");
        discountPrice2[3]= productUnit.findElement(By.cssSelector(".campaign-price")).getCssValue("font-size");
        discountPrice2[4]= productUnit.findElement(By.cssSelector(".campaign-price")).getCssValue("color");
        redCheck(discountPrice2[4]);

        size2=Float.parseFloat(discountPrice2[3].replaceAll("px",""));

        Assert.assertTrue(size1<size2);

        for(int i=0; i<4;i++) {
            Assert.assertTrue(regularPrice1[i].compareTo(regularPrice2[i])==0);
            Assert.assertTrue(discountPrice1[i].compareTo(discountPrice2[i])==0);
        }

        Assert.assertTrue(size1<size2);
        TimeUnit.SECONDS.sleep(1);
        driver.quit();
    }

    public void redCheck(String color)
    {
        String[] rgb;
        char c = 0;
        int j = 0;
        rgb = new String[3];

        color = color.substring(5);
        color = color.substring(0,color.length()-4);
        for(int i=0; i<color.length();i++) {
            if (c==','){
                j = j+1;
                c=0;
            }
            else {
            c = color.charAt(i);
            rgb[j]=rgb[j] + (String.valueOf(c));
            }
        }
        rgb[0] = rgb[0].replaceAll("null","").replace(',',' ').trim();
        rgb[1] = rgb[1].replaceAll("null","").replace(',',' ').trim();
        rgb[2] = rgb[2].replaceAll("null","").replace(',',' ').trim();

        Assert.assertTrue(Integer.parseInt(rgb[0])>Integer.parseInt(rgb[1]));
        Assert.assertTrue(Integer.parseInt(rgb[0])>Integer.parseInt(rgb[2]));
    }

    public void greyCheck(String color)
    {
        String[] rgb;
        char c = 0;
        int j = 0;
        rgb = new String[3];

        color = color.substring(5);
        color = color.substring(0,color.length()-4);
        for(int i=0; i<color.length();i++) {
            if (c==','){
                j = j+1;
                c=0;
            }
            else {
                c = color.charAt(i);
                rgb[j]=rgb[j] + (String.valueOf(c));
            }
        }
        rgb[0] = rgb[0].replaceAll("null","").replace(',',' ').trim();
        rgb[1] = rgb[1].replaceAll("null","").replace(',',' ').trim();
        rgb[2] = rgb[2].replaceAll("null","").replace(',',' ').trim();

        Assert.assertTrue(Integer.parseInt(rgb[0]) == Integer.parseInt(rgb[1]));
        Assert.assertTrue(Integer.parseInt(rgb[1]) == Integer.parseInt(rgb[2]));
    }
}

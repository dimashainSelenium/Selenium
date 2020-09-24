package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test9 {
    int country;
    private String[] countriesName;
    String[] sortedCountriesName;
    private String[] countriesZones;
    String[] sortedCounrtiesZones;
    List<WebElement> countriesListZones;
    List<WebElement> countriesListZonesSub;
    List<WebElement> geoCountriesListZones;
    List<WebElement> geoCountriesListZonesSub;
    String[]  geoCountriesListZonesSubArray;
    String[]  sortedGeoCountriesListZonesSubArray;

    @Test
    public void test9() throws InterruptedException {
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

        driver.findElement(By.linkText("Countries")).click();

        //Проверка стран
        List<WebElement> countriesRow = driver.findElements(By.cssSelector("[name=countries_form] .row"));
        countriesName = new String[countriesRow.size()];
        for (int i=0;i<countriesRow.size();i++) {
            countriesName[i]= countriesRow.get(i).findElements(By.cssSelector("a")).get(0).getText();
    }
        sortedCountriesName=countriesName;
        Arrays.sort(sortedCountriesName);
        for (int i=0;i<countriesName.length;i++){
            Assert.assertTrue(countriesName[i].equals(sortedCountriesName[i]));
        }

        //Проверка зон
        countriesListZones = driver.findElements(By.cssSelector("tr[class=row] td:nth-of-type(6)"));
        for (int i=0;i<countriesListZones.size();i++) {
            if (Integer.parseInt(countriesListZones.get(i).getText())>0){
                countriesRow.get(i).findElements(By.cssSelector("a")).get(0).click();
                TimeUnit.SECONDS.sleep(2);
                countriesListZonesSub = driver.findElements(By.cssSelector("#content input[name*='[name]'"));
                countriesZones= new String[countriesListZonesSub.size()];
                for (int j=0;j<countriesListZonesSub.size();j++) {
                    countriesZones[j]=countriesListZonesSub.get(j).getAttribute("value");
                }
                sortedCounrtiesZones=countriesZones;
                Arrays.sort(sortedCounrtiesZones);
                for (int l=0;l<countriesZones.length-1;l++){
                    Assert.assertTrue(countriesZones[l].equals(sortedCounrtiesZones[l]));
                    }
                driver.findElement(By.linkText("Countries")).click();
                TimeUnit.SECONDS.sleep(2);
                countriesListZones = driver.findElements(By.cssSelector("tr[class=row] td:nth-of-type(6)"));
                countriesRow = driver.findElements(By.cssSelector("[name=countries_form] .row"));
            }
        }

        //Проверка геозон
        driver.findElement(By.linkText("Geo Zones")).click();
        geoCountriesListZones = driver.findElements(By.cssSelector(".row a"));
        for (int i=0;i<geoCountriesListZones.size()-2;i++) {
            geoCountriesListZones.get(i).click();
            TimeUnit.SECONDS.sleep(2);

            geoCountriesListZonesSub = driver.findElements(By.cssSelector("[name*='[zone_code]'] [selected=selected]"));
            geoCountriesListZonesSubArray = new String[geoCountriesListZonesSub.size()];
            for (int j=0;j<geoCountriesListZonesSub.size();j++) {
                geoCountriesListZonesSubArray[j] = geoCountriesListZonesSub.get(j).getAttribute("textContent");
            }
            sortedGeoCountriesListZonesSubArray=geoCountriesListZonesSubArray;
            Arrays.sort(sortedGeoCountriesListZonesSubArray);
            for (int l=0;l<geoCountriesListZonesSubArray.length-1;l++){
                Assert.assertTrue(geoCountriesListZonesSubArray[l].equals(sortedGeoCountriesListZonesSubArray[l]));
            }

            driver.findElement(By.linkText("Geo Zones")).click();
            geoCountriesListZones = driver.findElements(By.cssSelector(".row a"));
        }

        TimeUnit.SECONDS.sleep(1);
        driver.quit();
    }
}

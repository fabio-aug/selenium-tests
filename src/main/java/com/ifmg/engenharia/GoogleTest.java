package com.ifmg.engenharia;

import java.util.List;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GoogleTest {
    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @Test
    public void test() {
        driver.navigate().to("https://www.google.com");

        List<WebElement> button = driver.findElements(By.name("btnI"));
        button.get(1).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.titleContains("Google Doodles"));

        assertEquals("Google Doodles", driver.getTitle());
    }

    @After
    public void closing() throws Exception {
        Thread.sleep(5000);
        driver.quit();
    }
}
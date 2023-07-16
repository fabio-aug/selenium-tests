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

public class HerokuTest {
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
        driver.navigate().to("https://the-internet.herokuapp.com/login");

        WebElement inputUsername = driver.findElement(By.name("username"));
        inputUsername.sendKeys("tomsmith");

        WebElement inputPassword = driver.findElement(By.name("password"));
        inputPassword.sendKeys("SuperSecretPassword!");

        List<WebElement> loginButton = driver.findElements(By.tagName("button"));
        loginButton.get(0).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.urlContains("secure"));

        assertEquals("https://the-internet.herokuapp.com/secure", driver.getCurrentUrl());
    }

    @After
    public void closing() throws Exception {
        Thread.sleep(5000);
        driver.quit();
    }
}
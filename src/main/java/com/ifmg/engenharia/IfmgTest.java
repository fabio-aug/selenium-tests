package com.ifmg.engenharia;

import java.util.List;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.*;


public class IfmgTest {
    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testLinks() {
        driver.navigate().to("https://www.ifmg.edu.br/sabara");

        WebElement myIfmg = driver.findElement(By.id("portalservicos-meu-ifmg"));
        assertEquals("Meu IFMG", myIfmg.getText());

        WebElement cpa = driver.findElement(By.id("portalservicos-cpa-1"));
        assertEquals("CPA", cpa.getText());

        WebElement email = driver.findElement(By.id("portalservicos-webmail"));
        assertEquals("Webmail", email.getText());

        WebElement contact = driver.findElement(By.id("portalservicos-fale-conosco"));
        assertEquals("Contato", contact.getText());

        WebElement exStudents = driver.findElement(By.id("portalservicos-ex-alunos"));
        assertEquals("Ex-Alunos", exStudents.getText());

        WebElement systemAccess = driver.findElement(By.id("portalservicos-acesso-a-sistemas"));
        assertEquals("Acesso a Sistemas", systemAccess.getText());
    }

    @Test
    public void testSearch() {
        driver.navigate().to("https://www.ifmg.edu.br/sabara");

        WebElement inputSearch = driver.findElement(By.id("nolivesearchGadget"));
        inputSearch.sendKeys("Nível Superior");

        List<WebElement> loginButton = driver.findElements(By.className("searchButton"));
        loginButton.get(0).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.titleContains("Busca"));

        List<WebElement> divResult = driver.findElements(By.id("search-results"));

        assertTrue(driver.getTitle().contains("Busca"));
        assertNotEquals("Nenhum resultado foi encontrado.", divResult.get(0).getText());
    }

    @After
    public void closing() throws Exception {
        Thread.sleep(5000);
        driver.quit();
    }
}

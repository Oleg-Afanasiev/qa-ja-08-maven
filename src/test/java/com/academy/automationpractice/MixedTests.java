package com.academy.automationpractice;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MixedTests {
    private WebDriver driver;
    private String baseUrl;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws IOException {
        Properties properties = new Properties();
        // вычитываем файл *.properties из директории <root>/src/main/java/resources
        properties.load(this.getClass().getClassLoader().getResourceAsStream("common.properties"));
        // Инициализируем драйвер Chrome
        System.setProperty("webdriver.chrome.driver", properties.getProperty("chrome.driver"));
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        baseUrl = properties.getProperty("baseUrl");
        // Неявное ожидание (Implicit Waits)
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
//        driver.manage().window().setSize(new Dimension(300, 200));
    }

    @Test
    public void testSearchAndSort() throws InterruptedException {
        // Попасть на главную страницу
        driver.get(baseUrl);
        WebElement searchField = driver.findElement(By.id("search_query_top"));
        searchField.click();
        searchField.clear();
        searchField.sendKeys("Dress");
        driver.findElement(By.name("submit_search")).click();

        driver.findElement(By.cssSelector("#list > a")).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.attributeToBe(
                        By.id("list"), "class", "selected"
                ));
        // Сортировка
        Select select = new Select(driver.findElement(By.id("selectProductSort")));
        select.selectByValue("name:desc");

        // Подождать

        // Проверки
//        Thread.sleep(3000);
        WebElement titleElement = driver.findElement(By.cssSelector("#center_column > ul > li:nth-child(1) > div > div > div > h5 > a"));
        String titleText = titleElement.getText().trim();
        WebElement priceElement = driver.findElement(By.cssSelector("#center_column > ul > li:nth-child(1) > div > div > div.right-block > div > div > span.price.product-price"));
        String priceText = priceElement.getText().trim();

        Assert.assertEquals(titleText, "Printed Summer Dress");
        Assert.assertEquals(priceText, "$28.98");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        // Закрываем браузер (все окна)
//        driver.quit();
    }
}

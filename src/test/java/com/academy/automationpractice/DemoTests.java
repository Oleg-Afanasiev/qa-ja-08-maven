package com.academy.automationpractice;

import com.academy.automationpractice.page.HomePage;
import com.academy.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class DemoTests extends BaseTest {

    @Test
    //
    public void testInNewTab() throws InterruptedException {
        driver.get("http://automationpractice.com/index.php");

        // Открываем новую вкладку (средствами java script)
        ((JavascriptExecutor)driver).executeScript("window.open('http://automationpractice.com/index.php','_blank');");

        // Переключаемся на открытую вкладку
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        // Действия на открытой владке
        WebElement signInButtonTab1 = driver.findElement(By.linkText("Sign in"));
        signInButtonTab1.click();
        Thread.sleep(3_000);

        // Закрываем текущую вкладку
        driver.close();

        // Переключаемся на исходную вкладку
        driver.switchTo().window(tabs.get(0));
        WebElement signInButton = driver.findElement(By.linkText("Sign in"));
        signInButton.click();
        Thread.sleep(3_000);
        driver.close();
    }

    @Test
    public void testProfileDemo() throws InterruptedException {
        new HomePage(driver, baseUrl)
                .goToHome()
//                .clickSignIn()
//                .fillEmail("oleg.kh81@gmail.com")
//                .fillPassword("123qwerty")
//                .clickSubmit()
        ;

        Thread.sleep(5_000);

//        driver.quit();
    }
}

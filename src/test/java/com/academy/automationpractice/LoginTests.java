package com.academy.automationpractice;

import com.academy.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(dataProvider = "incorrectAuthDataProvider")
    public void testIncorrectLogin(String login, String password, String errMsg) {
        driver.get(baseUrl);
        WebElement signInButton = driver.findElement(By.linkText("Sign in"));
        signInButton.click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(login); // "qwerty"
        driver.findElement(By.id("passwd")).click();
        driver.findElement(By.id("passwd")).clear();
        driver.findElement(By.id("passwd")).sendKeys(password); // "1234"
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Forgot your password?'])[1]/following::span[1]")).click();
    }

    @DataProvider(name = "incorrectAuthDataProvider")
    public Object[] incorrectAuthDataProvider() {
        return new Object[][] {
                {"qwerty", "1234", "Invalid email"},
                {"", "", ""}
        };
    }
}

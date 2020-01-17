package com.academy.automationpractice.page;

import com.academy.core.BasePage;
import org.openqa.selenium.WebDriver;

public class HeaderPage extends BasePage {

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    public HeaderPage(WebDriver driver, String baseUrl) {
        super(driver, baseUrl);
    }
}

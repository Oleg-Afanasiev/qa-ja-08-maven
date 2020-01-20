package com.academy.selenide.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

public class CreationFormPage {

    @FindBy(id="fname")
    private SelenideElement firstNameEl;

    @FindBy(id="lname")
    private SelenideElement lastNameEl;

    @FindBy(id="age")
    private SelenideElement ageEl;

    @FindBy(id="FEMALE")
    private SelenideElement femaleEl;

    @FindBy(css=".btn.btn-success")
    private SelenideElement submitBtn;

    @Step("fill first name {0}")
    public CreationFormPage fillFirstName(String firstName) {
        firstNameEl.setValue(firstName);
        return page(CreationFormPage.class);
    }

    @Step("fill last name {0}")
    public CreationFormPage fillLastName(String lastName) {
        lastNameEl.setValue(lastName);
        return page(CreationFormPage.class);
    }

    public CreationFormPage setAsFemale() {
        femaleEl.click();
        return page(CreationFormPage.class);
    }

    public CreationFormPage fillAge(int age) {
        ageEl.clear();
        ageEl.setValue(String.valueOf(age));
        return page(CreationFormPage.class);
    }

    public SubscribersPage submit() {
        submitBtn.click();
        return page(SubscribersPage.class);
    }
}

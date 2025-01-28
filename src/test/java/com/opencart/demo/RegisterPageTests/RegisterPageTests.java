package com.opencart.demo.RegisterPageTests;

import cartapp.BasePackage.CsvDataProviders;
import cartapp.BasePackage.TestUtillities;
import cartapp.Pages.HomePage;
import cartapp.Pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class RegisterPageTests extends TestUtillities {

   @Test(dataProvider = "csvReader", dataProviderClass = CsvDataProviders.class)
    public void negativeRegister(Map<String, String> testData) {

        String no = testData.get("no");
        String firstname = testData.get("firstname");
        String lastname = testData.get("lastname");
        String password = testData.get("password");
        String expectedErrorMessage = testData.get("expectedMessage");
        String description = testData.get("description");

        log.info("Starting negativeRegisterTest #" + no + " for " + description);

        //Open HomePage
        HomePage homePage = new HomePage(driver, log);
        homePage.openPage();

        //Click My Account link button
        homePage.clickMyAccountLink();

        //Open Register Page
        homePage.clickRegisterLink();
        RegisterPage registerPage = new RegisterPage(driver,log);
        Assert.assertEquals(registerPage.getPageUrl(), registerPage.getCurrentUrl(),"Page url is not expected");

        //Input negative register data
        registerPage.negativeRegister(firstname,lastname,password);
        //Click Continue button
        registerPage.clickAgreeConditions();
        registerPage.clickContinueButton();
                if (firstname == null) {
                   Assert.assertTrue(registerPage.getFirstNameErrorMessage().contains(expectedErrorMessage));
               } else if (lastname == null) {
                   Assert.assertTrue(registerPage.getLastNameErrorMessage().contains(expectedErrorMessage));
               } else if (password == null) {
                   Assert.assertTrue(registerPage.getPasswordErrorMessage().contains(expectedErrorMessage));
               }

   }


}

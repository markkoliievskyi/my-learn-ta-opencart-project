package com.opencart.demo.ContactUsPageTests;

import cartapp.BasePackage.CsvDataProviders;
import cartapp.BasePackage.TestUtillities;
import cartapp.Pages.ContactUsPage;
import cartapp.Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Map;

public class ContactUsPageTests extends TestUtillities {

    private final String expectedHomeUrl = "https://demo.opencart.com/en-gb?route=common/home";
    private final String expectedInvalidEmailText = "E-Mail Address does not appear to be valid!";
    private final String expectedEnquiryText = "Your enquiry has been successfully sent to the store owner!";

    @Parameters({"yourName", "email", "enquiry"})
    @Test
    public void successfulSendRequestForContact(String username, String email, String enquiry) {
        //Open Start page
        HomePage homePage = new HomePage(driver, log);
        homePage.openPage();
        //Navigate to Contact Us Page
        ContactUsPage contactUsPage = homePage.clickContactUsLink();
        //Fill in contract form
        contactUsPage.fillContactForm(username, email, enquiry);
        //Click Submit
        contactUsPage.clickSubmitButtonAndWaitForTransition();
        //Verify that URL endpoint was changed to /contact.success
        Assert.assertEquals(contactUsPage.getCurrentUrl(), contactUsPage.getSuccessContactPageUrl());
        //Verify that "Your enquiry has been successfully sent to the store owner!" text appears
        Assert.assertEquals(contactUsPage.getEnquirySentSuccessText(), expectedEnquiryText);
        //Return to Home Page by clicking Continue button
        contactUsPage.clickContinueButton();
        //String expectedHomeUrl = "https://demo.opencart.com/en-gb?route=common/home";
        Assert.assertEquals(homePage.getCurrentUrl(), expectedHomeUrl);
    }

    @Test(priority = 2, dataProvider = "csvReader", dataProviderClass = CsvDataProviders.class)
    public void invalidEmails(Map<String, String> testData) {

        String name = testData.get("name");
        String email = testData.get("email");
        String enquiry = testData.get("enquiry");

        //Open contactUs page
        ContactUsPage contactUsPage = new ContactUsPage(driver, log);
        contactUsPage.openPage();
        //Fill in contract form
        contactUsPage.fillContactForm(name, email, enquiry);
        //Click Submit button
        contactUsPage.clickSubmitButton();
        //Verify that invalid email text error appears
        Assert.assertEquals(contactUsPage.getInvalidEmailErrorText(), expectedInvalidEmailText);

    }

}

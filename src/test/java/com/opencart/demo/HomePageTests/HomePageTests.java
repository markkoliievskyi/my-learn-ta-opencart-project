package com.opencart.demo.HomePageTests;

import cartapp.BasePackage.TestUtillities;
import cartapp.Pages.ContactUsPage;
import cartapp.Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends TestUtillities {

    @Test
    public void openContactUsPage() {

        //open start page
        HomePage homePage = new HomePage(driver, log);
        homePage.openPage();
        //Click on phone (ContactUsPage link) button
        ContactUsPage contactUsPage = homePage.clickContactUsLink();
        //Verify ContactUsPage is opened
        String expectedContactUsPageUrl = contactUsPage.getPageUrl();
        String contactUsPageUrl = contactUsPage.getCurrentUrl();
        Assert.assertTrue(contactUsPageUrl.equals(expectedContactUsPageUrl));

    }

    @Test
    public void openContactUsPageByLinkText() {

        //open start page
        HomePage homePage = new HomePage(driver, log);
        homePage.openPage();
        //Click on phone (ContactUsPage link) button
        ContactUsPage contactUsPage = homePage.clickContactUsTextLink();
        //Verify ContactUsPage is opened
        String expectedContactUsPageUrl = contactUsPage.getPageUrl();
        String contactUsPageUrl = contactUsPage.getCurrentUrl();
        Assert.assertTrue(contactUsPageUrl.equals(expectedContactUsPageUrl),"Actual Page URL: " + contactUsPageUrl + " doesn't match expected URL: " + expectedContactUsPageUrl);

    }
}

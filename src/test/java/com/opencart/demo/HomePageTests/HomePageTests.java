package com.opencart.demo.HomePageTests;

import cartapp.BasePackage.TestUtillities;
import cartapp.Pages.ContactUsPage;
import cartapp.Pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

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

    @Test
    public void macbookImageDisplayedInTopCarousel() {

        //open start page
        HomePage homePage = new HomePage(driver,log);
        homePage.openPage();

        //Check Carousel for Macbook image
        homePage.getMacbookAirImage();
        //Verify Image is correct

        Assert.assertTrue(homePage.getMacbookAirImage().isDisplayed(), "Image is not displayed");
    }

    @Test
    public void iphoneImageDisplayedInTopCarousel() {

        //open start page
        HomePage homePage = new HomePage(driver,log);
        homePage.openPage();

        //Check Carousel for Macbook image
        homePage.getIphone6Image();
        //Verify Image is correct

        Assert.assertTrue(homePage.getIphone6Image().isDisplayed(), "Image is not displayed");
    }

    @Test
    public void macbookItemAddedToCart() {

        // Open start page
        HomePage homePage = new HomePage(driver, log);
        homePage.openPage();

        // Click on Add to Cart button under MacBook item in Featured section
        homePage.clickAddMacbookToCart();

        // Wait for the Shopping Cart Dropdown to become clickable, then click it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getShoppingCartDropdownLocator()));
        homePage.clickShoppingCartDropdownLocator();

        // Wait for the MacBook link to become visible and verify its text
        WebElement macbookLink = wait.until(ExpectedConditions.visibilityOf(homePage.getMacbookLink()));
        Assert.assertEquals(macbookLink.getText().trim(), "MacBook", "Item in cart doesn't match");
    }


}

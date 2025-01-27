package cartapp.Pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class ContactUsPage extends BasePageObject{

    public ContactUsPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private String pageUrl = "https://demo.opencart.com/en-gb?route=information/contact";
    private String successContactPageUrl = "https://demo.opencart.com/en-gb?route=information/contact.success";
    private By homePageButtonLocator = By.xpath("//i[@class='fas fa-home']");
    private By contactUsLinkLocator = By.xpath("//li[@class='breadcrumb-item']");
    private By contactUsHeader = By.xpath("//h1");
    private By ourLocationsHeader = By.xpath("//h3");
    private By storeAddress = By.xpath("//address");
    private By contactForm = By.xpath("//legend");
    private By yourNameInputLocator = By.id("input-name");
    private By emailInputLocator = By.id("input-email");
    private By enquiryInputLocator = By.id("input-enquiry");
    private By submitButtonLocator = By.xpath("//button[@type='submit']");
    private By continueButtonLocator = By.xpath("//a[@class='btn btn-primary']");
    private By enquirySentSuccess = By.xpath("//div[@id='content']/p");
    private By invalidEmailErrorLocator = By.xpath("//div[@id='error-email']");


    public void openPage() {
        log.info("Opening page: " + pageUrl);
        openUrl(pageUrl);
        log.info("Page opened!");
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public String getSuccessContactPageUrl() {
        return successContactPageUrl;

    }

    public String getStoreAddressText() {
        String addressText = find(storeAddress).getText();
        return addressText;
    }

    public String getContactFormHeaderText() {
        String contactFormHeaderText = find(contactForm).getText();
        return contactFormHeaderText;
    }

    public void fillContactForm(String yourName, String email, String enquiry) {
        log.info("Executing login with username: " + "["+yourName+"]" + " and email: " + "["+email+"]" + " and enquiry: " + "["+enquiry+"]");
        type(yourName, yourNameInputLocator);
        type(email, emailInputLocator);
        type(enquiry, enquiryInputLocator);
    }

    public void clickSubmitButtonAndWaitForTransition() {
        click(submitButtonLocator);
        waitForUrlVisibility(successContactPageUrl, Duration.ofSeconds(5));
    }

    public void clickSubmitButton() {
        click(submitButtonLocator);
    }

    public String getEnquirySentSuccessText() {
        String enquirySentText = find(enquirySentSuccess).getText();
        return enquirySentText;
    }

    public String getInvalidEmailErrorText() {
        waitForVisibilityOf(invalidEmailErrorLocator, Duration.ofSeconds(3));
        return find(invalidEmailErrorLocator).getText();
    }

    public void clickContinueButton() {
        click(continueButtonLocator);
    }


}

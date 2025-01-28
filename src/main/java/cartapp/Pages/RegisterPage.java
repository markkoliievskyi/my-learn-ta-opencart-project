package cartapp.Pages;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.UUID;

public class RegisterPage extends BasePageObject {

    public RegisterPage(WebDriver driver, Logger log) {
        super(driver, log);
    }
    private String pageUrl = "https://demo.opencart.com/en-gb?route=account/register";
    private By firstNameLocator = By.xpath("//input[@name='firstname']");
    private By lastNameLocator = By.xpath("//input[@name='lastname']");
    private By emailLocator = By.xpath("//input[@name='email']");
    private By passwordLocator = By.xpath("//input[@name='password']");
    private By subscribeNewsletterLocator = By.id("input-newsletter");
    private By agreeConditionsLocator = By.xpath("//input[@name='agree']");
    private By continueButtonLocator = By.xpath("//button[@type='submit']");
    private By errorMessageFirstNameLocator = By.id("error-firstname");
    private By errorMessageLastNameLocator = By.id("error-lastname");
    private By errorMessageEmailLocator = By.id("error-email");
    private By errorMessagePasswordLocator = By.id("error-password");

    public String getPageUrl() {
        return pageUrl;
    }
    public String getFirstNameErrorMessage(){return find(errorMessageFirstNameLocator).getText();}
    public String getLastNameErrorMessage(){return find(errorMessageLastNameLocator).getText();}
    public String getPasswordErrorMessage(){return find(errorMessagePasswordLocator).getText();}

    public WebElement firstName () {
        return find(firstNameLocator);
    }
    public WebElement lastName () {
        return find(lastNameLocator);
    }
    public WebElement email () {
        return find(emailLocator);
    }
    public WebElement password () {
        return find(passwordLocator);
    }
    public WebElement subscribeNewsletter () {
        return find(subscribeNewsletterLocator);
    }
    public void clickAgreeConditions () {
        click(agreeConditionsLocator);
    }
    public void clickContinueButton() {click(continueButtonLocator);}



    //Open Register page with its URL
    public void openPage() {
        log.info("Opening page: " + pageUrl);
        openUrl(pageUrl);
        log.info("Page opened!");
    }

    //Input valid register details
    public void validRegister(String firstname, String lastname, String password, String email) {
        log.info("Executing register with username: " + "["+firstname+"]" + "and lastname: " + "["+lastname+"]" + "and password: " + "["+password+"]" + "and email: " + "["+email+"]");
        type(firstname, firstNameLocator);
        type(lastname, lastNameLocator);
        type(password, passwordLocator);
        type(email, emailLocator);
        click(continueButtonLocator);
    }

    //Input invalid register details
    public void negativeRegister(String firstname, String lastname, String password) {
        String randomEmail = generateRandomEmail();
        log.info("Executing register with username: " + "["+firstname+"]" + "and lastname: " + "["+lastname+"]" + "and password: " + "["+password+"]" + "and email: " + "["+randomEmail+"]");
        type(firstname, firstNameLocator);
        type(lastname, lastNameLocator);
        type(password, passwordLocator);
        type(randomEmail, emailLocator);
        click(continueButtonLocator);
    }

    // Helper method to generate random email
    private String generateRandomEmail() {
        // Generate a random UUID and take the first 8 characters for uniqueness
        String randomPart = UUID.randomUUID().toString().substring(0, 8);
        return randomPart + "@google.com";
    }
}

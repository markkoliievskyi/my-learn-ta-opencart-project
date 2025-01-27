package cartapp.Pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends BasePageObject{

    private String pageUrl = "https://demo.opencart.com/en-gb?route=common/home";

    /**
     * TopHeader section
     */
    private By currencyDropdownLocator = By.linkText("Currency");
    private By contactsLocator = By.xpath("//i[@class='fa-solid fa-phone']");
    private By contactsTextLocator = By.xpath("//span[@class='d-none d-md-inline']");
    private By myAccountLocator = By.xpath("//i[@class='fa-solid fa-user']");
    private By wishlistLocator = By.id("wishlist-total");
    private By shoppingcartLocator = By.xpath("//i[@class='fa-solid fa-cart-shopping']");
    private By checkoutLocator = By.xpath("//i[@class='fa-solid fa-share']");
    private By addItemSuccessMessage = By.xpath("//div[@class='']");
    private By shoppingCartDropdownLocator = By.xpath("(//button[@type='button'])[2]");
    private By macbookLinkLocator = By.xpath("(//a[@href='https://demo.opencart.com/en-gb/product/macbook'])[1]");


    public void clickShoppingCartDropdownLocator() {
        click(shoppingCartDropdownLocator);

    }

    public WebElement getMacbookLink() {
        return find(macbookLinkLocator);
    }

    public WebElement getShoppingCartDropdownLocator() {
        return find(shoppingCartDropdownLocator);
    }

    /**
     *Top Carousel Section
     */
    private By carouselItem = By.id("carousel-item");
    private By macBookAirImageLocator = By.xpath("//img[@alt='MacBookAir']");
    //private By macBookAirLinkLocator = By.xpath();
    private By iphone6LinkLocator = By.xpath("//a[@href='https://demo.opencart.com/index.php?route=product/product&path=57&product_id=49']");
    private By iphone6ImageLocator = By.xpath("//img[@alt='iPhone 6']");

    public HomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public List<WebElement> getCarouselImage() {
        return findAll(carouselItem);
    }

    public WebElement getMacbookAirImage() {
          waitForVisibilityOf(macBookAirImageLocator);
          return find(macBookAirImageLocator);
    }

    public WebElement getIphone6Image() {
        return find(iphone6ImageLocator);
    }

    public void clickMacbookAirImage() {
        log.info("Clicking on Macbook Air image in top carousel");
        click(macBookAirImageLocator);
    }

    public void clickIphone6Image() {
        log.info("Clicking on Iphone 6 image in top carousel");
        click(iphone6LinkLocator);
    }

    /**
     *Featured Section
     */
    //private By addMacbookToCartLocator = By.xpath("//button[@aria-label='Add to Cart']");
    private By submitMacbookToCartButton = By.xpath("(//button[@type='submit'])[1]");

    public void clickAddMacbookToCart() {
        log.info("Clicking on Add to Cart button under Macbook item");
        find(submitMacbookToCartButton).click();
    }

    //Open Start page with its URL
    public void openPage() {
        log.info("Opening page: " + pageUrl);
        openUrl(pageUrl);
        log.info("Page opened!");
    }

    //Open Contact Us page
    public ContactUsPage clickContactUsLink() {
        log.info("Clicking on Phone button");
        click(contactsLocator);
        return new ContactUsPage(driver, log);
    }
    //Open Contact Us page with Text link
    public ContactUsPage clickContactUsTextLink() {
        log.info("Clicking on Phone number link");
        click(contactsTextLocator);
        return new ContactUsPage(driver, log);
    }


}

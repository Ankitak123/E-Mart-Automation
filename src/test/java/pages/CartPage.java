package pages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		this.driver = driver;
	}

	By addToCartBtn = By.xpath("//input[@class='button-2 product-box-add-to-cart-button']");
	By cartQty = By.xpath("//span[@class='cart-qty']");

	public void addToCart() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
	}

	public String getCartCount() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(cartQty));
		return driver.findElement(cartQty).getText();
	}

	public void openCart() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement cart = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Add to cart']")));

		cart.click();

		WebElement shoppingCart = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//span[@class='cart-label' and text()='Shopping cart']")));

		shoppingCart.click();
	}

	public void acceptTermsAndCheckout() {
		driver.findElement(By.id("termsofservice")).click();
		driver.findElement(By.id("checkout")).click();
	}
}
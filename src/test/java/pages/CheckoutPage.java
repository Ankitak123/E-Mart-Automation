package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class CheckoutPage {

	WebDriver driver;
	WebDriverWait wait;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Billing Address Continue
	public void billingContinue() {
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("(//input[@class='button-1 new-address-next-step-button' and @title='Continue'])[1]")))
				.click();
	}

	// Shipping Address Continue
	public void shippingContinue() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@onclick='Shipping.save()']"))).click();
	}

	// Shipping Method Continue
	public void shippingMethodContinue() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@onclick='ShippingMethod.save()']")))
				.click();
	}

	// Payment Method
	public void selectCreditCard() {
		wait.until(ExpectedConditions.elementToBeClickable(By.id("paymentmethod_2"))).click();
		driver.findElement(By.cssSelector("input.button-1.payment-method-next-step-button")).click();
	}

	// Enter invalid card
	public void enterInvalidCardDetails() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CardholderName")));

		driver.findElement(By.id("CardholderName")).sendKeys("Test User");
		driver.findElement(By.id("CardNumber")).sendKeys("1234567890123456");
		new Select(driver.findElement(By.id("ExpireMonth"))).selectByVisibleText("01");
		new Select(driver.findElement(By.id("ExpireYear"))).selectByVisibleText("2040");
		driver.findElement(By.id("CardCode")).sendKeys("123");
	}

	// Continue Payment
	public void continuePayment() {
		driver.findElement(By.cssSelector("input.button-1.payment-info-next-step-button")).click();
	}

	
	// Error Message
	public String getErrorMessage() {
		return driver.findElement(By.xpath("//div[@class='validation-summary-errors']")).getText();
	}
}
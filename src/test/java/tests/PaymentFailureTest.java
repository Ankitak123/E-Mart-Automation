package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.*;

public class PaymentFailureTest extends BaseTest {

	@Test
	public void verifyPaymentFailure() throws InterruptedException {

		LoginPage login = new LoginPage(driver);
		SearchPage search = new SearchPage(driver);
		CartPage cart = new CartPage(driver);
		CheckoutPage checkout = new CheckoutPage(driver);

		// Step 1: Login 
		login.clickLogin();
		login.login("testtuser1@gmail.com", "password");
		System.out.println("After login URL: " + driver.getCurrentUrl());

		// Step 2: Search Product
		search.searchProduct("Laptop");

		// Step 3: Add to cart
		driver.findElement(By.xpath("//input[@value='Add to cart']")).click();

		// Step 4: Go to Cart
		cart.openCart();

		// Step 5: Checkout
		cart.acceptTermsAndCheckout();
		checkout.billingContinue();
		checkout.shippingContinue();
		checkout.shippingMethodContinue();

		// Step 6: Select payment
		checkout.selectCreditCard();

		// Step 7: Enter invalid card
		checkout.enterInvalidCardDetails();

		// Step 8: Submit
		checkout.continuePayment();
		Thread.sleep(2000);
		// Step 9: Validate error
		String error = checkout.getErrorMessage();

		Assert.assertTrue(error.length() > 0, "Payment failure error not displayed");
	}
}
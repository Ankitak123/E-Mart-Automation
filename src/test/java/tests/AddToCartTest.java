package tests;

import base.BaseTest;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SearchPage;
import pages.CartPage;

public class AddToCartTest extends BaseTest {

	@Test

	public void testAddToCart() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Click Computers
		driver.findElement(By.linkText("Computers")).click();

		// Click Desktops category
		driver.findElement(By.linkText("Desktops")).click();

		// Wait for products
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".product-item .product-title a")));

		// ✅ Get correct product
		WebElement firstProduct = driver.findElement(By.cssSelector(".product-item .product-title a"));

		String productName = firstProduct.getText(); // ✅ correct name

		System.out.println("Captured Product Name: " + productName);

		firstProduct.click();

		// ✅ FIXED: handle dropdowns
		List<WebElement> dropdowns = driver.findElements(By.xpath("//select"));

		for (int i = 0; i < dropdowns.size(); i++) {
			Select select = new Select(driver.findElements(By.xpath("//select")).get(i));
			select.selectByIndex(1);
		}

		// ✅ Add to cart
		WebElement addToCartBtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[value='Add to cart']")));

		addToCartBtn.click();

		WebElement addToCartBtn1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='add-to-cart-button-72']")));

		addToCartBtn1.click();

		// ✅ FIXED: wait for success message
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bar-notification.success")));

		// ✅ wait for cart update
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".cart-qty"), "1"));

		String cartQuantity = driver.findElement(By.cssSelector(".cart-qty")).getText();

		System.out.println("Cart Count: " + cartQuantity);

		// ✅ FIXED assertion
		Assert.assertEquals(cartQuantity.trim(), "(1)");

		driver.findElement(By.linkText("Shopping cart")).click();

		WebElement cartProductName = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='product-name']")));

		String cartProductText = cartProductName.getText();

		System.out.println("Product Page Name: " + productName);
		System.out.println("Cart Product Name: " + cartProductText);

		// ✅ Better assertion
		Assert.assertTrue(cartProductText.toLowerCase().contains(productName.toLowerCase()),
				"Product name mismatch in cart");
	}
}
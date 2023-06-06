package testcases;

import static constant.Constant.driver;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageobjects.BookStorePage;
import utilities.BookAPIs;

public class Scenario3 extends BookStorePage {

	@BeforeTest
	public void initBroswer() {
		driver.get("https://demoqa.com/login");
	}

	@Test
	public void testScenario3() {
		// 1. Given there is book named “Learning JavaScript Design Patterns”
		BookAPIs bookAPIs = new BookAPIs();
		bookAPIs.addBookToProfile();

		// 2. And the user logs into application
		loginToPage();

		// 3. And the user is on Profile page
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.textToBe(headerSel, "Profile"));
		verifyText(headerSel, "Profile", "[ERR] The 'Profile' header is not displayed!");

		// 4. When the user search book “Learning JavaScript Design Patterns”
		driver.findElement(searchBoxSel).sendKeys("Learning JavaScript Design Patterns");

		// 5. And the user clicks on Delete icon
		driver.findElement(deleteBtnSel).click();

		// 6. And the user clicks on OK button
		driver.findElement(okBtnSel).click();

		// 7. And the user clicks on OK button of alert “Book deleted.”
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alert.accept();

		// 8. And the book is not shown
		verifyText(noBookSel, "No rows found", "[ERR] Books have not been deleted from collection!");

	}

	@AfterTest
	public void closeBrowser() {
		if (driver != null) {
			driver.close();
		}
	}
}

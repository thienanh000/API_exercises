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

public class Scenario1 extends BookStorePage {

	@BeforeTest
	public void initBroswer() {
		driver.get("https://demoqa.com/login");
	}

	@Test
	public void testScenario1() {

		// 0. Delete book
		BookAPIs deleteBook = new BookAPIs();
		deleteBook.deleteBookfromProfile();

		// 1. Login to application
		loginToPage();

		// 2. Navigate to Book Store page
		clickToElement(goToBookStoreBtnSel, true);
		verifyText(headerSel, "Book Store", "[ERR] The 'Book Store' header is not displayed!");

		// 3. Select a book "Git Pocket Guide"
		driver.findElement(bookSel).click();

		// 4. Click on Add To Your Collection
		clickToElement(addToCollectionBtnSel, true);

		// 5. Alert “Book added to your collection.” is shown
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());

		Alert alert = driver.switchTo().alert();
		verifyText(alert.getText(), "Book added to your collection.",
				"[ERR] The notification is '" + alert.getText() + "'");
		alert.accept();

		// 6. Book is shown in your profile
		clickToElement(profileMenuSel, true);
		verifyText(bookSel, "Git Pocket Guide", "[ERR] The book is not displayed in profile!");

	}

	@AfterTest
	public void closeBrowser() {
		if (driver != null) {
			driver.close();
		}
	}

}
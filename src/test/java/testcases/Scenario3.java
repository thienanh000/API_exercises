package testcases;

import static constant.Constant.driver;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageobjects.LoginPage;
import pageobjects.ProfilePage;
import utilities.BookAPIs;

public class Scenario3 {

	@BeforeTest
	public void initBroswer() {
		driver.get("https://demoqa.com/login");

		// 1. Given there is book named “Learning JavaScript Design Patterns”
		String isbn = "9781449331818";
		BookAPIs bookAPIs = new BookAPIs();
		bookAPIs.addBookToProfile(isbn);
	}

	@Test
	public void testScenario3() {

		// 2. And the user logs into application
		LoginPage loginPage = new LoginPage();
		loginPage.loginToPage();

		// 3. And the user is on Profile page
		ProfilePage profilePage = new ProfilePage();
		profilePage.verifyProfilePageHeader();

		// 4. When the user search book “Learning JavaScript Design Patterns”
		String bookName = "Learning JavaScript Design Patterns";
		profilePage.searchBook(bookName);

		// 5. And the user clicks on Delete icon
		profilePage.deleteBook();

		// 6. And the user clicks on OK button
		profilePage.clickOkBtn();

		// 7. And the user clicks on OK button of alert “Book deleted.”
		profilePage.acceptAlert();

		// 8. And the book is not shown
		profilePage.verifyBookIsNotShow();

	}

	@AfterTest
	public void closeBrowser() {
		if (driver != null) {
			driver.close();
		}
	}
}

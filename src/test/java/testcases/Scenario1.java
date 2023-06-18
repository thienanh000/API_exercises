package testcases;

import static constant.Constant.driver;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import constant.PageName;
import pageobjects.BookStorePage;
import pageobjects.LoginPage;
import pageobjects.ProfilePage;
import utilities.BookAPIs;

public class Scenario1 {

	@BeforeTest
	public void initBroswer() {
		driver.get("https://demoqa.com/login");

		// Delete book
		String isbn = "9781593277574";
		BookAPIs deleteBook = new BookAPIs();
		deleteBook.deleteBookfromProfile(isbn);
	}

	@Test
	public void testScenario1() {

		// 1. Login to application
		LoginPage loginPage = new LoginPage();
		loginPage.loginToPage();

		// 2. Navigate to Book Store page
		BookStorePage bookStorePage = new BookStorePage();
		bookStorePage.navBookStorePage();
		bookStorePage.verifyBookStoreHeader();

		// 3. Select a book "Git Pocket Guide"
		String bookName = "Understanding ECMAScript 6";
		By bookSel = By.xpath("//a[text()='" + bookName + "']");
		bookStorePage.selectBook(bookSel);

		// 4. Click on Add To Your Collection
		bookStorePage.addToCollection();

		// 5. Alert “Book added to your collection.” is shown
		bookStorePage.waitUntilAlertIsPresent();
		bookStorePage.verifyBookIsAdded();

		// 6. Book is shown in your profile
		ProfilePage profilePage = new ProfilePage();
		profilePage.navPageOnLeftMenu(PageName.PROFILE_PAGE);
		profilePage.verifyText(bookSel, bookName, "[ERR] The book is not displayed in profile!");

	}

	@AfterTest
	public void closeBrowser() {
		if (driver != null) {
			driver.close();
		}
	}

}
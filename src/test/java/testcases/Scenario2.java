package testcases;

import static constant.Constant.driver;

import java.util.List;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageobjects.BookStorePage;

public class Scenario2 {

	@BeforeTest
	public void initBroswer() {
		driver.get("https://demoqa.com/books");
	}

	@Test
	public void testScenario2() {
		// 1. Given there are books named “Learning JavaScript Design Patterns” and “Designing Evolvable Web APIs with ASP.NET”
		// 2. And the user is on Book Store page (https://demoqa.com/books)
		BookStorePage bookStorePage = new BookStorePage();
		bookStorePage.verifyBookStoreHeader();

		// 3. When the user input book name “Design” or "design"
		// 4. Then all books match with input criteria will be displayed.
		String searchText = "design";
		List<String> expectedBookList = bookStorePage.searchBookList(searchText);

		bookStorePage.verifyText(expectedBookList.get(0), "Learning JavaScript Design Patterns",
				"[ERR] The book 1 name is incorrect!");
		bookStorePage.verifyText(expectedBookList.get(1), "Designing Evolvable Web APIs with ASP.NET",
				"[ERR] The book 2 name is incorrect!");

	}

	@AfterTest
	public void closeBrowser() {
		if (driver != null) {
			driver.close();
		}
	}
}

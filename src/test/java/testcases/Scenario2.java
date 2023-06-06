package testcases;

import static constant.Constant.driver;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageobjects.BookStorePage;

public class Scenario2 extends BookStorePage {

	private static List<String> expectedBookList = new ArrayList<String>();

	@BeforeTest
	public void initBroswer() {
		driver.get("https://demoqa.com/books");
	}

	@Test
	public void testScenario2() {
		// 1. Given there are books named “Learning JavaScript Design Patterns” and
		// “Designing Evolvable Web APIs with ASP.NET”
		// 2. And the user is on Book Store page (https://demoqa.com/books)
		verifyText(bookStoreHeaderSel, "Book Store", "[ERR] The 'Book Store' header is not displayed!");

		// 3. When the user input book name “Design” or "design"
		driver.findElement(searchBoxSel).sendKeys("Design");

		// 4. Then all books match with input criteria will be displayed.
		List<WebElement> bookList = driver.findElements(bookListSel);
		for (WebElement webElement : bookList) {
			String bookName = webElement.getText();
			System.out.println(bookName);
			System.out.println(StringUtils.containsIgnoreCase(bookName, "design"));
			if (StringUtils.containsIgnoreCase(bookName, "design")) {
				expectedBookList.add(bookName);
			}
		}

		if (expectedBookList == null) {
			throw new IllegalArgumentException("[ERR] There are no books in the list!");
		} else {
			System.out.println(expectedBookList);
		}

		verifyText(expectedBookList.get(0), "Learning JavaScript Design Patterns",
				"[ERR] The book 1 name is incorrect!");
		verifyText(expectedBookList.get(1), "Designing Evolvable Web APIs with ASP.NET",
				"[ERR] The book 2 name is incorrect!");

	}

	@AfterTest
	public void closeBrowser() {
		if (driver != null) {
			driver.close();
		}
	}
}

package pageobjects;

import static constant.Constant.driver;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BookStorePage extends GeneralPage {

	private static By goToBookStoreBtnSel = By.cssSelector("#gotoStore");
	private static By addToCollectionBtnSel = By.cssSelector(".text-right.fullButton");
	private static By searchBoxSel = By.cssSelector("#searchBox");
	private static By bookListSel = By.cssSelector(".rt-tbody a");
	private static String bookStorePageHeaderName = "Book Store";

	public void navBookStorePage() {
		clickToElement(goToBookStoreBtnSel, true);
	}

	public void verifyBookStoreHeader() {
		verifyText(headerSel, bookStorePageHeaderName, "[ERR] The 'Book Store' header is not displayed!");
	}

	public void addToCollection() {
		clickToElement(addToCollectionBtnSel, true);
	}

	public void verifyBookIsAdded() {
		Alert alert = driver.switchTo().alert();
		verifyText(alert.getText(), "Book added to your collection.",
				"[ERR] The notification is '" + alert.getText() + "'");
		alert.accept();
	}

	public List<String> searchBookList(String searchText) {
		driver.findElement(searchBoxSel).sendKeys(searchText);
		List<String> searchedBookList = new ArrayList<String>();
		List<WebElement> bookList = driver.findElements(bookListSel);
		for (WebElement webElement : bookList) {
			String bookName = webElement.getText();
			System.out.println(bookName);
			System.out.println(StringUtils.containsIgnoreCase(bookName, searchText));
			if (StringUtils.containsIgnoreCase(bookName, searchText)) {
				searchedBookList.add(bookName);
			}
		}

		if (searchedBookList.isEmpty()) {
			throw new IllegalArgumentException("[ERR] There are no books in the list!");
		} else {
			System.out.println(searchedBookList);
		}

		return searchedBookList;

	}

	public void selectBook(By selector) {
		WebElement element = driver.findElement(selector);
		rollToElement(element, driver, true);
		element.click();
	}

}

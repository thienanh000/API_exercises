package pageobjects;

import static constant.Constant.driver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends GeneralPage {

	private static String profilePageHeaderName = "Profile";
	private static By searchBoxSel = By.cssSelector("#searchBox");
	private static By deleteBtnSel = By.cssSelector("#delete-record-undefined");
	private static By okBtnSel = By.cssSelector("#closeSmallModal-ok");
	private static By noBookSel = By.cssSelector(".rt-noData");

	public void verifyProfilePageHeader() {
		new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.textToBe(headerSel, profilePageHeaderName));
		verifyText(headerSel, profilePageHeaderName, "[ERR] The 'Profile' header is not displayed!");
	}

	public void searchBook(String bookName) {
		driver.findElement(searchBoxSel).sendKeys("Learning JavaScript Design Patterns");
	}

	public void deleteBook() {
		driver.findElement(deleteBtnSel).click();
	}

	public void clickOkBtn() {
		driver.findElement(okBtnSel).click();
	}

	public void verifyBookIsNotShow() {
		verifyText(noBookSel, "No rows found", "[ERR] Books have not been deleted from collection!");
	}

}

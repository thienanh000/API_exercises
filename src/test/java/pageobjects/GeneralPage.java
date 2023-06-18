package pageobjects;

import static constant.Constant.driver;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import constant.PageName;

public class GeneralPage {

	protected static By headerSel = By.cssSelector(".main-header");
	protected static By loginMenuSel = By.cssSelector(".element-list.collapse.show .menu-list #item-0");
	protected static By bookStoreMenuSel = By.cssSelector(".element-list.collapse.show .menu-list #item-2");
	protected static By profileMenuSel = By.cssSelector(".element-list.collapse.show .menu-list #item-3");
	protected static By bookStoreAPIMenuSel = By.cssSelector(".element-list.collapse.show .menu-list #item-4");
	private static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	public void rollToElement(WebElement element, WebDriver driver, Boolean position) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(" + position + ")", element);
	}

	public void clickToElement(By selector, Boolean position) {
		WebElement Element = driver.findElement(selector);
		rollToElement(Element, driver, position);
		Element.click();
	}

	public void verifyText(By selector, String expectedText, String errorMsg) {
		String actualText = driver.findElement(selector).getText();
		Assert.assertEquals(actualText, expectedText, errorMsg);
	}

	public void verifyText(String actualText, String expectedText, String errorMsg) {
		Assert.assertEquals(actualText, expectedText, errorMsg);
	}

	public void navPageOnLeftMenu(PageName pageName) {
		switch (pageName) {
		case LOGIN_PAGE:
			clickToElement(loginMenuSel, true);
			break;
		case BOOKSTORE_PAGE:
			clickToElement(bookStoreMenuSel, true);
			break;
		case PROFILE_PAGE:
			clickToElement(profileMenuSel, true);
			break;
		default:
			throw new IllegalArgumentException("[ERR] Page name is wrong!");
		}

	}

	public void waitUntilAlertIsPresent() {
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert() {
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

}

package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static constant.Constant.*;

public class GeneralPage {

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
}

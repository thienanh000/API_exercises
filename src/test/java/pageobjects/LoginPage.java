package pageobjects;

import static constant.Constant.PASSWORD;
import static constant.Constant.USERNAME;
import static constant.Constant.driver;

import org.openqa.selenium.By;

public class LoginPage extends GeneralPage {

	private static By userNameSel = By.cssSelector("#userName");
	private static By passwordSel = By.cssSelector("#password");
	private static By loginBtnSel = By.cssSelector("#login");

	public void loginToPage() {
		driver.findElement(userNameSel).sendKeys(USERNAME);
		driver.findElement(passwordSel).sendKeys(PASSWORD);
		driver.findElement(loginBtnSel).click();
	}
}

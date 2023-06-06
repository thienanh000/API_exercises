package pageobjects;

import static constant.Constant.PASSWORD;
import static constant.Constant.USERNAME;
import static constant.Constant.driver;

import org.openqa.selenium.By;

public class BookStorePage extends GeneralPage {
	private static By userNameSel = By.cssSelector("#userName");
	private static By passwordSel = By.cssSelector("#password");
	private static By loginBtnSel = By.cssSelector("#login");
	protected static By goToBookStoreBtnSel = By.cssSelector("#gotoStore");
	protected static By headerSel = By.cssSelector(".main-header");
	protected static By bookSel = By.xpath("//a[text()='Git Pocket Guide']");
	protected static By addToCollectionBtnSel = By.cssSelector(".text-right.fullButton");
	protected static By profileMenuSel = By.cssSelector(".element-list.collapse.show .menu-list #item-3");
	protected static By bookStoreHeaderSel = By.cssSelector(".main-header");
	protected static By searchBoxSel = By.cssSelector("#searchBox");
	protected static By bookListSel = By.cssSelector(".rt-tbody a");
	protected static By deleteBtnSel = By.cssSelector("#delete-record-undefined");
	protected static By okBtnSel = By.cssSelector("#closeSmallModal-ok");
	protected static By noBookSel = By.cssSelector(".rt-noData");

	public void loginToPage() {
		driver.findElement(userNameSel).sendKeys(USERNAME);
		driver.findElement(passwordSel).sendKeys(PASSWORD);
		driver.findElement(loginBtnSel).click();
	}

}

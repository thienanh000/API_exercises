package constant;

import org.openqa.selenium.WebDriver;

import driver.DriverFactory;

public class Constant extends DriverFactory {
	public static WebDriver driver = getChromeDriver();
	public static String USERNAME = "userName527";
	public static String PASSWORD = "Password@123";

}

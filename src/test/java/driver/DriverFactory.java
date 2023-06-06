package driver;

import java.time.Duration;

import org.apache.commons.exec.OS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

	protected static WebDriver driver;

	public static WebDriver getChromeDriver() {
		String currentProjectLocation = System.getProperty("user.dir");
		String chromeDriverLocation = "";

		if (OS.isFamilyMac()) {
			chromeDriverLocation = currentProjectLocation.concat("/src/test/java/driver/chromedriver");
		} else if (OS.isFamilyWindows()) {
			chromeDriverLocation = currentProjectLocation.concat("\\src\\test\\java\\driver\\chromedriver.exe");
		} else if (chromeDriverLocation.isEmpty()) {
			throw new IllegalArgumentException("[ERR] Cannot detect os type!");
		}

		System.setProperty("webdriver.chrome.driver", chromeDriverLocation);

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--incognito");
		chromeOptions.addArguments("--remote-allow-origins=*");

		WebDriver driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();

		return driver;
	}

}

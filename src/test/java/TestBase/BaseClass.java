package TestBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils; // for random String generation
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public WebDriver driver;
	public Properties p;
	public FileInputStream fileInput;

	@BeforeClass
	@Parameters({ "browser" })
	public void setup(String br) {

		// loading config file
		p = new Properties();
		String filePath = "C:\\Users\\saite\\eclipse-workspace\\Opencart\\src\\test\\resources\\config.properties";

		try {
			fileInput = new FileInputStream(filePath);
			p.load(fileInput);

		} catch (IOException e) {
			e.printStackTrace();
		}

		switch (br.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;

		case "edge":
			driver = new EdgeDriver();
			break;

		default:
			System.out.println("Invalid Browser name..");
			return;
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));
	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}

	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(8);
		return generatedString;
	}

	public String randomNumber() {
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}

	public String randomAlphaNumeric() {
		String generatedNumber = RandomStringUtils.randomNumeric(4);
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedNumber + "@" + generatedString;
	}

}

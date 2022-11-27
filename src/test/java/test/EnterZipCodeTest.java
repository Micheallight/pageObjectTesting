package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.DesignOverviewPage;

import java.time.Duration;

public class EnterZipCodeTest {
	private WebDriver webDriver;
	public static WebDriverWait webDriverWait;

	private static final String ZIP_CODE_FOR_ENTERING = "23456";

	@BeforeMethod
	public void setPrecondition() {
		webDriver = new ChromeDriver();
		webDriver.manage().window().maximize();
		webDriverWait = new WebDriverWait(webDriver, Duration.ofMillis(3000));
	}

	@Test
	public void enterAndCheckZipCode() {
		String enteredZipCode = new DesignOverviewPage(webDriver)
				.openPage()
				.openZipCodeInputWindow()
				.enterZipCode(ZIP_CODE_FOR_ENTERING)
				.confirmZipCode()
				.getEnteredZipCode();
		Assert.assertEquals(enteredZipCode, ZIP_CODE_FOR_ENTERING);
	}

	@AfterMethod(alwaysRun = true)
	public void endOfTest() {
		webDriver.quit();
		webDriver = null;
	}
}

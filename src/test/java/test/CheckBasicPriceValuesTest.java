package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.DesignOverviewPage;

import java.time.Duration;
import java.util.List;

public class CheckBasicPriceValuesTest {
	private WebDriver webDriver;
	public static WebDriverWait webDriverWait;

	private static final String EXPECTED_MODEL_3_PRICE_WITH_SAVINGS = "$40,390*";
	private static final String EXPECTED_MODEL_3_PERFORMANCE_PRICE_WITH_SAVINGS = "$56,390*";

	private static final String EXPECTED_MODEL_3_PRICE_WITHOUT_SAVINGS = "$46,990";
	private static final String EXPECTED_MODEL_3_PERFORMANCE_PRICE_WITHOUT_SAVINGS = "$62,990";

	@BeforeMethod
	public void setPrecondition() {
		webDriver = new ChromeDriver();
		webDriver.manage().window().maximize();
		webDriverWait = new WebDriverWait(webDriver, Duration.ofMillis(3000));
	}

	@Test
	public void checkingPriceWithSavings() {
		List<WebElement> model3PriceWithSavings = new DesignOverviewPage(webDriver)
				.openPage()
				.getPriceValues();
		Assert.assertEquals(model3PriceWithSavings.get(0).getText(), EXPECTED_MODEL_3_PRICE_WITH_SAVINGS);
	}

	@Test
	public void checkingPerformancePriceWithSavings() {
		List<WebElement> model3PriceWithSavings = new DesignOverviewPage(webDriver)
				.openPage()
				.getPriceValues();
		Assert.assertEquals(model3PriceWithSavings.get(1).getText(), EXPECTED_MODEL_3_PERFORMANCE_PRICE_WITH_SAVINGS);
	}

	@Test
	public void checkingPriceWithoutSavings() {
		List<WebElement> model3PriceWithSavings = new DesignOverviewPage(webDriver)
				.openPage()
				.switchToPurchasePrice()
				.getPriceValues();
		Assert.assertEquals(model3PriceWithSavings.get(0).getText(), EXPECTED_MODEL_3_PRICE_WITHOUT_SAVINGS);
	}

	@Test
	public void checkingPerformancePriceWithoutSavings() {
		List<WebElement> model3PriceWithSavings = new DesignOverviewPage(webDriver)
				.openPage()
				.switchToPurchasePrice()
				.getPriceValues();
		Assert.assertEquals(model3PriceWithSavings.get(1).getText(), EXPECTED_MODEL_3_PERFORMANCE_PRICE_WITHOUT_SAVINGS);
	}

	@AfterMethod(alwaysRun = true)
	public void endOfTest() {
		webDriver.quit();
		webDriver = null;
	}
}

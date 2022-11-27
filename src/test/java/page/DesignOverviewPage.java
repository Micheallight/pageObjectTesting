package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DesignOverviewPage extends AbstractPage {
	private static final Integer STAND_TIME_OF_WAITING = 3000;

	private static final String DESIGN_PAGE_URL = "https://www.tesla.com/model3/design#overview";

	private static final String PURCHASE_PRICE_BUTTON_XPATH = "//button[@id='purchase_price']";
	private static final String MODEL_3_PRICE_XPATH = "//div[@class='trim-title-container']/span/p";

	private static final String ENTER_ZIP_CODE_XPATH = "//span[@class='tds-link']";
	private static final String ZIP_CODE_INPUT_XPATH = "//input[@id='DeliveryPostalCode']";
	private static final String CONFIRM_BUTTON_XPATH = "//button[@class='tds-btn tds-btn tds-btn--primary tds-btn--large tds-btn--width-full']";
	private static final String ENTERED_ZIP_CODE_XPATH = "//button[@class=\"modal-trigger tds-o-text_color--20 tds-link\"]";

	private static final String EXPECTED_TEXT_NEAR_ENTERED_ZIP_STRING = "//span[text()[contains(.,'See Early Delivery Options')]]";

	@FindBy(xpath = PURCHASE_PRICE_BUTTON_XPATH)
	private WebElement purchasePriceButton;

	@FindBy(xpath = ENTER_ZIP_CODE_XPATH)
	private WebElement zipCodeButton;

	public DesignOverviewPage(WebDriver webDriver) {
		super(webDriver);
	}

	public List<WebElement> getPriceValues() {
		List<WebElement> listOfPriceValues = new WebDriverWait(webDriver, Duration.ofMillis(STAND_TIME_OF_WAITING))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(MODEL_3_PRICE_XPATH)));

		return listOfPriceValues;
	}

	public DesignOverviewPage switchToPurchasePrice() {
		new WebDriverWait(webDriver, Duration.ofMillis(STAND_TIME_OF_WAITING))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(PURCHASE_PRICE_BUTTON_XPATH)));
		purchasePriceButton.click();

		return this;
	}

	public DesignOverviewPage openZipCodeInputWindow() {
		new WebDriverWait(webDriver, Duration.ofMillis(STAND_TIME_OF_WAITING))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ENTER_ZIP_CODE_XPATH)));
		zipCodeButton.click();
		return this;
	}

	public DesignOverviewPage enterZipCode(String zipCode) {
		new WebDriverWait(webDriver, Duration.ofMillis(STAND_TIME_OF_WAITING))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ZIP_CODE_INPUT_XPATH)));
		WebElement inputZipCode = new WebDriverWait(webDriver, Duration.ofMillis(3000))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ZIP_CODE_INPUT_XPATH)));
		inputZipCode.clear();
		inputZipCode.sendKeys(zipCode);
		return this;
	}

	public DesignOverviewPage confirmZipCode() {
		new WebDriverWait(webDriver, Duration.ofMillis(STAND_TIME_OF_WAITING))
				.until(ExpectedConditions.elementToBeClickable(By.xpath(CONFIRM_BUTTON_XPATH)));
		new WebDriverWait(webDriver, Duration.ofMillis(STAND_TIME_OF_WAITING))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(CONFIRM_BUTTON_XPATH)));
		WebElement confirmButton = new WebDriverWait(webDriver, Duration.ofMillis(3000))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(CONFIRM_BUTTON_XPATH)));
		confirmButton.click();
		return this;
	}

	public String getEnteredZipCode() {
		new WebDriverWait(webDriver, Duration.ofMillis(STAND_TIME_OF_WAITING))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(EXPECTED_TEXT_NEAR_ENTERED_ZIP_STRING)));
		WebElement enteredZipCode = new WebDriverWait(webDriver, Duration.ofMillis(STAND_TIME_OF_WAITING))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ENTERED_ZIP_CODE_XPATH)));
		return enteredZipCode.getText();
	}

	@Override
	public DesignOverviewPage openPage() {
		webDriver.get(DESIGN_PAGE_URL);
		webDriver.get(DESIGN_PAGE_URL);
		return this;
	}
}

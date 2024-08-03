package fitpeo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.interactions.Actions;

public class FitpeoReimbursement {
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://www.fitpeo.com/");

		Thread.sleep(10000);
		By revenueCaluculatorPageButton = By.xpath("//div[text() = 'Revenue Calculator']");
		By slider = By.xpath("// span[contains(@class , 'MuiSlider-track')]");
		By checkBoxCPT99091 = By.xpath("//span[text()='57']");
		By checkBoxCPT99453 = By.xpath("//span[text()='19.19']");
		By checkBoxCPT99454 = By.xpath("//span[text()='63']");
		By checkBoxCPT99474 = By.xpath("//span[text()='15']");
		By headerValueForReimbursement = By
				.xpath("//p[text() ='Total Recurring Reimbursement for all Patients Per Month:']");

		getElement(revenueCaluculatorPageButton).click();
		Thread.sleep(10000);
		Actions act = new Actions(driver);
		act.clickAndHold(getElement(slider)).moveByOffset(217 / 2, 0).build().perform();

		Thread.sleep(10000);

		driver.navigate().refresh();
		Thread.sleep(10000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 250)");
		Thread.sleep(10000);
		WebElement sliderInputBox = driver.findElement(By.xpath("//input[contains(@class ,'MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall')]"));
		sliderInputBox.sendKeys("73");
		String inputBoxValue = sliderInputBox.getAttribute("value");

		if (inputBoxValue.equals("520")) {
			System.out.println("slider input box value : 520");
		} else {
			System.out.println("slider input box value : " + inputBoxValue);
		}

		getElement(checkBoxCPT99091).click();
		getElement(checkBoxCPT99453).click();
		getElement(checkBoxCPT99454).click();
		getElement(checkBoxCPT99474).click();
		String headerValue = getElement(headerValueForReimbursement).getText();

		// System.out.println(headerValue);
		if (headerValue.equals("$110700")) {
			System.out.println("Total Recurring Reimbursement for all Patients Per Month:$110700.");
		} else {
			System.out.println(
					"Total Recurring Reimbursement for all Patients Per Month: shows the value :" + headerValue);
		}

	}

	public static WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

}

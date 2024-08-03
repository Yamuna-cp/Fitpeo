package Assignment_FITPEO;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.annotation.JacksonInject.Value;

public class Fitpeoassignment {
	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new EdgeDriver();

		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		try {
			// Navigate to the FitPeo Homepage:
			driver.get("https://www.fitpeo.com/");
			System.out.println("Navigated to FitPeo Homepage");

			// Navigate to the Revenue Calculator Page:
			driver.findElement(By.linkText("Revenue Calculator")).click();
			System.out.println("Navigate to the Revenue Calculator Page");

			Thread.sleep(2000);

			// Scrolling down
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, 300)");
			System.out.println("Scrolled successfully");

			

			// Write code to update the slider
			WebElement slider = driver.findElement(By.cssSelector("input[type='range']"));
			js.executeScript("arguments[0].scrollIntoView(true);", slider); //
			js.executeScript("arguments[0].setAttribute('value', '820')", slider);

			// Update the Text Field and
			WebElement textField2 = driver.findElement(By.id(":r0:"));
			
			textField2.clear();
			textField2.sendKeys("560");
			wait.until(ExpectedConditions.textToBe(By.xpath("//input[@type='range']"), "560"));
			
			System.out.println("Text field executed");

			// Select CPT Codes
			WebElement cpt99091 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='CPT-99091']")));
			WebElement cpt99453 = driver.findElement(By.xpath("//input[@value='CPT-99453']"));
			WebElement cpt99454 = driver.findElement(By.xpath("//input[@value='CPT-99454']"));
			WebElement cpt99474 = driver.findElement(By.xpath("//input[@value='CPT-99474']"));

			cpt99091.click();
			cpt99453.click();
			cpt99454.click();
			cpt99474.click();

			// Validate Total Recurring Reimbursement
			WebElement totalRecurrence = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//h3[contains(text(),'Total Recurring Reimbursement for all Patients Per Month:')]")));
			String totalAmount = totalRecurrence.getText();
			assert totalAmount.contains("$110700") : "Total Recurring Reimbursement mismatch";

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the browser
			driver.quit();
		}

	}
}

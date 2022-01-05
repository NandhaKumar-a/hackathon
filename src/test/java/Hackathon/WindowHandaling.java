package Hackathon;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.testng.annotations.Test;

@Test(priority = 3)
public class WindowHandaling extends HackathonTest{
	//@Test(priority = 3)
	public void loginUsingGoogle() throws InterruptedException, IOException
	{
		testlog=report.createTest("Test Window Handling");
		
	driver.findElement(HackathonPOM.loginButton).click();
	logPass("Login is clicked - DONE");
	Thread.sleep(5000);
	takeScreenshot();
	driver.findElement(HackathonPOM.continueWithGoogle).click();
	//driver.switchTo().alert();
	Thread.sleep(10000);
	takeScreenshot();
	Set<String> windows = driver.getWindowHandles();
	for (String newwindow : windows) {
		driver.switchTo().window(newwindow);
		Thread.sleep(3000);
	}

	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.findElement(HackathonPOM.emailTextBox).sendKeys(HackathonReadExcelData.Sheet2());
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	logPass("Invalid email is entered");
	takeScreenshot();
	Thread.sleep(5000);
	driver.findElement(HackathonPOM.nextButton).click();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	logPass("Next button is clicked");
	Thread.sleep(5000);
	String errorMessage = driver.findElement(HackathonPOM.errorMessageDisplayed).getText();
	logPass(errorMessage);
	takeScreenshot();
	//driver.close();
	driver.navigate().to(URL);
	//driver.findElement(By.xpath("//*[@id=\"report_submit_close_login\"]")).click();

	}
}

package Hackathon;

import java.util.List;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.annotations.Test;

@Test(priority = 2)
public class UsedCars extends HackathonTest {

	//@Test(priority = 2)
	public void usedCars() throws InterruptedException
	{
		testlog=report.createTest("Test Used Cars");
	try {
		Actions action1 = new Actions(driver);
		WebElement usedCars = driver.findElement(HackathonPOM.usedCarsHover);
		action1.moveToElement(usedCars).perform();
		logPass("Hover on usedCars - DONE");
		
		driver.findElement(HackathonPOM.city).click();
		logPass("Chennai clicked - DONE");
		System.out.println(driver.findElement(HackathonPOM.popularfilter).getText());
		List<WebElement> usedCarsNames = driver.findElements(HackathonPOM.nameOfUsedCars);
		List<WebElement> usedCarsPrice = driver.findElements(HackathonPOM.priceOfUsedCars);
		System.out.println(usedCarsNames.size());
		System.out.println("The most popular cars used in Chennai are listed below:");
		takeScreenshot();
		for(int j = 0; j<3;j++)
		{
		System.out.println("Name of the car : " + usedCarsNames.get(j).getText());
		System.out.println("Price of the car : " + usedCarsPrice.get(j).getText());
		}
		takeScreenshot();
		driver.navigate().to(URL);
		logPass("navigation to homepage - DONE");
	} catch (Exception e) {
		logFail(e.getMessage());
	}

	}
}

package Hackathon;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

@Test(priority = 1)
public class UpcomingBikes extends HackathonTest {

	//@Test(priority = 1)
	public void newBikes() throws InterruptedException, IOException

	{
		testlog=report.createTest("Test New Bikes");
	try {
		Actions action = new Actions(driver);
		WebElement newBikes = driver.findElement(HackathonPOM.newBikesHover);
		action.moveToElement(newBikes).perform();
		Thread.sleep(2000);
		logPass("Hover on newBikes DONE");
		
		driver.findElement(HackathonPOM.upcomingBikes).click();
		Thread.sleep(2000);
		logPass("upcoming bikes has been clicked - DONE");
		
		takeScreenshot();
		Select select = new Select(driver.findElement(HackathonPOM.manufacturerListBox));
		select.selectByVisibleText(HackathonReadExcelData.Sheet1());
		Thread.sleep(2000);
		logPass("Honda has been clicked - DONE");
		takeScreenshot();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,1200)");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Thread.sleep(5000);
		WebElement viewMore = driver.findElement(HackathonPOM.viewMore);
		viewMore.click();
		//System.out.println(viewMore.isEnabled());
		logPass("view more has been clicked - DONE");
		
		List<WebElement> bikeName = driver.findElements(HackathonPOM.nameOfBikes);
		List<WebElement> bikePrice = driver.findElements(HackathonPOM.priceOfBikes);
		List<WebElement> bikeDate = driver.findElements(HackathonPOM.launchDateOfBikes);
		
		System.out.println(bikeName.size());
		
		logPass("The upcoming bikes are listed below : ");
		for(int i = 0 ; i <bikeName.size(); i++)
		{
		String price = bikePrice.get(i).getText().replaceAll("[^0-9]", "");
		int priceint = Integer.parseInt(price);
		
		if(priceint<400)
		{
		System.out.println("Name of the Bike : " + bikeName.get(i).getText());
		System.out.println("Price of the Bike : " + bikePrice.get(i).getText());
		System.out.println(bikeDate.get(i).getText());
		}
		}
		takeScreenshot();
		driver.navigate().to(URL);
		Thread.sleep(2000);
		logPass("navigation to homepage - DONE");
	} catch (NumberFormatException e) {
		
		logFail(e.getMessage());
	} catch (InterruptedException e) {
		
		logFail(e.getMessage());
	} catch (IOException e) {
		
		logFail(e.getMessage());
	}

	}
}

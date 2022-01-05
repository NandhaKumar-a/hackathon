package Hackathon;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Properties;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;



public class HackathonTest extends HackathonReadExcelData
{

HackathonReadExcelData obj = new HackathonReadExcelData();

WebDriver driver;

 String jobname;
 String URL, browser;
 String parent;
 
 public static ExtentHtmlReporter exthtml;
 public static ExtentReports report;
 public static ExtentTest testlog; 
 private Properties prop;
 

//FUNCTION TO ACCESS EXTERNAL PROPERTIES FILE
@BeforeClass
public void readConfigProperties() throws Exception
{
	startReport();
	testlog=report.createTest("Test Add");
FileInputStream fis = new FileInputStream("src\\test\\resources\\config.properties");
Properties properties=new Properties( );
properties.load(fis);
browser= properties.getProperty("browser");
URL=properties.getProperty("baseURL");
invokeBrowser(browser, URL);
}

//Creating Extent report

  public void startReport() 
  { prop = new Properties(); 
  try
  {
  prop.load(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/Config.properties")); 
  } catch (Exception e)
  {
  System.out.println("Error in the Properties File");
  }
  exthtml = new ExtentHtmlReporter( prop.getProperty("reportlocation") + "\\Hackathon_project " + getFileName() + ".html"); 
  report = new ExtentReports();
  report.attachReporter(exthtml);
  report.setSystemInfo("Host Name", "Nandha");
  report.setSystemInfo("Environment", "Test Environment");
  report.setSystemInfo("User Name", "Nandhakumar"); report.setSystemInfo("OS", "Windows 10 ");
  exthtml.config().setDocumentTitle("Hackathon Project");
  exthtml.config().setReportName("Hackathon Project");
   exthtml.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
  }
  
  //To pass the Status as Success to the Report 
  public void logPass(String message) 
  {
  testlog.log(Status.PASS, message);
  System.out.println(message);
  }
  
  
  
  //To pass the Status as Information to the Report 
  public void logInfo(String message)
  {
	  testlog.log(Status.INFO, message);
	  System.out.println(message); 
	  }
  
  
  
  //To pass the Status as Failure to the report
  public void logFail(String message)
  {
  testlog.log(Status.FAIL, message);
  System.err.println(message);
   }
 
//To generate the FileName as current date and time

  public String getFileName() 
  { Date date = new Date();
  return date.toString().replace(":", "_").toString().replace(" ", "_");
  }
 

//FUNCTION TO INVOKE THE WEB BROWSER
public void invokeBrowser(String browser,String URL) throws Exception
{
switch(browser)
{
case "Firefox":
{
System.setProperty("webdriver.gecko.driver","src\\test\\resources\\geckodriver.exe" );
driver=new FirefoxDriver();
break;
}
case "Edge":
{
System.setProperty("webdriver.edge.driver","src\\test\\resources\\msedgedriver.exe" );
driver=new EdgeDriver();
break;
}
case "Chrome":
{
System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
driver=new ChromeDriver();
}

}
driver.manage().window().maximize();
getUrl(URL);
}
//FUNCTION TO OPEN THE URL
public void getUrl(String URL) throws Exception
{
try {
	driver.get(URL);
	logPass("Browser Opened");
	takeScreenshot();
} catch (Exception e) {
	
	logFail(e.getMessage());
}
}



public void takeScreenshot() throws IOException {
	

	try {
	File ss = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	Date date = new Date();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd-hh-mm-ss");
	String d = df.format(date);
	FileUtils.copyFile(ss, new File("target\\" + d + ".png"));
	} catch (WebDriverException e) {
	
	logFail(e.getMessage());
	}
	}

//FUNCTION TO CLOSE THE BROWSER
@AfterClass
public void CloseBrowser()
{
try {
	driver.quit();
	logPass("Browser closed successfully");
	report.flush();
} catch (Exception e) {
	
	logFail("Exception:" + e.getMessage());
}
}

/*
 * public static void main(String[] args) throws Exception { HackathonTest
 * gtest=new HackathonTest(); gtest.readConfigProperties(); gtest.newBikes();
 * gtest.usedCars(); gtest.loginUsingGoogle(); gtest.CloseBrowser();
 * 
 * }
 */
}


























































/*//@Test(priority = 1)
public void newBikes() throws InterruptedException, IOException

{

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

//@Test(priority = 2)
public void usedCars() throws InterruptedException
{
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

//@Test(priority = 3)
public void loginUsingGoogle() throws InterruptedException, IOException
{
	
	
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

}*/

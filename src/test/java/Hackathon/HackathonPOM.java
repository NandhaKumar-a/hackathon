package Hackathon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class HackathonPOM {



WebDriver driver1;




static By newBikesHover = By.xpath("//*[@id=\"headerNewNavWrap\"]/nav/div/ul/li[3]/a");
static By upcomingBikes = By.xpath("//a[text() = 'Upcoming Bikes']");
static By manufacturerListBox = By.xpath("//*[@id=\"makeId\"]");
static By viewMore = By.xpath("//div//span[text() = 'View More Bikes ']");

static By nameOfBikes = By.xpath("//*[@id=\"modelList\"]/li//a");
static By priceOfBikes = By.xpath("//*[@id=\"modelList\"]/li//div[@class = \"b fnt-15\"]");
static By launchDateOfBikes = By.xpath("//div[@id = 'carModels']//ul[@id = 'modelList']/li/div/div/div[2]");

static By usedCarsHover = By.xpath("//*[@id=\"headerNewNavWrap\"]/nav/div/ul/li[6]/a");
static By city = By.xpath("//*[@id=\"headerNewNavWrap\"]/nav/div/ul/li[6]/ul/li/div[2]/ul/li[5]/a");
static By popularfilter=By.xpath("/html/body/div[11]/div/div[1]/div[1]/div[1]/div[2]/ul/li[2]/div[2]/div[4]");
static By nameOfUsedCars = By.xpath("//div[@id = 'mainPage']//a[@data-track-label = 'Car-name']");
static By priceOfUsedCars = By.xpath("//div[@id = 'mainPage']//div[@class = 'pt-10']/span");

static By loginButton = By.xpath("//*[@id=\"des_lIcon\"]");



static By continueWithGoogle = By.xpath("//span[text() = 'Continue with Google']");
static By emailTextBox = By.xpath("//*[@id=\"identifierId\"]");
static By nextButton = By.xpath("//*[@id='identifierNext']/div/button/span");
static By errorMessageDisplayed = By.xpath("//*[@id='view_container']/div/div/div[2]/div/div[1]/div/form/span/section/div/div/div[1]/div/div[2]/div[2]/div");






public HackathonPOM(WebDriver driver1)
{
this.driver1 = driver1;
}
}


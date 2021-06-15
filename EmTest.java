package edge_team;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EmTest {

	WebDriver driver;
	@SuppressWarnings("deprecation")
	@BeforeClass
	public void launchBrowser() {
		// WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", "C:\\Sjar\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		// driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		driver.get("https://cphyrevnmean01:3000/login.html");
		driver.manage().window().maximize();
	}

	@SuppressWarnings("deprecation")
	@Test(dataProvider = "jsondata")
	public void emTest(String data) throws InterruptedException {
		String elements[]=data.split(",");
		driver.findElement(By.id(elements[0])).sendKeys(elements[3]);
		driver.findElement(By.id(elements[1])).sendKeys(elements[4]);
		driver.findElement(By.xpath(elements[2])).click();
		driver.findElement(By.id(elements[5])).click();
		WebDriverWait wait= new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elements[6]))).click();
		driver.findElement(By.xpath(elements[7])).click();
		driver.findElement(By.xpath(elements[8])).click();
		WebElement wdb= driver.findElement(By.id(elements[9]));
		wdb.click();
		wdb.sendKeys(Keys.CONTROL+"A");
		wdb.sendKeys(elements[10]);
		WebElement wb= driver.findElement(By.id(elements[11]));
		wb.click();
		wb.sendKeys(elements[12]);
		driver.findElement(By.xpath(elements[13])).click();
		Thread.sleep(3000);
		WebElement table= driver.findElement(By.xpath(elements[14]));
		table.findElement(By.xpath(elements[15])).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath(elements[16])).click();
		WebElement element= driver.findElement(By.xpath(elements[17]));
		Actions action= new Actions(driver);
	    action.sendKeys(element, Keys.ESCAPE).perform();
	    driver.findElement(By.id(elements[18])).click();
	    driver.findElement(By.id(elements[19])).click();

	}

	@DataProvider(name = "jsondata")
	public  String[] readJson() throws IOException, ParseException {
		JSONParser jsonparser = new JSONParser();
		FileReader reader = new FileReader(".\\jsonFile\\extractManagerXpath&Data.json");
		Object obj = jsonparser.parse(reader);
		JSONObject Jsonobject = (JSONObject) obj;
		JSONArray dataArray= (JSONArray)Jsonobject.get("data");
		String array[]= new String[dataArray.size()];
		for(int i=0; i<dataArray.size(); i++) 
		{
			JSONObject emData= (JSONObject) dataArray.get(i);
			String usernamelocator= (String)emData.get("userNameElement");
			String passwordlocator= (String)emData.get("passwordElement");
			String LoginButton= (String)emData.get("loginElement");
			String usernameData= (String)emData.get("usernameValue");
			String passwordData= (String)emData.get("passwordValue");
			String clientButton= (String)emData.get("clientElement");
			String comDClick= (String)emData.get("ComD");
			String certClick= (String)emData.get("cert");
			String filesClick= (String)emData.get("files");
			String startdateLocator= (String)emData.get("startdateElement");
			String startDateData= (String)emData.get("startDate");
			String endDatelocator= (String)emData.get("enddateElement");
			String endDatedata= (String)emData.get("endDate");
			String runLocator= (String)emData.get("runElement");
			String table= (String)emData.get("tableElement");
			String tableValue= (String)emData.get("tableval");
			String backLocator= (String)emData.get("backElement");
			String popupClose= (String)emData.get("popupElement");
			String profileLocator= (String)emData.get("profileElement");
			String logoutLocator= (String)emData.get("logoutElement");
            array[i]= usernamelocator+","+passwordlocator+","+LoginButton+","+usernameData+","+passwordData+","+clientButton+","+comDClick+","+certClick+","+filesClick+","+startdateLocator+","+startDateData+","+endDatelocator+","+endDatedata+","+runLocator+","+table+","+tableValue+","+backLocator+","+popupClose+","+profileLocator+","+logoutLocator;
		}
		return array;
	}

}

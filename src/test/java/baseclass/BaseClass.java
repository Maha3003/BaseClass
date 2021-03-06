package baseclass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class BaseClass 
{
	
	public static WebDriver driver;
	public static JavascriptExecutor js;
	public static TakesScreenshot ts ;
	
	//public static Actions acc = new Actions(driver);
	//we cant write the above line. it will throw null pointer exception
	//coz when a class is created the static elements are initialized before object creation
	//itself. so driver will be pointing to null here. driver will be initialized only after we launch any browser
	public static Actions acc;
	public static Robot r;
	
	//returns an obj of Actions class
	public static Actions returnActionsObj()
	{
		acc = new Actions(driver);
		return acc;
	}
	
	
	//returns an object of Robot class
	public static Robot returnRobotObj() throws AWTException
	{
		 return r = new Robot();
		
	}
	
	//returns an typecasted object of JavaScriptExecutor
	public static JavascriptExecutor returnJSObj()
	{
		return js = (JavascriptExecutor)driver;
	}
	
	
	
	//maximize
	public static void maximize()
	{
		driver.manage().window().maximize();
	}
	
	//launch chrome browser
	public static void launchChrome()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sathishPC\\Desktop\\selenium\\Programs\\Selenium10AM\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		maximize();
	}
	
	//launch firefox browser
	public static void launchFirefox()
	{
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\sathishPC\\Desktop\\selenium\\Programs\\Selenium10AM\\Driver\\geckodriver.exe");
		driver = new FirefoxDriver();
	}
	
	//launch internet explorer
	public static void launchIE()
	{
		System.setProperty("webdriver.ie.driver", "C:\\Users\\sathishPC\\Desktop\\selenium\\IEDriverServer_x64_3.150.1\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		maximize();
	}
	
	
	//open url
	public static void openURL(String URL)
	{
		driver.get(URL);
	}
	
	//fill text box - username, pwd, etc
	public static void fillText(WebElement e, String text)
	{
		e.sendKeys(text);
	}
	
	
	//mouseover action
	public static void mouseOver(WebElement e)
	{	
		
		returnActionsObj().moveToElement(e).perform();
	}
	
	//drag and drop
	public static void dragAndDrop(WebElement from, WebElement to)
	{
		returnActionsObj().dragAndDrop(from, to).perform();
	}
	
	
	//press Down Key n number of times
	public static void pressDownKey(int n) throws AWTException
	{
		
		for(int i=0; i<n; i++) 
		{
			returnRobotObj().keyPress(KeyEvent.VK_DOWN);
			returnRobotObj().keyRelease(KeyEvent.VK_DOWN);
		}
	}
	
	//press up key n number of times
	public static void pressUpKey(int n) throws AWTException
	{
		
		for(int i=0; i<n; i++) 
		{
			returnRobotObj().keyPress(KeyEvent.VK_UP);
			returnRobotObj().keyRelease(KeyEvent.VK_UP);
		}
	}
	
	//press tab key n number of times 
	
	public static void pressTab(int n) throws AWTException
	{
		
		for(int i=0; i<n; i++) 
		{
			returnRobotObj().keyPress(KeyEvent.VK_TAB);
			returnRobotObj().keyRelease(KeyEvent.VK_TAB);
		}
	}
	
	//press enter key n number of times
	public static void pressEnter(int n) throws AWTException
	{

		for(int i=0; i<n; i++) 
		{
			returnRobotObj().keyPress(KeyEvent.VK_ENTER);
			returnRobotObj().keyRelease(KeyEvent.VK_ENTER);
		}
	}
	
	//right click an element
	public static void rightClick(WebElement e)
	{
		returnActionsObj().contextClick(e).perform();;
	}
	
	
	//double click an element
	public static void doubleClick(WebElement e)
	{
		returnActionsObj().doubleClick(e).perform();
	}
	
	
	//take screenshot
	//default path set is --> C:\Users\sathishPC\Desktop\selenium\Programs\BaseClass\Screenshot\
	public static void screenshot(String fileName) throws IOException
	{
		ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C:\\Users\\sathishPC\\Desktop\\selenium\\Programs\\BaseClass\\Screenshot\\"+fileName));
	}
	
	
	//scrollDown to an element
	public static void scrollDown(WebElement e)
	{
		returnJSObj().executeScript("arguments[0].scrollIntoView();", e);
	}
	
	//scroll Up to an element
	public static void scrollUp(WebElement e)
	{
		returnJSObj().executeScript("arguments[0].scrollIntoView();", e);
	}
	
	
	//accept alert
	public static void acceptAlert()
	{
		driver.switchTo().alert().accept();
	}
	
	//dismiss alert
	public static void dismissAlert()
	{
		driver.switchTo().alert().dismiss();
	}
	
	//enter value in alert textbox
	public static void enterAlertText(String str)
	{
		driver.switchTo().alert().sendKeys(str);
	}
	
	//gets the text from alert box
	public static void getAlertText()
	{
		driver.switchTo().alert().getText();
	}
	
	//selects a dropdown by the visible text
	public static void selectByVisibleText(WebElement e, String str)
	{
		Select s = new Select(e);
		s.selectByVisibleText(str);
	}
	
	//switch to nth tab
	public static void switchTab(int n)
	{
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> handlesList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(handlesList.get(n-1));
	}
	
	
	//close n th tab
	public static void closeTab(int n)
	{
		switchTab(n);
		driver.close();
	}
	
	
	//hightlight a web element
	public static void highLight(WebElement e, String color)
	{
		String higlightColor = "arguments[0].setAttribute('style', 'background-color:" +color +";');";
		returnJSObj().executeScript(higlightColor, e);
	}
	
	
}

package TestBase;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {
	
	public static WebDriver driver;
	protected static Properties repository;
	
	public TestBase() 
	{
		try 
		{
			
		repository = new Properties();
		String srcFile = System.getProperty("user.dir") + "\\src\\main\\java\\Config\\config.properties";

		FileInputStream fis = new FileInputStream(srcFile);
		repository.load(fis);
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}	
	}
	
	public static void initializeBrowser()
	{
		String browser = repository.getProperty("browser");
		if(browser.equals("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			System.setProperty("webdriver.chrome.driver", "C:/EclipseWorkspace/chromedriver_win32/chromedriver.exe");
			driver = new ChromeDriver(options);
			
		}
		else if(browser.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:/Eclipse Workspace/geckodriver-v0.21.0-win32/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(repository.getProperty("URL"));
		
	
	}
	
	
	public static boolean isElementClickable(WebElement element)      
	{
		try
		{
		   WebDriverWait wait = new WebDriverWait(driver, 5);
		   wait.until(ExpectedConditions.elementToBeClickable(element));
		   return true;
		}
		catch (Exception e)
		{
		  return false;
		}	
	}
	
	public static Map<String, String> readFromExcel(String filepath, String Sheet) throws IOException
	{
		
		FileInputStream fis = null;
		XSSFWorkbook workbook;
		XSSFSheet sheet;
		XSSFRow row;
		XSSFCell cell;
		String key = null;
		String value = null;
		HashMap<String, String> baseMap = new HashMap<String, String>();
		
		try 
		{
			fis  = new FileInputStream(filepath);
			
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		workbook = new XSSFWorkbook(fis);
		
		sheet = workbook.getSheet(Sheet);
		
		Iterator<Row> rowIterator = sheet.rowIterator();
		
		while(rowIterator.hasNext())
		{
			
			row = (XSSFRow) rowIterator.next();
			
			Iterator<Cell> cellIterator = row.cellIterator();
			
				while(cellIterator.hasNext())
				{
					cell = (XSSFCell) cellIterator.next();
					key = cell.getStringCellValue();
					//System.out.println("Key: " + key )
					
					
					cell = (XSSFCell) cellIterator.next();
					value = cell.getStringCellValue();
					//System.out.println("Value: " + value)
				}
				
				if(key != null && value != null)
				{
					baseMap.put(key, value);
				}		
		}
		
	
		workbook.close();
		return baseMap;
	}
	
	
	

}

package com.doorcheck;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class BrowserTest {
	
	
	private static final String pathToDriver = "/home/nhome/programs/selenium/chrome/chromedriver";
	
	private WebDriver browser;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("=================================");
		System.out.println("*** Begin Chrome Browser Test ***");
		System.out.println("================================="); 	
		DoorcheckApplication.main(new String[] {});
		Thread.sleep(1000);
        System.setProperty("webdriver.chrome.driver",pathToDriver);
		browser = new org.openqa.selenium.chrome.ChromeDriver();
	}

	/**
	 * testeamos:
	 * entrada
	 * listado dentro
	 * salida
	 * listado fuera
	 * listado historico
	 */
	@Test
	public void test1()
	{
		try {
			long waitDef = 1000;
			browser.manage().window().maximize();
			browser.get("http://localhost:8080/home");
			Thread.sleep(5000);
//			navegar a home ->doorcheck
			browser.findElement(By.id("doorchkbt")).click();
			Thread.sleep(waitDef);
			
//			fill input for Pedro & click en enter 
			browser.findElement(By.id("inputDNI")).sendKeys("4645634K"); 
			browser.findElement(By.id("enterbt")).click();
			
//			esperar  y comprobar
			Thread.sleep(waitDef);
			String txt = browser.findElement(By.id("resp")).getText();
			assertEquals("no checked on enter","Check ENTER OK person:Pedro",txt);
			
//			navegar a listado
			browser.findElement(By.id("homelnk")).click();
			Thread.sleep(waitDef);
			browser.findElement(By.id("listbt")).click();
			Thread.sleep(waitDef);
			
//			comprobar si esta dentro
			 
			WebElement TableElement = browser.findElement(By.id("tablelist"));
			List<WebElement> FirstColumns = TableElement.findElements(By.xpath(".//tr/td[1]"));
			assertEquals(FirstColumns.size(),1);
			WebElement td = FirstColumns.get(0);
			assertEquals("no checked on inside list",td.getText(),"Pedro");
			
//			navegar a home/doorcheck
			browser.findElement(By.id("homelnk")).click();
			Thread.sleep(waitDef);
			browser.findElement(By.id("doorchkbt")).click();
			
//			fill & click en exit
			browser.findElement(By.id("inputDNI")).sendKeys("4645634K"); 
			browser.findElement(By.id("exitbt")).click();
			
//			esperar  y comprobar
			Thread.sleep(waitDef);
			txt = browser.findElement(By.id("resp")).getText();
			assertEquals("no checked on exit","Check EXIT OK person:Pedro",txt);
			
//			navegar a listado nuevamente
			browser.findElement(By.id("homelnk")).click();
			Thread.sleep(waitDef);
			browser.findElement(By.id("listbt")).click();
			Thread.sleep(waitDef);
			
            // select outside & search
			Select select = new Select(browser.findElement(By.id("type")));			
			select.selectByVisibleText("outside");
			browser.findElement(By.id("searchbt")).click();
			Thread.sleep(waitDef);
			
//			comprobar si esta listado fuera
			TableElement = browser.findElement(By.id("tablelist"));
			FirstColumns = TableElement.findElements(By.xpath(".//tr/td[1]"));
			boolean isListed=false;
			for(WebElement e:FirstColumns) {
				if ("Pedro".equals(e.getText())) {
					isListed = true;
					break;
				}
			}
			assertEquals("No listed in outside list",isListed,true);	
			Thread.sleep(waitDef);
			
			// select history & search
			select.selectByVisibleText("history");
			browser.findElement(By.id("searchbt")).click();
			Thread.sleep(waitDef);
			
            //	checkear en history
			TableElement = browser.findElement(By.id("tablelist"));
			FirstColumns = TableElement.findElements(By.xpath(".//tr/td[1]"));
			assertEquals(FirstColumns.size(),1);
			td = FirstColumns.get(0);
			assertEquals("No listed in History",td.getText(),"Pedro");	
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	}
	
	@After
	public void tearDown() throws Exception
	{
	    browser.close();
	}

}

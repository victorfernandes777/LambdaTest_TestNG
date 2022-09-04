package myPackage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
public class myClass {
	
	public RemoteWebDriver driver=null;
	private static String status;
	String username = "victor.fernandes777";
	String accesskey = "Rj5lwsEiM71MlNk7pcA8vd9wOmFNQADY4SLRdS68FTdOsJz0jb";
	String gridURL = "@hub.lambdatest.com/wd/hub";
	   
	   @BeforeTest
	   @org.testng.annotations.Parameters(value={"browser","version","platform"})
	   	public void setUp(String browser, String version, String platform) throws Exception {
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    
	    capabilities.setCapability("browserName", browser);
	    capabilities.setCapability("version", version);
	    capabilities.setCapability("platform", platform); 
		capabilities.setCapability("build", "Assignment");
		capabilities.setCapability("name", "Assignment");
		capabilities.setCapability("network", true); 
		capabilities.setCapability("visual", true); 
		capabilities.setCapability("video", true); 
		capabilities.setCapability("console", true); 
		      
		     
		      try {
		          driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
		      } catch (MalformedURLException e) {
		          System.out.println("Invalid grid URL");
		      } catch (Exception e) {
		          System.out.println(e.getMessage());
		      }
		      System.out.println("****Setup done for - "+browser);
	    }
	   
	  
	   
	   @BeforeMethod
	   @org.testng.annotations.Parameters(value={"URL"})
	   	public void URLLaunch(String URL) throws Exception {
	    
		   driver.get(URL);
	        System.out.println("URL is launched successfully");
	    }
	 
	   /* Test Scenario 1:
1. Perform an explicit wait till the time all the elements in the DOM are available.
2. Use the SoftAssert to validate Page Title. Validate Against “LambdaTest” 
(expecting a failure and proceeding to the following statement).*/
	   
	   @Test(timeOut=20000)
	   public void Scenerio1() throws Exception
	   {
		   System.out.println("****Test Scenerio 1 execution started.****");
		   
	       WebElement lastElementToLoad = driver.findElement(By.className("pintrest-icon"));
	       WebDriverWait wait = new WebDriverWait(driver, 30);
	       wait.until(ExpectedConditions.elementToBeClickable(lastElementToLoad));
	       //lastElementToLoad.click();
	     
	       SoftAssert softassert = new SoftAssert();
	       String expectedtitle = "LambdaTest";
	       String actualtitle = driver.getTitle();
	       ArrayList<String> exceptionCapture = new ArrayList<>();
	       
	       try
	       {
	           
	    	   softassert.assertEquals(actualtitle, expectedtitle);
	    	   System.out.println("This statement will be executed even the previous statement is failed to demonstrate soft assertions");
	    	   softassert.assertAll();
	       }
	       catch(AssertionError e)
	       {
	           //Logic to capture Assertion error message with user customized message in exception tab on lambdatest
	           status = "failed";
	           exceptionCapture.add("Title not matched"+" "+e.getMessage());
	           ((JavascriptExecutor) driver).executeScript("lambda-exceptions", exceptionCapture);
	       }
	       System.out.println("****Test Scenerio 1 execution completed.****");
	   
	   }
  
/*Test Scenario 2:
1. Click “Checkbox Demo” under “Input Forms”.
2. Click the checkbox under the “Single Checkbox Demo” section.
3. Validate whether this checkbox is “selected”.
4. Now click the checkbox again and validate whether the checkbox is “unselected”.
  */
	   @Test(timeOut=20000)
	   public void Scenerio2() throws Exception
	   { 
		   System.out.println("****Test Scenerio 2 execution started.****");
		   WebElement CheckboxDemo = driver.findElement(By.partialLinkText("Checkbox"));
		   CheckboxDemo.click();
		   WebElement CheckboxOption = driver. findElement(By.id("isAgeSelected"));
		   CheckboxOption.click();
		   
		   System.out.println("The checkbox selection state after selection is - " + CheckboxOption.isSelected());
		   CheckboxOption.click();
		   
		   System.out.println("The checkbox selection state after unselection is " + CheckboxOption.isSelected());
	   
		   System.out.println("****Test Scenerio 2 execution completed.****");
	   }
	   
/*
Test Scenario 3:
1. Launch the same browser and URL given in the Pre-Condition.
2. Click “Javascript Alerts” under “Alerts & Modals”.
3. Now click the “Click Me” button in the “Java Script Alert Box”
section.
4. Validate the alert message “I am an alert box!” and click ok.
*/
	   @Test(timeOut=20000)
	   public void Scenerio3() throws Exception
	   { System.out.println("****Test Scenerio 3 execution started.****");
		   WebElement JSAlert = driver. findElement(By.xpath("//*[text()='Javascript Alerts']"));
		   WebDriverWait wait = new WebDriverWait(driver, 20);
	       wait.until(ExpectedConditions.elementToBeClickable(JSAlert));
	       JSAlert.click();
	       
	       WebElement ClickMe = driver. findElement(By.xpath("//*[@id=\"__next\"]/section[3]/div/div/div[2]/div[1]/button"));
	       ClickMe.click();
	       
	       String ExpMsg="I am an alert box!";
	       Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	       driver.switchTo().alert().getText();

	       String alertMessage= driver.switchTo().alert().getText();
	       ArrayList<String> exceptionCapture = new ArrayList<>();
	     
	       try
	       {
	    	   Assert.assertEquals(ExpMsg, alertMessage);
	    	   System.out.println("Alert Message is same");
	    	   status = "passed";
	       }
	       catch(AssertionError e)
	       {
	           //Logic to capture Assertion error message with user customized message in exception tab on lambdatest
	           status = "failed";
	           exceptionCapture.add("Alert Message is not same"+" "+e.getMessage());
	           ((JavascriptExecutor) driver).executeScript("lambda-exceptions", exceptionCapture);
	       }
	       
	       System.out.println("****Test Scenerio 3 execution completed.****");
	   }
	   
	   
	   
	   @AfterMethod
	   public void tearDown() throws Exception
	   {
	 
	      
		   	((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
		   	System.out.println("Status of execution updated sucessfully");
	           
	       
	   }
	   
	   @AfterSuite
	    public void cleanUp()
	    {
		   driver.quit();
	       System.out.println("****All close up activities completed***");
	    }
	   
}
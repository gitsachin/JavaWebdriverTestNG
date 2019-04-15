package com.auto.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.ScreenshotException;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.auto.configproperties.ConfigProperties;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;



public class TestCore extends Page {

	static DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd HH_mm_ss");
	static Date date = new Date();
	public static Properties object = new Properties();
	public static Properties config = new Properties();
	public static AppiumDriver driver1;
	public static RemoteWebDriver driver;
	public static String SCREENSHOT_FOLDER = "target/screenshots/";
	public static final String SCREENSHOT_FORMAT = ".png";
	private String testUrl;
	private String targetBrowser;
	private String os;
	private String crossBrowser;
	private String browserVersion;
	private String os_Version;
	private String os_Platform;
	private String deviceName;
	private String screen_Resolution;
    String testScore = "unset";
	
	public static final String URL = "http://" + ConfigProperties.USERNAME+ ":" + ConfigProperties.AUTOMATE_KEY +"@hub.crossbrowsertesting.com:80/wd/hub";
	
	public JsonNode setScore(String seleniumTestId, String score) throws UnirestException {
		// Mark a Selenium test as Pass/Fail
		HttpResponse<JsonNode> response = Unirest.put("http://crossbrowsertesting.com/api/v3/selenium/{seleniumTestId}")
		.basicAuth(ConfigProperties.USERNAME,ConfigProperties.AUTOMATE_KEY)
		.routeParam("seleniumTestId", seleniumTestId)
		.field("action","set_score")
		.field("score", score)
		.asJson();
		return response.getBody();
		}

		public String takeSnapshot(String seleniumTestId) throws UnirestException {
		/*
		* Takes a snapshot of the screen for the specified test.
		* The output of this function can be used as a parameter for setDescription()
		*/
		HttpResponse<JsonNode> response = Unirest.post("http://crossbrowsertesting.com/api/v3/selenium/{seleniumTestId}/snapshots")
		.basicAuth(ConfigProperties.USERNAME, ConfigProperties.AUTOMATE_KEY)
		.routeParam("seleniumTestId", seleniumTestId)
		.asJson();
		// grab out the snapshot "hash" from the response
		String snapshotHash = (String) response.getBody().getObject().get("hash");
		//System.out.println(snapshotHash);
		return snapshotHash;
		}

		public JsonNode setDescription(String seleniumTestId, String snapshotHash, String description) throws UnirestException{
		/*
		* sets the description for the given seleniemTestId and snapshotHash
		*/
		HttpResponse<JsonNode> response = Unirest.put("http://crossbrowsertesting.com/api/v3/selenium/{seleniumTestId}/snapshots/{snapshotHash}")
		.basicAuth(ConfigProperties.USERNAME, ConfigProperties.AUTOMATE_KEY)
		.routeParam("seleniumTestId", seleniumTestId)
		.routeParam("snapshotHash", snapshotHash)
		.field("description", description)
		.asJson();
		return response.getBody();
		}
	
	@BeforeSuite
	public void fetchSuiteConfiguration(ITestContext testContext) throws IOException, InterruptedException, MalformedURLException {

	
		testUrl = ConfigProperties.site_url;
		System.out.println("----------" + testUrl + "----------");
		targetBrowser = ConfigProperties.browser_name;
		os = ConfigProperties.OS;
		crossBrowser = ConfigProperties.crossBrowser;
		
		
		browserVersion = ConfigProperties.browser_version;
		os_Platform = ConfigProperties.platform;
		screen_Resolution = ConfigProperties.screenResulution;
		
		
		
		if (crossBrowser.toLowerCase().equals("local")) {

			if (os.toLowerCase().equals("windows")) {
				
		       if (targetBrowser.toLowerCase().contains("chrome")) {
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
							+ "//src//main//resources//driver_windows//chromedriver.exe");
					
					Map<String, Object> prefs = new HashMap<String, Object>();
					 prefs.put("download.default_directory",  System.getProperty("user.dir")+"\\src\\main\\resources\\downloads");
					 System.out.println( System.getProperty("user.dir")+"\\src\\main\\resources\\downloads");
					 ChromeOptions options = new ChromeOptions();
					 options.setExperimentalOption("prefs", prefs);
					driver = new ChromeDriver(options);
					driver.manage().window().maximize();
				}

				else if (targetBrowser.toLowerCase().contains("IE") || targetBrowser.toLowerCase().contains("ie")) {
					System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")
							+ "//src//main//resources//driver_windows//IEDriverServer.exe");
					driver = new InternetExplorerDriver();
					driver.manage().window().maximize();
				}

			}

			else if (os.toLowerCase().equals("mac")) {
				  if (targetBrowser.toLowerCase().contains("chrome")) {
					System.setProperty("webdriver.chrome.driver",
							System.getProperty("user.dir") + "//src//main//resources//driver_mac//chromedriver");
					driver = new ChromeDriver();
					driver.manage().window().maximize();
				}

				else if (targetBrowser.toLowerCase().contains("safari")) {
					driver = new SafariDriver();
					driver.manage().window().maximize();
				}

			}

			else if (os.toLowerCase().equals("linux")) {
				  if (targetBrowser.toLowerCase().contains("chrome")) {
					System.setProperty("webdriver.chrome.driver",
							System.getProperty("user.dir") + "//src//main//resources//driver_linux//chromedriver");
					driver = new ChromeDriver();
					driver.manage().window().maximize();
				}
			}
			
			else if(os.toLowerCase().equals("android")){
				 DesiredCapabilities caps=DesiredCapabilities.android();
				 caps.setCapability(MobileCapabilityType.BROWSER_NAME,BrowserType.CHROME);
				 caps.setCapability(MobileCapabilityType.PLATFORM,Platform.ANDROID);
				 caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"android");
				 caps.setCapability(MobileCapabilityType.DEVICE_NAME,"ZY3223FG76");
				 caps.setCapability(MobileCapabilityType.VERSION,"7.0");
				 caps.setCapability("project", "Aszet AutoTest");
				
		     driver1 = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);
			    driver = driver1;	
		}

		}
		
		else if(crossBrowser.toLowerCase().equals("crossbrowser")){

			if(os.toLowerCase().equals("windows")){
					//	driver.setFileDetector(new LocalFileDetector());
						DesiredCapabilities caps = new DesiredCapabilities();
						caps.setCapability("name", testContext.getCurrentXmlTest().getSuite().getName());
						caps.setCapability("build", "Aszet");
					    caps.setCapability("browserName", targetBrowser);
					    caps.setCapability("version", browserVersion);
					    caps.setCapability("project", "Aszet AutoTest");
					    caps.setCapability("platform", os_Platform);
					    caps.setCapability("screenResolution", "1366x768");
					    caps.setCapability("record_video", "true");
					    driver = new RemoteWebDriver(new URL(URL), caps);
					    System.out.println(driver.getSessionId());
					    driver.manage().window().maximize();
				
			}
			
			else if (os.toLowerCase().equals("mac")){
				
				 if(targetBrowser.toLowerCase().contains("safari")){
					
					DesiredCapabilities caps = new DesiredCapabilities();
					caps.setCapability("name", "Aszet Test");
				    caps.setCapability("browser", targetBrowser);
				    caps.setCapability("browser_version", browserVersion);
				    caps.setCapability("os", "OS X");
				    caps.setCapability("os_version", os_Version);
				    caps.setCapability("project", "Aszet AutoTest");
				    caps.setCapability("platform", os_Platform);
				    caps.setCapability("Screen Resolution", screen_Resolution);
				    
				    
				    driver = new RemoteWebDriver(new URL(URL), caps);
				    driver.manage().window().maximize();
				}
				 
				 else if(targetBrowser.toLowerCase().contains("chrome")){
					 
						DesiredCapabilities caps = new DesiredCapabilities();
					    caps.setCapability("browser", targetBrowser);
					    caps.setCapability("browser_version", browserVersion);
					    caps.setCapability("os", "OS X");
					    caps.setCapability("os_version", os_Version);
					    caps.setCapability("project", "Aszet AutoTest");
					    caps.setCapability("Screen Resolution", screen_Resolution);
					   
					    

					    driver = new RemoteWebDriver(new URL(URL), caps);
					    driver.manage().window().maximize();
					} 
			}
			
			else if (os.toLowerCase().equals("android")){
				
			 	DesiredCapabilities caps = new DesiredCapabilities();
			 	caps.setCapability("browserName", ConfigProperties.browser_name);
			 	caps.setCapability("deviceName", ConfigProperties.device_name);
			 	caps.setCapability("platformVersion", ConfigProperties.device_version);
			 	caps.setCapability("platformName", ConfigProperties.OS);
			 	caps.setCapability("deviceOrientation", "portrait");
			    driver1 = new AndroidDriver(new URL(URL), caps);
			    driver=driver1;
		 }
			
		}


	}

	/**
	 * WebDriver initialization
	 * 
	 * @return WebDriver object
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@BeforeMethod(alwaysRun = true)
	public void setup(Method method) throws IOException, InterruptedException {

		// Open test url
		driver.get(testUrl);
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		log("--------------------------------------------------------", ILogLevel.TESTCASE);
		log("Test [" + method.getName() + "] Started", ILogLevel.TESTCASE);
		log("--------------------------------------------------------", ILogLevel.TESTCASE);

	}

	/**
	 * capture screenshot on test(pass/fail)
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws UnirestException 
	 * 
	 * 
	 */


	@AfterMethod(alwaysRun=true)
	public void setScreenshot(ITestResult result) throws InterruptedException, IOException, UnirestException {
			
			try {
				if(result.isSuccess()){
				testScore = "pass";
			    //WebDriver returned = new Augmenter().augment(webDriver);
			   /* if ( driver != null) {
			    	// Take screenshot of first browser window
			        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			        try {
			        FileUtils.copyFile(f, new File(SCREENSHOT_FOLDER+  result.getName()+dateFormat.format(date) + SCREENSHOT_FORMAT)
			        .getAbsoluteFile());
			        } catch (IOException e) { 
			        e.printStackTrace(); 
			     }
			    }

			   } catch (ScreenshotException se) {
			    se.printStackTrace();
			   }catch(Exception e){
			    e.printStackTrace();
			   }
			  }  */
			}
				else{
					String snapshotHash = takeSnapshot(driver.getSessionId().toString());
					setDescription(driver.getSessionId().toString(), snapshotHash, toString());
					testScore = "fail";
				}
			}
			catch(AssertionError ae) {
				
				}

	}  
	
	@AfterSuite
	public void tearDown() throws UnirestException, InterruptedException{
		if(driver!=null){
			// here we make an api call to actually send the score
			if(driver.getSessionId().toString()==null){
			
			// Close the driver
			driver.close();
			
			}else{
				setScore(driver.getSessionId().toString(),testScore);
				driver.quit();
				//Thread.sleep(15000);
				Thread.sleep(2000);
			}
			
		}
	}
	

}

package core.webDriver;

import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WebDriverWrapper
{
    public static boolean Switch = true;
    public static final String USERNAME = "andreiarnautov1";
    public static final String AUTOMATE_KEY = "yiFysr9R61GJTRg1TDzt";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    private WebDriver driver;
    public WebDriverWrapper()
    {
        if (!Switch) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browser", "Chrome");
            caps.setCapability("browser_version", "51.0");
            caps.setCapability("os", "Windows");
            caps.setCapability("os_version", "10");
            caps.setCapability("resolution", "1280x1024");
            caps.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
            caps.setCapability("build", "TestNG - Sample");

            try {
                driver = new RemoteWebDriver(new URL(URL), caps);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        if (Switch) {
            String OsType = System.getProperty("os.name");
            if (OsType.startsWith("Windows"))
            {
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                String assembliesDirectory = FindAssembliesDirectory();
                ChromeOptions chromeOptions = new ChromeOptions();
                String directory = combine(new String[]{assembliesDirectory, "//Selenium", "//chromedriver.exe"});
                System.setProperty("webdriver.chrome.driver",directory);
                String chromePath = combine(new String[]{assembliesDirectory, "//Chrome", "//chrome.exe"});
                chromeOptions.setBinary(chromePath);
                chromeOptions.addArguments("start-maximized");
                chromeOptions.addArguments("--disable-webdriver-enable-native-events");
                chromeOptions.addArguments("--disable-download.prompt-for-download");
                chromeOptions.addArguments("--download.default-directory=", WebDriverDownloadDirectory.DirectoryPath);
                chromeOptions.addArguments("--enable-download.directory_upgrade");
                chromeOptions.addArguments("--profile-content-settings.pattern_pairs=http://*,*");
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");

                capabilities.setBrowserName("chrome");
                driver = new ChromeDriver(capabilities);
            }

            else {
                String directory = new File("").getAbsoluteFile()  + "/chromedriver";
                try {
                    Runtime.getRuntime().exec("chmod +x chromedriver");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                ChromeOptions chromeOptions = new ChromeOptions();
                System.setProperty("webdriver.chrome.driver", directory);
                chromeOptions.setBinary("/usr/bin/google-chrome");
                chromeOptions.addArguments("start-maximized");
                chromeOptions.addArguments("-incognito");
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

                ChromeDriverService chromeDriverService = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(directory))
                        .usingAnyFreePort()
                        .withEnvironment(ImmutableMap.of("DISPLAY",":22"))
                        .build();
                try {
                    chromeDriverService.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                driver = new ChromeDriver(chromeDriverService, capabilities);
            }
        }
     }

    public static String combine(String[] args) {
        String result = "";
        for (int i=0; i<args.length; i++ )
        {
            result = result.concat(args[i]);
        }
        return result;
    }

    public final String getUrl()
    {
        return driver.getCurrentUrl();
    }

    public final void GoToUri(URL uri)
    {
        driver.navigate().to(uri);
    }

    public final void Refresh()
    {
        driver.navigate().refresh();
    }

    public final void WaitElementPresent(By locator)
    {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public final void WaitElementClicked(By locator)
    {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public final SearchContext GetSearchContext()
    {
        return driver;
    }

    public final void DeleteAllCookies()
    {
        try
        {
            driver.manage().deleteAllCookies();
        }
        catch (UnhandledAlertException e)
        {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
            driver.manage().deleteAllCookies();
        }
    }

    public final void SetCookie(String cookieName, String cookieValue)
    {
        driver.manage().addCookie(new Cookie(cookieName, cookieValue, "/"));
    }

    public String FindCookie(String cookieName)
    {
        Cookie cookie = driver.manage().getCookieNamed(cookieName);
        if (cookie == null)
            return null;
        return cookie.getValue();
    }

    public static final class DotNetToJavaStringHelper
    {
        public static String substring(String string, int start, int length)
        {
            if (length < 0)
                throw new IndexOutOfBoundsException("Parameter length cannot be negative.");

            return string.substring(start, start + length);
        }
    }

    public final Object ExecuteScript(String script)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript(script);
    }

    public final Object javascriptExecutor(String script, WebElement webElement)
    {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        return jse.executeScript(script, webElement);
    }

    public final void WaitForAjax()
    {try {
        while (true) // Handle timeout somewhere
        {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            boolean ajaxIsComplete = (boolean) js.executeScript("return jQuery.active == 0");
            if (ajaxIsComplete)
            {
                break;
            }
        }
        while (true) // Handle timeout somewhere
        {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            boolean ajaxIsComplete = (boolean) js.executeScript("return typeof(Ajax) != 'function' || Ajax.activeRequestCount == 0;");
            if (ajaxIsComplete)
            {
                break;
            }
        }
        while (true) // Handle timeout somewhere
        {JavascriptExecutor js = (JavascriptExecutor) driver;
            boolean ajaxIsComplete = (boolean) js.executeScript("return typeof(dojo) != 'function' ||  dojo.io.XMLHTTPTransport.inFlight.length == 0;");
            if (ajaxIsComplete)
            {
                break;
            }
        }
    }catch (WebDriverException e){}
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
    }

    public final Alert Alert(int timeout) {
        return Alert(timeout, 100);
    }

    public final Alert Alert() {
        return Alert(6000, 100);
    }

    public final Alert Alert(int timeout, int waitTimeout) {
        Stopwatch w = Stopwatch.createStarted();
        do
        {
            try
            {
                return driver.switchTo().alert();
            }
            catch (RuntimeException e)
            {
                try {
                    Thread.sleep(waitTimeout);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        } while (w.elapsed(TimeUnit.NANOSECONDS) < timeout);
        return driver.switchTo().alert();
    }

    public final WebDriver switchToFrame(By locator)
    {
                return (new WebDriverWait(driver, 10))
            .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
//        return driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
    }

    public final String getCurrentUrl()
    {
        return driver.getCurrentUrl();
    }

    public final Set<String> getWindowHandles()
    {
        return driver.getWindowHandles();
    }

    public final void switchToWindow(String name)
    {
        driver.switchTo().window(name);
    }

    public final void close()
    {
        driver.close();
    }

    public final String getWindowHandle()
    {
        return driver.getWindowHandle();
    }

    public final WebDriver switchToDefaultContent()
    {
        return driver.switchTo().defaultContent();
    }

    public final void Quit()
    {
        try
        {
            try
            {
                driver.close();
            }
            finally
            {
                driver.quit();
            }
        }
        catch (RuntimeException exception)
        {
            System.out.printf("Ошибка при остановке ChromeDriver:\r\n%1$s" + "\r\n", exception);
        }
    }

    public final void CleanDownloadDirectory()
    {
        WebDriverDownloadDirectory.Clean();
    }

    public final String[] GetDownloadedFileNames()
    {
        return WebDriverDownloadDirectory.GetDownloadedFileNames();
    }

    public final void WaitDownloadFiles(int expectedCountFiles, int maximalWaitTime) throws InterruptedException {
        WaitDownloadFiles(expectedCountFiles, maximalWaitTime, 120);
    }

    public final void WaitDownloadFiles(int expectedCountFiles) throws InterruptedException {
        WaitDownloadFiles(expectedCountFiles, 15000, 120);
    }

    public final void WaitDownloadFiles(int expectedCountFiles, int maximalWaitTime, int sleepInterval) throws InterruptedException {
        WebDriverDownloadDirectory.WaitDownloadFiles(expectedCountFiles, maximalWaitTime, sleepInterval);
    }

    private String FindAssembliesDirectory()
    {
        java.io.File currentDirectory = new File(".").getAbsoluteFile().getParentFile();
        while (true)
        {
            if (currentDirectory == null)
            {
                throw new RuntimeException("The folder Assemblies not found");
            }
            java.io.File[] directories = currentDirectory.listFiles();
            for (java.io.File directoryInfo : directories)
            {
                if (directoryInfo.getName().equals("Assemblies") || directoryInfo.getName().equals("assemblies"))
                {
                    return directoryInfo.getPath();
                }
            }
            currentDirectory = currentDirectory.getParentFile();
        }
    }

    public byte[] CaptureScreenshot()
    {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}



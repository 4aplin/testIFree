package core.systemPages;

import core.systemControls.PageLoadCounter;
import core.webDriver.WebDriverCache;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class WebDriverFunctionalTestBase
{
    @BeforeSuite
    public void SetUp()
    {
        WebDriverCache.RestartIfNeed();
        try{
        WebDriverCache.getWebDriver().DeleteAllCookies();
        WebDriverCache.getWebDriver().CleanDownloadDirectory();
        }
        catch (UnreachableBrowserException e){}
        PageLoadCounter.Reset();
    }

    @AfterSuite
    public void TearDown()
    {
        try
        {
            if (WebDriverCache.getInitialized())
            {
                TearDownInternal();
            }
        }
        finally
        {
            WebDriverCache.getWebDriver().DeleteAllCookies();
            try {
                WebDriverCache.getWebDriver().ExecuteScript("localStorage.clear();");
            }
            catch (WebDriverException e){}
            WebDriverCache.DestroyInstance();
        }
    }

    private void TearDownInternal()
    {
        try
        {
            PageLoadCounter.AssertPageNotLoaded();
        }
        finally
        {
        }
    }

    public final <TPage extends PageBase> TPage LoadPage(Class<TPage> pageClass, String localPath)  {
        URL g = null;
        try {
            g = new URL(new URL(String.format("http://%1$s/", getApplicationBaseUrl())), localPath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return PageBase.GoToUri(pageClass,g);
    }

    public final <TPage extends PageBase> TPage LoadPageUrl(Class<TPage> pageClass, String localPath)  {
        try {
            return PageBase.<TPage>GoToUri(pageClass,new URL(localPath));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getApplicationBaseUrl()
    {
        return "";
    }

}


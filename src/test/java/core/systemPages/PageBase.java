package core.systemPages;

import core.systemControls.PageLoadCounter;
import core.webDriver.WebDriverCache;
import org.junit.Assert;
import org.openqa.selenium.TimeoutException;
import ru.yandex.qatools.allure.annotations.Step;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public abstract class PageBase
{
    public PageBase()
    {
    }

    public String windowHandle = GetWindowHandle();

    public abstract void BrowseWaitVisible();

    public <TPage extends PageBase>  TPage  GoTo(Class<TPage> pageClass)
    {
        return GoTo(pageClass, 1);
    }

    @Step ("Go to on {0}")
    public <TPage extends PageBase>  TPage  GoTo(Class<TPage> pageClass, int pageLoads) {
        VerifyPageIsAlive();
        PageLoadCounter.WaitPageLoaded(pageLoads);
        CleanFields(this);
        TPage newPage = null;
        try {
            newPage = pageClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        newPage.WaitForAjax();
        try {
            newPage.BrowseWaitVisible();}
        catch (TimeoutException e) {
            newPage.BrowseWaitVisible();}
        catch (AssertionError e) {
            newPage.BrowseWaitVisible();
        }
        return newPage;
    }

    public final <TPage extends PageBase> TPage ChangePageType(Class<TPage> pageClass) {
    VerifyPageIsAlive();
    CleanFields(this);
        TPage newPage = null;
        try {
            newPage = pageClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        newPage.BrowseWaitVisible();
    return newPage;
}

    @Step ("Go to on {1}")
   public static <TPage extends PageBase> TPage GoToUri(Class<TPage> pageClass, URL uri)  {
        WebDriverCache.getWebDriver().GoToUri(uri);
        PageLoadCounter.InitPageLoadCounterCookie();
        PageLoadCounter.WaitPageLoaded();
        WebDriverCache.getWebDriver().SetCookie("testingMode", "1");

       TPage newPage = null;
       try {
           newPage = pageClass.newInstance();
       } catch (InstantiationException e) {
           e.printStackTrace();
       } catch (IllegalAccessException e) {
           e.printStackTrace();
       }
       newPage.BrowseWaitVisible();
//      InitPage();
        return newPage;
   }

    public static <TPage extends PageBase> TPage GoToUri(Class<TPage> pageClass, String uri)  {
        try {
            URL url = new URL(uri.toString());
             return GoToUri(pageClass, url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.fail("can not go to page");
        return null;
    }

    public final String GetUrlParameter(String parameterName)
    {
        return WebDriverCache.getWebDriver().getUrl();
    }

    public final String GetUrl()
    {
        return WebDriverCache.getWebDriver().getUrl();
    }

   public static <TPage extends PageBase> TPage RefreshPage(Class<TPage> pageClass) {
    return PageBase.<TPage, TPage>RefreshPage(pageClass, pageClass);
}

    public static <TPage extends PageBase, TPage1 extends PageBase> TPage1 RefreshPage(Class<TPage> pageClass,Class<TPage1> pageClass1) {
        TPage newPage = null;
        try {
            newPage = pageClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        newPage.VerifyPageIsAlive();
        WebDriverCache.getWebDriver().Refresh();
        PageLoadCounter.WaitPageLoaded();
//        CleanFields(this);
        TPage1 newPage1 = null;
        try {
            newPage1 = pageClass1.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        newPage.BrowseWaitVisible();
        InitPage();
        return newPage1;
    }

    private static void InitPage()
    {
        WebDriverCache.getWebDriver().ExecuteScript("$(document.body).addClass('testingMode')");
    }

    private static void CleanFields(PageBase from)
    {
//        FieldsCleanerCache.Clean(from);
    }

    void VerifyPageIsAlive()
    {
        if (alive == null)
        {
            throw new IllegalStateException("This page is already close");
        }
    }

    private final Object alive = new Object();

    public void WaitForAjax()
    {
        WebDriverCache.getWebDriver().WaitForAjax();
        WaitPage(1000);
    }

    public String GetCurrentUrl()
    {
        return WebDriverCache.getWebDriver().getCurrentUrl();
    }

    public void SwitchToDefaultWindows()
    {
        WebDriverCache.getWebDriver().switchToWindow(windowHandle);
    }

    private Set<String> GetWindowHandles()
    {
        return WebDriverCache.getWebDriver().getWindowHandles();
    }

    public Set<String> GetUrlsWindowPage()
    {
        Set<String> namesPage = new HashSet<>();
        Set<String> namesWidows = WebDriverCache.getWebDriver().getWindowHandles();
        for (String windows : namesWidows )
        {
            WebDriverCache.getWebDriver().switchToWindow(windows);
            namesPage.add(GetCurrentUrl());
        }
        WebDriverCache.getWebDriver().switchToWindow(windowHandle);
        return namesPage;
    }

    public void CloseAllPageWithoutDefaultPage()
    {
        Set<String> namesWidows = WebDriverCache.getWebDriver().getWindowHandles();
        for (String windows : namesWidows )
        {
            if(!windows.equals(windowHandle))
            {
                WebDriverCache.getWebDriver().switchToWindow(windows);
                WebDriverCache.getWebDriver().close();
            }
        }
        WebDriverCache.getWebDriver().switchToWindow(windowHandle);
    }

    public String GetWindowHandle()
    {
        return WebDriverCache.getWebDriver().getWindowHandle();
    }

    public final void WaitPage(int value) {
        try {
            Thread.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

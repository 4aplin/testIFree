package core.webDriver;

public final class WebDriverCache
{
    private static int count;

    private static boolean domainUnloadInitialized;

    private static WebDriverWrapper webDriver;

    public static WebDriverWrapper getWebDriver()
    {
        if (webDriver == null)
        {
            SetInstance(new WebDriverWrapper());
        }
        return webDriver;
    }

    public static boolean getInitialized()
    {
        return webDriver != null;
    }

    public static void RestartIfNeed()
    {
        if (++count % 20 == 0)
        {
            if (webDriver != null)
            {
                webDriver.Quit();
                webDriver = null;
            }
        }
    }

    public static void SetInstance(WebDriverWrapper webDriverWrapper)
    {
        if (webDriver != null)
        {
            throw new WebDriverCacheInitializationException("Cannot set instance when another instance is initialized");
        }
        if (!domainUnloadInitialized)
        {
            domainUnloadInitialized = true;
        }
        webDriver = webDriverWrapper;
    }

    public static void DestroyInstance()
    {
        webDriver.Quit();
        webDriver = null;

    }

}

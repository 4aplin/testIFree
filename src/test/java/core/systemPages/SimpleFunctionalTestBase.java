package core.systemPages;

import core.webDriver.WebDriverCache;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

//@Listeners({ElementScreenshot.class})
public class SimpleFunctionalTestBase extends WebDriverFunctionalTestBase
{
    @BeforeSuite
    @Override
    public void SetUp() {
        super.SetUp();
    }

    @AfterSuite
    @Override
    public void TearDown()  {
            super.TearDown();
    }

    protected final void ResetDownloadFilesState()
    {
        WebDriverCache.getWebDriver().CleanDownloadDirectory();
    }
}

package core;

import core.pages.LoginPage;
import core.systemPages.SimpleFunctionalTestBase;
import core.webDriver.WebDriverCache;
import org.testng.annotations.BeforeMethod;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SetUrlTestBase extends SimpleFunctionalTestBase
{
    @Override
    public String getApplicationBaseUrl()
    {
        return "app.aimylogic.com";
    }

    @Override
    @BeforeMethod
    public void SetUp()
    {
        super.SetUp();
        LoadPage(LoginPage.class, "");
        WebDriverCache.getWebDriver().DeleteAllCookies();
    }


    public String newEmail = emailGeneration();
    public String email = "test123@.testcom";
    public String pass = "123456789";
    public String name = "Tester";
    public String emailGeneration()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        long millis = System.currentTimeMillis();
        String newDate = dateFormat.format(new Date());
        return "test" + newDate + millis + "@gmail.com";
    }
}

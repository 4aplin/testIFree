package core.systemControls;


import core.supportClass.StopWatch;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;

public class Autocomplete extends  TextInput
{
    public Autocomplete(By locator)
    {
        this(locator, null);
    }

    public Autocomplete(By locator, HtmlControl container)
    {
        super(locator, container);
    }

    public void SetValueSelect(String value)
    {
        SetValueSelect(value, null);
    }

    @Step("set value {0}, and choose with text {1}")
    public void SetValueSelect(String value, String valueExpected)
    {
        WebElement input = element.findElement(By.xpath("input"));
        input.clear();
        input.sendKeys(value);

        if (valueExpected == null) valueExpected = value;

        StaticText list = new StaticText(By.xpath("//ul[@class='dropdown-menu ng-isolate-scope']"));
        list.WaitPresenceWithRetries();
        list.WaitVisibleWithRetries();

        StaticText element1 = new StaticText(By.xpath("//ul[@class='dropdown-menu ng-isolate-scope']/li"));
        element1.WaitPresenceWithRetries();
        element1.WaitVisibleWithRetries();

        StopWatch w = new StopWatch();
        w.start();
        do
        {
            java.util.List<WebElement> elements = element.findElements(By.xpath("//ul[@class='dropdown-menu ng-isolate-scope']/li"));
            for (WebElement element:
                    elements) {
                if (element.getText().contains(valueExpected))
                {
                    element.click();
                    return;
                }
            }
        } while (w.getElapsedTimeSecs()*1000 < 30000);
        w.stop();
        Assert.fail(String.format("Not found element '%1$s' in select, for used '%2$s'",valueExpected, value ));
    }
}


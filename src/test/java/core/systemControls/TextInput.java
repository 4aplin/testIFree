package core.systemControls;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import ru.yandex.qatools.allure.annotations.Step;

public class TextInput extends HtmlControl
{
    public TextInput(By locator)
    {
        this(locator, null);
    }

    public TextInput(By locator, HtmlControl container)
    {
        super(locator, container);
    }

    @Step("set value {0}")
    public void SetValueAndWait(String value)
    {
        Clear();
        element.sendKeys(value + Keys.TAB);
    }

    @Step("set value {0}")
    public final void SetValue(String value)
    {
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        Clear();
        element.sendKeys(value + Keys.TAB);
    }

    public final void Clear()
    {
        element.clear();
    }

    @Step("copy value {0}")
    public final void Copy()
    {
        element.sendKeys(Keys.CONTROL + "a" + "c" + Keys.CONTROL + Keys.TAB);
    }

    @Step("paste value {0}")
    public final void Paste()
    {
        Clear();
        element.sendKeys(Keys.CONTROL + "v" + Keys.CONTROL + Keys.TAB);
    }
}


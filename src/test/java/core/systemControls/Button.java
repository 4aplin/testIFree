package core.systemControls;

import org.openqa.selenium.By;

public class Button extends HtmlControl
{
    public Button(By locator)
    {
        this(locator, null);
    }

    public Button(By locator, HtmlControl container)
    {
        super(locator, container);
    }

    @Override
    public boolean getIsEnabled()
    {
        return !hasClass("disabled");
    }
}
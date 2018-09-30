package core.systemControls;

import org.openqa.selenium.By;


public class StaticText extends HtmlControl
{
    public StaticText(By locator)
    {
        this(locator, null);
    }

    public StaticText(By locator, HtmlControl container)
    {
        super(locator, container);
    }

    public StaticText(String idLocator)
    {
        this(idLocator, null);
    }

    public StaticText(String idLocator, HtmlControl container)
    {
        super(idLocator, container);
    }
}

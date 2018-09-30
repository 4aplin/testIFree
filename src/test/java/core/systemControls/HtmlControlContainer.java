package core.systemControls;


import org.openqa.selenium.By;

public class HtmlControlContainer extends HtmlControl
{
    public HtmlControlContainer(By locator)
    {
        this(locator, null);
    }

    public HtmlControlContainer(By locator, HtmlControl container)
    {
        super(locator, container);
    }
}

package core.systemControls;


import core.supportClass.BY;
import org.openqa.selenium.By;

public class List extends HtmlControl
{
    public List(By locator)
    {
        this(locator, null);
    }

    public List(By locator, HtmlControl container)
    {
        super(locator, container);
    }

    public final int getCount()
    {
        return this.element.findElements(By.xpath(".//ul/li")).size();
    }

    public final List getItem(int index)
    {
        return new List(BY.ByNthOfBy(By.xpath(".//ul/li"), index), this);
    }

    public final List findByName(String value)
    {
        for (int i = 0; i < getCount(); i++)
        {
            if (value.equals(this.getItem(i).getText()))
            {
                return this.getItem(i);
            }
        }
        throw new RuntimeException(String.format("Don't found text '%1$s'", value));
    }
}

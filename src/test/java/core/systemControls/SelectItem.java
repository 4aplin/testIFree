package core.systemControls;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Quotes;

public class SelectItem extends HtmlControl
{
    public SelectItem(By locator)
    {
        this(locator, null);
    }

    public SelectItem(By locator, HtmlControl container)
    {
        super(locator, container);
        HtmlControlContainer controlContainer = new HtmlControlContainer(locator, container);
    }

    public void selectByText(String text)
    {
        Waiter.WaitExeption(() -> this.element.click(), "Wait can click");
        WebElement options = this.element.findElement(By.xpath(".//div/a/div[normalize-space(.) = " + Quotes.escape(text) + "]"));
        options.click();
    }


}


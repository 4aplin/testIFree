package core.systemControls;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Quotes;

import java.util.List;

public class SelectLook extends HtmlControl
{
    public SelectLook(By locator)
    {
        this(locator, null);
    }

    public SelectLook(By locator, HtmlControl container)
    {
        super(locator, container);
        HtmlControlContainer controlContainer = new HtmlControlContainer(locator, container);
    }

    public void selectByText(String text)
    {
        Waiter.WaitExeption(() -> this.element.click(), "Wait can click");
        WebElement options = this.element.findElement(By.xpath(".//*[@role='option']/div/div[normalize-space(.) = " + Quotes.escape(text) + "]"));
        options.click();
    }


}


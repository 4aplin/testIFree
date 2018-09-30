package core.systemControls;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Quotes;

public class SelectHistory extends HtmlControl
{

    public SelectHistory(By locator)
    {
        this(locator, null);
    }

    public SelectHistory(By locator, HtmlControl container)
    {
        super(locator, container);
        HtmlControlContainer controlContainer = new HtmlControlContainer(locator, container);
    }

    @Override
    public String getText()
    {
        WebElement optionsValue = this.element.findElement(By.xpath(".//*[@ng-model='discover.searchItem']"));
        return optionsValue.getText();
    }

    @Override
    public void WaitText(String expectedText)
    {
        String description = formatWithLocator(String.format("Waited text '%1$s' into element", expectedText));
        Waiter.Wait(() -> getText().equals(expectedText), description);
    }

    public void selectByText(String text)
    {
        WebElement optionsInput = this.element.findElement(By.xpath(".//*[@ng-model='discover.searchItem']"));
        Waiter.WaitExeption(() -> optionsInput.sendKeys(text), "Could not enter text");

        WebElement options = this.element.findElement(By.xpath(".//ul[@class='item-list']"));
        Waiter.WaitExeption(() -> options.isEnabled(), "Could not select");

        WebElement optionsValue = this.element.findElement(By.xpath(".//li/div/a[normalize-space(.) = " + Quotes.escape(text) + "]"));
        optionsValue.click();
    }
}


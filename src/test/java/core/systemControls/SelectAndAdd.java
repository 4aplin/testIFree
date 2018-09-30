package core.systemControls;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Quotes;

public class SelectAndAdd extends HtmlControl
{

    public SelectAndAdd(By locator)
    {
        this(locator, null);
    }

    public SelectAndAdd(By locator, HtmlControl container)
    {
        super(locator, container);
        HtmlControlContainer controlContainer = new HtmlControlContainer(locator, container);
    }

    @Override
    public String getText()
    {
        return this.element.findElement(By.xpath(".//button")).getText();
    }

    @Override
    public void WaitText(String expectedText)
    {
        WebElement optionsValue = this.element.findElement(By.xpath(".//*[@class='select2-chosen']/span"));
        String description = formatWithLocator(String.format("Waited text '%1$s' into element", expectedText));
        Waiter.Wait(() -> optionsValue.getText().equals(expectedText), description);
    }

    public void selectByText(String text)
    {
        Waiter.WaitExeption(() -> this.element.click(), "Waite can click");

        WebElement optionsInput = this.element.findElement(By.xpath(".//*[@class='dropdown-search']/input"));
        Waiter.WaitExeption(() -> optionsInput.sendKeys(text), "Could not enter text");

        WebElement options = this.element.findElement(By.xpath(".//ul[@class='dropdown-content']/li/div/div[normalize-space(.) = " + Quotes.escape(text) + "]"));
        options.click();

        WebElement optionsValue = this.element.findElement(By.xpath("./button[normalize-space(.) = " + Quotes.escape(text) + "]"));
        String value = optionsValue.getText();
        if (!value.equalsIgnoreCase(text))
            throw new NoSuchElementException("Cannot locate element with text: " + text);
    }
}


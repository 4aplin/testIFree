package core.systemControls;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Quotes;

import java.util.List;

public class Select extends HtmlControl
{
    public Select(By locator)
    {
        this(locator, null);
    }

    public Select(By locator, HtmlControl container)
    {
        super(locator, container);
        HtmlControlContainer controlContainer = new HtmlControlContainer(locator, container);
    }

    @Override
    public String getText()
    {
        return this.element.findElement(By.xpath(".//a/span[2]/span")).getText();
    }

    @Override
    public void WaitText(String expectedText)
    {
        String description = formatWithLocator(String.format("Waited text '%1$s' into element", expectedText));
        Waiter.Wait(() -> this.element.findElement(By.xpath(".//*[@class='select2-chosen']/span]")).getText().equals(expectedText), description);
    }

    public void selectByText(String text)
    {
        Waiter.WaitExeption(() -> this.element.click(), "Wait can click");
        WebElement options = this.element.findElement(By.xpath(".//*[@role='option']/div/div[normalize-space(.) = " + Quotes.escape(text) + "]"));
        options.click();

        WebElement optionsValue = this.element.findElement(By.xpath(".//*[@class='select2-chosen']/span[normalize-space(.) = " + Quotes.escape(text) + "]"));
        String value = optionsValue.getText();
        if (!value.equalsIgnoreCase(text))
            throw new NoSuchElementException("Cannot locate element with text: " + text);
    }

    public void selectByTextContains(String text)
    {
        Waiter.WaitExeption(() -> element.click(), "Waite can click");

        List options = this.element.findElements(By.xpath(".//option"));
        for (int i= 0; i < options.size(); i++ )
        {
            WebElement option = (WebElement) options.get(i);
            if (option.getText().contains(text))
            {
                option.click();
                String value = (String) javascriptExecuteWebElement("return arguments[0].value;");
                WebElement optionsValue = this.element.findElement(By.xpath(".//option[@value = " + Quotes.escape(value) + "]"));
                if (!optionsValue.getText().contains(text))
                    throw new NoSuchElementException("Cannot locate element with text: " + text);
                break;
            }
            throw new NoSuchElementException("Cannot locate element with text: " + text);
        }

    }
    private void setSelected(WebElement option) {
        if(!option.isSelected()) {
            option.click();
        }}
}


package core.systemControls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;

public class RadioButton extends HtmlControl
{
    public RadioButton(By locator)
    {
        this(locator, null);
    }

    public RadioButton(By locator, HtmlControl container)
    {
        super(locator, container);
    }

    private boolean getChecked()
    {
        WebElement check = element.findElement(By.xpath("div"));
        String value = check.getAttribute("class");
        return value.contains("selected");
    }

    @Step("radiobutton selected")
    public void waitChecked()
    {
        String description = formatWithLocator(String.format("Wait Checked in element"));
        Waiter.Wait(() -> getChecked() == true, description);
    }

    @Step("radiobutton unselected")
    public void waitUnchecked()
    {
        String description = formatWithLocator(String.format("Wait Unchecked in element"));
        Waiter.Wait(() -> getChecked() == false, description);
    }

    @Override
    public String getText()
    {
        WebElement check = element.findElement(By.xpath("span"));
        return check.getText().trim();
    }
}

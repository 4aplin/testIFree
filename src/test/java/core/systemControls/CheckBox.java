package core.systemControls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;

public class CheckBox  extends HtmlControl
{
    public CheckBox(By locator)
    {
        this(locator, null);
    }

    public CheckBox(By locator, HtmlControl container)
    {
        super(locator, container);
    }

    private boolean getChecked()
    {
        WebElement check = element.findElement(By.xpath("div"));
        String value = check.getAttribute("class");
        return value.contains("selected");
    }

    @Step("checkbox selected")
    public void checkAndWait()
    {
        if (!getChecked())
        {
            element.click();
        }
    }

    @Step("checkbox unselected")
    public void uncheckAndWait()
    {
        if (getChecked())
        {
            element.click();
        }
    }

    @Step("waitChecked")
    public void waitChecked()
    {
        String description = formatWithLocator(String.format("Wait Checked in element"));
        Waiter.Wait(() -> getChecked() == true, description);
    }

    @Step("waitUnchecked")
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


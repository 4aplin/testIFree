package core.systemControls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;

public class ButtonHidden extends HtmlControl
{
    public ButtonHidden(By locator)
    {
        this(locator, null);
    }

    public ButtonHidden(By locator, HtmlControl container)
    {
        super(locator, container);
    }

    private boolean getVisible()
    {
        WebElement mouseover = element.findElement(By.xpath("div"));
        String value = mouseover.getAttribute("class");
        return value.contains("visible");
    }

    @Step("unhidden button")
    public void getVisibleButton() {
        if (getVisible()) {
            element.click();
        }
    }
}
package core.systemControls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;

public class Hint extends HtmlControl
{
    public Hint(By locator)
    {
        this(locator, null);
    }

    public Hint(By locator, HtmlControl container)
    {
        super(locator, container);
    }
    public boolean getVisible()
    {
        WebElement mouseover = element.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[1]/div/div/div[3]/div/div[2]/div/div[1]/ul/li[2]/div/form/fieldset/div/i"));
        String value = mouseover.getAttribute("class");
        return value.contains("visible");
    }
    @Step("unhidden button")
    public void getVisibleButton() {
        if (getVisible()) {
            element.click();
        }
    }
    @Override
    public String getText()
    {
        WebElement optionsValue = this.element.findElement(By.xpath("//*[@id=\"errorIconPopoverTemplateName.html\"]"));
        return optionsValue.getText();
    }
 /*   public void getHintText() {
        WebElement mouseoverAndHold = this.mouseover();

    } */
}

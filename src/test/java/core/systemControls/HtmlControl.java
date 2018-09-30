package core.systemControls;

import core.webDriver.WebDriverCache;
import core.webDriver.WebElementWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Step;

public abstract class HtmlControl {
    private String controlDescription;
    public WebElementWrapper element;
    public By locator;
    public SearchContext searchContext;

    protected HtmlControl(By locator) {
        Container = null;
        this.locator = locator;
        controlDescription = FormatControlDescription(locator.toString(), Container);
        searchContext = Container.getSearchContext();
        element = new WebElementWrapper(searchContext, locator, controlDescription);
    }

    protected HtmlControl(By locator, HtmlControl container) {
        Container = container;
        this.locator = locator;
        controlDescription = FormatControlDescription(locator.toString(), container);
        if (Container == null) searchContext = Container.getSearchContext();
        else searchContext = Container.getSearchContext(Container);
        element = new WebElementWrapper(searchContext, locator, controlDescription);
    }

    private HtmlControl Container;

    protected HtmlControl(String idLocator) {
        this(idLocator, null);
    }

    protected HtmlControl(String idLocator, HtmlControl container) {
        this(By.id(idLocator), container);
    }

    public final String FormatControlDescription(String locatorString, HtmlControl container)
    {
        String description = String.format("%1$s (%2$s)", this.getClass().getSimpleName(), locatorString);
        if (container == null)
        {
            return description;
        }
        return String.format("%1$s in the context of the element%2$s", description, container.controlDescription);
    }
    public static SearchContext getSearchContext(HtmlControl container)
    {
        return container.element;
    }
    public static SearchContext getSearchContext()
    {
        return WebDriverCache.getWebDriver().GetSearchContext();
    }

    public final void switchToFrame()
    {
        WebDriverCache.getWebDriver().switchToFrame(locator);
    }

    public final void javascriptExecutorClick()
    {
        WebElement button = searchContext.findElement(locator);
        WebDriverCache.getWebDriver().javascriptExecutor("arguments[0].click();", button);
    }

    public final Object javascriptExecuteWebElement(String script)
    {
        WebElement webElement = searchContext.findElement(locator);
        return WebDriverCache.getWebDriver().javascriptExecutor(script, webElement);
    }

    public final Object javascriptExecute(String script)
    {
        return WebDriverCache.getWebDriver().ExecuteScript(script);
    }

    public final void switchToDefaultContent()
    {
        WebDriverCache.getWebDriver().switchToDefaultContent();
    }

    public boolean getIsEnabled()
    {
        return !hasClass("disabled");
    }

    public final boolean getIsPresent()
    {
        return (Container == null || Container.getIsPresent()) && searchContext.findElements(locator).size() > 0;
    }

    public boolean getIsVisible()
    {
        return element.isDisplayed();
    }

    public final SearchContext searchContext()
    {
        return searchContext;
    }

    public final void mouseover()
    {
        element.mouseover();
    }

    public final void sendKeysToBody(String text)
    {
        element.sendKeysToBody(text);
    }

    public final void sendKeys(String text)
    {
        element.sendKeys(text);
    }

    public String getText()
    {
         element.getText().trim();
        if (element.getText().trim().equals(""))
            Waiter.Wait(() -> !element.getText().trim().equals(""), "wait txt not equals null", 3000);
        return element.getText().trim();
    }

    public String getTagName()
    {
        return element.getTagName().trim();
    }

    public String getValue()
    {
        return element.getAttribute("value");
    }

    public void AssertNessesary() throws InterruptedException {
        WaitClassPresenceWithRetries("field__incorrect");
        WaitAttributeValue("title", "The field must be filled");
    }

    public final boolean hasClass(String className)
    {
        String actualClasses = null;
        RefObject<String> tempRef_actualClasses = new RefObject<String>(actualClasses);
        boolean tempVar = hasClass(className, tempRef_actualClasses);
        actualClasses = tempRef_actualClasses.argValue;
        return tempVar;
    }

    private boolean hasClass(String className, RefObject<String> actualClasses) {
        actualClasses.argValue = getAttributeValue("class");
        String[] actualClassesArray = actualClasses.argValue.split(String.valueOf(new String[]{" ", "\r", "\n", "\t"}), -1);
        String[] expectedClassesArray = className.split(String.valueOf(new String[] {" ", "\r", "\n", "\t"}), -1);
return true;
//        return expectedClassesArray.All(actualClassesArray.contains);
    }

    public String getAttributeValue(String attributeName)
    {
        return element.getAttribute(attributeName);
    }

    public String formatWithLocator(String text)
    {
        return String.format("'%1$s' '%2$s'", text, controlDescription);
    }

    @Step("wait visible")
    public final void WaitVisible()
    {
        String description = formatWithLocator("Wait visible element");
        Assert.assertEquals(getIsVisible(), true, description);
    }

    public final void WaitVisibleWithRetries()
    {
        WaitVisibleWithRetries(null);
    }

    @Step("wait visible element")
    public final void WaitVisibleWithRetries(Integer timeout)
    {
        WebDriverCache.getWebDriver().WaitElementPresent(locator);
//        String description = formatWithLocator("Wait visible element");
//        Waiter.WaitExeption(()-> getIsVisible(), description, timeout);
    }

    @Step("wait invisible element")
    public final void WaitInvisible()
    {
        String description = formatWithLocator("Wait invisible element");
        Assert.assertTrue(getIsVisible(), description);
    }

    public final void WaitInvisibleWithRetries()
    {
        WaitInvisibleWithRetries(null);
    }

    @Step("wait invisible element")
    public final void WaitInvisibleWithRetries(Integer timeout) {
        String description = formatWithLocator("wait invisible element");
        Waiter.Wait(() -> !getIsVisible(), description, timeout);
    }

    @Step("wait presence element")
    public final void WaitPresence()
    {
        String description = formatWithLocator("wait presence element");
        Assert.assertTrue(getIsPresent(), description);
    }

    public final void WaitPresenceWithRetries()
    {
        WaitPresenceWithRetries(null);
    }

    @Step("wait presence element")
    public final void WaitPresenceWithRetries(Integer timeout)
    {
        String description = formatWithLocator("wait presence element");
        Waiter.Wait(() -> getIsPresent(), description, timeout);
    }

    @Step("wait absence element")
    public final void WaitAbsence()
    {
        String description = formatWithLocator("wait absence element");
        Assert.assertFalse(getIsPresent(), description);
    }

    public final void WaitAbsenceWithRetries()
    {
        WaitAbsenceWithRetries(null);
    }

    @Step("wait absence element")
    public final void WaitAbsenceWithRetries(Integer timeout)
    {
        String description = formatWithLocator("wait absence element");
        Waiter.Wait(() -> !getIsPresent(), description, timeout);
    }

    @Step("wait enabled element")
    public final void WaitEnabled()
    {
        String description = formatWithLocator("wait absence element");
        Assert.assertTrue(getIsEnabled(), description);
    }

    public final void WaitEnabledWithRetries()
    {
        WaitEnabledWithRetries(null);
    }

    @Step("wait enabled element")
    public final void WaitEnabledWithRetries(Integer timeout)
    {
        String description = formatWithLocator("wait enabled element");
        Waiter.Wait(() -> getIsEnabled(), description, timeout);
    }

    @Step("wait disabled element")
    public final void WaitDisabled()
    {
        String description = formatWithLocator("wait disabled element");
        Assert.assertFalse(getIsEnabled(), description);
    }

    public final void WaitDisabledWithRetries()
    {
        WaitDisabledWithRetries(null);
    }

    @Step("wait disabled element")
    public final void WaitDisabledWithRetries(Integer timeout)
    {
        String description = formatWithLocator("wait disabled element");
        Waiter.Wait(() -> !getIsEnabled(), description, timeout);
    }

    @Step("wait text {0} in element")
    public void WaitText(String expectedText)
    {
        String description = formatWithLocator(String.format("wait text '%1$s' in element", expectedText));
        Waiter.Wait(() -> getText().equals(expectedText), description);
    }

    @Step("wait value {0} in element")
    public final void WaitValue(String value)
    {
        String description = formatWithLocator(String.format("wait value '%1$s' in element", value));
        Waiter.Wait(() -> getAttributeValue("value").equals(value), description);
    }

    @Step("wait start text {0} in element")
    public final void WaitTextStartsWith(String expectedText)
    {
        String description = formatWithLocator(String.format("wait start text '%1$s' in element", expectedText));
        Waiter.Wait(() -> getText().startsWith(expectedText), description);
    }

    @Step("wait text {0} in contain")
    public final void WaitTextContains(String expectedText)
    {
        String description = formatWithLocator(String.format("wait text '%1$s' in contain", expectedText));
        Waiter.Wait(() -> getText().contains(expectedText), description);
    }

     public final void WaitTextContainsWithRetries(String expectedText)
    {
        WaitTextContainsWithRetries(expectedText, null);
    }

    @Step("wait text {0} in contain")
    public final void WaitTextContainsWithRetries(String expectedText, Integer timeout)
    {
        String description = formatWithLocator(String.format("wait text '%1$s' in contain", expectedText));
        Waiter.Wait(() -> getText().contains(expectedText), description, timeout);
    }

    @Step("wait class {0} in element")
    public void WaitClassPresence(String className)
    {
        String description = formatWithLocator(String.format("wait class '%1$s' in element", className));
        String actualClasses = null;
        RefObject<String> tempRef_actualClasses = new RefObject<String>(actualClasses);
        Assert.assertTrue(hasClass(className, tempRef_actualClasses), String.format("'%1$s', actual class: '%2$s'", description, actualClasses));
        actualClasses = tempRef_actualClasses.argValue;
    }

    @Step("wait class {0} in element")
    public void WaitClassPresenceWithRetries(String className)
    {
        WaitClassPresenceWithRetries(className, null);
    }

    @Step("wait class {0} in element")
    public void WaitClassPresenceWithRetries(String className, Integer timeout)
    {
        String description = formatWithLocator(String.format("wait class '%1$s' in element", className));
        Waiter.Wait(() -> hasClass(className), description, timeout);
    }

    @Step("wait absence class {0} in element")
    public final void WaitClassAbsence(String className)
    {
        String description = formatWithLocator(String.format("wait absence class '%1$s' in element", className));
        String actualClasses = null;
        RefObject<String> tempRef_actualClasses = new RefObject<String>(actualClasses);
        Assert.assertFalse(hasClass(className, tempRef_actualClasses), String.format("'%1$s', actual class: '%2$s'", description, actualClasses));
        actualClasses = tempRef_actualClasses.argValue;
    }

    public final void WaitClassAbsenceWithRetries(String className)
    {
        WaitClassAbsenceWithRetries(className, null);
    }

    @Step("wait absence class {0} in element")
    public final void WaitClassAbsenceWithRetries(String className, Integer timeout) {
        String description = formatWithLocator(String.format("Ожидание отсутствия класса '%1$s'  in element", className));
        Waiter.Wait(() -> !hasClass(className), description, timeout);
    }

    @Step("wait attribute {0} with value {1} in element")
    public final void WaitAttributeValue(String attributeName, String expectedText)
    {
        String description = formatWithLocator(String.format("wait attribute '%1$s' with value '%2$s' in element", attributeName, expectedText));
        Assert.assertEquals(expectedText, getAttributeValue(attributeName), description);
    }

    public final void WaitAttributeValueWithRetries(String attributeName, String expectedText)
    {
        WaitAttributeValueWithRetries(attributeName, expectedText, null);
    }


    @Step("wait attribute {0} with value {1} in element")
    public final void WaitAttributeValueWithRetries(String attributeName, String expectedText, Integer timeout)
    {
        String description = formatWithLocator(String.format("wait attribute '%1$s' with value '%2$s' in element", attributeName, expectedText));
        Waiter.Wait(() -> expectedText == getAttributeValue(attributeName), description, timeout);
    }

    @Step("click in element")
    public void click() {
        WebDriverCache.getWebDriver().WaitElementClicked(locator);
        Waiter.WaitExeption(() -> element.click(), "can't click in element");
    }
}
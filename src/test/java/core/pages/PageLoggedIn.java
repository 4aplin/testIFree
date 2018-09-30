package core.pages;

import core.systemControls.Button;
import core.systemPages.PageBase;
import org.openqa.selenium.By;

public class PageLoggedIn extends PageBase {

    public PageLoggedIn () {
        CreateButton = new Button(By.xpath("//*[@id=\"root\"]/div/div/main/div[1]/div[1]/div/div"));
    }

    public Button CreateButton;

    @Override
    public void BrowseWaitVisible() {
        CreateButton.WaitVisibleWithRetries();
    }
}

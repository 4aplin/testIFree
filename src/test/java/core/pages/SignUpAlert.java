package core.pages;

import core.systemControls.*;
import core.systemPages.PageBase;
import org.openqa.selenium.By;

public class SignUpAlert extends PageBase {

    public SignUpAlert() {
          AlertError = new ButtonHidden(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div[2]/div/div[1]/div/form/div[1]/div[1]"));
    }

    public ButtonHidden AlertError;

    @Override
    public void BrowseWaitVisible() {
        AlertError.WaitVisibleWithRetries();
    }
}

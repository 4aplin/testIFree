package core.pages;

import core.systemControls.*;
import core.systemPages.PageBase;
import org.openqa.selenium.By;

public class SignUpAlert2 extends PageBase {

    public SignUpAlert2() {
        AlertError2 = new ButtonHidden(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div[2]/div/div[1]/div/form/div[1]/div[2]"));
    }

    public ButtonHidden AlertError2;

    @Override
    public void BrowseWaitVisible() {
        AlertError2.WaitVisibleWithRetries();
    }
}

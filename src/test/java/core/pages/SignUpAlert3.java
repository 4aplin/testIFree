package core.pages;

import core.systemControls.*;
import core.systemPages.PageBase;
import org.openqa.selenium.By;

public class SignUpAlert3 extends PageBase {

    public SignUpAlert3() {
        AlertError3 = new ButtonHidden(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div[2]/div/div[1]/div/form/div[1]/div[3]"));
    }

    public ButtonHidden AlertError3;

    @Override
    public void BrowseWaitVisible() {
        AlertError3.WaitVisibleWithRetries();
    }
}

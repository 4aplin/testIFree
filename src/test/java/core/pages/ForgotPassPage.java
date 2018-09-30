package core.pages;

import core.systemControls.TextInput;
import core.systemPages.PageBase;
import org.openqa.selenium.By;

public class ForgotPassPage extends PageBase {

    public ForgotPassPage() {
        EmailInput = new TextInput(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div[2]/div/div[1]/div/form/div[1]/div/div/input"));
    }

    public TextInput EmailInput;

    @Override
    public void BrowseWaitVisible() {
        EmailInput.WaitVisibleWithRetries();
    }

}

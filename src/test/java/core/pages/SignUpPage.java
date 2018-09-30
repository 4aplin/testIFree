package core.pages;

import core.systemControls.*;
import core.systemPages.PageBase;
import org.openqa.selenium.By;

public class SignUpPage extends PageBase {

    public SignUpPage() {
        NameInput = new TextInput(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div[2]/div/div[1]/div/form/div[1]/div[1]/div/input"));
        EmailInput = new TextInput(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div[2]/div/div[1]/div/form/div[1]/div[2]/div/input"));
        PassInput = new TextInput(By.xpath("//*[@id=\"password\"]"));
        CreateAccountBtn = new Button(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div[2]/div/div[1]/div/form/div[2]/button"));
    }

    public TextInput NameInput;
    public TextInput EmailInput;
    public TextInput PassInput;
    public Button CreateAccountBtn;

    @Override
    public void BrowseWaitVisible() {
        EmailInput.WaitVisibleWithRetries();
        NameInput.WaitVisibleWithRetries();
        PassInput.WaitVisibleWithRetries();
        CreateAccountBtn.WaitVisibleWithRetries();
    }
}

package core.pages;

import core.systemControls.Button;
import core.systemControls.Hint;
import core.systemControls.StaticText;
import core.systemControls.TextInput;
import core.systemPages.PageBase;
import org.openqa.selenium.By;

public class SignUpPage extends PageBase {
    public SignUpPage() {
        NameInput = new TextInput(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div[2]/div/div[1]/div/form/div[1]/div[1]/div/input"));
        EmailInput = new TextInput(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div[2]/div/div[1]/div/form/div[1]/div[2]/div/input"));
        PassInput = new TextInput(By.xpath("//*[@id=\"password\"]"));
        CreateAccountBtn = new Button(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div[2]/div/div[1]/div/form/div[2]/button"));
        AlertError1 = new StaticText(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div[2]/div/div[1]/div/form/div[1]/div[1]"));
                                                     //*[@id="root"]/div/div/div/main/div/div[2]/div/div[1]/div/form/div[1]/div[1]
        AlertError2 = new StaticText(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div[2]/div/div[1]/div/form/div[1]/div[2]"));
        AlertError3 = new StaticText(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div[2]/div/div[1]/div/form/div[1]/div[3]"));
    }

    public TextInput NameInput;
    public TextInput EmailInput;
    public TextInput PassInput;
    public Button CreateAccountBtn;
    public StaticText AlertError1;
    public StaticText AlertError2;
    public StaticText AlertError3;

    @Override
    public void BrowseWaitVisible() {
        EmailInput.WaitVisibleWithRetries();
        NameInput.WaitVisibleWithRetries();
        PassInput.WaitVisibleWithRetries();
        CreateAccountBtn.WaitVisibleWithRetries();
    }
}

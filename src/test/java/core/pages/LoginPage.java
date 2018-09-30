package core.pages;

import core.supportClass.BY;
import core.systemControls.*;
import core.systemPages.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage extends PageBase {

    public LoginPage() {
        LoginInput = new TextInput(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div[2]/div/div[1]/div/form/div[1]/div[1]/div/input"));
        PasswordInput = new TextInput(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div[2]/div/div[1]/div/form/div[1]/div[2]/div/input"));
        SignInButton = new Button(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div[2]/div/div[1]/div/form/div[2]/button"));
        ErrorMessage = new StaticText(By.tagName("div.jss246"));
        ForgotPasswordLink = new Link(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div[2]/div/div[1]/div/form/div[1]/a"));
        SignUpLink = new Link(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div[2]/div/div[2]/a"));
    }

    public TextInput LoginInput;
    public TextInput PasswordInput;
    public Button SignInButton;
    public StaticText ErrorMessage;
    public Link ForgotPasswordLink;
    public Link SignUpLink;

    @Override
    public void BrowseWaitVisible() {
        LoginInput.WaitVisibleWithRetries();
        PasswordInput.WaitVisibleWithRetries();
        SignInButton.WaitVisibleWithRetries();
    }



}

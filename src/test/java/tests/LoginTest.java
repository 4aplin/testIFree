package tests;

import core.SetUrlTestBase;
import core.systemControls.StaticText;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import core.pages.*;


public class LoginTest extends SetUrlTestBase
{
    @Test (invocationTimeOut = 150000)
        public void SignInEmailTest() {
            LoginPage newPage = new LoginPage();
            newPage.LoginInput.SetValue("chaplin369@gmail.com");
            newPage.PasswordInput.SetValue("J02elt8699@#!");
            newPage.SignInButton.click();

            newPage.GoTo(PageLoggedIn.class);

        }

    @Test(invocationTimeOut = 150000)
        public void SignInEmailIncorrectEmailTest() {

        LoginPage loginPage = new LoginPage();
        loginPage.LoginInput.SetValue("chaplin369@gmail.com4");
        loginPage.PasswordInput.SetValue("123123");
        loginPage.SignInButton.click();

        StaticText error = new StaticText(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div[2]/div/div[1]/div/form/div[1]/div[1]/div"));

        error.WaitVisibleWithRetries();
        String qqq = error.getText();
        Assert.assertEquals(qqq, "Неверный логин или пароль");

    }

    @Test(invocationTimeOut = 150000)
        public void SignInEmailIncorrectPassTest() {

        LoginPage loginPage = new LoginPage();
        loginPage.LoginInput.SetValue("chaplin369@gmail.com");
        loginPage.PasswordInput.SetValue("123123");
        loginPage.SignInButton.click();

        StaticText error = new StaticText(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div[2]/div/div[1]/div/form/div[1]/div[1]/div"));

        error.WaitVisibleWithRetries();
        String qqq = error.getText();
        Assert.assertEquals(qqq, "Неверный логин или пароль");

    }

    @Test(invocationTimeOut = 150000)
        public void SignInEmailEmptyFieldsTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.LoginInput.SetValue("");
        loginPage.PasswordInput.SetValue("");
        loginPage.SignInButton.click();

        StaticText error = new StaticText(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div[2]/div/div[1]/div/form/div[1]/div[1]/div"));

        error.WaitVisibleWithRetries();
        String qqq = error.getText();
        Assert.assertEquals(qqq, "Неверный логин или пароль");
    }

    @Test(invocationTimeOut = 150000)
        public void ForgotLinkTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.ForgotPasswordLink.click();

        loginPage.GoTo(ForgotPassPage.class);
        ForgotPassPage forgotPassPage = new ForgotPassPage();
    }
}


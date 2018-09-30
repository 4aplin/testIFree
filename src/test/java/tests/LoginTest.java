package tests;

import core.SetUrlTestBase;
import core.systemControls.StaticText;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import core.pages.*;


public class LoginTest extends SetUrlTestBase
{
    @Test (invocationTimeOut = 150000)
        public void SignInEmailTestTest() {
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

        loginPage.ErrorMessage.getIsVisible();
        loginPage.WaitForAjax();

        loginPage.ErrorMessage.WaitVisibleWithRetries();
        loginPage.ErrorMessage.WaitText("Неверный логин или пароль");

    }

    @Test(invocationTimeOut = 150000)
        public void SignInEmailIncorrectPassTest() {

        LoginPage loginPage = new LoginPage();
        loginPage.LoginInput.SetValue("chaplin369@gmail.com");
        loginPage.PasswordInput.SetValue("123123");
        loginPage.SignInButton.click();

        loginPage.ErrorMessage.WaitVisibleWithRetries();
        loginPage.ErrorMessage.WaitText("Неверный логин или пароль");

    }

    @Test(invocationTimeOut = 300000)
        public void SignInEmailEmptyFieldsTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage();
        loginPage.LoginInput.SetValue("");
        loginPage.PasswordInput.SetValue("");
        loginPage.SignInButton.click();

//        WebElement element = driver.findElement(By.id("first"));
//        driver.findElement.By.linkText("Неверный логин или пароль");
        loginPage.ErrorMessage.getIsVisible();
        loginPage.ErrorMessage.WaitVisibleWithRetries();
        System.out.println(loginPage.ErrorMessage.getText());
        loginPage.ErrorMessage.WaitText("Неверный логин или пароль");
   /*     String alert = loginPage.ErrorMessage.getText();

        WaitForTextToAppear(alert, "Неверный логин или пароль");
        loginPage.ErrorMessage.WaitVisibleWithRetries();*/
    }

    private void WaitForTextToAppear(String errorMessage, String text) {
        Assert.assertEquals(errorMessage, text);
    }

    @Test
        public void ForgotLinkTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.ForgotPasswordLink.click();

        loginPage.GoTo(ForgotPassPage.class);
        ForgotPassPage forgotPassPage = new ForgotPassPage();
    }
}


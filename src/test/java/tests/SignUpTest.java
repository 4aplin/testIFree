package tests;

import core.SetUrlTestBase;
import core.pages.*;
import core.systemControls.Waiter;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SignUpTest extends SetUrlTestBase
    {
        Waiter waiter = new Waiter();

        @Test(invocationTimeOut = 150000)
        public void SignUpCorrectTest() {
            LoginPage loginPage = new LoginPage();
            loginPage.SignUpLink.click();
            loginPage.WaitForAjax();

            SignUpPage signUpPage = loginPage.GoTo(SignUpPage.class);
            signUpPage.NameInput.SetValue(name);
            signUpPage.EmailInput.SetValue(newEmail);
            signUpPage.PassInput.SetValue(pass);
            signUpPage.CreateAccountBtn.click();
            signUpPage.WaitForAjax();

            PageLoggedIn pageLoggedIn = signUpPage.GoTo(PageLoggedIn.class);
        }

        @Test
        public void SignUpEmptyFields() {
            LoginPage loginPage = new LoginPage();
            loginPage.SignUpLink.click();
            loginPage.WaitForAjax();

            SignUpPage signUpPage = loginPage.GoTo(SignUpPage.class);
            signUpPage.EmailInput.SetValue("");
            signUpPage.PassInput.SetValue("");
            signUpPage.CreateAccountBtn.click();

            signUpPage.AlertError1.WaitVisibleWithRetries();
            signUpPage.AlertError2.WaitVisibleWithRetries();
            signUpPage.AlertError3.WaitVisibleWithRetries();

            signUpPage.AlertError1.WaitText("Почта не задана");
            signUpPage.AlertError2.WaitText("Пароль не задан");
            signUpPage.AlertError3.WaitText("Слишком слабый пароль, длина пароля должна быть не меньше 8 символов");

        }


        @Test
        public void SignUpWithoutEmail() {
            LoginPage loginPage = new LoginPage();
            loginPage.SignUpLink.click();
            loginPage.WaitForAjax();

            SignUpPage signUpPage = loginPage.GoTo(SignUpPage.class);
            signUpPage.EmailInput.SetValue("");
            signUpPage.PassInput.SetValue(pass);
            signUpPage.CreateAccountBtn.click();

            signUpPage.AlertError1.getIsVisible();
            signUpPage.AlertError1.WaitVisibleWithRetries();
            signUpPage.AlertError1.WaitText("Почта не задана");

        }


        @Test // todo этот тест не проходит изза отсутствия проверки в поле ввода Email на вид ххх@хх.хх
        public void SignUpIncorrectEmail() {
            LoginPage loginPage = new LoginPage();
            loginPage.SignUpLink.click();
            loginPage.WaitForAjax();

            SignUpPage signUpPage = loginPage.GoTo(SignUpPage.class);
            signUpPage.EmailInput.SetValue("123@123");
            signUpPage.PassInput.SetValue(pass);
            signUpPage.CreateAccountBtn.click();

            signUpPage.AlertError1.WaitVisibleWithRetries();
            signUpPage.AlertError1.WaitText("Почти на задана");

        }

        @Test // todo этот тест не проходит изза отсутствия проверки в поле ввода имени ограничения кол-ва символов
        public void SignUpLongName() {
            LoginPage loginPage = new LoginPage();
            loginPage.SignUpLink.click();
            loginPage.WaitForAjax();

            SignUpPage signUpPage = loginPage.GoTo(SignUpPage.class);
            signUpPage.NameInput.SetValue("33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333");
            signUpPage.EmailInput.SetValue(newEmail);
            signUpPage.PassInput.SetValue(pass);
            signUpPage.CreateAccountBtn.click();

            signUpPage.AlertError1.WaitVisibleWithRetries();
            signUpPage.AlertError1.WaitText("Слишком длинное Имя");
        }

    }

package tests;

import core.SetUrlTestBase;
import core.pages.*;
import org.testng.annotations.Test;

public class SignUpTest extends SetUrlTestBase
    {
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

        @Test(invocationTimeOut = 150000)
        public void SignUpEmptyFields() {
            LoginPage loginPage = new LoginPage();
            loginPage.SignUpLink.click();
            loginPage.WaitForAjax();

            SignUpPage signUpPage = loginPage.GoTo(SignUpPage.class);
            signUpPage.EmailInput.SetValue("");
            signUpPage.PassInput.SetValue("");
            signUpPage.CreateAccountBtn.click();

            signUpPage.GoTo(SignUpAlert.class);
            SignUpAlert signUpAlert = new SignUpAlert();
            signUpAlert.AlertError.WaitVisible();
            signUpAlert.AlertError.WaitText("Почта не задана");

            signUpAlert.GoTo(SignUpAlert2.class);
            SignUpAlert2 signUpAlert2 = new SignUpAlert2();
            signUpAlert2.AlertError2.WaitVisible();
            signUpAlert2.AlertError2.WaitText("Пароль не задан");

            signUpAlert2.GoTo(SignUpAlert3.class);
            SignUpAlert3 signUpAlert3 = new SignUpAlert3();
            signUpAlert3.AlertError3.WaitVisible();
            signUpAlert3.AlertError3.WaitText("Слишком слабый пароль, длина пароля должна быть не меньше 8 символов");

        }


        @Test(invocationTimeOut = 150000)
        public void SignUpWithoutEmail() {
            LoginPage loginPage = new LoginPage();
            loginPage.SignUpLink.click();
            loginPage.WaitForAjax();

            SignUpPage signUpPage = loginPage.GoTo(SignUpPage.class);
            signUpPage.EmailInput.SetValue("");
            signUpPage.PassInput.SetValue(pass);
            signUpPage.CreateAccountBtn.click();

            signUpPage.GoTo(SignUpAlert.class);
            SignUpAlert signUpAlert = new SignUpAlert();
            signUpAlert.AlertError.WaitVisible();
            signUpAlert.AlertError.WaitText("Почта не задана");

        }


        @Test(invocationTimeOut = 150000)// todo этот тест не проходит изза отсутствия проверки в поле ввода Email на вид ххх@хх.хх
        public void SignUpIncorrectEmail() {
            LoginPage loginPage = new LoginPage();
            loginPage.SignUpLink.click();
            loginPage.WaitForAjax();

            SignUpPage signUpPage = loginPage.GoTo(SignUpPage.class);
            signUpPage.EmailInput.SetValue("123@123");
            signUpPage.PassInput.SetValue(pass);
            signUpPage.CreateAccountBtn.click();

            signUpPage.GoTo(SignUpAlert.class);
            SignUpAlert signUpAlert = new SignUpAlert();
            signUpAlert.AlertError.WaitVisible();
            signUpAlert.AlertError.WaitText("Почта не задана");

        }

        @Test(invocationTimeOut = 150000) // todo этот тест не проходит изза отсутствия проверки в поле ввода имени ограничения кол-ва символов
        public void SignUpLongName() {
            LoginPage loginPage = new LoginPage();
            loginPage.SignUpLink.click();
            loginPage.WaitForAjax();

            SignUpPage signUpPage = loginPage.GoTo(SignUpPage.class);
            signUpPage.NameInput.SetValue("33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333");
            signUpPage.EmailInput.SetValue(newEmail);
            signUpPage.PassInput.SetValue(pass);
            signUpPage.CreateAccountBtn.click();

            signUpPage.GoTo(SignUpAlert.class);
            SignUpAlert signUpAlert = new SignUpAlert();
            signUpAlert.AlertError.WaitVisible();
            signUpAlert.AlertError.WaitText("Слишком длинное имя");
        }

    }

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginFormPage extends BasePage{


    public LoginFormPage(WebDriver navegador) {
        super(navegador);
    }

    public LoginFormPage digitarLogin(String login){
        navegador.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys(login);

        return this;
    }

    public LoginFormPage digitarSenha(String senha){
        navegador.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys(senha);

        return this;
    }

    public ScretaPage clicarSignIn(){
        navegador.findElement(By.linkText("SIGN IN")).click();

        return new ScretaPage(navegador);
    }

    public ScretaPage fazerLogin(String login, String senha){
        digitarLogin(login);
        digitarSenha(senha);
        clicarSignIn();

        return new ScretaPage(navegador);
    }

}

package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.print.attribute.standard.MediaSize;
import java.util.concurrent.TimeUnit;

public class informacoesUsuarioTest {
    private WebDriver navegador;

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\OLIVA\\Desktop\\drivers\\chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        navegador.get("http://www.juliodelima.com.br/taskit");

        navegador.findElement(By.linkText("Sign in")).click();

        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));
        formularioSignInBox.findElement(By.name("login")).sendKeys("Julio0001");
        formularioSignInBox.findElement(By.name("password")).sendKeys("123456");

        navegador.findElement(By.linkText("SIGN IN")).click();
        navegador.findElement(By.className("me")).click();
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }

    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(){
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();
        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

        WebElement campoType = popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText("Phone");

        popupAddMoreData.findElement(By.name("contact")).sendKeys("+5511984131888");

        popupAddMoreData.findElement(By.linkText("SAVE")).click();

        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        Assert.assertEquals("Your contact has been added!", mensagem);
    }

    @Test
    public void removerUmContatoDeUsuario(){
        //Clicar no elemento pelo xpath (identificar pelo text e excluir o proximo elemento "a")
        navegador.findElement(By.xpath("//span[text()=\"+5511 33334444\"]/following-sibling::a")).click();
        //Confirmar janela do java script
        navegador.switchTo().alert().accept();
        //Validar que a mensagem apresentada foi Rest in peace, dear phone!
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        Assert.assertEquals("Rest in peace, dear phone!", mensagem);
        //Aguardar até 10 segundos para que a janela desapareça
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));
        //Clicar no link Logout
        navegador.findElement(By.linkText("Logout")).click();
    }

    @After
    public void tearDown(){
        navegador.quit();
    }
}

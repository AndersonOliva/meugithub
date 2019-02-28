package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ScretaPage extends BasePage {

    public ScretaPage(WebDriver navegador) {
        super(navegador);
    }

    public MePage clicarMe(){
        navegador.findElement(By.className("me")).click();

        return new MePage(navegador);
    }

}

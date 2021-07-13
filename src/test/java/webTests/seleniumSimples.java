// 1-Pacotes
package webTests;

// 2-Bibliotecas

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import utils.Evidencias;

// 3-Classe
public class seleniumSimples<dependsOnMethods> {
    // 3.1-Atributos

    WebDriver driver;                       // declarar o objeto do Selenium WebDriver
    Evidencias evidencias;
    static String dataHora = new SimpleDateFormat("YYYY-MM-dd HH-mm-ss").getCalendar().getTime().toString();

    // 3.2-Métodos e Funções
    @BeforeMethod
    public void iniciar(){
        // A - Início
        System.setProperty("webdriver.chrome.driver", "drivers/chrome/90/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1600, 833));
        driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS);

        evidencias = new Evidencias();
    }

    @AfterMethod
    public void finalizar(){
        // C - Finalizar o teste
        driver.quit();
    }

    @Test(priority = 2, dependsOnMethods = {"consultarCursoMantis"})
       public void consultarCursoCTFL() throws IOException {
        String casoDeTeste = "Consultar Curso CTFL";

        // B - Realziar o teste
        driver.get("https://www.iterasys.com.br");                                          // abre o site
        evidencias.print(driver, dataHora, casoDeTeste,"Passo 1 - Abriu o site");

        driver.findElement(By.id("searchtext")).click();                                    // clica no campo de pesquisa
        driver.findElement(By.id("searchtext")).clear();                                    // limpa o campo de pesquisa
        driver.findElement(By.id("searchtext")).sendKeys("preparatório ctfl");  // escreve no campo de pesquisa

        evidencias.print(driver, dataHora, casoDeTeste,"Passo 2 - Digitou Preparatorio CTFL");
        // Clique na lupa
        driver.findElement(By.id("btn_form_search")).click();

        assertEquals(driver.findElement(By.cssSelector("h3")).getText(), "Cursos › \"preparatório ctfl\"");

        evidencias.print(driver, dataHora, casoDeTeste,"Passo 3 - Exibiu a lista de cursos");
        driver.findElement(By.cssSelector("span.comprar")).click();

        assertEquals(driver.findElement(By.cssSelector("span.item-title")).getText(), "Preparatório CTFL");
        assertEquals(driver.findElement(By.cssSelector("span.new-price")).getText(), "R$ 169,00");
        evidencias.print(driver, dataHora, casoDeTeste,"Passo 4 - Exibiu o carrinho de compras");
    }

    @Test(priority = 1)
    public void consultarCursoMantis() throws IOException {
        String casoDeTeste = "Consultar Curso Mantis";

        // B - Realizar o teste
        driver.get("https://www.iterasys.com.br");                                          // abre o site
        evidencias.print(driver, dataHora, casoDeTeste, "Passo 1 - Abriu o site");

        driver.findElement(By.id("searchtext")).click();                                    // clica no campo de pesquisa
        driver.findElement(By.id("searchtext")).clear();                                    // limpa o campo de pesquisa
        driver.findElement(By.id("searchtext")).sendKeys("mantis");             // escreve no campo de pesquisa

        evidencias.print(driver, dataHora, casoDeTeste,"Passo 2 - Digitou Mantis");
        // Clique na lupa
        driver.findElement(By.id("btn_form_search")).click();

        assertEquals(driver.findElement(By.cssSelector("h3")).getText(), "Cursos › \"mantis\"");

        evidencias.print(driver, dataHora, casoDeTeste,"Passo 3 - Exibiu a lista de cursos");
        driver.findElement(By.cssSelector("span.comprar")).click();

        assertEquals(driver.findElement(By.cssSelector("span.item-title")).getText(), "Mantis");
        assertEquals(driver.findElement(By.cssSelector("span.new-price")).getText(), "R$ 49,99");
        evidencias.print(driver, dataHora, casoDeTeste,"Passo 4 - Exibiu o carrinho de compras");
    }

}

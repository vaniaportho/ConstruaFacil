package webTests;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class comprarCursoCS {

    WebDriver driver;

    @Before
    public void iniciar(){
        System.setProperty("webdriver.chrome.driver", "drivers/chrome/90/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1600, 833));
        driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS);
        System.out.println("0 - Antes do Teste iniciar");
    }

    @After
    public void finalizar(){
        driver.quit();
        System.out.println("Z - Depois do Teste iniciar");
    }

    @Dado("^que acesso o site da Iterasys$")
    public void queAcessoOSiteDaIterasys() {
        driver.get("https://www.iterasys.com.br");
        System.out.println("1 - Acessou o site");
    }

    @Quando("^pesquiso por \"([^\"]*)\"$")
    public void pesquisoPor(String Curso) {
        driver.findElement(By.id("searchtext")).click();
        driver.findElement(By.id("searchtext")).clear();
        driver.findElement(By.id("searchtext")).sendKeys(Keys.chord("Mantis"));
        System.out.println("2 - Digitou o nome do curso " + Curso);
    }

    @E("^clico na lupa$")
    public void clicoNaLupa() {
        driver.findElement(By.id("btn_form_search")).click();
        System.out.println("3 - Clicou na lupa");
    }

    @Entao("^vejo a lista de resultados para o curso \"([^\"]*)\"$")
    public void vejoAListaDeResultadosParaOCurso(String Curso) {
        assertEquals(driver.findElement(By.cssSelector("h3")).getText(), "Cursos › \"" + Curso + "\"");
        System.out.println("4 - Exibiu a lista de resultados para o curso " + Curso);
    }

    @Quando("^clico em Matricule-se$")
    public void clicoEmMatriculeSe() {
        driver.findElement(By.cssSelector("span.comprar")).click();
        System.out.println("5 - Clicou em Matricule-se");
    }

    @Entao("^confirmo o nome do curso como \"([^\"]*)\" e o preco do curso de \"([^\"]*)\"$")
    public void confirmoONomeDoCursoComoEOPrecoDoCursoDe(String Curso, String Preco) {
        assertEquals(driver.findElement(By.cssSelector("span.item-title")).getText(), Curso);
        assertEquals(driver.findElement(By.cssSelector("span.new-price")).getText(), Preco);
        System.out.println("6 - Confirmou o nome como " + Curso + " e o preco do curso como " + Preco);
    }
}

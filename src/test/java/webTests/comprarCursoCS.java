package webTests;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class comprarCursoCS {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void iniciar(){
        System.setProperty("webdriver.chrome.driver", "drivers/chrome/92/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1600, 833));
        driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS);

        wait = new WebDriverWait(driver,60,1); //espera até 60 segundos

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
    public void pesquisoPor(String curso) {
        driver.findElement(By.id("searchtext")).click();
        driver.findElement(By.id("searchtext")).clear();
        driver.findElement(By.id("searchtext")).sendKeys(Keys.chord(curso));
        System.out.println("2 - Digitou o nome do curso " + curso);
    }

    @E("^clico na lupa$")
    public void clicoNaLupa() {
        driver.findElement(By.id("btn_form_search")).click();
        System.out.println("3 - Clicou na lupa");
    }

    @Entao("^vejo a lista de resultados para o curso \"([^\"]*)\"$")
    public void vejoAListaDeResultadosParaOCurso(String curso) {
        String textoEsperado = "Cursos › \"" + curso + "\"";
        //WebElement h3texto = driver.findElement(By.cssSelector("h3"));

        wait.until(ExpectedConditions.textToBe(By.cssSelector("h3"), textoEsperado));
        assertEquals(driver.findElement(By.cssSelector("h3")).getText(), "Cursos › \"" + curso + "\"");
        System.out.println("4 - Exibiu a lista de resultados para o curso " + curso);
    }

    @Quando("^clico em Matricule-se$")
    public void clicoEmMatriculeSe() {
        driver.findElement(By.cssSelector("span.comprar")).click();
        System.out.println("5 - Clicou em Matricule-se");
    }

    @Entao("^confirmo o nome do curso como \"([^\"]*)\" e o preco do curso de \"([^\"]*)\"$")
    public void confirmoONomeDoCursoComoEOPrecoDoCursoDe(String curso, String preco) {

        wait.until(ExpectedConditions.textToBe(By.cssSelector("span.item-title"),curso));
        assertEquals(driver.findElement(By.cssSelector("span.item-title")).getText(), curso);
        assertEquals(driver.findElement(By.cssSelector("span.new-price")).getText(), preco);
        System.out.println("6 - Confirmou o nome como " + curso + " e o preco do curso como " + preco);
    }

    @E("^clico pressiono Enter$")
    public void clicoPressionoEnter() {
        driver.findElement(By.id("searchtext")).sendKeys(Keys.ENTER);
        System.out.println("3a - Pressionou Enter");
    }

    @Quando("^clico na imagem de um curso$")
    public void clicoNaImagemDeUmCurso() throws InterruptedException {
        //driver.findElement(By.cssSelector("span.mais")).click();
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("body.flat-theme:nth-child(2) div.container.lis_loja:nth-child(2) div.initial-cursos div.row.lis_loja:nth-child(3) div.col-md-12 div.owl-carousel.owl-theme.course-list.lis_produtos.owl-loaded.owl-drag div.owl-stage-outer div.owl-stage div.owl-item.active:nth-child(3) div.item-plan.item.item-course a:nth-child(2) > span.comprar")).click();
        //WebElement saibaMais = driver.findElement(By.xpath("//body[1]/main[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/a[1]"));

        WebElement ele = driver.findElement(By.xpath("/html[1]/body[1]/main[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/a[1]"));
        Actions action = new Actions(driver);
        action.moveToElement(ele).perform();
        System.out.println("3 - Clicou no curso");
    }

    @Entao("^vejo a pagina com detalhes do curso$")
    public void vejoAPaginaComDetalhesDoCurso() {
        wait.until(ExpectedConditions.titleIs("Mantis - Iterasys"));
        assertEquals(driver.getTitle(),"Mantis - Iterasys");
    }

    @E("^clico no botao OK do popup da LGPD$")
    public void clicoNoBotaoOKDoPopupDaLGPD() {
        driver.findElement(By.cssSelector("a.cc-btn.cc-dismiss")).click();
    }
}

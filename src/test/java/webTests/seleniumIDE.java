package webTests;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class seleniumIDE {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @BeforeMethod
    public void iniciar() {
        System.setProperty("webdriver.chrome.driver", "drivers/chrome/90/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS);
    }

    @AfterMethod
    public void finalizar() {
        driver.quit();
    }
    @Test
    public void consultarCurso() {
        driver.get("https://iterasys.com.br/");
        driver.manage().window().setSize(new Dimension(1600, 833));
        driver.findElement(By.id("searchtext")).click();
        driver.findElement(By.id("searchtext")).sendKeys("Mantis");
        driver.findElement(By.cssSelector(".fa-search")).click();
        assertThat(driver.findElement(By.cssSelector("h3")).getText(), is("Cursos ? \\\"Mantis\\\""));
        assertThat(driver.findElement(By.cssSelector(".comprar")).getText(), is("MATRICULE-SE  "));
    }
    @Test
    public void consultarCurso2() {
        driver.get("https://iterasys.com.br/");
        driver.manage().window().setSize(new Dimension(829, 680));
        driver.findElement(By.id("searchtext")).click();
        driver.findElement(By.id("searchtext")).sendKeys("testlink");
        driver.findElement(By.cssSelector(".fa-search")).click();
        assertThat(driver.findElement(By.cssSelector("h3")).getText(), is("Cursos ? \\\"testlink\\\""));
        assertThat(driver.findElement(By.cssSelector(".comprar")).getText(), is("MATRICULE-SE  "));
    }
}


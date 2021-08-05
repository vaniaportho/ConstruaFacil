package stepsPO;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Hooks {

    @Before
    public void iniciarPO (){
        System.out.println("Passo 0");
    }

    @After
    public void finalizarPO (){
        System.out.println("Passo Z");
    }

}

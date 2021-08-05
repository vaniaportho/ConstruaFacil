package stepsPO;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
        features   = {"src/test/resources/featuresPO"}, //onde estão os cenários em Gherkin
        glue       = {"stepsPO"},                       //onde estão as definições de passos
        dryRun     = false,                            //Exibição de log
        monochrome = true,                              //Detalhes do log
        plugin     ={
                        "pretty",                      //formatação visual do Cucumber
                        "html:target/reports/extentereports",     //saida HTML do relatório simples
                        "json:target/reports/extentreports.json",  // saida json com os dados
                        //"com.cucumber.listener.ExtentCucumberFormater:target/reports/dashboard.html"
}

)

public class Runner extends AbstractTestNGCucumberTests {


}

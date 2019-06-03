package desafio;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import suporte.Generator;
import suporte.Screenshot;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class TestarLivroTest {
    private WebDriver navegador;
    public String autordolivro = " ";
    public String isbn = " ";
    public String screenshotArquivo = " ";

    @Rule
    //Regra para pegar o nome do metódo
    public TestName test = new TestName();


    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tiago\\Documents\\Drivers\\chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Navegar para pagina do Submarino
        navegador.get("https://www.submarino.com.br/");

        //Localizar o primeiro livro da pagina que esta na div de id="content-top"
        WebElement localizarprimeirolivro = navegador.findElement(By.id("content-top"));

        //Clicar no primeiro livro da página que possui o link href="https://www.submarino.com.br/produto/132517001"
        localizarprimeirolivro.findElement(By.xpath("//a[@href='https://www.submarino.com.br/produto/132517001']")).click();

        //Ao entrar na página do livro localizar o autor
        WebElement localizarautor = navegador.findElement(By.xpath("//*[@id=\"info-section\"]/div[2]/section/div/div[3]/table/tbody/tr[3]/td[2]/span"));

        //Localizar o nome autor
        autordolivro = localizarautor.getText();
        System.out.println("Autor:" + autordolivro); // Para verificar o nome do autor

        //Tirar printsreen da tela
        String screenshotArquivo = "C:\\Users\\tiago\\Documents\\Test-Report\\Testar_Livros\\" + Generator.dataHoraParaArquivo() + test.getMethodName() + ".png";
        Screenshot.tirar(navegador,screenshotArquivo);

        //Localizar e armazenar o ISBN do livro.
        WebElement localizarisbn = navegador.findElement(By.xpath("//*[@id=\"info-section\"]/div[2]/section/div/div[3]/table/tbody/tr[4]/td[2]/span"));
        isbn = localizarisbn.getText();
        System.out.println("ISBN: " + isbn);

        //Tirar printsreen da tela
        screenshotArquivo = "C:\\Users\\tiago\\Documents\\Test-Report\\Testar_Livros\\" + Generator.dataHoraParaArquivo() + test.getMethodName() + ".png";
        Screenshot.tirar(navegador,screenshotArquivo);

    }

    @Test
    public void testarlivroamazon() {

        navegador.get("https://www.amazon.com.br/");
        //Inseri o ISBN no campo de busca com id="twotabsearchtextbox"
        WebElement campobuscaamazon = navegador.findElement(By.id("twotabsearchtextbox"));
        campobuscaamazon.sendKeys(isbn);

        //Clicar no botão pesquisar de xpath: //*[@id="nav-search"]/form/div[2]/div/input
        WebElement buscalivroamazon = navegador.findElement(By.xpath("//*[@id=\"nav-search\"]/form/div[2]/div/input"));
        buscalivroamazon.click();

        //Clicar no livro de xpath: //*[@id="search"]/div[1]/div[2]/div/span[3]/div[1]/div[1]/div/div/div/div[2]/div[1]/div/div/span/a/div/img
        WebElement clicarlivroamazon = navegador.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[2]/div/span[3]/div[1]/div[1]/div/div/div/div[2]/div[1]/div/div/span/a/div/img"));
        clicarlivroamazon.click();

        //Localizar nome do autor através do xpath: //*[@id="bylineInfo"]/span[1]/a
        WebElement localizarautoramazon = navegador.findElement(By.xpath("//*[@id=\"bylineInfo\"]/span[1]/a"));
        String nomeautoramazon = localizarautoramazon.getText();

        //Validar nome do autor
        assertEquals(autordolivro, nomeautoramazon);

        //Tirar printsreen da tela
        screenshotArquivo = "C:\\Users\\tiago\\Documents\\Test-Report\\Testar_Livros\\" + Generator.dataHoraParaArquivo() + test.getMethodName() + ".png";
        Screenshot.tirar(navegador,screenshotArquivo);
    }

    @Test
    public void testarlivroamericanas() {
        //Acessar Americanas.com
        navegador.get("https://www.americanas.com.br/");
        //Inseri o ISBN no campo de busca id="h_search-input"
        WebElement campobuscaamericanas = navegador.findElement(By.id("h_search-input"));
        campobuscaamericanas.sendKeys(isbn);

        //Clicar no botão pesquisar id="h_search-btn"
        WebElement buscalivroamericanas = navegador.findElement(By.id("h_search-btn"));
        buscalivroamericanas.click();

        //Clicar no livro //*[@id="content-middle"]/div[5]/div/div/div/div[1]/div/div/div[2]/a/section/div[1]/div/div/picture/img
        WebElement clicarlivroamericanas = navegador.findElement(By.xpath("//*[@id=\"content-middle\"]/div[5]/div/div/div/div[1]/div/div/div[2]/a/section/div[1]/div/div/picture/img"));
        clicarlivroamericanas.click();

        //Localizar nome do autor
        WebElement localizarautoramericanas = navegador.findElement(By.xpath("//*[@id=\"info-section\"]/div[2]/section/div/div[3]/table/tbody/tr[12]/td[2]/span"));
        String nomeautoramericanas = localizarautoramericanas.getText();

        //Validar nome do autor
        assertEquals(autordolivro, nomeautoramericanas);

        //Tirar printsreen da tela
        screenshotArquivo = "C:\\Users\\tiago\\Documents\\Test-Report\\Testar_Livros\\" + Generator.dataHoraParaArquivo() + test.getMethodName() + ".png";
        Screenshot.tirar(navegador,screenshotArquivo);

    }

    @After
    public void tearDown() {
        //Fechar o navegador
        navegador.quit();
    }

}
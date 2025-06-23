import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClienteCadastroSeleniumTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://localhost:4200/"); // ou a URL da tela de cadastro
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void deveCadastrarClienteComSucesso() throws InterruptedException {
        driver.findElement(By.id("cliNome")).sendKeys("GameOnTeste");
        driver.findElement(By.id("cliCpf")).sendKeys("12332112332");
        driver.findElement(By.id("endRua")).sendKeys("Rua Sistema Gamer");
        driver.findElement(By.id("endNumero")).sendKeys("195");
        driver.findElement(By.id("endCidade")).sendKeys("Cidade Gamer");
        driver.findElement(By.id("endCep")).sendKeys("12345-122");
        driver.findElement(By.id("endEstado")).sendKeys("RJ");
        driver.findElement(By.id("conCelular")).sendKeys("98457-2314");
        driver.findElement(By.id("conTelefoneComercial")).sendKeys("3333-4444");
        driver.findElement(By.id("conEmail")).sendKeys("GameOnTeste@email.com");

        driver.findElement(By.id("btnSalvar")).click();

        Thread.sleep(3000); // use WebDriverWait se possível

        WebElement mensagem = driver.findElement(By.id("Aprovado!!"));
        assertTrue(mensagem.isDisplayed());
    }
}

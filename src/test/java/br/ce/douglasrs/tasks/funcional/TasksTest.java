package br.ce.douglasrs.tasks.funcional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	
	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = acessarAplicacao();
		
		//clicar em add todo
		driver.findElement(By.id("addTodo")).click();
		
		//escrever no campo task
		driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
		
		//escrever data
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
		
		//clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		//verificar a mensagem de retorno
		String mensagem = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Success!", mensagem);
		
		//fechar o driver
		driver.quit();
	}
	
	@Test
	public void deveRetornarErroDataInvalida() {
		WebDriver driver = acessarAplicacao();
		
		try {
			//clicar em add todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever no campo task
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//escrever data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//verificar a mensagem de retorno
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", mensagem);	
		} finally {
			//fechar o driver
			driver.quit();
		}
	}
		
	@Test
	public void deveRetornarErroSemDescricao() {
		WebDriver driver = acessarAplicacao();
		
		try {
			//clicar em add todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//verificar a mensagem de retorno
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", mensagem);	
		} finally {
			//fechar o driver
			driver.quit();
		}

	}

	@Test
	public void deveRetornarErroPorFaltaDeData() {
		WebDriver driver = acessarAplicacao();
		
		//clicar em add todo
		driver.findElement(By.id("addTodo")).click();
		
		//escrever no campo task
		driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
		
		//escrever data
		//driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
		
		//clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		//verificar a mensagem de retorno
		String mensagem = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the due date", mensagem);
		
		//fechar o driver
		driver.quit();
	}
		
		
}

package Login;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.mashape.unirest.http.exceptions.UnirestException;

import API.Discord;
import Consultar.Buscar;
import FTP.FtpClient;


public class Login {
	
	public static WebDriver driver;	
	private static ArrayList<String> tabs;
	private static Robot robo;
	private static String captcha;
	private static HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
	private static String downloadFilepath = System.getProperty("user.dir");
	public static String arquivo;
	
	public Login() throws UnirestException {
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.setExperimentalOption("prefs", chromePrefs);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		Discord discord = new Discord();
	}
	
	
	public static void Logar() throws InterruptedException, AWTException, IOException, UnirestException {
		File clear = new File("nomes.txt");
		PrintWriter clearNomes = new PrintWriter(clear);
		clearNomes.write("");
		clearNomes.close();
		driver.get("http://notcertiptu.prefeitura.sp.gov.br/PaginasRestritas/frm001_Gerar_Notif_Lanc.aspx");
		Thread.sleep(5000);  
		Captcha();
		try {
		 captcha = Discord.Run();
		}catch(Exception e) {
			System.out.println("ERRO -> O Robô foi finalizado por algum erro!");
		}
		WebElement cpf = driver.findElement(By.id("formBody_txtUser"));
		
		cpf.sendKeys("");
		Thread.sleep(500);
		
		WebElement senha = driver.findElement(By.id("formBody_txtPassword"));
		
		senha.sendKeys("");
		Thread.sleep(500);
		WebElement captchaInserir = driver.findElement(By.id("txtValidacao"));
		captchaInserir.sendKeys(captcha);

		Thread.sleep(10000);
		driver.findElement(By.id("formBody_Button1")).click();
		Buscar.Busca();
		
	
		//BUTTON
		
	}
	
	public static void Captcha() throws IOException, InterruptedException, AWTException {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open('');");

		Thread.sleep(2000);
		tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://loginsenhaweb.prefeitura.sp.gov.br/user_control/pgImagem.ashx");
		Thread.sleep(1000);
		
		
		js.executeScript("document.body.style.zoom = '800%';");
		Thread.sleep(2000);
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		arquivo = new Random().nextInt(Integer.MAX_VALUE) +"captcha.png";
		FileUtils.copyFile(scrFile, new File(arquivo));
		driver.close();
		driver.switchTo().window(tabs.get(0));
		FtpClient ftp = new FtpClient();
		ftp.dir("captcha");
		ftp.UploadFile(arquivo);
		scrFile.delete();
		File arquivoDelete = new File(arquivo);
		arquivoDelete.delete();
       
    }
	
	public static void quit() {
		driver.quit();
	}
		
	}


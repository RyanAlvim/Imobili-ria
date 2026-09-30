package Consultar;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.mashape.unirest.http.exceptions.UnirestException;

import Assertiva.Assertiva;
import Login.Login;
import PDF.Reader;

public class Buscar {
	
	
	public static void Busca() throws InterruptedException, IOException, AWTException, UnirestException {
		
		try {
		
		Scanner in = new Scanner(new FileReader("cadastroImovel.txt"));
		while (in.hasNextLine()) {
			Login.driver.get("http://notcertiptu.prefeitura.sp.gov.br/PaginasRestritas/frm001_Gerar_Notif_Lanc.aspx");
			Thread.sleep(3000);
		    String line = in.nextLine();
		    String[] cadImovel = line.split("-");
			WebElement cadastroImovel = Login.driver.findElement(By.id("txt_SQL"));
			cadastroImovel.sendKeys(cadImovel[0]+cadImovel[1]);
			Thread.sleep(1000);
			WebElement data = Login.driver.findElement(By.id("txt_Exercicio"));
			data.sendKeys("2022");
			Thread.sleep(1000);
			Login.driver.findElement(By.id("btnConsultar")).click();
			Thread.sleep(1000);
			Reader.LerPdf();
			try {
				Login.driver.findElement(By.id("btnGerar")).click();
				Thread.sleep(10000);
			}catch(Exception e) {
				System.out.println("Não tem esse Imóvel: ");
			}
		}
		Login.driver.close();
		Assertiva assertiva = new Assertiva();
		assertiva.AssertivaDados();
	}catch(Exception e) {
		Login.driver.quit();
	}
	}

}

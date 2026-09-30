package PDF;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


public class Reader {
	
	public static String cpfAssertiva;
	public static HashMap<String, String> cpf_Endereco = new HashMap<String, String>();
	
	public static void LerPdf() {
		try {
			Thread.sleep(10000);
			File file = new File("NotificacaoLancamento.pdf");
			PDDocument document = PDDocument.load(file);
			PDFTextStripper pdfStripper = new PDFTextStripper();
			String text = pdfStripper.getText(document);

			String cpfSplit[] = text.split("CPF");
			String cpfSemtratar = cpfSplit[1];
			String cpfSplit2[] = cpfSemtratar.split("\n");
			cpfAssertiva = cpfSplit2[0];
						
			String replaceAll = cpfAssertiva.replaceAll(" ", "");
			System.out.println("CPF: " + replaceAll);
			cpf_Endereco.put(replaceAll, replaceAll);
			System.out.println(replaceAll);
			document.close();
			file.delete();
			//file.delete();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

package Email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	
	
	

	public static void Enviar(String emailCorretor, String titulo, String mensagem) {
		
		final String email = "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>\n"
				+ "    <html xmlns='http://www.w3.org/1999/xhtml'>\n"
				+ "      <head>\n"
				+ "        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />\n"
				+ "        <meta name='viewport' content='width=device-width, initial-scale=1.0'/>\n"
				+ "        <style>\n"
				+ "        table, td, div, h1, p {font-family: Roboto, sans-serif;}\n"
				+ "        </style>\n"
				+ "        <link rel='preconnect' href='https://fonts.googleapis.com'>\n"
				+ "        <link rel='preconnect' href='https://fonts.gstatic.com' crossorigin>\n"
				+ "        <link href='https://fonts.googleapis.com/css2?family=Roboto:wght@400&display=swap' rel='stylesheet'>\n"
				+ "        <link href='https://fonts.googleapis.com/css2?family=Hubballi&display=swap' rel='stylesheet'>\n"
				+ "      </head>\n"
				+ "    <body style='margin: 0; padding: 0;'>\n"
				+ "     <table border='0' cellpadding='0' cellspacing='0' width='100%'>\n"
				+ "      <tr>\n"
				+ "       <td>\n"
				+ "        <table align='center' border='0' cellpadding='0' cellspacing='0' width='600' style='border-collapse: collapse;border: 0px solid #ccc;border-spacing: 0;'>\n"
				+ "          <tr>\n"
				+ "          <td width='250' valign='middle' style='padding: 0 0 0 35%;'>\n"
				+ "            <center>\n"
				+ "           <img src='http://lopeselite.com/assets/images/lopes.png' width='180'>\n"
				+ "        </center>\n"
				+ "          </td>\n"
				+ "          <td style='font-size: 0; line-height: 0;' width='100'>\n"
				+ "           &nbsp;\n"
				+ "          </td>\n"
				+ "          <td width='250' valign='bottom' style='padding: 0 15px 0 0;'>\n"
				+ "           <p style='font-size:20px;line-height:24px;font-family:Hubballi,cursive;float: right;color: #999;'> <span style='color: #333;font-weight: bold;letter-spacing: 1px;'></span></p>\n"
				+ "          </td>\n"
				+ "        </tr>\n"
				+ "        <tr>\n"
				+ "          <td colspan='3' width='600'>\n"
				+ "            <div style='width: 100%;height: 4px;background: #1b304d;'></div>\n"
				+ "          </td>\n"
				+ "        </tr>\n"
				+ "        <tr>\n"
				+ "          <td colspan='3'style='padding: 0 0 0 20px;'>\n"
				+ "            <p style='font-family: Roboto, sans-serif;font-size: 22px;color: #4d4d4d;margin-bottom: 0;'>" + mensagem + "</p>\n"
				+ "          </td>\n"
				+ "        </tr>\n"
				+ "        \n"
				+ "        <table align='center' border='0' cellpadding='0' cellspacing='0' style='border-collapse: collapse;border: 0px solid #ddd;border-spacing: 0;width: 600px;margin-top: 20px;'>\n"
				+ "          <tr>\n"
				+ "            <td>\n"
				+ "              <p style='padding-left: 20px;font-family: Roboto, sans-serif;color: #333;font-size: 15px;'>Qualquer erro contate um administrador da Lopes</p>\n"
				+ "            </td>\n"
				+ "          </tr>\n"
				+ "        </table>\n"
				+ "        <table align='center' border='0' cellpadding='0' cellspacing='0' style='border-collapse: collapse;border: 0px solid #ddd;border-spacing: 0;width: 600px;font-family: Roboto, sans-serif;background-color: #1b304d;color: white;margin-top: 30px;'>\n"
				+ "          <tr>\n"
				+ "            <td colspan='3'><p style='text-align: center;font-family: Roboto, sans-serif;font-size: 14px;padding: 25px 0px 25px 0px'>Av Ibirapuera, 2033 cj 112<br><br><strong style='font-weight: normal;letter-spacing: 1px;'>(11) 4890-2335</strong></p></td>\n"
				+ "          </tr>\n"
				+ "        </table>\n"
				+ "      </table>\n"
				+ "       </td>\n"
				+ "      </tr>\n"
				+ "     </table>\n"
				+ "    </body>\n"
				+ "    </html>";
		
		/*try {
			
			
			
			Properties properties = new Properties();
			properties.put("mail.smtp.host", "smtp.office365.com");
			properties.setProperty("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.ssl.trust", "smtp.office365.com");
			//properties.setProperty("mail.smtp.port", "587");
			properties.setProperty("mail.smtp.user","");
			properties.setProperty("mail.smtp.auth", "true");
			Session sessao = Session.getDefaultInstance(properties);
			MimeMessage envioMensagem = new MimeMessage(sessao);
			envioMensagem.setFrom(new InternetAddress(""));
			envioMensagem.addRecipient(Message.RecipientType.TO, new InternetAddress(emailCorretor));
			envioMensagem.setSubject(titulo);
			envioMensagem.setText(email,null,"html");
			
			Transport transport = sessao.getTransport("smtp");
			transport.connect("","");
			transport.sendMessage(envioMensagem, envioMensagem.getAllRecipients());
			transport.close();
			System.out.println("Enviado");
		}catch(Exception e) {
			e.printStackTrace();
		}*/
		System.out.println(email);
	}
}

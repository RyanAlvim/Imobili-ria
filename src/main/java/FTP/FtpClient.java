package FTP;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import Login.Login;

public class FtpClient {
	
	private static FTPClient ftp;
	
	public FtpClient() throws SocketException, IOException {
		ftp = new FTPClient();
		ftp.connect( "" );
		ftp.login( "", "" );
	}
	
	
	public static void UploadFile(String file) throws IOException {
		ftp.enterLocalPassiveMode();
	      
        ftp.setFileType(FTP.BINARY_FILE_TYPE);

        File LocalFile = new File(Login.arquivo);

        String remoteFile = Login.arquivo;
        InputStream inputStream = new FileInputStream(LocalFile);

        boolean done = ftp.storeFile(remoteFile, inputStream);
        System.out.println("AQUI: " + done);
        inputStream.close();
        ftp.logout();
		
	}
	
	public static boolean dir(String dir) throws IOException {
		return ftp.changeWorkingDirectory (dir);
	}
	

}

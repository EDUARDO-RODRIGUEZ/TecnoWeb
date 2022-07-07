package Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import org.apache.commons.net.ftp.FTPClient;

public class ClientFtp {

    public static String uploadImage(String typeImage) {
        String server = "mail.tecnoweb.org.bo";
        int port = 21;
        String user = "grupo08sa";
        String pass = "grup008grup008";
        FTPClient ftpClient = new FTPClient();
        LocalDateTime filename = LocalDateTime.now();
        try {
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

            // obtener file local
            File LocalFile = new File(String.format("./tmp.%s", typeImage));

            String firstRemoteFile = String.format("./imagenes/%s.%s", filename, typeImage);
            InputStream inputStream = new FileInputStream(LocalFile);
            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("El archivo se subio correctamente");

            }
        } catch (IOException ex) {
            System.out.println("Error FTP: " + ex.getMessage());
            ex.printStackTrace();
        }

        return filename.toString() + "." + typeImage;
    }
}

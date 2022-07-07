package Service;

import Helper.ComandoSmtp;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClienteSmtp {

    public static String HOST = "mail.tecnoweb.org.bo";
    public static Integer PORT = 25;
    public String domain;

    public ClienteSmtp(String domain) {
        this.domain = domain;
    }

    public void sendMessage(String mailfrom, String rcpto, String subject, String message) {

        System.out.println("*******ClienteSmtp*******");

        try {
            Socket server = new Socket(HOST, PORT);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(server.getInputStream()));
            DataOutputStream salida = new DataOutputStream(server.getOutputStream());
            if (server == null || entrada == null || salida == null) {
                System.out.println("Error de campos null !!!");
                return;
            }
            System.out.println("S: " + entrada.readLine());

            System.out.print("C: " + ComandoSmtp.HELO(domain));
            salida.writeBytes(ComandoSmtp.HELO(domain));
            System.out.println("S: " + entrada.readLine());

            System.out.print("C: " + ComandoSmtp.MAIL_FROM(mailfrom));
            salida.writeBytes(ComandoSmtp.MAIL_FROM(mailfrom));
            System.out.println("S: " + entrada.readLine());

            System.out.print("C: " + ComandoSmtp.RCPT_TO(rcpto));
            salida.writeBytes(ComandoSmtp.RCPT_TO(rcpto));
            System.out.println("S: " + entrada.readLine());

            System.out.print("C: " + ComandoSmtp.DATA());
            salida.writeBytes(ComandoSmtp.DATA());
            System.out.println("S: " + entrada.readLine());

            System.out.print("C: " + ComandoSmtp.SET_DATA(subject, message));
            salida.writeBytes(ComandoSmtp.SET_DATA(subject, message));
            System.out.println("S: " + entrada.readLine());

            System.out.print("C: " + ComandoSmtp.QUIT());
            salida.writeBytes(ComandoSmtp.QUIT());
            System.out.println("S: " + entrada.readLine());

        } catch (Exception e) {
            System.out.println("ClienteSmtp:sendMessage: " + e.getMessage());
        }

        System.out.println("*************************");
    }

    private String getMultiline(BufferedReader in) throws IOException {
        String lines = "";
        while (true) {
            String line = in.readLine();
            if (line == null) {
                throw new IOException(" S : Server unawares closed the connection.");
            }
            if (line.equals(".")) {
                break;
            }
            if ((line.length() > 0) && (line.charAt(0) == '.')) {
                line = line.substring(1);
            }
            lines = lines + "\n" + line;
        }
        return lines;
    }

}

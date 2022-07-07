package Service;

import Helper.ComandoPop;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientePop {

    public static String HOST = "mail.tecnoweb.org.bo";
    public static Integer PORT = 110;
    public static Integer LAST_MESSAGE = 0;
    public static boolean NEWMESSAGE = false;
    public static String MESSAGE = "";

    public String usuario = "grupo08sa";
    public String contraseña = "grup008grup008";
    private DataOutputStream salida;
    private BufferedReader entrada;

    public ClientePop() {
    }

    public ClientePop(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public void start() {

        try {
            Socket server = new Socket(HOST, PORT);
            entrada = new BufferedReader(new InputStreamReader(server.getInputStream()));
            salida = new DataOutputStream(server.getOutputStream());
            if (server == null || entrada == null || salida == null) {
                System.out.println("Error de campos null !!!");
                return;
            }
            System.out.println("S: " + entrada.readLine());

            System.out.print("C: " + ComandoPop.AUTH_USER(usuario));
            salida.writeBytes(ComandoPop.AUTH_USER(usuario));
            System.out.println("S: " + entrada.readLine());

            System.out.print("C: " + ComandoPop.AUTH_PASS(contraseña));
            salida.writeBytes(ComandoPop.AUTH_PASS(contraseña));
            System.out.println("S: " + entrada.readLine());

            System.out.print("C: " + ComandoPop.CANT_MESSAGE());
            salida.writeBytes(ComandoPop.CANT_MESSAGE());
            String countMessage = entrada.readLine();
            System.out.println("S: " + countMessage);
            VerifyNewMessage(countMessage);

            System.out.print("C: " + ComandoPop.CLOSE_CONEXION());
            salida.writeBytes(ComandoPop.CLOSE_CONEXION());
            System.out.println("S: " + entrada.readLine());

        } catch (Exception e) {
            System.out.println("ClientePop: OnMessage" + e.getMessage());
        }
    }

    //"+OK 2 4527"
    // LAST_MESSAGE != 0 : Ignora este if la primera vez ya que carga el ultimo mensaje
    public void VerifyNewMessage(String message) throws IOException {
        String[] splitMessage = message.split(" ");
        Integer lastMessage = Integer.parseInt(splitMessage[1]);
        System.out.println(LAST_MESSAGE);
        System.out.println(lastMessage);

        if (lastMessage > LAST_MESSAGE) {
            if (LAST_MESSAGE != 0) {
                NEWMESSAGE = true;
                loadMessage(lastMessage);
            }
            LAST_MESSAGE = lastMessage;
        }
    }

    private void loadMessage(Integer id_message) throws IOException {
        System.out.println("Load Message");
        System.out.print("C: " + ComandoPop.GET_MESSAGE(id_message));
        salida.writeBytes(ComandoPop.GET_MESSAGE(id_message));
        String messageRecibido = getMultiline(entrada);
        System.out.println(messageRecibido);
        MESSAGE = messageRecibido;
        System.out.println("Nuevo Mensaje Recibido !!!");
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

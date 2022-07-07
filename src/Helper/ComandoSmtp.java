package Helper;

public class ComandoSmtp {

    public static String HELO(String domain) {
        return "HELO " + domain + "\n";
    }

    public static String MAIL_FROM(String correo) {
        return "MAIL FROM: <" + correo + ">\n";
    }

    public static String RCPT_TO(String correo) {
        return "RCPT TO: <" + correo + ">\n";
    }

    public static String DATA() {
        return "DATA\n";
    }

    public static String SET_DATA(String subject, String message) {
        return "Subject:" + subject + "\r\n" + message + "\n" + ".\n";
    }

    public static String QUIT() {
        return "QUIT\n";
    }
}

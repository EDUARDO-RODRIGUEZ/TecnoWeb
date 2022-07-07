package Helper;

public class ComandoPop {

    public static String AUTH_USER(String user) {
        return "USER " + user + "\n";
    }

    public static String AUTH_PASS(String pass) {
        return "PASS " + pass + "\n";
    }

    public static String LIST_MESSAGE() {
        return "LIST\n";
    }

    public static String GET_MESSAGE(Integer id) {
        return "RETR " + id + "\n";
    }

    public static String CLOSE_CONEXION() {
        return "QUIT\n";
    }

    public static String CANT_MESSAGE() {
        return "STAT\n";
    }
}

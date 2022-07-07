package Config;

import Service.ClienteSmtp;
import View.ResponseView;

public class Validation {

    public static boolean isValid(String[] params, int cant, ClienteSmtp clienteSmtp, String correo) {
        if (params.length == cant) {
            return true;
        }
        clienteSmtp.sendMessage(
                "grupo08sa@tecnoweb.org.bo",
                correo,
                String.format("Validacion"),
                ResponseView.ResponseConfirm("Error en el comando debe agregar " + cant + " parametros!!!")
        );
        return false;
    }

}

package Helper;

import java.util.ArrayList;
import java.util.Map;

public class Cadena {

    public static int INDEX = 0;

    public static String getRouteParams(String cadena) {
        String result = "";
        while (INDEX <= cadena.length()) {
            String linea = ReadLine(cadena);
            if (linea.contains("Subject")) {
                if (linea.contains("(") && linea.contains(")")) {
                    result = linea;
                    break;
                }
                String params = ReadLine(cadena);
                result = linea + params;
                break;
            }
        }
        INDEX = 0;
        return result;
    }

    public static void Search(String cadena, ArrayList<String> search, Map map) {

        while (INDEX <= cadena.length()) {
            String linea = ReadLine(cadena);
            String delete = "";
            for (String element : search) {
                if (linea.contains(element)) {
                    map.put(element, linea);
                    delete = element;
                    break;
                }
            }

            if (delete.length() > 0) {
                search.remove(delete);
                delete = "";
            }

            if (search.size() == 0) {
                break;
            }
        }

        INDEX = 0;
    }

    public static String ReadLine(String cadena) {

        String linea = "";
        while (INDEX <= cadena.length()) {
            if (INDEX >= cadena.length() || cadena.charAt(INDEX) == '\n') {
                INDEX++;
                break;
            }
            linea += cadena.charAt(INDEX);
            INDEX++;
        }

        return linea;
    }

    /*Metodos para imagen*/
    private static void SaltarLineas(int saltos, String cadena) {
        for (int i = 0; i < saltos; i++) {
            ReadLine(cadena);
        }
    }

    private static String obtenerFileBase64(String cadena) {
        String filebase64 = "";
        while (true) {
            String linea = ReadLine(cadena);
            if (linea.contains("--=")) {
                break;
            }
            filebase64 += linea;
        }
        return filebase64;
    }

    private static String obtenerTypeImage(String linea) {
        String type = "png";

        if (linea.contains("jpg")) {
            type = "jpg";
        } else if (linea.contains("jpeg")) {
            type = "jpeg";
        }

        return type;
    }

    public static void getImage(String cadena, Map map) {
        while (INDEX <= cadena.length()) {
            String linea = ReadLine(cadena);
            if (linea.contains("image")) {
                map.put("type", obtenerTypeImage(linea));
                SaltarLineas(5, cadena);
                map.put("image", obtenerFileBase64(cadena));
                break;
            }
        }
        INDEX = 0;
    }

    public static void getPdf(String cadena, Map map) {
        while (INDEX <= cadena.length()) {
            String linea = ReadLine(cadena);
            if (linea.contains("application/pdf")) {
                SaltarLineas(5, cadena);
                map.put("pdf", obtenerFileBase64(cadena));
                break;
            }
        }
        INDEX = 0;
    }

}

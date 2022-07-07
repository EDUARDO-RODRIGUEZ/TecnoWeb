package Helper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class FileManagement {

    public static void saveFile(String base64, String typeFile) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(base64);
            Files.write(Paths.get(String.format("./tmp.%s", typeFile)), decodedBytes);
        } catch (Exception e) {
            System.out.println("error save file :" + e.getMessage());
        }
    }

    public static void removeFile(String typeImage) {
        File fichero = new File(String.format("./tmp.%s", typeImage));
        if (fichero.delete()) {
            System.out.println("El acrhivo se borro satisfactoriamente");
        } else {
            System.out.println("El archivo no se pudo borrar");
        }
    }

}

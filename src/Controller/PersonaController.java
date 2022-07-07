package Controller;

import Entity.EntityPersona;
import Helper.Cadena;
import Helper.FileManagement;
import Model.Persona;
import Service.ClientFtp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PersonaController {

    /*Datos obtenidos de la base de datos*/
    public static List<EntityPersona> getLike(String word) {
        Persona persona = new Persona();
        List<EntityPersona> data = persona.getLike(word);
        persona.close();
        return data;
    }

    public static List<String> getPrueba(String message) {
        HashMap map = new HashMap();
//        Subir  Imagen        
//        Cadena.getImage(message, map);
//        String typeImage = (String) map.get("type");
//        String imageBase64 = (String) map.get("image");
//        FileManagement.saveFile(imageBase64, typeImage);
//        ClientFtp.uploadImage(typeImage);
//        FileManagement.removeFile(typeImage);

//        Subir  Pdf
//        Cadena.getPdf(message, map);
//        String pdfBase64 = (String) map.get("pdf");
//        FileManagement.saveFile(pdfBase64, "pdf");
//        ClientFtp.uploadImage("pdf");
//        FileManagement.removeFile("pdf");
        return Arrays.asList("eduardo", "ed@ed.com", "1234545");
    }

}

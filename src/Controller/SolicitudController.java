package Controller;

import Entity.RespSolicitud;
import Helper.Cadena;
import Helper.FileManagement;
import Model.Solicitud;
import Service.ClientFtp;
import java.util.HashMap;
import java.util.List;

public class SolicitudController {

    /*SENDSOLICITUD (codigo,idmateria,idconv)*/
    public static String sendSolicitud(String[] params, String message) {
        String codigoEst = params[0];
        int idMateria = Integer.parseInt(params[1]);
        int idconv = Integer.parseInt(params[2]);

        /*---------------Upload  Pdf--------------*/
        HashMap map = new HashMap();
        Cadena.getPdf(message, map);
        String pdfBase64 = (String) map.get("pdf");
        FileManagement.saveFile(pdfBase64, "pdf");
        String filename = ClientFtp.uploadImage("pdf");
        FileManagement.removeFile("pdf");
        /*---------------------------------------*/

        Solicitud solicitud = new Solicitud();
        String response = solicitud.sendSolicitud(codigoEst, idMateria, idconv, filename);
        solicitud.close();
        return response;
    }

    /*SENDRESPSOL (codigoe,aceptado,notaac,idconv,idmateria)*/
    public static String SendResponseSolicitud(String[] params) {
        String codigoe = params[0];
        boolean aceptado = Boolean.parseBoolean(params[1]);
        double notaac = Double.parseDouble(params[2]);
        int idconv = Integer.parseInt(params[3]);
        int idmateria = Integer.parseInt(params[4]);
        Solicitud solicitud = new Solicitud();
        String response = solicitud.SendResponseSolicitud(codigoe, aceptado, notaac, idconv, idmateria);
        solicitud.close();
        return response;
    }

    //SHOWALLSOL (pinicio,pfin)
    public static List<RespSolicitud> ShowAllSolicitudes(String[] params) {
        String pinicio = params[0];
        String pfin = params[1];
        Solicitud solicitud = new Solicitud();
        List<RespSolicitud> AllSolicitudes = solicitud.ShowAllSolicitudes(pinicio, pfin);
        solicitud.close();
        return AllSolicitudes;
    }
}

package Controller;

import Entity.RespEstudianteAceptado;
import Entity.RespMateriaEvaluar;
import Entity.RespCalificacionEstudiante;
import Entity.RespDocente;
import Helper.Cadena;
import Helper.FileManagement;
import Model.Docente;
import Service.ClientFtp;
import java.util.HashMap;
import java.util.List;

public class DocenteController {

    /*SHOWMATEVALUAR (pinicio,pfin,codigod)*/
    public static List<RespMateriaEvaluar> ShowMateriasEvaluar(String[] params) {
        String pinicio = params[0];
        String pfin = params[1];
        String codigod = params[2];
        Docente docente = new Docente();
        List<RespMateriaEvaluar> MateriasEvaluar = docente.ShowMateriasEvaluar(pinicio, pfin, codigod);
        docente.close();
        return MateriasEvaluar;
    }

    /*SHOWESTUDIANTESACEPT(idconv,idmateria)*/
    public static List<RespEstudianteAceptado> ShowEstudiantesAceptados(String[] params) {
        int idconvocatoria = Integer.parseInt(params[0]);
        int idmateria = Integer.parseInt(params[1]);
        Docente docente = new Docente();
        List<RespEstudianteAceptado> EstudiantesAceptados = docente.ShowEstudiantesAceptados(idconvocatoria, idmateria);
        docente.close();
        return EstudiantesAceptados;
    }

    //1990-02-10
    //17:40
    /*ADDINFOEXAMEN (codigod,idmateria,idconv,fecha,hora,lugar)*/
    public static String AgregarInfoExamen(String[] params) {
        String codigod = params[0];
        int idmateria = Integer.parseInt(params[1]);
        int idconv = Integer.parseInt(params[2]);
        String fecha = params[3];
        String hora = params[4];
        String lugar = params[5];
        Docente docente = new Docente();
        String message = docente.AgregarInfoExamen(codigod, idmateria, idconv, fecha, hora, lugar);
        docente.close();
        return message;
    }

    /*SHOWCALIFESTUDIANTE (codigod,idmateria,convocatoria)*/
    public static List<RespCalificacionEstudiante> ShowCalificacionEstudiante(String[] params) {
        String codigod = params[0];
        int idmateria = Integer.parseInt(params[1]);
        int conovcatoria = Integer.parseInt(params[2]);
        Docente docente = new Docente();
        List<RespCalificacionEstudiante> CalificacionesEstudiante = docente.ShowCalificacionEstudiante(conovcatoria, idmateria, codigod);
        docente.close();
        return CalificacionesEstudiante;
    }

    /*ADDCALIFICACION (codigod,idmateria,idconvocatoria,codigoe,notac,notap)*/
    public static String ADDCALIFICACION(String[] params) {

        boolean update = false;

        if (params.length == 7) {
            update = Boolean.parseBoolean(params[6]);
        }

        String codigod = params[0];
        int idmateria = Integer.parseInt(params[1]);
        int idconvocatoria = Integer.parseInt(params[2]);
        String codigoe = params[3];
        double notac = Double.parseDouble(params[4]);
        double notap = Double.parseDouble(params[5]);

        Docente docente = new Docente();
        String response = "";
        if (update) {
            response = docente.ActualizarCalificacion(codigod, idmateria, idconvocatoria, codigoe, notac, notap);
        } else {
            response = docente.AgregarCalificacion(codigod, idmateria, idconvocatoria, codigoe, notac, notap);
        }
        docente.close();
        return response;
    }

    /*UPLOADEXAMEN (codigod,idmateria,idconvocatoria)*/
    public static String UploadExamen(String[] params, String message) {
        String codigod = params[0];
        int idmateria = Integer.parseInt(params[1]);
        int idconvocatoria = Integer.parseInt(params[2]);

        /*---------------Upload  Pdf--------------*/
        HashMap map = new HashMap();
        Cadena.getPdf(message, map);
        String pdfBase64 = (String) map.get("pdf");
        FileManagement.saveFile(pdfBase64, "pdf");
        String filename = ClientFtp.uploadImage("pdf");
        FileManagement.removeFile("pdf");
        /*---------------------------------------*/

        Docente docente = new Docente();
        String response = docente.UploadExamen(filename, codigod, idmateria, idconvocatoria);
        docente.close();
        return response;
    }

    /*CREATEDOCENTE_(codigod,nombre,apellido,email,pass)*/
    public static String CreateDocente(String[] params) {
        String codigoe = params[0];
        String nombre = params[1];
        String apellido = params[2];
        String email = params[3];
        String pass = params[4];
        Docente docente = new Docente();
        String response = docente.CrearDocente(codigoe, nombre, apellido, email, pass);
        docente.close();
        return response;
    }

    /*SHOWDOCENTE_()*/
    public static List<RespDocente> ShowAllDocente() {
        Docente docente = new Docente();
        List<RespDocente> AllDocente = docente.ShowAllDocente();
        docente.close();
        return AllDocente;
    }

    /*ADDEVALUADOR_(codigod,idmateria,idconvocatoria)*/
    public static String AddEvaluador(String[] params) {
        String codigod = params[0];
        int idmateria = Integer.parseInt(params[1]);
        int idconvocatoria = Integer.parseInt(params[2]);
        Docente docente = new Docente();
        String response = docente.AgregarEvaluador(codigod, idmateria, idconvocatoria);
        docente.close();
        return response;
    }
}

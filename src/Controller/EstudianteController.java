package Controller;

import Entity.RespCalificacionMateria;
import Entity.RespMateriaPostulada;
import Model.Estudiante;
import java.util.List;

public class EstudianteController {

    /*SHOWMATPOST (pinicio,pfin,codigoe)*/
    public static List<RespMateriaPostulada> ShowMateriaPostuladas(String[] params) {
        String pinicio = params[0];
        String pfin = params[1];
        String codigoe = params[2];
        Estudiante estudiante = new Estudiante();
        List<RespMateriaPostulada> materiasPostuladas = estudiante.getMateriasPostulada(pinicio, pfin, codigoe);
        estudiante.close();
        return materiasPostuladas;
    }

    /*SHOWCALIFTMAT(codigoe,idmtaeria,idconvocatoria)*/
    public static List<RespCalificacionMateria> ShowCalificacionMateria(String[] params) {
        String codigoe = params[0];
        int idmateria = Integer.parseInt(params[1]);
        int idconvocatoria = Integer.parseInt(params[2]);
        Estudiante estudiante = new Estudiante();
        List<RespCalificacionMateria> CalificacionesMateria = estudiante.ShowCalificacionesMateria(codigoe, idmateria, idconvocatoria);
        estudiante.close();
        return CalificacionesMateria;
    }

    /*CREATEESTUDIANTE_(codigoe,nombre,apellido,email,pass)*/
    public static String CreateEstudiante(String[] params) {
        String codigoe = params[0];
        String nombre = params[1];
        String apellido = params[2];
        String email = params[3];
        String pass = params[4];
        Estudiante estudiante = new Estudiante();
        String response = estudiante.CreateEstudiante(codigoe, nombre, apellido, email, pass);
        estudiante.close();
        return response;
    }

    /*UPDATEESTUDIANTE_(codigoe,nombre,apellido,email,pass)*/
    /*UPDATEDOCENTE_(codigoe,nombre,apellido,email,pass)*/
    public static String UpdateUsuario(String[] params) {
        String codigoe = params[0];
        String nombre = params[1];
        String apellido = params[2];
        String email = params[3];
        String pass = params[4];
        Estudiante estudiante = new Estudiante();
        String response = estudiante.UpdateUsuario(codigoe, nombre, apellido, email, pass);
        estudiante.close();
        return response;
    }

    /*CREATEAUXILIAR_(codigoe,telefono)*/
    public static String CreateAuxiliar(String[] params) {
        String codigoe = params[0];
        String telefono = params[1];
        Estudiante estudiante = new Estudiante();
        String response = estudiante.CreateAuxiliar(codigoe, telefono);
        estudiante.close();
        return response;
    }

}

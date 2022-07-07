package Controller;

import Entity.RespActividad;
import Model.Actividad;
import java.util.List;

public class ActividadController {

    /*SHOWACTIVIDAD_(idcronograma)*/
    public static List<RespActividad> ShowActividad(String[] params) {
        int idcronograma = Integer.parseInt(params[0]);
        Actividad actividad = new Actividad();
        List<RespActividad> AllActividades = actividad.ShowAllActividades(idcronograma);
        actividad.close();
        return AllActividades;
    }

    /*CREATEACTIVIDAD_(idcronograma,nombre,fecha)*/
    public static String CreateActividad(String[] params) {
        int idcronograma = Integer.parseInt(params[0]);
        String nombre = params[1];
        String fecha = params[2];
        Actividad actividad = new Actividad();
        String response = actividad.CrearActividad(nombre, fecha, idcronograma);
        actividad.close();
        return response;
    }

}

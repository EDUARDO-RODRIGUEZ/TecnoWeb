package Controller;

import Entity.RespConvocatoria;
import Model.Convocatoria;
import java.util.List;

public class ConvocatoriaController {

    /*SHOWCONV_()*/
    public static List<RespConvocatoria> ShowAllConvocatoria() {
        Convocatoria convocatoria = new Convocatoria();
        List<RespConvocatoria> AllConvocatoria = convocatoria.ShowAllConvocatoria();
        convocatoria.close();
        return AllConvocatoria;
    }

    /*CREATECONV_(titulo,descripcion,fecha,idperiodo)*/
    public static String CreateConvocatoria(String[] params) {
        String titulo = params[0];
        String descripcion = params[1];
        String fecha = params[2];
        int idperiodo = Integer.parseInt(params[3]);
        Convocatoria convocatoria = new Convocatoria();
        String response = convocatoria.CreateConvocatoriaAux(titulo, descripcion, fecha, idperiodo);
        convocatoria.close();
        return response;
    }

}

package Controller;

import Entity.RespCronograma;
import Model.Cronograma;
import java.util.List;

public class CronogramaController {

    /*SHOWCRONOG_()*/
    public static List<RespCronograma> ShowAllCronograma() {
        Cronograma cronograma = new Cronograma();
        List<RespCronograma> AllCronograma = cronograma.ShowAllCronograma();
        cronograma.close();
        return AllCronograma;
    }

    /*CREATECRONOGRAMA_(fecha,idperiodo)*/
    public static String CreateCronograma(String[] params) {
        String fecha = params[0];
        int idperiodo = Integer.parseInt(params[1]);
        Cronograma cronograma = new Cronograma();
        String response = cronograma.CreateCronograma(fecha, idperiodo);
        cronograma.close();
        return response;
    }
}

package Controller;

import Entity.EntityPeriodo;
import Model.Periodo;
import java.util.List;

public class PeriodoController {

    /*SHOWPERIODO_()*/
    public static List<EntityPeriodo> ShowPeriodo() {
        Periodo periodo = new Periodo();
        List<EntityPeriodo> allPeriodos = periodo.ShowPeriodo();
        periodo.close();
        return allPeriodos;
    }
    /*CREATEPERIODO_(inicio,fin)*/
    public static String CreatePeriodo(String[] params) {
        String inicio = params[0];
        String fin = params[1];
        Periodo periodo = new Periodo();
        String response = periodo.CreatePeriodo(inicio, fin);
        periodo.close();
        return response;
    }

}

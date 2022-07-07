package Controller;

import Entity.EntityGrupo;
import Model.Grupo;
import java.util.List;

public class GrupoControler {

    /*SHOWGRUPO_()*/
    public static List<EntityGrupo> ShowAllGrupo() {
        Grupo grupo = new Grupo();
        List<EntityGrupo> AllGrupo = grupo.ShowGrupo();
        grupo.close();
        return AllGrupo;
    }

    /*CREATEGRUPO_(nombre)*/
    public static String CreateGrupo(String[] params) {
        String nombre = params[0];
        Grupo grupo = new Grupo();
        String response = grupo.CreateGrupo(nombre);
        grupo.close();
        return response;
    }

}

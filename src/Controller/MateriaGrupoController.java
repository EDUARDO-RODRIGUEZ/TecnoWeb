package Controller;

import Entity.RespDocenteMatGrupo;
import Entity.RespMateriaGrupo;
import Model.MateriaGrupo;
import java.util.List;

public class MateriaGrupoController {

    /*SHOWMATGRUPO_()*/
    public static List<RespMateriaGrupo> ShowAllMateriaGrupo() {
        MateriaGrupo mg = new MateriaGrupo();
        List<RespMateriaGrupo> allMateriaGrupo = mg.ShowMateriaGrupo();
        mg.close();
        return allMateriaGrupo;
    }

    /*CREATEMATGRUP_(idmateria,idgrupo,idtipo)*/
    public static String CreateMateriaGrupo(String[] params) {
        int idmateria = Integer.parseInt(params[0]);
        int idgrupo = Integer.parseInt(params[1]);
        int idtipo = Integer.parseInt(params[2]);
        MateriaGrupo mg = new MateriaGrupo();
        String response = mg.CreateMateriaGrupo(idmateria, idgrupo, idtipo);
        mg.close();
        return response;
    }
    
    /*Docente Materia Grupo*/

    /*SHOWDOCMATGRUP_()*/
    public static List<RespDocenteMatGrupo> ShowAllDocMatGrup() {
        MateriaGrupo mg = new MateriaGrupo();
        List<RespDocenteMatGrupo> AllDocMatGrup = mg.ShowAllDocMatGrup();
        mg.close();
        return AllDocMatGrup;
    }

    /*CREATEDOCMATGRUP_(codigod,Idmateria,idgrupo)*/
    public static String CreateDocMatGrup(String[] params) {
        String codigod = params[0];
        int idmateria = Integer.parseInt(params[1]);
        int idgrupo = Integer.parseInt(params[2]);
        MateriaGrupo mg = new MateriaGrupo();
        String response = mg.CreateDocMatGrup(codigod, idmateria, idgrupo);
        mg.close();
        return response;
    }

}

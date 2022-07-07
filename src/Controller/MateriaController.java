package Controller;

import Entity.EntityMateria;
import Entity.RespDocenteMateriagrupoPeriodo;
import Entity.RespMateriaOfertada;
import Model.Materia;
import java.util.List;

public class MateriaController {

    //SHOWMATOF_(pinicio,pfin)
    public static List<RespMateriaOfertada> getAllMateriaOfertada(String[] params) {
        String pinicio = params[0];
        String pfin = params[1];
        Materia materia = new Materia();
        List<RespMateriaOfertada> lista = materia.getAllMateriaOfertada(pinicio, pfin);
        materia.close();
        return lista;
    }

    //SHOWMATERIA_()
    public static List<EntityMateria> ShowAllMateria() {
        Materia materia = new Materia();
        List<EntityMateria> AllMateria = materia.ShowAllMateria();
        materia.close();
        return AllMateria;
    }

    //CREATEMATERIA_(nombre,sigla,cargah)
    public static String CreateMateria(String[] params) {
        String nombre = params[0];
        String sigla = params[1];
        int cargah = Integer.parseInt(params[2]);
        Materia materia = new Materia();
        String response = materia.CreateMateria(nombre, sigla, cargah);
        materia.close();
        return response;
    }

    /*CREATEMATOF_(idmateria,idconvocatoria)*/
    public static String CreateMateriaOfertada(String[] params) {
        int idmateria = Integer.parseInt(params[0]);
        int idconvocatoria = Integer.parseInt(params[1]);
        Materia materia = new Materia();
        String response = materia.CreateMateriaOfertada(idmateria, idconvocatoria);
        materia.close();
        return response;
    }


    /*ADDDOCMATGRUPOPERIODO_(codigod,idmateria,idgrupo,idperiodo,codigoauxiliar)*/
    public static String AgregarDocMatGrupoPeriodo(String[] params) {
        String codigod = params[0];
        int idmateria = Integer.parseInt(params[1]);
        int idgrupo = Integer.parseInt(params[2]);
        int idperiodo = Integer.parseInt(params[3]);
        String codigoaux = params[4];
        Materia mat = new Materia();
        String response = mat.AgrergarDocMateriaGrupo(codigod, idmateria, idgrupo, idperiodo, codigoaux);
        mat.close();
        return response;
    }

    public static List<RespDocenteMateriagrupoPeriodo> ShowDocAuxMatGrupPer(String[] params) {
        int idperiodo = Integer.parseInt(params[3]);
        Materia materia = new Materia();
        List<RespDocenteMateriagrupoPeriodo> AllDocenteMateriagrupPer = materia.ShowDocenteMateriagrupPer(idperiodo);
        materia.close();
        return AllDocenteMateriagrupPer;
    }

}

package Model;

import Config.ConexionDB;
import Entity.EntityMateria;
import Entity.RespDocenteMateriagrupoPeriodo;
import Entity.RespMateriaOfertada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Materia {

    private Connection db;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement pstm;

    /*Query*/
    public static String SELECT_ALL_MATERIA_OFERTADA_PERIODO = ""
            + "select p.inicio,p.fin,c.id as convocatoria,c.fecha as FechaConv,m.nombre as Materia,m.id as idmateria\n"
            + "from Periodo as p,Convocatoria as c,Materia as m,MateriaOfertada mo\n"
            + "where p.inicio = ? and p.fin = ? and p.id=c.idperiodo \n"
            + "and mo.idconvocatoria=c.id  and mo.idmateria=m.id;";

    public static String SHOWALLMATERIA = "select*\n"
            + "from Materia";

    public static String CREATE_MATERIA_OFERTADA = "insert into MateriaOfertada(idmateria,idconvocatoria) values(?,?);";

    public static String ADDDOCMATGRUPO = ""
            + "insert into DocenteMateriaGrupoPeriodo(codigo,idmateria,idgrupo,idperiodo,codigoauxiliar) \n"
            + "values(?,?,?,?,?);";

    public static String SHOWDOCMATGRUPOPERIODO = ""
            + "select p.inicio ||' '||p.fin as periodo,d.nombre ||' '|| d.apellido as docente ,a.nombre ||' '|| a.apellido Auxiliar,aux.telefono,m.nombre Materia,g.nombre Grupo\n"
            + "from Usuario as d,DocenteMateriaGrupo as dmg,MateriaGrupo mg,Grupo as g,Materia as m,DocenteMateriaGrupoPeriodo as dmgp,Usuario as a,Periodo as p,Auxiliar as aux\n"
            + "where dmg.codigo=d.codigo and dmg.idmateria=mg.idmateria and dmg.idgrupo=mg.idgrupo\n"
            + "	  and mg.idmateria=m.id and mg.idgrupo=g.id and dmg.codigo=dmgp.codigo and dmg.idmateria=dmgp.idmateria\n"
            + "	  and dmg.idgrupo=dmgp.idgrupo  and a.codigo=dmgp.codigoauxiliar\n"
            + "	  and dmgp.idperiodo=p.id and p.id=?\n"
            + "	  and a.codigo=aux.codigo";

    public static String CREATEMATERIA = "insert into Materia(nombre,sigla,cargahoraria) values(?,?,?);";

    /*Constructor*/
    public Materia() {
        this.db = ConexionDB.getConnection();
    }

    /*Methods*/
    public List<RespMateriaOfertada> getAllMateriaOfertada(String pinicio, String pfin) {
        ArrayList<RespMateriaOfertada> lista = new ArrayList<RespMateriaOfertada>();
        try {
            pstm = db.prepareStatement(SELECT_ALL_MATERIA_OFERTADA_PERIODO);
            pstm.setString(1, pinicio.toString());
            pstm.setString(2, pfin.toString());
            System.out.println(pstm);
            rs = pstm.executeQuery();
            while (rs.next()) {
                RespMateriaOfertada materia = new RespMateriaOfertada();
                materia.setInicio(rs.getString("inicio"));
                materia.setFin(rs.getString("fin"));
                materia.setConvocatoria(rs.getInt("convocatoria"));
                materia.setFechaconv(rs.getString("fechaconv"));
                materia.setMateria(rs.getString("materia"));
                materia.setIdmateria(rs.getInt("idmateria"));
                lista.add(materia);
            }
        } catch (SQLException ex) {
            System.out.println("Error getAllMateriaOfertada!!!!!! : " + ex.getMessage());
        }
        System.out.println("Size:" + lista.size());
        return lista;
    }

    public List<EntityMateria> ShowAllMateria() {
        ArrayList<EntityMateria> lista = new ArrayList<EntityMateria>();
        try {
            pstm = db.prepareStatement(SHOWALLMATERIA);
            rs = pstm.executeQuery();
            while (rs.next()) {
                EntityMateria mat = new EntityMateria();
                mat.setIdmateria(rs.getInt("id"));
                mat.setNombre(rs.getString("nombre"));
                mat.setSigla(rs.getString("sigla"));
                mat.setCargaHoraria(rs.getInt("cargahoraria"));
                lista.add(mat);
            }
        } catch (SQLException ex) {
            System.out.println("Error ShowAllMateria!!!!!! : " + ex.getMessage());
        }
        return lista;
    }

    public String CreateMateriaOfertada(int idmateria, int idconvocatoria) {
        try {
            pstm = db.prepareStatement(CREATE_MATERIA_OFERTADA);
            pstm.setInt(1, idmateria);
            pstm.setInt(2, idconvocatoria);
            int row = pstm.executeUpdate();
            if (row > 0) {
                return "Se creo la materia ofertada";
            }
        } catch (SQLException ex) {
            System.out.println("Error CreateMateriaOfertada!!!!!! : " + ex.getMessage());
        }
        return "No se creo la materia ofertada";
    }

    public String AgrergarDocMateriaGrupo(String codigod, int idmateria, int idgrupo, int idperiodo, String codigoauxiliar) {
        try {
            pstm = db.prepareStatement(ADDDOCMATGRUPO);
            pstm.setString(1, codigod);
            pstm.setInt(2, idmateria);
            pstm.setInt(3, idgrupo);
            pstm.setInt(4, idperiodo);
            pstm.setString(5, codigoauxiliar);
            int row = pstm.executeUpdate();
            if (row > 0) {
                return "Se agrego Docente Materia Grupo Periodo";
            }
        } catch (SQLException ex) {
            System.out.println("Error AgrergarDocMateriaGrupo!!!!!! : " + ex.getMessage());
        }
        return "No se agrego Docente Materia Grupo Periodo";
    }

    public List<RespDocenteMateriagrupoPeriodo> ShowDocenteMateriagrupPer(int idperiodo) {
        ArrayList<RespDocenteMateriagrupoPeriodo> lista = new ArrayList<>();
        try {
            pstm = db.prepareStatement(SHOWDOCMATGRUPOPERIODO);
            pstm.setInt(1, idperiodo);
            System.out.println(pstm);
            rs = pstm.executeQuery();
            while (rs.next()) {
                RespDocenteMateriagrupoPeriodo obj = new RespDocenteMateriagrupoPeriodo();
                obj.setPeriodo(rs.getString("periodo"));
                obj.setDocente(rs.getString("docente"));
                obj.setAuxiliar(rs.getString("auxiliar"));
                obj.setTelefono(rs.getString("telefono"));
                obj.setMateria(rs.getString("materia"));
                obj.setGrupo(rs.getString("grupo"));
                lista.add(obj);
            }
        } catch (SQLException ex) {
            System.out.println("Error ShowDocenteMateriagrupPer!!!!!! : " + ex.getMessage());
        }
        return lista;
    }

    public String CreateMateria(String nombre, String sigla, int cargahoraria) {
        try {
            pstm = db.prepareStatement(CREATEMATERIA);
            pstm.setString(1, nombre);
            pstm.setString(2, sigla);
            pstm.setInt(3, cargahoraria);
            int row = pstm.executeUpdate();
            if (row > 0) {
                return "Se creo la materia";
            }
        } catch (SQLException ex) {
            System.out.println("Error CreateMateria!!!!!! : " + ex.getMessage());
        }
        return "No se creo la materia";
    }

    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (db != null) {
                db.close();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

}

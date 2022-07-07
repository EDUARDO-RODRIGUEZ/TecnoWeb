package Model;

import Config.ConexionDB;
import Entity.RespDocenteMatGrupo;
import Entity.RespMateriaGrupo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MateriaGrupo {

    private Connection db;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement pstm;

    public MateriaGrupo() {
        this.db = ConexionDB.getConnection();
    }

    public static String CREATEMATERIAGRUPO = "insert into MateriaGrupo(idmateria,idgrupo,idtipo) values(?,?,?);";
    public static String SHOWMATERIAGRUPO = ""
            + "select m.nombre as materia,g.nombre as grupo\n"
            + "From MateriaGrupo as mg,Tipo as t,Grupo as g,Materia as m\n"
            + "where mg.idtipo=t.id and mg.idmateria=m.id and mg.idgrupo=g.id";

    public static String SHOWALLDOCMATGRUPO = ""
            + "select d.nombre ||' '|| d.apellido as docente,m.nombre as materia,g.nombre as grupo\n"
            + "From  DocenteMateriaGrupo as dmg,Usuario as d,MateriaGrupo mg,Materia as m,Grupo as g\n"
            + "where dmg.codigo=d.codigo and dmg.idmateria=mg.idmateria and dmg.idgrupo=mg.idgrupo\n"
            + "	and mg.idmateria=m.id and mg.idgrupo=g.id ";
    public static String CREATEDOCMATGRUPO = "insert into DocenteMateriaGrupo(codigo,idmateria,idgrupo) values(?,?,?);";

    public String CreateMateriaGrupo(int idmateria, int idgrupo, int idtipo) {
        try {
            pstm = db.prepareStatement(CREATEMATERIAGRUPO);
            pstm.setInt(1, idmateria);
            pstm.setInt(2, idgrupo);
            pstm.setInt(3, idtipo);
            int row = pstm.executeUpdate();
            if (row > 0) {
                return "Se creo la materia grupo";
            }
        } catch (SQLException ex) {
            System.out.println("Error CreateMateriaGrupo!!!!!! : " + ex.getMessage());
        }
        return "No se creo la materia grupo";
    }

    public List<RespMateriaGrupo> ShowMateriaGrupo() {
        ArrayList<RespMateriaGrupo> lista = new ArrayList<RespMateriaGrupo>();
        try {
            pstm = db.prepareStatement(SHOWMATERIAGRUPO);
            rs = pstm.executeQuery();
            while (rs.next()) {
                RespMateriaGrupo mg = new RespMateriaGrupo();
                mg.setMateria(rs.getString("materia"));
                mg.setGrupo(rs.getString("grupo"));
                lista.add(mg);
            }
        } catch (SQLException ex) {
            System.out.println("Error ShowMateriaGrupo!!!!!! : " + ex.getMessage());
        }
        return lista;
    }

    public List<RespDocenteMatGrupo> ShowAllDocMatGrup() {
        ArrayList<RespDocenteMatGrupo> lista = new ArrayList<>();
        try {
            pstm = db.prepareStatement(SHOWALLDOCMATGRUPO);
            rs = pstm.executeQuery();
            while (rs.next()) {
                RespDocenteMatGrupo dmg = new RespDocenteMatGrupo();
                dmg.setDocente(rs.getString("docente"));
                dmg.setMateria(rs.getString("materia"));
                dmg.setGrupo(rs.getString("grupo"));
                lista.add(dmg);
            }
        } catch (SQLException ex) {
            System.out.println("Error ShowAllDocMatGrup!!!!!! : " + ex.getMessage());
        }
        return lista;
    }

    public String CreateDocMatGrup(String codigod, int idmateria, int idgrupo) {
        try {
            pstm = db.prepareStatement(CREATEDOCMATGRUPO);
            pstm.setString(1, codigod);
            pstm.setInt(2, idmateria);
            pstm.setInt(3, idgrupo);
            int row = pstm.executeUpdate();
            if (row > 0) {
                return "Se creo el Docente Materia Grupo";
            }
        } catch (SQLException ex) {
            System.out.println("Error ShowAllDocMatGrup!!!!!! : " + ex.getMessage());
        }
        return "No se creo el Docente Materia Grupo";
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

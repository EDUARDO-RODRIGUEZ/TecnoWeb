package Model;

import Config.ConexionDB;
import Entity.EntityGrupo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Grupo {

    private Connection db;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement pstm;

    public Grupo() {
        this.db = ConexionDB.getConnection();
    }

    public static String CREATEGRUPO = "insert into Grupo(nombre) values(?);";
    public static String SHOWGRUPO = "select*from Grupo";

    public List<EntityGrupo> ShowGrupo() {
        ArrayList<EntityGrupo> lista = new ArrayList<>();
        try {
            pstm = db.prepareStatement(SHOWGRUPO);
            rs = pstm.executeQuery();
            while (rs.next()) {
                EntityGrupo grupo = new EntityGrupo();
                grupo.setId(rs.getInt("id"));
                grupo.setNombre(rs.getString("nombre"));
                lista.add(grupo);
            }
        } catch (SQLException ex) {
            System.out.println("Error ShowGrupo!!!!!! : " + ex.getMessage());
        }
        return lista;
    }

    public String CreateGrupo(String nombre) {
        try {
            pstm = db.prepareStatement(CREATEGRUPO);
            pstm.setString(1, nombre);
            int row = pstm.executeUpdate();
            if (row > 0) {
                return "Se creo el grupo";
            }
        } catch (SQLException ex) {
            System.out.println("Error CreateGrupo!!!!!! : " + ex.getMessage());
        }
        return "No se creo el grupo";
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

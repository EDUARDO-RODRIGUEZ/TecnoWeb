package Model;

import Config.ConexionDB;
import Entity.EntityPeriodo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Periodo {

    private Connection db;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement pstm;

    public Periodo() {
        this.db = ConexionDB.getConnection();
    }

    public static String SHOWPERIODO = "select*from Periodo";
    public static String CREATEPERIODO = "insert into Periodo(inicio,fin) values(?,?);";

    public String CreatePeriodo(String inicio, String fin) {
        try {
            pstm = db.prepareStatement(CREATEPERIODO);
            pstm.setString(1, inicio);
            pstm.setString(2, fin);
            int row = pstm.executeUpdate();
            if (row > 0) {
                return "Se creo el Periodo correctamente";
            }
        } catch (SQLException ex) {
            System.out.println("Error CreatePeriodo !!!!!! : " + ex.getMessage());
        }
        return "No se pudo crear el periodo";
    }

    public List<EntityPeriodo> ShowPeriodo() {
        ArrayList<EntityPeriodo> lista = new ArrayList<EntityPeriodo>();
        try {
            pstm = db.prepareStatement(SHOWPERIODO);
            rs = pstm.executeQuery();
            while (rs.next()) {
                EntityPeriodo periodo = new EntityPeriodo();
                periodo.setId(rs.getInt("id"));
                periodo.setInicio(rs.getString("inicio"));
                periodo.setFin(rs.getString("fin"));
                lista.add(periodo);
            }
        } catch (SQLException ex) {
            System.out.println("Error ShowPeriodo !!!!!! : " + ex.getMessage());
        }
        return lista;
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

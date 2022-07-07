package Model;

import Config.ConexionDB;
import Entity.RespCronograma;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cronograma {

    private Connection db;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement pstm;

    public Cronograma() {
        this.db = ConexionDB.getConnection();
    }

    public static String SHOWALLCRONOGRAMA = ""
            + "select c.id cronograma,c.fecha,p.inicio,p.fin\n"
            + "From Cronograma as c,Periodo as p\n"
            + "where c.idperiodo=p.id";

    public static String CREATECRONOGRAMA = "insert into Cronograma(fecha,idperiodo) values(?,?);";

    public List<RespCronograma> ShowAllCronograma() {
        ArrayList<RespCronograma> lista = new ArrayList<>();
        try {
            pstm = db.prepareStatement(SHOWALLCRONOGRAMA);
            rs = pstm.executeQuery();
            while (rs.next()) {
                RespCronograma cronograma = new RespCronograma();
                cronograma.setCronograma(rs.getInt("cronograma"));
                cronograma.setFecha(rs.getString("fecha"));
                cronograma.setInicio(rs.getString("inicio"));
                cronograma.setFin(rs.getString("fin"));
                lista.add(cronograma);
            }
        } catch (SQLException ex) {
            System.out.println("Error ShowAllCronograma !!!!!! : " + ex.getMessage());
        }
        return lista;
    }

    public String CreateCronograma(String fecha, int idperiodo) {
        try {
            pstm = db.prepareStatement(CREATECRONOGRAMA);
            pstm.setDate(1, Date.valueOf(LocalDate.parse(fecha)));
            pstm.setInt(2, idperiodo);
            int row = pstm.executeUpdate();
            if (row > 0) {
                return "Se creo el cronograma";
            }
        } catch (SQLException ex) {
            System.out.println("Error ShowAllCronograma !!!!!! : " + ex.getMessage());
        }
        return "No se creo el cronograma";
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

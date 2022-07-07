package Model;

import Config.ConexionDB;
import Entity.RespActividad;
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

public class Actividad {

    private Connection db;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement pstm;

    public Actividad() {
        this.db = ConexionDB.getConnection();
    }

    public static String SHOWACTIVIDADES = ""
            + "select a.nombre,a.fecha,a.idcronograma as cronograma,p.inicio ||' '|| p.fin as periodo\n"
            + "from Actividad as a,Cronograma as c,Periodo as p \n"
            + "where a.idcronograma=? and c.id=a.idcronograma and c.idperiodo=p.id";
    public static String CREATEACTIVIDAD = ""
            + "insert into Actividad(nombre,fecha,idcronograma) values (?,?,?);";

    public List<RespActividad> ShowAllActividades(int cronograma) {
        ArrayList<RespActividad> lista = new ArrayList<>();
        try {
            pstm = db.prepareStatement(SHOWACTIVIDADES);
            pstm.setInt(1, cronograma);
            rs = pstm.executeQuery();
            while (rs.next()) {
                RespActividad actividad = new RespActividad();
                actividad.setNombre(rs.getString("nombre"));
                actividad.setFecha(rs.getString("fecha"));
                actividad.setCronograma(rs.getInt("cronograma"));
                actividad.setPeriodo(rs.getString("periodo"));
                lista.add(actividad);
            }
        } catch (SQLException ex) {
            System.out.println("Error ShowAllConvocatoria !!!!!! : " + ex.getMessage());
        }
        return lista;
    }

    public String CrearActividad(String actividad, String fecha, int idcronograma) {
        try {
            pstm = db.prepareStatement(CREATEACTIVIDAD);
            pstm.setString(1, actividad);
            pstm.setDate(2, Date.valueOf(LocalDate.parse(fecha)));
            pstm.setInt(3, idcronograma);
            int row = pstm.executeUpdate();
            if (row > 0) {
                return "Se creo la actividad";
            }
        } catch (SQLException ex) {
            System.out.println("Error ShowAllConvocatoria !!!!!! : " + ex.getMessage());
        }
        return "No se creo la actividad";
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

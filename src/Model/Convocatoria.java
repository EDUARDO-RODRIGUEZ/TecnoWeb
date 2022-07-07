package Model;

import Config.ConexionDB;
import Entity.RespConvocatoria;
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

public class Convocatoria {

    private Connection db;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement pstm;

    public Convocatoria() {
        this.db = ConexionDB.getConnection();
    }

    public static String SHOWCONVOCATORIA = ""
            + "select c.id as convocatoria ,c.titulo,c.fecha,p.inicio||' '||p.fin as periodo\n"
            + "From Convocatoria as c,Periodo as p\n"
            + "where p.id=c.idperiodo";

    public static String CREATECONVOCATORIA = ""
            + "insert into Convocatoria(titulo,descripcion,fecha,idtipoconvocatoria,idperiodo) \n"
            + "	values(?,?,?,?,?);";

    public List<RespConvocatoria> ShowAllConvocatoria() {
        ArrayList<RespConvocatoria> lista = new ArrayList<>();
        try {
            pstm = db.prepareStatement(SHOWCONVOCATORIA);
            rs = pstm.executeQuery();
            while (rs.next()) {
                RespConvocatoria conv = new RespConvocatoria();
                conv.setConvocatoria(rs.getInt("convocatoria"));
                conv.setTitulo(rs.getString("titulo"));
                conv.setFecha(rs.getString("fecha"));
                conv.setPeriodo(rs.getString("periodo"));
                lista.add(conv);
            }
        } catch (SQLException ex) {
            System.out.println("Error ShowAllConvocatoria !!!!!! : " + ex.getMessage());
        }
        return lista;
    }

    public String CreateConvocatoriaAux(String titulo, String descripcion, String fecha, int idperiodo) {
        try {
            pstm = db.prepareStatement(CREATECONVOCATORIA);
            pstm.setString(1, titulo);
            pstm.setString(2, descripcion);
            pstm.setDate(3, Date.valueOf(LocalDate.parse(fecha)));
            pstm.setInt(4, 1);
            pstm.setInt(5, idperiodo);
            int row = pstm.executeUpdate();
            if (row > 0) {
                return "Se creo la convocatoria";
            }
        } catch (SQLException ex) {
            System.out.println("Error ShowAllConvocatoria !!!!!! : " + ex.getMessage());
        }
        return "No se creo la convocatoria";
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

package Model;

import Config.ConexionDB;
import Entity.RespSolicitud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Solicitud {

    private Connection db;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement pstm;

    /*Constructor*/
    public Solicitud() {
        this.db = ConexionDB.getConnection();
    }

    /*Query*/
    public static String ADD_SOLICITUD = ""
            + "insert into Solicitud(codigo,idmateria,idconvocatoria,aceptado,notaacumulada,notafinal,filecv) \n"
            + "values(?,?,?,false,0,0,?);";

    public static String SEND_RESPONSE_SOLICITUD = ""
            + "UPDATE Solicitud\n"
            + "SET aceptado = ?, notaacumulada= ?\n"
            + "WHERE codigo=? and idconvocatoria=? and idmateria=?;";

    public static String SHOWALLSOL = ""
            + "select p.inicio || ' ' || p.fin as periodo,m.nombre as materia,m.id as idmateria,e.nombre||' '||e.apellido as estudiante,e.codigo as codigoe,c.id as convocatoria,s.aceptado,s.notaacumulada\n"
            + "From Solicitud as s,Usuario as e,MateriaOfertada as mo,Materia as m,Convocatoria as c,Periodo as p\n"
            + "where p.inicio=? and p.fin=? and c.idperiodo=p.id and c.id=mo.idconvocatoria\n"
            + "		and mo.idmateria=m.id and mo.idmateria=s.idmateria and mo.idconvocatoria=s.idconvocatoria\n"
            + "		and s.codigo=e.codigo";
    
    /*Methods*/
    public String sendSolicitud(String codigoe, int idmateria, int idconv, String filename) {
        try {
            pstm = db.prepareStatement(ADD_SOLICITUD);
            pstm.setString(1, codigoe);
            pstm.setInt(2, idmateria);
            pstm.setInt(3, idconv);
            pstm.setString(4, filename);
            int count = pstm.executeUpdate();
            if (count > 0) {
                return "Se envio la solicitud";
            }
        } catch (SQLException ex) {
            System.out.println("Error sendSolicitud!!!!!! : " + ex.getMessage());
        }
        return "No se pudo enviar la solicitud";
    }

    public String SendResponseSolicitud(String codigoe, boolean aceptado, double notaac, int idconv, int idmateria) {
        try {
            pstm = db.prepareStatement(SEND_RESPONSE_SOLICITUD);
            pstm.setBoolean(1, aceptado);
            pstm.setDouble(2, notaac);
            pstm.setString(3, codigoe);
            pstm.setInt(4, idconv);
            pstm.setInt(5, idmateria);
            int count = pstm.executeUpdate();
            if (count > 0) {
                return "Se Respondio a la solicitud";
            }
        } catch (SQLException ex) {
            System.out.println("Error SendResponseSolicitud!!!!!! : " + ex.getMessage());
        }
        return "Error no se pudo responder a la solicitud";
    }

    public List<RespSolicitud> ShowAllSolicitudes(String pinicio, String pfin) {
        ArrayList<RespSolicitud> lista = new ArrayList<>();
        try {
            pstm = db.prepareStatement(SHOWALLSOL);
            pstm.setString(1, pinicio);
            pstm.setString(2, pfin);
            rs = pstm.executeQuery();
            while (rs.next()) {
                RespSolicitud solicitud = new RespSolicitud();
                solicitud.setPeriodo(rs.getString("periodo"));
                solicitud.setMateria(rs.getString("materia"));
                solicitud.setIdmateria(rs.getInt("idmateria"));
                solicitud.setEstudiante(rs.getString("estudiante"));
                solicitud.setCodigoe(rs.getString("codigoe"));
                solicitud.setConvocatoria(rs.getInt("convocatoria"));
                solicitud.setAceptado(rs.getBoolean("aceptado"));
                solicitud.setNotaac(rs.getDouble("notaacumulada"));
                lista.add(solicitud);
            }
        } catch (SQLException ex) {
            System.out.println("Error SHOWALLSOL!!!!!! : " + ex.getMessage());
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

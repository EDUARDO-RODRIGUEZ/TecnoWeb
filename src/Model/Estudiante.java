package Model;

import Config.ConexionDB;
import Entity.RespCalificacionMateria;
import Entity.RespMateriaPostulada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Estudiante {

    private Connection db;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement pstm;

    /*Constructor*/
    public Estudiante() {
        this.db = ConexionDB.getConnection();
    }

    /*Query*/
    public static String SELECT_ALL_MATERIA_POSTULADA_PERIODO = ""
            + "select p.inicio ||' '|| p.fin as periodo ,c.id as convocatoria,m.nombre as materia,m.id as idmateria,(e.nombre||''||e.apellido) as estudiante,s.aceptado,s.notaacumulada,s.notafinal\n"
            + "From Solicitud as s,Usuario as e,MateriaOfertada as mo,Materia as m,Convocatoria as c,Periodo as p\n"
            + "where p.inicio=? and p.fin=? and c.idperiodo=p.id and c.id=mo.idconvocatoria\n"
            + "		and mo.idmateria=m.id and mo.idmateria=s.idmateria and mo.idconvocatoria=s.idconvocatoria\n"
            + "		and s.codigo=e.codigo and e.codigo=?";

    public static String SELECT_CALIFICACION_MATERIA = ""
            + "select d.nombre ||' '||d.apellido as docente,m.nombre as materia,e.nombre||' '||e.apellido  as estudiante,n.notaconocimiento,n.notapedagogica,n.notafinal\n"
            + "from Usuario as e,Solicitud as s,MateriaOfertada as mo,Materia as m,Convocatoria as c,Usuario as d,Examen as ex,Nota as n\n"
            + "where e.codigo=? and s.codigo=e.codigo and s.idmateria=mo.idmateria and \n"
            + "	s.idconvocatoria=mo.idconvocatoria and mo.idmateria=m.id and mo.idconvocatoria=c.id \n"
            + "	and m.id=? and c.id=? and mo.idmateria=ex.idmateria and mo.idconvocatoria=ex.idconvocatoria and\n"
            + "	ex.codigo=d.codigo and n.codigodocente=ex.codigo and n.idconvocatoria=ex.idconvocatoria\n"
            + "	and n.idmateria=ex.idmateria and s.codigo=n.codigoestudiante";

    public static String CREATEUSUARIO = ""
            + "insert into Usuario(codigo,nombre,apellido,email,pass,idrol) \n"
            + "	values(?,?,?,?,?,?);";

    public static String CREATEESTUDIANTE = "insert into Estudiante(codigo) values(?);";

    public static String UPDATEUSUARIO = ""
            + "Update Usuario \n"
            + "set nombre=?,apellido=?,email=?,pass=?\n"
            + "where codigo=?;";

    public static String CREATEAUXILIAR = "insert into Auxiliar(codigo,telefono) values(?,?);";

    /*Methods*/
    public List<RespMateriaPostulada> getMateriasPostulada(String pinicio, String pfin, String codigoe) {
        ArrayList<RespMateriaPostulada> lista = new ArrayList<>();
        try {
            pstm = db.prepareStatement(SELECT_ALL_MATERIA_POSTULADA_PERIODO);
            pstm.setString(1, pinicio);
            pstm.setString(2, pfin);
            pstm.setString(3, codigoe);
            rs = pstm.executeQuery();
            while (rs.next()) {
                RespMateriaPostulada matpost = new RespMateriaPostulada();
                matpost.setPeriodo(rs.getString("periodo"));
                matpost.setConvocatoria(rs.getInt("convocatoria"));
                matpost.setMateria(rs.getString("materia"));
                matpost.setIdmateria(rs.getInt("idmateria"));
                matpost.setEstudiante(rs.getString("estudiante"));
                matpost.setAceptado(rs.getBoolean("aceptado"));
                matpost.setNotaacumulada(rs.getDouble("notaacumulada"));
                matpost.setNotafinal(rs.getDouble("notafinal"));
                lista.add(matpost);
            }
        } catch (SQLException ex) {
            System.out.println("Error getMateriasPostulada!!!!!! : " + ex.getMessage());
        }
        return lista;
    }

    public List<RespCalificacionMateria> ShowCalificacionesMateria(String codigoe, int idmateria, int idconvocatoria) {
        ArrayList<RespCalificacionMateria> lista = new ArrayList<RespCalificacionMateria>();
        try {
            pstm = db.prepareStatement(SELECT_CALIFICACION_MATERIA);
            pstm.setString(1, codigoe);
            pstm.setInt(2, idmateria);
            pstm.setInt(3, idconvocatoria);
            rs = pstm.executeQuery();
            while (rs.next()) {
                RespCalificacionMateria calificacion = new RespCalificacionMateria();
                calificacion.setDocente(rs.getString("docente"));
                calificacion.setMateria(rs.getString("materia"));
                calificacion.setEstudiante(rs.getString("estudiante"));
                calificacion.setNotaconocimiento(rs.getDouble("notaconocimiento"));
                calificacion.setNotapedagogica(rs.getDouble("notapedagogica"));
                calificacion.setNotafinal(rs.getDouble("notafinal"));
                lista.add(calificacion);
            }
        } catch (SQLException ex) {
            System.out.println("Error ShowCalificacionesMateria!!!!!! : " + ex.getMessage());
        }
        return lista;
    }

    public String CreateEstudiante(String codigoe, String nombre, String apellido, String email, String pass) {
        try {
            pstm = db.prepareStatement(CREATEUSUARIO);
            pstm.setString(1, codigoe);
            pstm.setString(2, nombre);
            pstm.setString(3, apellido);
            pstm.setString(4, email);
            pstm.setString(5, pass);
            pstm.setInt(6, 3);
            int rowAffected1 = pstm.executeUpdate();
            pstm = db.prepareStatement(CREATEESTUDIANTE);
            pstm.setString(1, codigoe);
            int rowAffected2 = pstm.executeUpdate();
            if (rowAffected1 > 0 && rowAffected2 > 0) {
                return "El Estudiante se creo correctamente";
            }
        } catch (SQLException ex) {
            System.out.println("Error CreateEstudiante !!!!!! : " + ex.getMessage());
        }
        return "Error nose pudo crear al estudiante";
    }

    public String UpdateUsuario(String codigoe, String nombre, String apellido, String email, String pass) {
        try {
            pstm = db.prepareStatement(UPDATEUSUARIO);
            pstm.setString(1, nombre);
            pstm.setString(2, apellido);
            pstm.setString(3, email);
            pstm.setString(4, pass);
            pstm.setString(5, codigoe);
            System.out.println(pstm);
            int row = pstm.executeUpdate();
            if (row > 0) {
                return "Se actualizo el Usuario Correctamente";
            }
        } catch (SQLException ex) {
            System.out.println("Error UpdateUsuario !!!!!! : " + ex.getMessage());
        }
        return "Error nose pudo actualizar el Usuario";
    }

    public String CreateAuxiliar(String codigoe, String telefono) {
        try {
            pstm = db.prepareStatement(CREATEAUXILIAR);
            pstm.setString(1, codigoe);
            pstm.setString(2, telefono);
            int row = pstm.executeUpdate();
            if (row > 0) {
                return "El auxiliar se creo correctamente";
            }
        } catch (SQLException ex) {
            System.out.println("Error UpdateUsuario !!!!!! : " + ex.getMessage());
        }
        return "El auxiliar no se pudo crear";
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

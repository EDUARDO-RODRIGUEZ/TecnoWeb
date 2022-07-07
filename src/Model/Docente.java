package Model;

import Config.ConexionDB;
import Entity.RespEstudianteAceptado;
import Entity.RespMateriaEvaluar;
import Entity.RespCalificacionEstudiante;
import Entity.RespDocente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Docente {

    private Connection db;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement pstm;

    public Docente() {
        this.db = ConexionDB.getConnection();
    }

    /*Query*/
    public static String SELECT_ALL_MATERIA_EVALUAR = ""
            + "select p.inicio ||' '|| p.fin as periodo,c.id as convocatoria,m.nombre as materia,m.id as idmateria,d.nombre ||' '||d.apellido as docente,ex.fecha,ex.hora,ex.lugar,ex.filename as examen\n"
            + "from Periodo as p,Convocatoria as c,MateriaOfertada as mo,Materia as m,Examen as ex,Usuario as d\n"
            + "where p.inicio=? and p.fin=? and  p.id=c.idperiodo and c.id=mo.idconvocatoria and mo.idmateria=m.id  \n"
            + "and mo.idmateria=ex.idmateria  and mo.idconvocatoria=ex.idconvocatoria and ex.codigo=d.codigo and d.codigo=?;";

    public static String SELECT_ALL_ESTUDIANTE_ACEPTADO = ""
            + "select p.inicio ||' '|| p.fin as periodo,c.id as convocatoria,m.nombre as materia,m.id as idmateria,e.nombre ||' '|| e.apellido as estudiante,e.codigo as codigoe\n"
            + "from Periodo as p,Convocatoria as c,MateriaOfertada as mo,Materia as m,Solicitud as s,Usuario as e\n"
            + "where p.id=c.idperiodo and c.id=? and c.id=mo.idconvocatoria and mo.idmateria=m.id \n"
            + "and mo.idmateria=s.idmateria and mo.idconvocatoria=s.idconvocatoria and s.codigo=e.codigo\n"
            + "and s.aceptado=true and m.id=?;";

    public static String ADD_INFO_EXAMEN = ""
            + "UPDATE Examen\n"
            + "SET fecha = ? , hora = ? ,lugar = ?\n"
            + "WHERE codigo = ? and idmateria = ? and idconvocatoria = ?;";

    public static String SHOWCALIFICACIONESTUDIANTE = ""
            + "select p.inicio||' '|| p.fin as periodo,d.codigo as codigod,m.nombre as materia,m.id as idmateria,c.id as idconvocatoria,e.nombre ||' '|| e.apellido as estudiante,n.notapedagogica,n.notaconocimiento,n.notafinal\n"
            + "from Periodo as p,Convocatoria as c,Materia as m,MateriaOfertada as mo,Usuario as d,Examen as ex,Usuario as e,Nota as n,Solicitud as s\n"
            + "where c.id=? and p.id=c.idperiodo and c.id=mo.idconvocatoria and mo.idmateria=m.id and m.id=? and\n"
            + "mo.idmateria=ex.idmateria and mo.idconvocatoria= ex.idconvocatoria and ex.codigo=d.codigo and\n"
            + "d.codigo=? and ex.codigo=n.codigodocente and ex.idmateria=n.idmateria and\n"
            + "ex.idconvocatoria=n.idconvocatoria and n.codigoestudiante=s.codigo and n.idmateria=s.idmateria\n"
            + "and n.idconvocatoria=s.idconvocatoria and e.codigo=s.codigo";

    public static String ADDCALIFICACION = ""
            + "insert into Nota(codigodocente,idmateria,idconvocatoria,codigoestudiante,notaconocimiento,notapedagogica,notafinal) \n"
            + "values(?,?,?,?,?,?,?); ";

    public static String UPDATECALIFICACION = ""
            + "UPDATE Nota\n"
            + "SET notaconocimiento=?,notapedagogica=?,notafinal=?\n"
            + "WHERE codigodocente=? and idmateria=? and idconvocatoria=? and codigoestudiante=?";

    public static String SUMARNOTAFINALSOLICITUD = ""
            + "UPDATE Solicitud\n"
            + "SET notafinal=notaacumulada+?\n"
            + "WHERE codigo=? and idmateria=? and idconvocatoria=?\n";

    public static String UPLOADEXAMEN = ""
            + "UPDATE Examen\n"
            + "SET filename=?\n"
            + "WHERE codigo=? and idmateria=? and idconvocatoria=?";

    public static String CREATEUSUARIO = ""
            + "insert into Usuario(codigo,nombre,apellido,email,pass,idrol) \n"
            + "values(?,?,?,?,?,?);";

    public static String CREATEDOCENTE = "insert into Docente(codigo) values(?);";

    public static String SHOWDOCENTE = ""
            + "select codigo,nombre ||' '|| apellido nombre, email\n"
            + "from Usuario where idrol=2 ";
    public static String ADDEVALUADOR = ""
            + "insert into Examen(codigo,idmateria,idconvocatoria) \n"
            + "values(?,?,?);";

    public List<RespMateriaEvaluar> ShowMateriasEvaluar(String pinicio, String pfin, String codigod) {
        ArrayList<RespMateriaEvaluar> lista = new ArrayList<RespMateriaEvaluar>();
        try {
            pstm = db.prepareStatement(SELECT_ALL_MATERIA_EVALUAR);
            pstm.setString(1, pinicio);
            pstm.setString(2, pfin);
            pstm.setString(3, codigod);
            rs = pstm.executeQuery();
            while (rs.next()) {
                RespMateriaEvaluar materia = new RespMateriaEvaluar();
                materia.setPeriodo(rs.getString("periodo"));
                materia.setConvocatoria(rs.getInt("convocatoria"));
                materia.setMateria(rs.getString("materia"));
                materia.setIdmateria(rs.getInt("idmateria"));
                materia.setDocente(rs.getString("docente"));
                if (rs.getDate("fecha") != null) {
                    materia.setFecha(rs.getDate("fecha").toString());
                }
                if (rs.getTime("hora") != null) {
                    materia.setHora(rs.getTime("hora").toString());
                }
                if (rs.getString("lugar") != null) {
                    materia.setLugar(rs.getString("lugar"));
                }
                if (rs.getString("examen") != null) {
                    materia.setExamen(rs.getString("examen"));
                }
                lista.add(materia);
            }
        } catch (SQLException ex) {
            System.out.println("Error ShowMateriasEvaluar!!!!!! : " + ex.getMessage());
        }
        return lista;
    }

    public List<RespEstudianteAceptado> ShowEstudiantesAceptados(int convocatoria, int materia) {
        ArrayList<RespEstudianteAceptado> lista = new ArrayList<RespEstudianteAceptado>();
        try {
            pstm = db.prepareStatement(SELECT_ALL_ESTUDIANTE_ACEPTADO);
            pstm.setInt(1, convocatoria);
            pstm.setInt(2, materia);
            rs = pstm.executeQuery();
            while (rs.next()) {
                RespEstudianteAceptado estudiante = new RespEstudianteAceptado();
                estudiante.setPeriodo(rs.getString("periodo"));
                estudiante.setConvocatoria(rs.getInt("convocatoria"));
                estudiante.setMateria(rs.getString("materia"));
                estudiante.setIdmateria(rs.getInt("idmateria"));
                estudiante.setEstudiante(rs.getString("estudiante"));
                estudiante.setCodigoe(rs.getString("codigoe"));
                lista.add(estudiante);
            }
        } catch (SQLException ex) {
            System.out.println("Error ShowEstudiantesAceptados!!!!!! : " + ex.getMessage());
        }
        return lista;
    }

    public String AgregarInfoExamen(String codigod, int idmateria, int idconv, String fecha, String hora, String lugar) {

        try {
            pstm = db.prepareStatement(ADD_INFO_EXAMEN);
            pstm.setDate(1, Date.valueOf(LocalDate.parse(fecha)));
            pstm.setTime(2, Time.valueOf(LocalTime.parse(hora)));
            pstm.setString(3, lugar);
            pstm.setString(4, codigod);
            pstm.setInt(5, idmateria);
            pstm.setInt(6, idconv);
            int row = pstm.executeUpdate();
            if (row > 0) {
                return "Se agrego la informacion al examen";
            }
        } catch (SQLException ex) {
            System.out.println("Error AgregarInfoExamen!!!!!! : " + ex.getMessage());
        }
        return "No se pudo agregar la informacion al examen";
    }

    public List<RespCalificacionEstudiante> ShowCalificacionEstudiante(int convocatoria, int idmateria, String codigod) {
        ArrayList<RespCalificacionEstudiante> lista = new ArrayList<RespCalificacionEstudiante>();
        try {
            pstm = db.prepareStatement(SHOWCALIFICACIONESTUDIANTE);
            pstm.setInt(1, convocatoria);
            pstm.setInt(2, idmateria);
            pstm.setString(3, codigod);
            rs = pstm.executeQuery();
            while (rs.next()) {
                RespCalificacionEstudiante estudiante = new RespCalificacionEstudiante();
                estudiante.setPeriodo(rs.getString("periodo"));
                estudiante.setCodigod(rs.getString("codigod"));
                estudiante.setMateria(rs.getString("materia"));
                estudiante.setIdmateria(rs.getInt("idmateria"));
                estudiante.setIdconvocatoria(rs.getInt("idconvocatoria"));
                estudiante.setEstudiante(rs.getString("estudiante"));
                estudiante.setNotapedagogica(rs.getDouble("notapedagogica"));
                estudiante.setNotaconocimiento(rs.getDouble("notaconocimiento"));
                estudiante.setNotafinal(rs.getDouble("notafinal"));
                lista.add(estudiante);
            }
        } catch (SQLException ex) {
            System.out.println("Error ShowCalificacionEstudiante!!!!!! : " + ex.getMessage());
        }
        return lista;
    }

    public String AgregarCalificacion(String codigod, int idmateria, int idconvocatoria, String codigoe, double notac, double notap) {
        try {
            Double nf = notac + notap;
            pstm = db.prepareStatement(ADDCALIFICACION);
            pstm.setString(1, codigod);
            pstm.setInt(2, idmateria);
            pstm.setInt(3, idconvocatoria);
            pstm.setString(4, codigoe);
            pstm.setDouble(5, notac);
            pstm.setDouble(6, notap);
            pstm.setDouble(7, nf);
            int rowaffected = pstm.executeUpdate();
            if (rowaffected > 0) {
                SumarNotafinalSolicitud(nf, codigoe, idmateria, idconvocatoria);
                return "Se agrego la nota correctamente";
            }
        } catch (SQLException ex) {
            System.out.println("Error AgregarCalificacion!!!!!! : " + ex.getMessage());
        }
        return "No se pudo agregar la nota";
    }

    //codigo=? and idmateria=? and idconvocatoria=?\n"
    public void SumarNotafinalSolicitud(double nf, String codigoe, int idmateria, int idconvocatoria) {
        try {
            pstm = db.prepareStatement(SUMARNOTAFINALSOLICITUD);
            pstm.setDouble(1, nf);
            pstm.setString(2, codigoe);
            pstm.setInt(3, idmateria);
            pstm.setInt(4, idconvocatoria);
            int rowaffected = pstm.executeUpdate();
            if (rowaffected > 0) {
                System.out.println("Se actualizo la Nota final Solicitud");
            }
        } catch (SQLException ex) {
            System.out.println("Error SumarNotafinalSolicitud!!!!!! : " + ex.getMessage());
        }
    }

    public String ActualizarCalificacion(String codigod, int idmateria, int idconvocatoria, String codigoe, double notac, double notap) {
        try {
            Double nf = notac + notap;
            pstm = db.prepareStatement(UPDATECALIFICACION);
            pstm.setDouble(1, notac);
            pstm.setDouble(2, notap);
            pstm.setDouble(3, nf);
            pstm.setString(4, codigod);
            pstm.setInt(5, idmateria);
            pstm.setInt(6, idconvocatoria);
            pstm.setString(7, codigoe);
            int rowaffected = pstm.executeUpdate();
            if (rowaffected > 0) {
                SumarNotafinalSolicitud(nf, codigoe, idmateria, idconvocatoria);
                return "Se actualizo la nota correctamente";
            }
        } catch (SQLException ex) {
            System.out.println("Error ActualizarCalificacion!!!!!! : " + ex.getMessage());
        }
        return "No se pudo actualizar la nota";
    }

    public String UploadExamen(String filename, String codigod, int idmateria, int idconvocatoria) {
        try {
            pstm = db.prepareStatement(UPLOADEXAMEN);
            pstm.setString(1, filename);
            pstm.setString(2, codigod);
            pstm.setInt(3, idmateria);
            pstm.setInt(4, idconvocatoria);
            int rowaffected = pstm.executeUpdate();
            if (rowaffected > 0) {
                return "Se guardo el examen correctamente";
            }
        } catch (SQLException ex) {
            System.out.println("Error UploadExamen!!!!!! : " + ex.getMessage());
        }
        return "No Se pudo guardar el examen";
    }

    public String CrearDocente(String codigod, String nombre, String apellido, String email, String pass) {
        try {
            pstm = db.prepareStatement(CREATEUSUARIO);
            pstm.setString(1, codigod);
            pstm.setString(2, nombre);
            pstm.setString(3, apellido);
            pstm.setString(4, email);
            pstm.setString(5, pass);
            pstm.setInt(6, 2);
            int row1 = pstm.executeUpdate();
            pstm = db.prepareStatement(CREATEDOCENTE);
            pstm.setString(1, codigod);
            int row2 = pstm.executeUpdate();
            if (row1 > 0 && row2 > 0) {
                return "Se creo el Usuario Docente Correctamente";
            }
        } catch (SQLException ex) {
            System.out.println("Error CrearDocente!!!!!! : " + ex.getMessage());
        }
        return "No se pudo crear el Usuario Docente";
    }

    public List<RespDocente> ShowAllDocente() {
        ArrayList<RespDocente> lista = new ArrayList<>();
        try {
            pstm = db.prepareStatement(SHOWDOCENTE);
            rs = pstm.executeQuery();
            while (rs.next()) {
                RespDocente docente = new RespDocente();
                docente.setCodigo(rs.getString("codigo"));
                docente.setNombre(rs.getString("nombre"));
                docente.setEmail(rs.getString("email"));
                lista.add(docente);
            }
        } catch (SQLException ex) {
            System.out.println("Error CrearDocente!!!!!! : " + ex.getMessage());
        }
        return lista;
    }

    public String AgregarEvaluador(String codigod, int idmateria, int convocatoria) {
        try {
            pstm = db.prepareStatement(ADDEVALUADOR);
            pstm.setString(1, codigod);
            pstm.setInt(2, idmateria);
            pstm.setInt(3, convocatoria);
            int row = pstm.executeUpdate();
            if (row > 0) {
                return "Se agrego al Evaluador";
            }
        } catch (SQLException ex) {
            System.out.println("Error AgregarEvaluador!!!!!! : " + ex.getMessage());
        }
        return "No se agrego al evaluador";
    }

    /*Methods*/
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

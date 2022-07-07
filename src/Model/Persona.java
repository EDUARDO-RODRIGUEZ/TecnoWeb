package Model;

import Config.ConexionDB;
import Entity.EntityPersona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Persona {

    private Connection db;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement pstm;

    //QUERY
    public static String SELECT_LIKE = "select p.* from persona p where lower(p.per_nom) like ? or  lower(p.per_appm)  like ? or lower(p.per_email) like ? ";

    public Persona() {
        this.db = ConexionDB.getConnection();
    }


    /*METHODS*/
    
    public List<EntityPersona> getLike(String pword) {
        pword = pword.toLowerCase();
        List<EntityPersona> persona = new ArrayList<>();

        try {
            pstm = db.prepareStatement(SELECT_LIKE);
            pstm.setString(1, "%" + pword + "%");
            pstm.setString(2, "%" + pword + "%");
            pstm.setString(3, "%" + pword + "%");

            rs = pstm.executeQuery();
            while (rs.next()) {
                EntityPersona entity = new EntityPersona();
                entity.setPer_cod(rs.getString("per_cod"));
                entity.setPer_nom(rs.getString("per_nom"));
                entity.setPer_appm(rs.getString("per_appm"));
                entity.setPer_prof(rs.getString("per_prof"));
                entity.setPer_telf(rs.getString("per_telf"));
                entity.setPer_cel(rs.getString("per_cel"));
                entity.setPer_email(rs.getString("per_email"));
                entity.setPer_dir(rs.getString("per_dir"));
                entity.setPer_fnac(rs.getString("per_fnac"));
                entity.setPer_flug(rs.getString("per_flug"));
                entity.setPer_type(rs.getInt("per_type"));
                entity.setPer_pass(rs.getString("per_pass"));
                persona.add(entity);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return persona;
    }

    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (db != null) {
                db.close();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

}

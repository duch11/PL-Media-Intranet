package plmedia.intranet.dao;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import plmedia.intranet.model.Wing;

import java.sql.*;

/**
 * DBUtil extends JdbcUserDetailsManager.
 * Adds own prepared statements and methods.
 * @author Tobias Thomsen
 */

public class DBUtil extends JdbcUserDetailsManager  {

    // SQL's
    public static final String DEF_CREATE_ALLERGEN_SQL = "insert into allergen (allergen_name, allergen_description) values (?,?)";
    public static final String DEF_ADD_ALLERGEN_SQL = "insert into child_allergen (fk_child_id, fk_allergen_id) values (?,?)";
/* test */ public static final String TEST_STRING_SQL = "SELECT * FROM wing";
/* test */ public static final String SECOND_TEST_STRING_SQL = "SELECT first_name FROM user";


    // Fields
    private String createAllergenSql = DEF_CREATE_ALLERGEN_SQL;
    private String addAllergenSql = DEF_ADD_ALLERGEN_SQL;



/* test */ private String test = TEST_STRING_SQL;
/* test */ private String second_test = SECOND_TEST_STRING_SQL;

    // Methods

    /**
     * Creates a new allergen, adding it to the database
     * @param name
     * @param description
     */
    public void createAllergen(String name, String description) {

        getJdbcTemplate().update(createAllergenSql, new PreparedStatementSetter() {
            // Anonymous method
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, name);
                ps.setString(2, description);
            }
        });
    }

    /**
     * Adds an existing allergen to a child
     * @param child_id
     * @param allergen_id
     */
    public void addAllergen(int child_id, int allergen_id){
        getJdbcTemplate().update(addAllergenSql, new PreparedStatementSetter() {
            // Anonymous method
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, child_id);
                ps.setInt(2, allergen_id);
            }
        });
    }

    /**
     * 'test' works by knowing how many much data it get and with which datatype and then feeds them to a constructor (The wing constructor isn't commited - was only used for testing).
     */
    public void test(){
        try(
                Connection conn = ConMan.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(test)
        ) {
            int i = 1;
            while (rs.next()) {
                //KOMMENTERET UD INDTIL KONSTRUKTOR I WING ER LAVET
                //Wing wing = new Wing(rs.getInt("wing_id"), rs.getString("wing_name"), rs.getString("wing_description"));
                //System.out.println(wing.getWingName());
                i++;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void bruh(){
        try(
                Connection conn = ConMan.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(second_test);
        ) {
            while(rs.next()){
            System.out.println(rs.getString(1));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}

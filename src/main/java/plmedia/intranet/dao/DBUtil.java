package plmedia.intranet.dao;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * DBUtil extends JdbcUserDetailsManager.
 * Adds own prepared statements and methods.
 * @author Tobias Thomsen
 */

public class DBUtil extends JdbcUserDetailsManager {

    // SQL's
    public static final String DEF_CREATE_ALLERGEN_SQL = "insert into allergen (allergen_name, allergen_description) values (?,?)";
    public static final String DEF_ADD_ALLERGEN_SQL = "insert into child_allergen (fk_child_id, fk_allergen_id) values (?,?)";

    public static final String CALL_TEST = "{CALL test_sp(?)}";

    // Fields
    private String createAllergenSql = DEF_CREATE_ALLERGEN_SQL;
    private String addAllergenSql = DEF_ADD_ALLERGEN_SQL;

    private String test = CALL_TEST;

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
     * Why Doesn't Glue Stick To The Inside Of The Glue Bottle?
     * @param id
     */
    public void test(String id){
        try(
            Connection conn = ConnectionManager.getConnection();
            CallableStatement statement = conn.prepareCall(test);
        ) {
            statement.execute();
            statement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}

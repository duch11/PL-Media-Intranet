package plmedia.intranet.dao;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

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


    // Fields
    private String createAllergenSql = DEF_CREATE_ALLERGEN_SQL;


    // Methods
    public void createAllergen(String name, String description) {

        getJdbcTemplate().update(createAllergenSql, new PreparedStatementSetter() {
            // Anonymous method
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, name);
                ps.setString(2, description);
            }
        });
    }


}

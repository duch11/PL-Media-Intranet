package plmedia.intranet.dao.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import plmedia.intranet.dao.ConMan;
import plmedia.intranet.dao.Statements;
import plmedia.intranet.model.Employee;
import plmedia.intranet.model.Parent;


public class DBcreate {

  Util util = new Util();

  public int createParent(Parent parent) {
    try(
        PreparedStatement stmt = ConMan.prepStat(Statements.DEF_CREATE_PARENT_USER_SQL);
    ) {

      if (util.CheckEmail(parent.getUserEmail()) != 10){
        stmt.setString(1, parent.getPassword());
        stmt.setString(2, parent.getUserEmail());
        stmt.setString(3, parent.getFirstName());
        stmt.setString(4, parent.getLastName());
        stmt.setString(5, "ROLE_PAR");
        stmt.setInt(6, 1);

        stmt.executeUpdate();
        System.out.println("Parent user created");
        return 1; // Error codes?
      }
      System.out.println("Parent user NOT created");
    } catch (SQLException e){
      e.printStackTrace();
    }
    return -1; // Error codes?
  }

  public int createEmployee(Employee employee) {
    try(
        PreparedStatement stmt = ConMan.prepStat(Statements.DEF_CREATE_EMPLOYEE_USER_SQL);
    ) {

      if (util.CheckEmail(employee.getUserEmail()) != 10){
        stmt.setString(1, employee.getPassword());
        stmt.setString(2, employee.getUserEmail());
        stmt.setString(3, employee.getFirstName());
        stmt.setString(4, employee.getLastName());
        stmt.setString(5, "ROLE_EMP");
        stmt.setInt(6, 1);


        stmt.executeUpdate();
        System.out.println("Employee user created");
        return 1; // Error codes?
      }
      System.out.println("Employee user NOT created");
    } catch (SQLException e){
      e.printStackTrace();
    }
    return -1; // Error codes?
  }
}

package plmedia.intranet.dao.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;
import plmedia.intranet.dao.DBUtil.DBcreate;
import plmedia.intranet.model.Employee;
import plmedia.intranet.model.Parent;
import plmedia.intranet.model.User;

/**
 * A repository for Employee objects.
 * @author Tobias Thomsen
 * @author Simon le Févre Ryom
 */

@Repository
public class EmployeeRepo implements IRepo<Employee>, IUserRepo<Employee>  {


  DBcreate dbc = new DBcreate();

  /**
   * Creates Employee object. Takes logic from DBcreate.
   * @param employee
   * @return int
   */
  @Override
  public int Create(Employee employee) {
    return dbc.createEmployee(employee);
  }

  @Override
  public Employee Read(int i) {
    return null;
  }

  @Override
  public int Update(Employee employee) {
    return 0;
  }

  @Override
  public int Delete(Employee employee) {
    return 0;
  }

  @Override
  public ArrayList<Employee> ReadAll() {
    return null;
  }

  /**
   *
   * @param group
   * @return
   */
  public ArrayList<User> ReadGroup(String group) {
    return null;
  }
}

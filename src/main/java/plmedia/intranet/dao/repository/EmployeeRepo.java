package plmedia.intranet.dao.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;
import plmedia.intranet.dao.DBUtil.*;
import plmedia.intranet.model.Employee;


/**
 * A repository for Employee objects.
 * @author Tobias Thomsen
 * @author Simon le Févre Ryom
 */

@Repository
public class EmployeeRepo implements IRepo<Employee> {


  DBcreate dbc = new DBcreate();
  DBread dbr = new DBread();
  DBupdate dbu = new DBupdate();

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
  public Employee Read(int id) {
    return dbr.readEmployeeByID(id);
  }

  @Override
  public int Update(Employee employee) {
    return dbu.updateEmployee(employee);
  }

  @Override
  public int Delete(Employee employee) {
    return 0;
  }

  @Override
  public ArrayList<Employee> ReadAll() {
    return dbr.readAllEmployees();
  }

  public ArrayList<Employee> readAllEmployeesByGroup(int id){
    return dbr.readAllEmployeesByGroup(id);
  }

}

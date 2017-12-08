package plmedia.intranet.dao.repository;

import ch.qos.logback.core.db.dialect.DBUtil;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import plmedia.intranet.dao.DBUtil.DBupdate;
import plmedia.intranet.dao.DBUtil.Util;
import plmedia.intranet.dao.DBUtil.DBcreate;
import plmedia.intranet.model.Child;
import plmedia.intranet.model.Employee;
import plmedia.intranet.model.Parent;
import plmedia.intranet.model.Wing;

/**
 * Repository for the Util class.
 * @author Simon le Févre Ryom
 * @author Tobias Thomsen
 */
@Repository
public class UtilRepo {

  Util util = new Util();
  DBupdate dbu = new DBupdate();

  public int checkEmail(String email){
    return util.checkEmail(email);
  }

  public int updateChildToParent(Parent parent, ArrayList<Integer> newChildren) {
    return dbu.updateChildToParent(parent, newChildren);
  }

  public int updatePermissionByID(Employee employee, ArrayList<Integer> newPermission) {
    return dbu.updatePermissionByID(employee, newPermission);
  }

  public int updateChildAllergens(Child child, ArrayList<Integer> newAllergen) {
    return dbu.updateChildAllergens(child, newAllergen);
  }
}





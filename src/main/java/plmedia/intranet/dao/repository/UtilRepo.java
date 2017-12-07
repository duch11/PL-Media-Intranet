package plmedia.intranet.dao.repository;

import ch.qos.logback.core.db.dialect.DBUtil;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import plmedia.intranet.dao.DBUtil.Util;
import plmedia.intranet.dao.DBUtil.DBcreate;
import plmedia.intranet.model.Child;
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
  DBcreate dbc = new DBcreate();

  public int checkEmail(String email){
    return util.checkEmail(email);
  }

  public int addChildrenToParent(Parent parent, ArrayList<Child> childList) {
    return util.addChildrenToParent(parent, childList);
  }

  public int deleteChildrenFromParent(Parent parent, ArrayList<Child> childList) {
    return util.deleteChildrenFromParent(parent, childList);
  }
  
}

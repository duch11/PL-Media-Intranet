package plmedia.intranet.dao.repository;

import ch.qos.logback.core.db.dialect.DBUtil;
import plmedia.intranet.dao.DBUtil.Util;
import plmedia.intranet.dao.DBUtil.DBcreate;
import plmedia.intranet.model.Wing;

/**
 * Repository for the Util class.
 * @author Simon le Févre Ryom
 */
public class UtilRepo {

  Util util = new Util();

  DBcreate dbc = new DBcreate();

  public int checkEmail(String email){
    return util.checkEmail(email);
  }

  
}

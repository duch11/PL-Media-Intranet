package plmedia.intranet.dao.repository;

import ch.qos.logback.core.db.dialect.DBUtil;
import plmedia.intranet.dao.DBUtil.Util;
import plmedia.intranet.model.Wing;

public class UtilRepo {

  Util util = new Util();

  public int createWing() {
    return 0;
  }

  public Wing readWing(int i) {
    return null;
  }

  public int checkEmail(String email){
    return util.checkEmail(email);
  }

  
}

package plmedia.intranet.dao.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import plmedia.intranet.dao.ConMan;
import plmedia.intranet.dao.Statements;
import plmedia.intranet.model.Permission;

public class DBupdate {

  public void updatePermissionByID(int id) {
    try (
        PreparedStatement stmt = ConMan.prepStat(Statements.DEF_UPDATE_PERMISSION_BY_ID);
    ) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();
      rs.first();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}

package plmedia.intranet.dao.repository;

import java.util.ArrayList;
import plmedia.intranet.dao.DBUtil.DBcreate;
import plmedia.intranet.model.Group;

/**
 * A repository for user groups.
 * @author Simon le Févre Ryom
 */
public class GroupRepo implements IRepo<Group> {

   DBcreate db = new DBcreate();

  @Override
  public int Create(Group group) {
    return db.createGroup(group);
  }

  @Override
  public Group Read(int i) {
    return null;
  }

  @Override
  public int Update(Group group) {
    return 0;
  }

  @Override
  public int Delete(Group group) {
    return 0;
  }

  @Override
  public ArrayList<Group> ReadAll() {
    return null;
  }
}







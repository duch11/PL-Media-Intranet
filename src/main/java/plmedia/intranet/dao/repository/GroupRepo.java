package plmedia.intranet.dao.repository;

import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import plmedia.intranet.dao.DBUtil.DBcreate;
import plmedia.intranet.dao.DBUtil.DBread;
import plmedia.intranet.dao.DBUtil.DBupdate;
import plmedia.intranet.model.Group;

/**
 * A repository for user groups.
 * @author Simon le Févre Ryom
 * @author Tobias Thomsen
 */

@Repository
public class GroupRepo implements IRepo<Group> {

   DBcreate dbc = new DBcreate();
   DBread dbr = new DBread();
   DBupdate dbu = new DBupdate();

  @Override
  public int Create(Group group) {
    return dbc.createGroup(group);
  }

  @Override
  public Group Read(int id) {
    return dbr.readGroupByID(id);
  }

  @Override
  public int Update(Group group) {
    return dbu.updateGroup(group);
  }

  @Override
  public int Delete(Group group) {
    return 0;
  }

  @Override
  public ArrayList<Group> ReadAll() {
    return dbr.readAllGroups();
  }

  public Group readGroupByUserID(int id){
    return dbr.readGroupByUserID(id);
  }

  public ArrayList<Group> readGroupIDsByUserID(int id) {
    return dbr.readGroupIDsByUserID(id);
  }
}







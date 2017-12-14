package plmedia.intranet.dao.repository;

import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import plmedia.intranet.dao.DBUtil.DBcreate;
import plmedia.intranet.dao.DBUtil.DBdelete;
import plmedia.intranet.dao.DBUtil.DBread;
import plmedia.intranet.dao.DBUtil.DBupdate;
import plmedia.intranet.model.Wing;

/**
 * A repository for Wings
 * @author Simon le Févre Ryom
 * @author Tobias Thomsen
 */

@Repository
public class WingRepo implements IRepo<Wing>{

  DBcreate dbc = new DBcreate();
  DBread dbr = new DBread();
  DBupdate dbu = new DBupdate();
  DBdelete dbd = new DBdelete();

  @Override
  public int Create(Wing wing) {
    return dbc.createWing(wing);
  }

  @Override
  public Wing Read(int id) {
    return dbr.readWingByID(id);
  }

  @Override
  public int Update(Wing wing) {
    return dbu.updateWing(wing);
  }

  @Override
  public int Delete(Wing wing) {
    return dbd.deleteWing(wing);
  }

  @Override
  public ArrayList<Wing> ReadAll() {
    return dbr.readAllWings();
  }

  public Wing readWingByUserID(int id) {
    return dbr.readWingByUserID(id);
  }

  public ArrayList<Wing> readWingIDsByUserID(int id) {
    return dbr.readWingIDsByUserID(id);
  }

  public ArrayList<Wing> readWingIDsByChildID(int id) {
    return dbr.readWingIDsByChildID(id);
  }

  public Wing readWingByChildID(int id){ return dbr.readWingByChildID(id); }

}

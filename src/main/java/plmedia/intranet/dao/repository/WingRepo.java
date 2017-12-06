package plmedia.intranet.dao.repository;

import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import plmedia.intranet.dao.DBUtil.DBcreate;
import plmedia.intranet.dao.DBUtil.DBread;
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

  /**
   * Creates Wing object. Takes logic from DBcreate.
   * @param wing
   * @return int
   */
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
    return 0;
  }

  @Override
  public int Delete(Wing wing) {
    return 0;
  }

  @Override
  public ArrayList<Wing> ReadAll() {
    return dbr.readAllWings();
  }
}

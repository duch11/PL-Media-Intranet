package plmedia.intranet.dao.repository;

import java.util.ArrayList;
import plmedia.intranet.dao.DBUtil.DBcreate;
import plmedia.intranet.model.Wing;

/**
 * A repository for Wings
 * @author Simon le Févre Ryom
 */
public class WingRepo implements IRepo<Wing>{

  DBcreate dbc = new DBcreate();

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
  public Wing Read(int i) {
    return null;
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
    return null;
  }
}

package plmedia.intranet.dao.repository;

import java.util.ArrayList;

/**
 *
 * @author Simon le Févre Ryom
 * @author Tobias Thomsen
 */
public interface IRepo<T> {

  /**
   * @param t
   * @return
   */
  public int Create(T t);

  /**
   * @param id
   * @return
   */
  public T Read(int id);

  /**
   * Updates the user
   * @return
   */
  public int Update(T t);

  /**
   * Deletes the user
   * @return
   */
  public int Delete(T t);

  /**
   * Reads all
   * @return
   */
  public ArrayList<T> ReadAll();
}

package plmedia.intranet.dao.repository;

public interface IUserRepo<T> {

  /**
   *
   * @author Tobias Thomsen
   */



  /**
   * Checks the database for an existing email
   * @param email
   * @return
   */
  public boolean CheckEmail(String email);


}

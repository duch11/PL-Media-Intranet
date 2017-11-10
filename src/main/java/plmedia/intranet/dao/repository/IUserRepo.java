package plmedia.intranet.dao.repository;

import java.util.ArrayList;
import plmedia.intranet.model.*;

public interface IUserRepo<T> {

    /**
     *
     * @author Tobias Thomsen
     */

    /**
     * Creates the user
     * @param firstName
     * @param lastName
     * @param password
     * @param email
     * @param permissions
     * @return
     */
    public int Create(String firstName, String lastName, String password, String email, ArrayList<String> permissions[]);

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
     * Checks the database for an existing email
     * @param email
     * @return
     */
    public boolean CheckEmail(String email);

    /**
     *
     * @param group
     * @return
     */
    public ArrayList<User> ReadGroup(String group);


    /**
     * Returns all users
     * @return
     */
    public ArrayList<User> ReadAll();
}

package plmedia.intranet.repository;

import java.util.ArrayList;
import plmedia.intranet.model.*;

public interface IUserRepo {

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

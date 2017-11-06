package plmedia.intranet.repository;

import plmedia.intranet.model.User;

import java.util.ArrayList;

/**
 * @author Tobias Thomsen
 */

public class UserRepo implements IUserRepo {
    @Override
    public int Create(String firstName, String lastName, String password, String email, ArrayList<String>[] permissions) {
        return 0;
    }

    @Override
    public boolean CheckEmail(String email) {
        return false;
    }

    @Override
    public ArrayList<User> ReadGroup(String group) {
        return null;
    }

    @Override
    public ArrayList<User> ReadAll() {
        return null;
    }
}

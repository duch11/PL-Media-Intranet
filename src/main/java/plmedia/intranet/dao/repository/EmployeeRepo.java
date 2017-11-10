package plmedia.intranet.dao.repository;

import java.util.ArrayList;

/**
 * @author Tobias Thomsen
 */

public class EmployeeRepo extends UserRepo {

    private ArrayList<String> Groups = new ArrayList<>();




    /**
     *
     * @return
     */
    public ArrayList<String> getGroups(){
        return Groups;
    }

    /**
     *
     * @param s
     */
    public void addGroup(String s){

    }

}

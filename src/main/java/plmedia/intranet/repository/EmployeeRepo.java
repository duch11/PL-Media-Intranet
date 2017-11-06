package plmedia.intranet.repository;

import plmedia.intranet.model.Employee;

import java.util.ArrayList;

/**
 * @author Tobias Thomsen
 */

public class EmployeeRepo extends UserRepo {

    private ArrayList<String> Groups = new ArrayList<>();


    /**
     * Updates the employee
     * @param employee
     * @return
     */
    public int Update(Employee employee){

        return 0;
    }

    /**
     * Deletes the employee
     * @param employee
     * @return
     */
    public int Delete(Employee employee){

        return 0;
    }

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

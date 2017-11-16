package plmedia.intranet.dao;

import java.sql.*;


/**
 * @author Tobias Thomsen
 */
public class Prepstmt {



    public static final String DEF_CREATE_ALLERGEN = "INSERT INTO allergen" +
            "(allergen_name, allergen_description) VALUES (?,?)";

    public static void CreateAllergen(String name, String disc){

    }
}

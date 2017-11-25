package plmedia.intranet.dao;

import java.util.ArrayList;
import plmedia.intranet.model.Child;
import plmedia.intranet.model.Parent;

public interface DBUtilInterface {

  ArrayList<Parent> getAllParents();

  ArrayList<String> getPermissions(int id);

  ArrayList<Integer> GetChildrenIDByParentID(int id);

  Child getChildObject(int id);


}

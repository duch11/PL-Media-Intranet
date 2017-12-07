package plmedia.intranet.controller;
import com.sun.org.apache.xpath.internal.operations.Mod;
import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import plmedia.intranet.dao.repository.EmployeeRepo;
import plmedia.intranet.dao.repository.GroupRepo;
import plmedia.intranet.dao.repository.IUserRepo;
import plmedia.intranet.dao.repository.ParentRepo;
import plmedia.intranet.dao.repository.PermissionRepo;
import plmedia.intranet.model.*;

import java.util.Date;

/**
 * @author Jonas Holm
 * @author Andreas
 * */

@Controller
public class AdminController {

  @Autowired
  ParentRepo parentRepo;

  @Autowired
  EmployeeRepo employeeRepo;

  @Autowired
  GroupRepo groupRepo;

  @Autowired
  PermissionRepo permissionRepo;

  //TEST KODE
  //TODO: SLET MIG TESTKODE TIL GRUPPER
  ArrayList<Group> employeeGroups = new ArrayList<>();
  ArrayList<Employee> employees = new ArrayList<>();
  ArrayList<Employee> employeesSorted;

  ArrayList<Parent> parents = new ArrayList<>();
  ArrayList<Child> children = new ArrayList<>();
  ArrayList<Permission> globalPermissions = new ArrayList<>();

  public AdminController() {
    java.sql.Date dato = new java.sql.Date(2);
    globalPermissions.add(new Permission(1, "Kill hitler", "allows something"));
    globalPermissions.add(new Permission(2, "Kill stalin", "allows something"));
    globalPermissions.add(new Permission(3, "Kill bush", "allows something"));
    globalPermissions.add(new Permission(4, "Kill spongebob", "allows something"));

    employeeGroups.add(new Group(1,"Ledelsen", "Skal have mere i løn"));
    employeeGroups.add(new Group(2,"Ansatte", "De er seje"));
    employeeGroups.add(new Group(3,"Praktikanter", "Andrulle er sød"));


    Employee hej = new Employee("123", "jonas.dk", "jonas", "Holm");
    hej.setUserId(0);
    ArrayList<Permission> perm = new ArrayList<>();
    perm.add(globalPermissions.get(0));
    perm.add(globalPermissions.get(1));
    perm.add(globalPermissions.get(2));
    perm.add(globalPermissions.get(3));
    hej.setPermissions(perm);

    Employee hej1 = new Employee("123", "faisal.dk", "faisal", "faisal");
    hej1.setUserId(1);
    ArrayList<Permission> perm1 = new ArrayList<>();
    perm1.add(globalPermissions.get(0));
    perm1.add(globalPermissions.get(1));
    perm1.add(globalPermissions.get(2));
    perm1.add(globalPermissions.get(3));
    hej1.setPermissions(perm1);

    Employee hej2 = new Employee("123", "ssa.dk", "Andreas", "Nissen");
    hej2.setUserId(2);
    ArrayList<Permission> perm2 = new ArrayList<>();
    perm2.add(globalPermissions.get(0));
    perm2.add(globalPermissions.get(2));
    perm2.add(globalPermissions.get(3));
    hej2.setPermissions(perm2);

    Employee hej3 = new Employee("123", "pid.ss", "Toby", "Thomsen");
    hej3.setUserId(3);
    ArrayList<Permission> perm3 = new ArrayList<>();
    perm3.add(globalPermissions.get(0));
    perm3.add(globalPermissions.get(1));
    perm3.add(globalPermissions.get(3));
    hej3.setPermissions(perm3);

    Employee hej4 = new Employee("123", "jggonas.dk", "Simon", "BOOIII");
    hej4.setUserId(4);
    ArrayList<Permission> perm4 = new ArrayList<>();
    perm4.add(globalPermissions.get(1));
    perm4.add(globalPermissions.get(2));
    perm4.add(globalPermissions.get(3));
    hej4.setPermissions(perm4);

    hej.setGroup(employeeGroups.get(1));
    hej1.setGroup(employeeGroups.get(0));
    hej2.setGroup(employeeGroups.get(2));
    hej3.setGroup(employeeGroups.get(1));
    hej4.setGroup(employeeGroups.get(2));

    employees.add(hej);
    employees.add(hej1);
    employees.add(hej2);
    employees.add(hej3);
    employees.add(hej4);


    parents.add(new Parent(0,"123","jonas@sss.dk", "Sten","Hansen", perm1));
    parents.add(new Parent(1, "123","jonas@sss.dk", "Argild","Gertsen",perm2));
    parents.add(new Parent(2,"123","jonas@sss.dk", "Jullebejler","Kanstrup",perm3));

    children.add(new Child("Alma","Sørensen", new java.sql.Date(2),"hejvej 123", 1));
    children.add(new Child("Carlo","Grimladen", new java.sql.Date(2),"hejvej 123", 1));
    children.add(new Child("Silas","Sørensen", new java.sql.Date(2),"hejvej 123", 1));
    children.add(new Child("Rui","LactoseFri", new java.sql.Date(2),"hejvej 123", 1));
  }

  //TEST KODESLUT

  /**
   * Base method for showing adminpanel
  * makes renaming and adding "universal" actions more sensible
  */

  public String showAdminPanel(Model model, Principal principal) {

    /**
     * ENG: Principal DK: "Grund-sikkerhedskonto"
     * Spring framework injecter den selv, ligesom den goer med Model.
     * */
    model.addAttribute("test", principal.getName());
    model.addAttribute("employeeGroups", employeeGroups);
    return "adminpanel";
  }


  @RequestMapping(value = {"/admin/employees", "/admin"}, method = RequestMethod.GET)
  public String adminPanelEmp(Model model, Principal principal) {
    model.addAttribute("allUsers", true);
    model.addAttribute("newEmployee", new Employee());

    model.addAttribute("employees", employees);
    model.addAttribute("employees", employeeRepo.ReadAll());
    return showAdminPanel(model, principal);
  }

  @RequestMapping(value = {"/admin/employees"}, method = RequestMethod.GET, params = {"groupID"})
  public String adminPanelEmpGroup(Model model, Principal principal, @RequestParam int groupID) {

    for (Group g : employeeGroups){
      if(g.getId() == groupID){
        model.addAttribute("currentGroup", g);
        employeesSorted = new ArrayList<>();
        for (Employee emp : employees){
          if(emp.getGroup().getId() == groupID){
            employeesSorted.add(emp);
          }
        }
      }
    }
    model.addAttribute("employees", employeesSorted);
    model.addAttribute("newEmployee", new Employee());

    return showAdminPanel(model, principal);
  }

  @RequestMapping(value = {"/admin/parents"}, method = RequestMethod.GET)
  public String adminPanelParents(Model model, Principal principal) {
    model.addAttribute("parents", parents);
    model.addAttribute("newParent", new Parent());
    return showAdminPanel(model, principal);
  }

  @RequestMapping(value = {"/admin/children"}, method = RequestMethod.GET)
  public String adminPanelChildren(Model model, Principal principal) {
    model.addAttribute("children", children);
    model.addAttribute("newChild", new Child());
    return showAdminPanel(model, principal);
  }

  @RequestMapping(value = {"/admin/details"}, method = RequestMethod.GET, params = {"employee"})
  public String empDetails(Model model,Principal principal, @RequestParam int employee) {
    for(User user : employees){
      if(user.getUserId() == employee){
        model.addAttribute("user", user);
        model.addAttribute("employeeDetails", true);
        model.addAttribute("generalPermissions", permissionRepo.readAllPermissions());
        model.addAttribute("allGroups", employeeGroups);
        showAdminPanel(model, principal);
        return "detailsview";
      }
    }
    return "redirect:/admin/employees";
  }

  @RequestMapping(value = {"/admin/details"}, method = RequestMethod.GET, params = {"parent"})
  public String parentDetails(Model model, Principal principal, @RequestParam int parent) {
    for(User user : parents){
      if(user.getUserId() == parent){
        model.addAttribute("user", user);
        model.addAttribute("parentDetails", true);
        showAdminPanel(model, principal);
        return "detailsview";
      }
    }
    return "redirect:/admin/parents";
  }

  @RequestMapping(value = {"/admin/details"}, method = RequestMethod.GET, params = {"child"})
  public String childDetails(Model model, Principal principal, @RequestParam int child) {
    model.addAttribute("child", "repo.getChild()" + child);

    showAdminPanel(model,principal);
    return "children";
  }


  /**
   * Create Methods
   * */
  @RequestMapping(value = {"/admin/create/employee"}, method = RequestMethod.POST)
  public String createUser(@ModelAttribute Employee newEmployee){

    System.out.println(newEmployee);

    employeeRepo.Create(newEmployee);

    return "redirect:/admin/employees";
  }

  @RequestMapping(value = {"/admin/create/parent"}, method = RequestMethod.POST)
  public String createParent(@ModelAttribute Parent newParent){

    System.out.println(newParent);

    parentRepo.Create(newParent);

    return "redirect:/admin/parents";
  }

  /**
   * Employee only update methods
   * */
  @RequestMapping(value = {"/admin/update/employee"}, method = RequestMethod.POST, params = {"permissionIDs", "ID"})
  public String updatePermission(@RequestParam ArrayList<Integer> permissionIDs, @RequestParam int ID ){
    ArrayList<Permission> permissions = new ArrayList<>();

    for (Integer i : permissionIDs){
      if(i.intValue() != -1){
        permissions.add(permissionRepo.readPermissionByID(i));
      }
    }
    //TODO: impl update permissions with repo
    System.out.println(ID);
    System.out.println(permissions);
    return "redirect:/admin/details?employee=" + ID;
  }

  @RequestMapping(value = {"/admin/update/employee"}, method = RequestMethod.POST, params = {"groupID", "ID"})
  public String updateGroup(@RequestParam int groupID, @RequestParam int ID ){
    Group newGroup;
    for (Group g : employeeGroups){
      if(g.getId() == groupID){
        //TODO: impl update group with repo
        newGroup = g;
        System.out.println(newGroup.getGroupName() + " " + ID);
      }
    }
    return "redirect:/admin/details?employee=" + ID;
  }

  /**
   * Update Name + utility method (for redirection between parent and user)
   * */
  @RequestMapping(value = {"/admin/update/employee"}, method = RequestMethod.POST, params = {"firstName", "lastName", "ID"})
  public String updateEmpName(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int ID){
    updateName(firstName,lastName,ID);
    return "redirect:/admin/details?employee=" + ID;
  }

  @RequestMapping(value = {"/admin/update/parent"}, method = RequestMethod.POST, params = {"firstName", "lastName", "ID"})
  public String updateParName(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int ID){
    updateName(firstName,lastName,ID);
    return "redirect:/admin/details?parent=" + ID;
  }

  public void updateName(String firstName, String lastName, int ID){
    System.out.println(firstName + " " + lastName + " " + ID);
  }

  /**
   * Update Email + utility method (for redirection between parent and user)
   * */
  @RequestMapping(value = {"/admin/update/employee"}, method = RequestMethod.POST, params = {"email", "ID"})
  public String updateEmpEmail(@RequestParam String email, @RequestParam int ID){
    updateEmail(email,ID);
    return "redirect:/admin/details?employee=" + ID;
  }

  @RequestMapping(value = {"/admin/update/parent"}, method = RequestMethod.POST, params = {"email", "ID"})
  public String updateParEmail(@RequestParam String email, @RequestParam int ID){
    updateEmail(email,ID);
    return "redirect:/admin/details?parent=" + ID;
  }

  public void updateEmail(String email, int ID){
    System.out.println(email + " " + ID);
  }

  /**
   * Update Password + utility method
   * */
  @RequestMapping(value = {"/admin/update/parent"}, method = RequestMethod.POST, params = {"oldPass", "newPass", "newPassRepeat", "ID"})
  public String updateParPassword(@RequestParam String oldPass,@RequestParam String newPass,@RequestParam String newPassRepeat, @RequestParam int ID) {
    updatePassword(oldPass,newPass,newPassRepeat, ID);
    return "redirect:/admin/details?parent=" + ID;
  }

  @RequestMapping(value = {"/admin/update/employee"}, method = RequestMethod.POST, params = {"oldPass", "newPass", "newPassRepeat", "ID"})
  public String updateEmpPassword(@RequestParam String oldPass,@RequestParam String newPass,@RequestParam String newPassRepeat, @RequestParam int ID) {
    updatePassword(oldPass,newPass,newPassRepeat, ID);
    return "redirect:/admin/details?employee=" + ID;
  }

  public void updatePassword(String oldPass, String newPass, String newPassRepeat, int ID){
    System.out.println(oldPass + " " + newPass + " " + newPassRepeat + " " + ID);
  }

}


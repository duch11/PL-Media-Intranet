package plmedia.intranet.controller;
import com.sun.org.apache.xpath.internal.operations.Mod;
import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import plmedia.intranet.dao.repository.EmployeeRepo;
import plmedia.intranet.dao.repository.IUserRepo;
import plmedia.intranet.dao.repository.ParentRepo;
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

  //TEST KODE
  //TODO: SLET MIG TESTKODE TIL GRUPPER
  ArrayList<Group> employeeGroups = new ArrayList<>();
  ArrayList<User> employees = new ArrayList<>();
  ArrayList<Parent> parents = new ArrayList<>();
  ArrayList<Child> children = new ArrayList<>();
  ArrayList<Permission> globalPermissions = new ArrayList<>();

  public AdminController() {
    globalPermissions.add(new Permission(1, "Kill hitler", "allows something"));
    globalPermissions.add(new Permission(2, "Kill stalin", "allows something"));
    globalPermissions.add(new Permission(3, "Kill bush", "allows something"));
    globalPermissions.add(new Permission(4, "Kill spongebob", "allows something"));

    employeeGroups.add(new Group(1,"Ledelsen", "beskrivelse"));
    employeeGroups.add(new Group(2,"Ansatte", "beskrivelse"));
    employeeGroups.add(new Group(3,"Praktikanter", "beskrivelse"));





    Employee hej = new Employee("123", "jonas.dk", "jonas", "Holm");
    hej.setUserId(1);
    ArrayList<Permission> perm1 = new ArrayList<>();
    perm1.add(globalPermissions.get(0));
    perm1.add(globalPermissions.get(1));
    perm1.add(globalPermissions.get(2));
    perm1.add(globalPermissions.get(3));
    hej.setPermissions(perm1);

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

    employees.add(hej);
    employees.add(hej2);
    employees.add(hej3);
    employees.add(hej4);

    parents.add(new Parent(0,"123","jonas@sss.dk", "Sten","Hansen", perm1));
    parents.add(new Parent(1, "123","jonas@sss.dk", "Argild","Gertsen",perm2));
    parents.add(new Parent(2,"123","jonas@sss.dk", "Jullebejler","Kanstrup",perm3));

    children.add(new Child("Alma","Sørensen", new Date(),"hejvej 123"));
    children.add(new Child("Carlo","Grimladen", new Date(),"hejvej 123"));
    children.add(new Child("Silas","Sørensen", new Date(),"hejvej 123"));
    children.add(new Child("Rui","LactoseFri", new Date(),"hejvej 123"));
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
    model.addAttribute("allUsers", employeeGroups.get(0));
    model.addAttribute("newEmployee", new Employee());
    model.addAttribute("employees", employees);
    return showAdminPanel(model, principal);
  }

  @RequestMapping(value = {"/admin/employees"}, method = RequestMethod.GET, params = {"groupID"})
  public String adminPanelEmpGroup(Model model, Principal principal, @RequestParam int groupID) {

    for (Group g : employeeGroups){
      if(g.getId() == groupID){
        model.addAttribute("currentGroup", g);
      }
    }
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
  public String userDetails(Model model,Principal principal, @RequestParam int employee) {
    for(User user : employees){
      if(employee == user.getUserId()){
        model.addAttribute("user", user);
        model.addAttribute("employeeDetails", true);
      }
    }
    model.addAttribute("globalPermissions", globalPermissions);
    showAdminPanel(model, principal);
    return "detailsview";
  }

  @RequestMapping(value = {"/admin/details"}, method = RequestMethod.GET, params = {"parent"})
  public String parentDetails(Model model, Principal principal, @RequestParam int parent) {
    for(User user : parents){
      if(parent == user.getUserId()){
        model.addAttribute("user", user);
        model.addAttribute("parentDetails", true);
      }
    }
    model.addAttribute("globalPermissions", globalPermissions);
    showAdminPanel(model, principal);
    return "detailsview";
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
  public String createParent(Model model, @ModelAttribute Parent newParent){

    System.out.println(newParent);

    parentRepo.Create(newParent);

    return "redirect:/admin/parents";
  }


}


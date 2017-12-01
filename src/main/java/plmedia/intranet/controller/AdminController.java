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
import plmedia.intranet.model.Child;
import plmedia.intranet.model.Employee;
import plmedia.intranet.model.Group;
import plmedia.intranet.model.Parent;
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
  ArrayList<Parent> parents = new ArrayList<>();
  ArrayList<Child> children = new ArrayList<>();

  public AdminController() {
    employeeGroups.add(new Group(1,"Ledelsen", "beskrivelse"));
    employeeGroups.add(new Group(2,"Ansatte", "beskrivelse"));
    employeeGroups.add(new Group(3,"Praktikanter", "beskrivelse"));

    parents.add(new Parent("123","jonas@sss.dk", "Sten","Hansen"));
    parents.add(new Parent("123","jonas@sss.dk", "Argild","Gertsen"));
    parents.add(new Parent("123","jonas@sss.dk", "Jullebejler","Kanstrup"));

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
    model.addAttribute("parent", new Parent());
    model.addAttribute("employee", new Employee());
    model.addAttribute("child", new Child());
    model.addAttribute("employeeGroups", employeeGroups);
    return "adminpanel";
  }


  @RequestMapping(value = {"/admin/employees", "/admin"}, method = RequestMethod.GET)
  public String adminPanelEmp(Model model, Principal principal) {
    model.addAttribute("currentGroup", employeeGroups.get(0));
    model.addAttribute("employees");
    return showAdminPanel(model, principal);
  }

  @RequestMapping(value = {"/admin/employees"}, method = RequestMethod.GET, params = {"groupID"})
  public String adminPanelEmpGroup(Model model, Principal principal, @RequestParam int groupID) {

    for (Group g : employeeGroups){
      if(g.getId() == groupID){
        model.addAttribute("currentGroup", g);
      }
    }

    return showAdminPanel(model, principal);
  }

  @RequestMapping(value = {"/admin/parents"}, method = RequestMethod.GET)
  public String adminPanelParents(Model model, Principal principal) {



    model.addAttribute("parents", parents);
    return showAdminPanel(model, principal);
  }

  @RequestMapping(value = {"/admin/children"}, method = RequestMethod.GET)
  public String adminPanelChildren(Model model, Principal principal) {
    model.addAttribute("children", children);
    return showAdminPanel(model, principal);
  }




  @RequestMapping(value = {"/admin/details"}, method = RequestMethod.GET, params = {"user"})
  public String userDetails(Model model,Principal principal, @RequestParam int user) {
    model.addAttribute("user", "Kurt Klausen");
    showAdminPanel(model, principal);
    return "detailsview";
  }

  @RequestMapping(value = {"/admin/details"}, method = RequestMethod.GET, params = {"child"})
  public String childDetails(Model model, @RequestParam int child) {
    model.addAttribute("child", "repo.getChild()" + child);
    return "detailsview";
  }

  @RequestMapping(value = {"/admin/details"}, method = RequestMethod.GET, params = {"parent"})
  public String parentDetails(Model model, @RequestParam int parent) {
    model.addAttribute("parent", "repo.getParent()" + parent);
    return "detailsview";
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


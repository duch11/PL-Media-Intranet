package plmedia.intranet.controller;


import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import plmedia.intranet.model.Employee;
import plmedia.intranet.model.User;

import java.util.ArrayList;

@Controller
public class AdminController {

  /**
   * Base method for showing adminpanel
  * makes renaming and adding "universal" actions more sensible
  */

  public String showAdminPanel(Model model, Principal principal) {

    /**
     * ENG: Principal DK: "Grund-sikkerhedskonto"
     * Spring framework injecter den selv, ligesom den gør med Model.
     * */
    System.out.println(principal.getClass().toString());
    String name = principal.getName();
    model.addAttribute("test", name);

    return "adminpanel";
  }


  @RequestMapping(value = {"/admin/employees", "/admin"}, method = RequestMethod.GET)
  public String adminPanelEmp(Model model, Principal principal) {

    model.addAttribute("employees");
    return showAdminPanel(model, principal);
  }

  @RequestMapping(value = {"/admin/employees"}, method = RequestMethod.GET, params = {"groupID"})
  public String adminPanelEmpGroup(Model model, Principal principal, @RequestParam int groupID) {
    model.addAttribute("employees" + groupID);
    return showAdminPanel(model, principal);
  }

  @RequestMapping(value = {"/admin/parents"}, method = RequestMethod.GET)
  public String adminPanelParents(Model model, Principal principal) {
    model.addAttribute("parents");
    return showAdminPanel(model, principal);
  }

  @RequestMapping(value = {"/admin/children"}, method = RequestMethod.GET)
  public String adminPanelChildren(Model model, Principal principal) {
    model.addAttribute("children");
    return showAdminPanel(model, principal);
  }




  @RequestMapping(value = {"/admin/details"}, method = RequestMethod.GET, params = {"user"})
  public String userDetails(Model model, @RequestParam int user) {
    model.addAttribute("user", "repo.getUser()" + user);
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


}


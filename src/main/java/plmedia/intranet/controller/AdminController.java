package plmedia.intranet.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

  /*
  * Base method for showing adminpanel
  * makes renaming and adding "universal" actions more sensible
  */

  public String showAdminPanel(Model model) {
    return "adminpanel";
  }


  @RequestMapping(value = {"/admin/employees", "/admin"}, method = RequestMethod.GET)
  public String adminPanelEmp(Model model) {

    model.addAttribute("employees");
    return showAdminPanel(model);
  }

  @RequestMapping(value = {"/admin/employees"}, method = RequestMethod.GET, params = {"groupID"})
  public String adminPanelEmpGroup(Model model, @RequestParam int groupID) {
    model.addAttribute("employees" + groupID);
    return showAdminPanel(model);
  }

  @RequestMapping(value = {"/admin/parents"}, method = RequestMethod.GET)
  public String adminPanelParents(Model model) {
    model.addAttribute("parents");
    return showAdminPanel(model);
  }

  @RequestMapping(value = {"/admin/children"}, method = RequestMethod.GET)
  public String adminPanelChildren(Model model) {
    model.addAttribute("children");
    return showAdminPanel(model);
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


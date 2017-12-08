package plmedia.intranet.controller;

import java.security.Principal;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import plmedia.intranet.dao.repository.ChildRepo;
import plmedia.intranet.dao.repository.ParentRepo;
import plmedia.intranet.model.Child;
import plmedia.intranet.model.Parent;

/**
 * @author Andreas Nissen
 * @author Jonas Holm
 */
@Controller
public class ParentController {

  @Autowired
  ParentRepo parentRepo;

  @Autowired
  ChildRepo childRepo;

  // lists
  ArrayList<Parent> parents = new ArrayList<>();
  ArrayList<Child> children = new ArrayList<>();


  public String showParentView(Model model, Principal principal) {


    model.addAttribute("test", principal.getName());
    return "parentview";
  }



  @RequestMapping(value = {"/parents"}, method = RequestMethod.GET)
  public String parentViewEmp(Model model, Principal principal) {
    model.addAttribute("allUsers", true);
    model.addAttribute("employees", parentRepo.ReadAll());

    model.addAttribute("parents", parents);
    return showParentView(model, principal);
  }





  public String showChildren(Model model, Principal principal) {

    model.addAttribute("test", principal.getName());
    return "children";
  }
  @RequestMapping(value = {"/parents/children"}, method = RequestMethod.GET)
  public String child(Model model, Principal principal) {


    model.addAttribute("children", children);
    return showChildren(model, principal);
  }




}

package plmedia.intranet.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Andreas Nissen
 * @author Jonas Holm
 */
@Controller
public class ParentController {



  public String showParentView(Model model, Principal principal) {


    model.addAttribute("test", principal.getName());
    return "parentview";
  }



  @RequestMapping(value = {"/parents"}, method = RequestMethod.GET)
  public String parentViewEmp(Model model, Principal principal) {

    model.addAttribute("parents");
    return showParentView(model, principal);
  }



  public String showChildren(Model model, Principal principal) {

    model.addAttribute("test", principal.getName());
    return "children";
  }
  @RequestMapping(value = {"/parents/children"}, method = RequestMethod.GET)
  public String child(Model model, Principal principal) {


    model.addAttribute("children");
    return showChildren(model, principal);
  }




}

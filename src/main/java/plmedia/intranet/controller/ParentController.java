package plmedia.intranet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Andreas Nissen
 */
@Controller
public class ParentController {



  public String showParentView(Model model) {
    return "parentview";
  }

  @RequestMapping(value = {"/parents"}, method = RequestMethod.GET)
  public String parentViewEmp(Model model) {

    model.addAttribute("parents");
    return showParentView(model);
  }



  public String showChildren(Model model) {
    return "children";
  }
  @RequestMapping(value = {"/children"}, method = RequestMethod.GET)
  public String child(Model model) {


    model.addAttribute("children");
    return showChildren(model);
  }




}

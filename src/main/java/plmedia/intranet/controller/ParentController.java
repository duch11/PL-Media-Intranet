package plmedia.intranet.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class ParentController {



  public String showParentView(Model model) {
    return "parentview";
  }

  @RequestMapping(value = {"parent"}, method = RequestMethod.GET)
  public String parentviewEmp(Model model) {

    model.addAttribute("parent");
    return showParentView(model);
  }




  @RequestMapping(value = {"/children"}, method = RequestMethod.GET)
  public String child(Model model) {


    model.addAttribute("test");

    return "children";
  }



}

package plmedia.intranet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

  @RequestMapping(value = {"/template"}, method = RequestMethod.GET)
  public String test(Model model) {
    model.addAttribute("test");
    return "template";
  }
  @RequestMapping(value = {"/", "","/adminpanel"}, method = RequestMethod.GET)
  public String adminPanel(Model model) {
    return "adminpanel";
  }

  @RequestMapping(value = {"/details"}, method = RequestMethod.GET)
  public String details(Model model) {
    return "detailsview";
  }



}

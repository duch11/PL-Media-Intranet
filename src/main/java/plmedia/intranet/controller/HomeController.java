package plmedia.intranet.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
* @author Jonas Holm
* */

@Controller
public class HomeController {


  /**
  * Gives a good overview over where the default entry point is
  * */
  @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
  public String defaultEntryPoint(Model model, Principal principal) {
      return new AdminController().showAdminPanel(model, principal);
  }

  @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
  public String login(Model model){
    return "login";
  }

  /**
  * Template tester
  * */
  @RequestMapping(value = {"/template"}, method = RequestMethod.GET)
  public String test(Model model) {


    model.addAttribute("test");

    return "template";
  }
}

package plmedia.intranet.controller;

import java.security.Principal;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
* @author Jonas Holm
 *@author Andreas Nissen
* */

@Controller
public class HomeController {


  /**
  * Gives a good overview over where the default entry point is
  * */
  @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
  public String defaultEntryPoint(Model model, Principal principal) {




    /**
     * Dirigere de rette users, i den rette retning
     * Her er det vigtigt at type har enten værdien ROLE_EMP eller ROLE_PAR
     * */
    if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_EMP"))){
      return new AdminController().showAdminPanel(model, principal);
    }
    return new ParentController().showParentView(model, principal);
  }

  @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
  public String login(Model model){
    return "login";
  }

  /**
  * Template tester
  * */


}

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

private void showPanals(Model model, Principal principal) {
  System.out.println("hejsa med dig");
  ArrayList<Child> children = new ArrayList<>();
  System.out.println(childRepo);
  for(Integer i : childRepo.ReadChildrenIDbyParentID
      (parentRepo.readParentByEmail(
          principal.getName()).getUserId())){
    children.add(childRepo.Read(i.intValue()));
  }
  model.addAttribute("children",children);
  model.addAttribute("test", principal.getName());
}

  public String showParentView(Model model, Principal principal) {

    System.out.println("før showpanels");
    showPanals(model, principal);
    System.out.println("før parent view");
    return "parentview";
  }



  @RequestMapping(value = {"/parents"}, method = RequestMethod.GET)
  public String parentViewEmp(Model model, Principal principal) {

    model.addAttribute("allUsers", true);
    model.addAttribute("employees", parentRepo.ReadAll());


    model.addAttribute("parents", parents);
    return showParentView(model, principal);
  }





  public String showChildView(Model model, Principal principal) {

    showPanals(model, principal);
    return "childview";
  }


  @RequestMapping(value = {"/parents/children"}, method = RequestMethod.GET)
  public String child(Model model, Principal principal) {



    return showChildView(model, principal);
  }




}

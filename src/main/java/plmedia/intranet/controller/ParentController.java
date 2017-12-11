package plmedia.intranet.controller;

import java.security.Principal;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import plmedia.intranet.dao.repository.AllergenRepo;
import plmedia.intranet.dao.repository.ChildRepo;
import plmedia.intranet.dao.repository.ParentRepo;
import plmedia.intranet.dao.repository.WingRepo;
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

  @Autowired
  AllergenRepo allergenRepo;

  @Autowired
  WingRepo wingRepo;

  // lists
  ArrayList<Parent> parents = new ArrayList<>();

private void showPanals(Model model, Principal principal) {

  ArrayList<Child> children = new ArrayList<>();
  for(Integer i : childRepo.ReadChildrenIDbyParentID
      (parentRepo.readParentByEmail(
          principal.getName()).getUserId())){
    children.add(childRepo.Read(i.intValue()));
  }

  model.addAttribute("children",children);
  model.addAttribute("test", principal.getName());
  System.out.println();

}

  public String showParentView(Model model, Principal principal) {


    showPanals(model, principal);

    return "parentview";
  }



  @RequestMapping(value = {"/parents"}, method = RequestMethod.GET)
  public String parentView(Model model, Principal principal) {


    model.addAttribute("children" , childRepo.ReadAll());


    model.addAttribute("parents", parents);
    return showParentView(model, principal);
  }

  @RequestMapping(value = {"/parent/details"}, method = RequestMethod.GET, params = {"parent"})
  public String parentDetails(Model model, Principal principal, @RequestParam int parent) {

    model.addAttribute("user", parentRepo.Read(parent));
    model.addAttribute("parentDetails", true);
    showParentView(model, principal);
    return "parentview";
  }





  public String showChildView(Model model, Principal principal) {

    showPanals(model, principal);
    return "childview";
  }




  @RequestMapping(value = {"/parents/children"}, method = RequestMethod.GET, params = {"child"})
  public String childDetails(Model model, Principal principal, @RequestParam int child) {
    model.addAttribute("child", childRepo.Read(child));
    model.addAttribute("allergen", allergenRepo.readAllergenByChildID(child));
    model.addAttribute("wing", wingRepo.Read(childRepo.Read(child).getWingId()));
    model.addAttribute("childDetails", true);
    showChildView(model,principal);
    return "childview";
  }


  /**
   * update
   */

  /**
   * update first and last name
   */
  @RequestMapping(value = {"/parent/update/child"}, method = RequestMethod.POST, params = {"firstName", "ID"})
  public String updateChildFirstName(@RequestParam String firstName, @RequestParam int ID){
    updateFirstName(firstName,ID);
    return "redirect:/parent/childview?child=" + ID;
  }


  public void updateFirstName(String firstName, int ID){
    System.out.println(firstName + "  " + ID);
  }

  @RequestMapping(value = {"/parent/update/child"}, method = RequestMethod.POST, params = {"lastName", "ID"})
  public String updateChildLastName(@RequestParam String lastName, @RequestParam int ID){
    updateLastName(lastName,ID);
    return "redirect:/parent/childview?child=" + ID;
  }

  public void updateLastName(String lastName, int ID){
    System.out.println(lastName + "  " + ID);
  }

  /**
   * update birthday
   */
  @RequestMapping(value = {"/parent/update/child"}, method = RequestMethod.POST, params = {"birthday", "ID"})
  public String updateChildbirthday(@RequestParam java.sql.Date birthday, @RequestParam int ID) {
    updateBirthday(birthday, ID);
    return "redirect:/parent/details?child=" + ID;
  }

    public void updateBirthday(java.sql.Date birthday , int ID){
      System.out.println(birthday + "  " + ID);
  }

  /**
   * update address
   */
  @RequestMapping(value = {"/parent/update/child"}, method = RequestMethod.POST, params = {"address", "ID"})
  public String updateChildAddress(@RequestParam String address, @RequestParam int ID){
    updateAddress(address,ID);
    return "redirect:/parent/details?child=" + ID;
  }


  public void updateAddress(String address, int ID) {
    System.out.println(address + "  " + ID);

  }

}

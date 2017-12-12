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

  Parent currentParent;



private void showPanals(Model model, Principal principal) {

  ArrayList<Child> children = new ArrayList<>();
  for(Integer i : childRepo.ReadChildrenIDbyParentID
      (parentRepo.readParentByEmail(
          principal.getName()).getUserId())){
    children.add(childRepo.Read(i.intValue()));
  }
  if(currentParent == null){
    currentParent = parentRepo.readParentByEmail(principal.getName());
  } else if (!currentParent.getUserEmail().equals(principal.getName())){
    currentParent = parentRepo.readParentByEmail(principal.getName());
  }
  model.addAttribute("children",children);
  model.addAttribute("currentUser", currentParent);
  model.addAttribute("user", currentParent);


}

  public String showParentView(Model model, Principal principal) {


    showPanals(model, principal);

    return "detailsview";
  }







  @RequestMapping(value = {"/parents"}, method = RequestMethod.GET)
  public String parentView(Model model, Principal principal) {



    return showParentView(model, principal);
  }

  @RequestMapping(value = {"/parents"}, method = RequestMethod.GET, params = {"status"})
  public String parentView(Model model,Principal principal, @RequestParam int status) {
    model.addAttribute("status", status);
    return showParentView(model, principal);
  }





  public String showChildView(Model model, Principal principal) {

    showPanals(model, principal);
    return "childview";
  }



  @RequestMapping(value = {"/parents/children"}, method = RequestMethod.GET, params = {"child"})
  public String childDetails(Model model, Principal principal, @RequestParam int child ) {
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
    Child child = childRepo.Read(ID);
    child.setFirstName(firstName);
    childRepo.Update(child);


    return "redirect:/parent/children?child=" + ID;
  }





  @RequestMapping(value = {"/parent/update/child"}, method = RequestMethod.POST, params = {"lastName", "ID"})
  public String updateChildLastName(@RequestParam String lastName, @RequestParam int ID){
    Child child = childRepo.Read(ID);
    child.setLastName(lastName);
    childRepo.Update(child);

    return "redirect:/parent/details?child=" + ID;
  }



  /**
   * update birthday
   */
  @RequestMapping(value = {"/parent/update/child"}, method = RequestMethod.POST, params = {"birthday", "ID"})
  public String updateChildbirthday(@RequestParam java.sql.Date birthday, @RequestParam int ID) {
    Child child = childRepo.Read(ID);
    child.setBirthday(birthday);
    childRepo.Update(child);
    return "redirect:/parent/children?child=" + ID;
  }


  /**
   * update address
   */
  @RequestMapping(value = {"/parent/update/child"}, method = RequestMethod.POST, params = {"address", "ID"})
  public String updateChildAddress(@RequestParam String address, @RequestParam int ID){
    Child child = childRepo.Read(ID);
    child.setAddress(address);
    childRepo.Update(child);
    return "redirect:/parent/children?child=" + ID;
  }


  /**
   * update parents
   */

 // @RequestMapping(value = {"/parent/update/child"}, method = RequestMethod.POST, params = {"parents", "ID"})
 // public  String updateChildParents(@RequestParam )

  /**
   * update allergens
   */




}

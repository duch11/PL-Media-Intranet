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
import plmedia.intranet.dao.repository.UtilRepo;
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

  @Autowired
  UtilRepo utilRepo;

private void showPanals(Model model, Principal principal) {
  Parent currentParent = parentRepo.readParentByEmail(principal.getName());

  ArrayList<Child> children = childRepo.readChildrenByParentID(currentParent.getUserId());
  model.addAttribute("children",children);
  model.addAttribute("currentUser", currentParent);
  model.addAttribute("user", currentParent);

}

  public String showParentView(Model model, Principal principal) {
    System.out.println("showParentView");

    showPanals(model, principal);

    return "detailsview";
  }

  @RequestMapping(value = {"/parents"}, method = RequestMethod.GET)
  public String parentView(Model model, Principal principal) {
    System.out.println("parentView");
    return showParentView(model, principal);
  }

  @RequestMapping(value = {"/parents"}, method = RequestMethod.GET, params = {"status"})
  public String parentView(Model model,Principal principal, @RequestParam int status) {
    model.addAttribute("status", status);
    return showParentView(model, principal);
  }

  @RequestMapping(value = {"/parents/children"}, method = RequestMethod.GET, params = {"child"})
  public String childDetails(Model model, Principal principal, @RequestParam int child ) {
    showPanals(model, principal);
    model.addAttribute("child", childRepo.Read(child));
    model.addAttribute("allergens", allergenRepo.readAllergenByChildID(child));
    model.addAttribute("allAllergens", allergenRepo.ReadAll());
    model.addAttribute("wing", wingRepo.Read(childRepo.Read(child).getWingId()));
    model.addAttribute("childsParents" , parentRepo.readParentByChildID(child));
    model.addAttribute("childDetails", true);
    return "childview";
  }

  /**
   * update parent name
   */
  @RequestMapping(value = {"/parent/update/parent"}, method = RequestMethod.POST, params = {"firstName", "lastName"})
  public String updateParName(Principal principal, @RequestParam String firstName, @RequestParam String lastName ) {
    Parent parent = parentRepo.readParentByEmail(principal.getName());
    parent.setFirstName(firstName);
    parent.setLastName(lastName);
    parentRepo.Update(parent);
    return "redirect:/parents";
  }
/*

  */
/**
   * update parent email
   *//*

  @RequestMapping(value = {"/parent/update/parent"}, method = RequestMethod.POST, params = {"email"})
  public String updateParEmail(@RequestParam String email) {
    Parent parent = parentRepo.Read(currentParent.getUserId());

    parent.setUserEmail(email);

    if(currentParent.getUserEmail().equals(email)){

      return "redirect:/parents";
    }
    parentRepo.Update(parent);

    return "redirect:/parents";
  }
*/


  /**
   * update password
   * @param oldPass
   * @param newPass
   * @param newPassRepeat
   * @return
   */
  @RequestMapping(value = {"/parent/update/parent"}, method = RequestMethod.POST, params = {"oldPass", "newPass", "newPassRepeat"})
  public String updateParPassword(Principal principal, @RequestParam String oldPass,@RequestParam String newPass,@RequestParam String newPassRepeat) {
    Parent parent = parentRepo.readParentByEmail(principal.getName());

    int checkPassStatus = utilRepo.checkPassword(parent.getUserId(), oldPass);
    Boolean newPassMatches = newPass.equals(newPassRepeat);


    if(newPassMatches && checkPassStatus == 1){
      parent.setPassword(newPass);
      parentRepo.Update(parent);
      return "redirect:/parents/?status=" + checkPassStatus;
    } else if(!newPassMatches) {
      return "redirect:/parents/?status=-2";
    }
    return "redirect:/parents/?status=" + checkPassStatus;
  }

  /**
   * update first name children
   */
  @RequestMapping(value = {"/parent/update/child"}, method = RequestMethod.POST, params = {"firstName", "ID"})
  public String updateChildFirstName(@RequestParam String firstName, @RequestParam int ID){
    Child child = childRepo.Read(ID);
    child.setFirstName(firstName);
    childRepo.Update(child);
    return "redirect:/parents/children?child=" + ID;
  }

  /**
   * update last name children
   */
  @RequestMapping(value = {"/parent/update/child"}, method = RequestMethod.POST, params = {"lastName", "ID"})
  public String updateChildLastName(@RequestParam String lastName, @RequestParam int ID){
    Child child = childRepo.Read(ID);
    child.setLastName(lastName);
    childRepo.Update(child);

    return "redirect:/parents/children?child=" + ID;
  }

  /**
   * update birthday
   */
  @RequestMapping(value = {"/parent/update/child"}, method = RequestMethod.POST, params = {"birthday", "ID"})
  public String updateChildbirthday(@RequestParam java.sql.Date birthday, @RequestParam int ID) {
    Child child = childRepo.Read(ID);
    child.setBirthday(birthday);
    childRepo.Update(child);
    return "redirect:/parents/children?child=" + ID;
  }

  /**
   * update address
   */
  @RequestMapping(value = {"/parent/update/child"}, method = RequestMethod.POST, params = {"address", "ID"})
  public String updateChildAddress(@RequestParam String address, @RequestParam int ID){
    Child child = childRepo.Read(ID);
    child.setAddress(address);
    childRepo.Update(child);
    return "redirect:/parents/children?child=" + ID;
  }

  /**
   * update Allergen
   */
  @RequestMapping(value = {"parent/update/child"}, method = RequestMethod.POST, params =  {"allergens", "ID"})
  public String updateChildAllergens(@RequestParam ArrayList<Integer> allergens, @RequestParam int ID){
    /** Remove the null value if there's no need for it (to avoid less 'pretty' occurencess with null and all)*/
    if(allergens.size() > 1){
      allergens.remove(null);
    }
    allergenRepo.updateChildAllergens(childRepo.Read(ID), allergens);

    return "redirect:/parents/children?child=" + ID;
  }
}

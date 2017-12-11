package plmedia.intranet.controller;
import java.security.Principal;
import java.util.ArrayList;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import plmedia.intranet.dao.repository.AllergenRepo;
import plmedia.intranet.dao.repository.ChildRepo;
import plmedia.intranet.dao.repository.EmployeeRepo;
import plmedia.intranet.dao.repository.GroupRepo;
import plmedia.intranet.dao.repository.ParentRepo;
import plmedia.intranet.dao.repository.PermissionRepo;
import plmedia.intranet.dao.repository.UtilRepo;
import plmedia.intranet.dao.repository.WingRepo;
import plmedia.intranet.model.*;

/**
 * @author Jonas Holm
 * @author Andreas
 * */

@Controller
public class AdminController {

  @Autowired
  AllergenRepo allergenRepo;

  @Autowired
  ChildRepo childRepo;

  @Autowired
  ParentRepo parentRepo;

  @Autowired
  EmployeeRepo employeeRepo;

  @Autowired
  GroupRepo groupRepo;

  @Autowired
  PermissionRepo permissionRepo;

  @Autowired
  UtilRepo utilRepo;

  @Autowired
  WingRepo wingRepo;

  Employee currentUser;

  public AdminController() {

  }


  /**
   * Base method for showing adminpanel
  * makes renaming and adding "universal" actions more sensible
  */


  public String showAdminPanel(Model model, Principal principal) {

    if(currentUser == null){
      currentUser = employeeRepo.readEmployeeByEmail(principal.getName());
    } else if (!currentUser.getUserEmail().equals(principal.getName())){
      currentUser = employeeRepo.readEmployeeByEmail(principal.getName());
    }

    /** ENG: Principal DK: "Grund-sikkerhedskonto" * */

    for(Permission perm : permissionRepo.readPermissionsByUserID(currentUser.getUserId())){
      model.addAttribute(perm.getPermissionName().replaceAll(" ", "_"), true);
    }

    model.addAttribute("currentUser", currentUser);
    model.addAttribute("employeeGroups", groupRepo.ReadAll());
    model.addAttribute("allWings", wingRepo.ReadAll());
    return "adminpanel";
  }



  @RequestMapping(value = {"/admin/employees", "/admin"}, method = RequestMethod.GET)
  public String adminPanelEmp(Model model, Principal principal) {
    model.addAttribute("allUsers", true);
    model.addAttribute("newEmployee", new Employee());
    model.addAttribute("employees", employeeRepo.ReadAll());
    return showAdminPanel(model, principal);
  }

  @RequestMapping(value = {"/admin/employees"}, method = RequestMethod.GET, params = {"groupID"})
  public String adminPanelEmpGroup(Model model, Principal principal, @RequestParam int groupID) {
    model.addAttribute("currentGroup", groupRepo.Read(groupID));
    model.addAttribute("employees", employeeRepo.readAllEmployeesByGroup(groupID));
    model.addAttribute("newEmployee", new Employee());

    return showAdminPanel(model, principal);
  }

  @RequestMapping(value = {"/admin/parents"}, method = RequestMethod.GET)
  public String adminPanelParents(Model model, Principal principal) {
    model.addAttribute("parents", parentRepo.ReadAll());
    model.addAttribute("newParent", new Parent());
    return showAdminPanel(model, principal);
  }

  @RequestMapping(value = {"/admin/children"}, method = RequestMethod.GET)
  public String adminPanelChildren(Model model, Principal principal) {
    model.addAttribute("children", childRepo.ReadAll());
    model.addAttribute("newChild", new Child());
    return showAdminPanel(model, principal);
  }

  @RequestMapping(value = {"/admin/settings"}, method = RequestMethod.GET)
  public String showAdminPanelSettings(Model model, Principal principal) {
    model.addAttribute("newGroup", new Group());
    model.addAttribute("newWing", new Wing());
    showAdminPanel(model, principal);
    return "settingsview";
  }


  @RequestMapping(value = {"/admin/details"}, method = RequestMethod.GET, params = {"employee"})
  public String empDetails(Model model,Principal principal, @RequestParam int employee) {
    showAdminPanel(model, principal);
    model.addAttribute("user", employeeRepo.Read(employee));
    model.addAttribute("employeeDetails", true);

    //removes the permission option if it's the user itself
    ArrayList<Permission> permissionsToSend = permissionRepo.readAllPermissions();
    if(employee == currentUser.getUserId()){
      ArrayList<Permission> substitutePermissions = new ArrayList<>();
      substitutePermissions.add(permissionRepo.readPermissionByID(28));
      substitutePermissions.add(permissionRepo.readPermissionByID(29));
      for (int i = 0; i<2; i++){
        int index = -1;
        for (Permission p : permissionsToSend) {
          if(p.getPermissionName().equals("Edit permissions") || p.getPermissionName().equals("Edit own permissions")){
            index = permissionsToSend.indexOf(p);
            break;
          }
        }
        permissionsToSend.remove(index);
      }
      model.addAttribute("substitutePermissions", substitutePermissions);
    }

    model.addAttribute("generalPermissions", permissionsToSend);
    return "detailsview";
  }

  @RequestMapping(value = {"/admin/details"}, method = RequestMethod.GET, params = {"employee", "status"})
  public String empDetails(Model model,Principal principal, @RequestParam int employee, @RequestParam int status) {
    model.addAttribute("status", status);
    return empDetails(model, principal, employee);
  }

  @RequestMapping(value = {"/admin/details"}, method = RequestMethod.GET, params = {"parent"})
  public String parentDetails(Model model, Principal principal, @RequestParam int parent) {
    model.addAttribute("user", parentRepo.Read(parent));
    model.addAttribute("parentDetails", true);
    showAdminPanel(model, principal);
    return "detailsview";
  }
  @RequestMapping(value = {"/admin/details"}, method = RequestMethod.GET, params = {"parent", "status"})
  public String parentDetails(Model model, Principal principal, @RequestParam int parent, @RequestParam int status) {
    model.addAttribute("status", status);
    return parentDetails(model, principal, parent);
  }


  @RequestMapping(value = {"/admin/details"}, method = RequestMethod.GET, params = {"child"})
  public String childDetails(Model model, Principal principal, @RequestParam int child) {
    model.addAttribute("child", childRepo.Read(child));
    model.addAttribute("wing", wingRepo.Read(childRepo.Read(child).getWingId()));
    model.addAttribute("allergen", allergenRepo.readAllergenByChildID(child));
    showAdminPanel(model,principal);
    return "childview";
  }


  /**
   * Create Methods
   * */
  @RequestMapping(value = {"/admin/create/employee"}, method = RequestMethod.POST)
  public String createUser(@ModelAttribute Employee newEmployee){

    employeeRepo.Create(newEmployee);

    return "redirect:/admin/employees";
  }

  @RequestMapping(value = {"/admin/create/parent"}, method = RequestMethod.POST)
  public String createParent(@ModelAttribute Parent newParent){
    parentRepo.Create(newParent);
    return "redirect:/admin/parents";
  }

  @RequestMapping(value = {"/admin/create/child"}, method = RequestMethod.POST)
  public String createChild(@ModelAttribute Child newChild){
    childRepo.Create(newChild);
    return "redirect:/admin/children";
  }

  /**
   * Update methods from here on
   * */

  @RequestMapping(value = {"/admin/update/group"}, method = RequestMethod.POST, params = {"groupName", "groupDescription", "ID"})
  public String updateGroupDetails(@RequestParam String groupName, @RequestParam String groupDescription, @RequestParam int ID) {

    Group updatedGroup = groupRepo.Read(ID);
    updatedGroup.setGroupName(groupName);
    updatedGroup.setGroupDescription(groupDescription);
    System.out.println("Update group returned code<" + groupRepo.Update(updatedGroup) + "> object: " + updatedGroup);

    return "redirect:/admin/settings";
  }

  @RequestMapping(value = {"/admin/update/wing"}, method = RequestMethod.POST, params = {"wingName", "wingDescription", "ID"})
  public String updateWingDetails(@RequestParam String wingName, @RequestParam String wingDescription, @RequestParam int ID) {

    Wing updatedWing = wingRepo.Read(ID);
    updatedWing.setWingName(wingName);
    updatedWing.setWingDescription(wingDescription);
    System.out.println("Update wing returned code<" + wingRepo.Update(updatedWing) + "> object: " + updatedWing);
    return "redirect:/admin/settings";
  }

  /**
   * Employee only update methods
   * */
  @RequestMapping(value = {"/admin/update/employee"}, method = RequestMethod.POST, params = {"permissionIDs", "ID"})
  public String updatePermission(@RequestParam ArrayList<Integer> permissionIDs, @RequestParam int ID ){

    /** Remove the null value if there's no need for it (to avoid less 'pretty' occurencess with null and all)*/
    if(permissionIDs.size() > 1){
      permissionIDs.remove(null);
    }
    permissionRepo.updatePermissionByID(employeeRepo.Read(ID),permissionIDs);
    return "redirect:/admin/details?employee=" + ID;
  }

  @RequestMapping(value = {"/admin/update/employee"}, method = RequestMethod.POST, params = {"groupID", "ID"})
  public String updateGroup(@RequestParam int groupID, @RequestParam int ID ){
    ArrayList<Integer> groupIdConversion = new ArrayList<>();
    groupIdConversion.add(groupID);
    utilRepo.updateEmployeeGroup(employeeRepo.Read(ID), groupIdConversion);
    return "redirect:/admin/details?employee=" + ID;
  }

  /**
   * Update Name + utility method (for redirection between parent and user)
   * */
  @RequestMapping(value = {"/admin/update/employee"}, method = RequestMethod.POST, params = {"firstName", "lastName", "ID"})
  public String updateEmpName(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int ID){
    Employee employee = employeeRepo.Read(ID);
    employee.setFirstName(firstName);
    employee.setLastName(lastName);
    employeeRepo.Update(employee);
    return "redirect:/admin/details?employee=" + ID;
  }

  @RequestMapping(value = {"/admin/update/parent"}, method = RequestMethod.POST, params = {"firstName", "lastName", "ID"})
  public String updateParName(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int ID){
    Parent parent = parentRepo.Read(ID);
    parent.setFirstName(firstName);
    parent.setLastName(lastName);
    parentRepo.Update(parent);
    return "redirect:/admin/details?parent=" + ID;
  }


  /**
   * Update Email + utility method (for redirection between parent and user)
   * */
  @RequestMapping(value = {"/admin/update/employee"}, method = RequestMethod.POST, params = {"email", "ID"})
  public String updateEmpEmail(Principal principal, @RequestParam String email, @RequestParam int ID){
    Employee employee = employeeRepo.Read(ID);
    employee.setUserEmail(email);
    if(currentUser.getUserEmail().equals(email)){

      return "redirect:/admin/details?employee=" + ID;
    }
    employeeRepo.Update(employee);
    return "redirect:/admin/details?employee=" + ID;
  }

  @RequestMapping(value = {"/admin/update/parent"}, method = RequestMethod.POST, params = {"email", "ID"})
  public String updateParEmail(@RequestParam String email, @RequestParam int ID){
    Parent parent = parentRepo.Read(ID);
    parent.setUserEmail(email);
    parentRepo.Update(parent);
    return "redirect:/admin/details?parent=" + ID;
  }


  /**
   * Update Password + utility method
   * */
  @RequestMapping(value = {"/admin/update/parent"}, method = RequestMethod.POST, params = {"oldPass", "newPass", "newPassRepeat", "ID"})
  public String updateParPassword(@RequestParam String oldPass,@RequestParam String newPass,@RequestParam String newPassRepeat, @RequestParam int ID) {
    Parent parent = parentRepo.Read(ID);
    int checkPassStatus = utilRepo.checkPassword(ID, oldPass);
    boolean newPassMatches = newPass.equals(newPassRepeat);

    if(newPassMatches && checkPassStatus == 1){
      parent.setPassword(newPass);
      parentRepo.Update(parent);
      return "redirect:/admin/details?parent=" + ID + "&status=" + checkPassStatus;
    } else if(!newPassMatches) {
      return "redirect:/admin/details?parent=" + ID + "&status=-2";
    }
    return "redirect:/admin/details?parent=" + ID + "&status=" + checkPassStatus;
  }

  @RequestMapping(value = {"/admin/update/employee"}, method = RequestMethod.POST, params = {"oldPass", "newPass", "newPassRepeat", "ID"})
  public String updateEmpPassword(@RequestParam String oldPass,@RequestParam String newPass,@RequestParam String newPassRepeat, @RequestParam int ID) {
    Employee employee = employeeRepo.Read(ID);
    int checkPassStatus = utilRepo.checkPassword(ID, oldPass);
    boolean newPassMatches = newPass.equals(newPassRepeat);

    if(newPassMatches && checkPassStatus == 1){
      employee.setPassword(newPass);
      employeeRepo.Update(employee);
      return "redirect:/admin/details?employee=" + ID + "&status=" + checkPassStatus;
    } else if(!newPassMatches) {
      return "redirect:/admin/details?employee=" + ID + "&status=-2";
    }
    return "redirect:/admin/details?employee=" + ID + "&status=" + checkPassStatus;
  }


}


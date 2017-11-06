package plmedia.intranet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {


    /*
    * Gives a good overview over where the default entry point is
    * */
    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String defaultEntryPoint(Model model) {
        return new AdminController().showAdminPanel(model);
    }


    /*
    * Template tester
    * */
    @RequestMapping(value = {"/template"}, method = RequestMethod.GET)
    public String test(Model model) {


        model.addAttribute("test");

        return "template";
    }
}

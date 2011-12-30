package no.dusken.medarbeidere;

import no.dusken.common.editor.BindByIdEditor;
import no.dusken.common.editor.CollectionEditor;
import no.dusken.common.editor.DateEditor;
import no.dusken.common.ldap.SaveToLdap;
import no.dusken.common.model.Department;
import no.dusken.common.model.Person;
import no.dusken.common.service.DepartmentService;
import no.dusken.common.service.PersonService;
import no.dusken.common.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author Marvin B. Lillehaug <lillehau@underdusken.no>
 */
@Controller
@RequestMapping("/medarbeidere")
public class MedarbeiderController {

    private String listview = "medarbeidere";
    private String medarbeiderview = "medarbeider";

    @Autowired
    private PersonService personService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private SaveToLdap saveToLdap;

    @RequestMapping("/aktive")
    public String getAktivePersons(Model model){
        model.addAttribute("medarbeidereheader", "Aktive personer");
        model.addAttribute("people", personService.getByActive());
        return listview;
    }

    @RequestMapping("/ikkeaktive")
    public String getIkkeAktivePersons(Model model){
        model.addAttribute("medarbeidereheader", "Ikke-aktive personer");
        model.addAttribute("people", personService.getByNotActive());
        return listview;
    }

    @RequestMapping(value = "/{uid}", method = RequestMethod.GET)
    public String getMedarbeider(@PathVariable String uid, Model model){
        Person person = personService.getByUsername(uid);

        model.addAttribute("person", person);
        return "view";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam String search, BindingResult result, Model model){
        model.addAttribute("medarbeidereheader", "Søk: " + search);
        model.addAttribute("people", null);
        return "index";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Department.class, new BindByIdEditor(departmentService));
        binder.registerCustomEditor(List.class, "roles", new CollectionEditor(List.class, roleService));
        binder.registerCustomEditor(Date.class, new DateEditor("yyyy-MM-dd"));
    }
}

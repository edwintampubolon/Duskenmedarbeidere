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

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @author Marvin B. Lillehaug <lillehau@underdusken.no>
 */
@Controller
@RequestMapping("/medarbeidere/rediger")
public class EditMedarbeiderController {

    @Autowired
    private PersonService personService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private SaveToLdap saveToLdap;

    @ModelAttribute
    public Person redigerMedarbeider(@PathVariable String uid){
        Person person = personService.getByUsername(uid);
        if(person == null){
            person = new Person();
            person.setUsername(uid);
        }

        return person;
    }

    @RequestMapping(value = "/{uid}", method = RequestMethod.GET)
    public String getRolesAndDepartmentsg(Model model){
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("departments", departmentService.findAll());
        return "medarbeider/medarbeider";
    }

    @RequestMapping(value = "/{uid}", method = RequestMethod.POST)
    public String lagreMedarbeider(@ModelAttribute @Valid Person person, BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getModel());
            model.addAttribute("person", person);
            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("departments", departmentService.findAll());
            return "medarbeider/medarbeider";
        } else {
            Long employeeNumber = saveToLdap.saveToLdap(person);
            person.setExternalID(employeeNumber);
            person.setExternalSource("Ldap");
            Person p = personService.save(person);
            return "redirect:/medarbeidere/" + p.getUsername();
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Department.class, new BindByIdEditor(departmentService));
        binder.registerCustomEditor(List.class, "roles", new CollectionEditor(List.class, roleService));
        binder.registerCustomEditor(Date.class, new DateEditor("yyyy-MM-dd"));
    }
}

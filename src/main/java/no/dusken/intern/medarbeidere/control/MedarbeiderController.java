package no.dusken.intern.medarbeidere.control;

import no.dusken.common.editor.BindByIdEditor;
import no.dusken.common.editor.CollectionEditor;
import no.dusken.common.editor.DateEditor;
import no.dusken.common.model.Department;
import no.dusken.common.model.Person;
import no.dusken.common.model.Person_;
import no.dusken.common.service.DepartmentService;
import no.dusken.common.service.PersonService;
import no.dusken.common.service.RoleService;
import no.dusken.intern.service.PersonSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Marvin B. Lillehaug <lillehau@underdusken.no>
 */
@Controller
@RequestMapping("/medarbeidere")
public class MedarbeiderController {

    @Autowired
    private PersonService personService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PersonSearch personSearch;

    @RequestMapping("/aktive")
    public String getAktivePersons(Model model){
        model.addAttribute("medarbeidereheader", "Aktive personer");
        model.addAttribute("people", personService.getByActive());
        return "medarbeider/medarbeidere";
    }

    @RequestMapping("/ikkeaktive")
    public String getIkkeAktivePersons(Model model){
        model.addAttribute("medarbeidereheader", "Ikke-aktive personer");
        model.addAttribute("people", personService.getByNotActive());
        return "medarbeider/medarbeidere";
    }

    @RequestMapping(value = "/{uid}", method = RequestMethod.GET)
    public String getMedarbeider(@PathVariable String uid, Model model){
        Person person = personService.getByUsername(uid);

        model.addAttribute("person", person);
        return "medarbeider/view";
    }

    @RequestMapping
    public String index(Model model){
        model.addAttribute("roles", roleService.findAll());
        return "medarbeider/index";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam final String search, Model model){
        model.addAttribute("medarbeidereheader", "Søk: " + search);
        model.addAttribute("people", personSearch.searchByFirstAndSurname(search));
        model.addAttribute("search", search);
        return "medarbeider/index";
    }

    @RequestMapping("/fodselsdager")
    public String fodselsdager(Model model){
        Specification<Person> datespecification = new Specification<Person>() {
            @Override
            public Predicate toPredicate(Root<Person> personRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Calendar> birthdate = personRoot.get(Person_.birthdate);
                Expression<Integer> birthmonth = cb.function("month", Integer.class, birthdate);
                Expression<Integer> birthday = cb.function("day", Integer.class, birthdate);

                Expression<java.sql.Date> currentdate = cb.currentDate();

                Expression<Integer> currentmonth = cb.function("month", Integer.class, currentdate);
                Expression<Integer> currentday = cb.function("day", Integer.class, currentdate);
                Predicate active = cb.equal(personRoot.get(Person_.active), true);

                return cb.and(cb.ge(birthmonth, currentmonth), cb.ge(birthday, currentday), active);
            }
        };

        Page all = personService.findAll(datespecification, new PageRequest(0, 5));
        model.addAttribute("personer", all.getContent());
        return "medarbeider/fodselsdager";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Department.class, new BindByIdEditor(departmentService));
        binder.registerCustomEditor(List.class, "roles", new CollectionEditor(List.class, roleService));
        binder.registerCustomEditor(Date.class, new DateEditor("yyyy-MM-dd"));
    }
}

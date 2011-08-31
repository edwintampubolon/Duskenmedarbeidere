package no.dusken.medarbeidere;

import no.dusken.common.model.Person;
import no.dusken.common.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @author Marvin B. Lillehaug <lillehau@underdusken.no>
 */
@Controller
public class MedarbeiderController {

    private String listview = "medarbeidere";
    private String medarbeiderview = "medarbeider";

    private PersonService personService;

    @RequestMapping("/aktive")
    public String getAktivePersons(Model model){
        model.addAttribute("header", "Aktive personer");
        model.addAttribute("person", personService.getActivePersons());
        return listview;
    }

    @RequestMapping("/ikkeaktive")
    public String getIkkeAktivePersons(Model model){
        model.addAttribute("header", "Ikke-aktive personer");
        model.addAttribute("person", personService.getNonActivePersons());
        return listview;
    }

    @RequestMapping(value = "/medarbeider/{uid}", method = RequestMethod.GET)
    public String getMedarbeider(@PathVariable String uid, Model model){
        model.addAttribute("person", personService.getPersonByUsername(uid));
        return medarbeiderview;
    }

    @RequestMapping(value = "/medarbeider/{uid}", method = RequestMethod.POST)
    public String updateMedarbeider(@Valid Person person, Model model){

        return medarbeiderview;
    }

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
}

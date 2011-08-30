package no.dusken.medarbeidere;

import no.dusken.common.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Marvin B. Lillehaug <lillehau@underdusken.no>
 */
@Controller
public class MedarbeiderController {

    private String view = "medarbeidere";
    private PersonService personService;

    @RequestMapping("/aktive")
    public String getAktivePersons(Model model){
        model.addAttribute("header", "Aktive personer");
        model.addAttribute("person", personService.getActivePersons());
        return view;
    }

    @RequestMapping("/ikkeaktive")
    public String getIkkeAktivePersons(Model model){
        model.addAttribute("header", "Ikke-aktive personer");
        model.addAttribute("person", personService.getNonActivePersons());
        return view;
    }

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
}

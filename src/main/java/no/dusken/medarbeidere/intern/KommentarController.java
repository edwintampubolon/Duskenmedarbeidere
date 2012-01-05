package no.dusken.medarbeidere.intern;

import no.dusken.medarbeidere.service.KommentarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/kommentar")
public class KommentarController {
    @Autowired
    private KommentarService kommentarService;

    @RequestMapping(value = "/${objekt}", method = RequestMethod.GET)
    public String getForObjekt(@PathVariable String object, Model model){
        model.addAttribute("kommentarer", kommentarService.findByKommentarTil(object));
        model.addAttribute("objectId", object);
        return "kommentar/view";
    }

    @RequestMapping(value = "/ny", method = RequestMethod.GET)
    public ResponseEntity ny(@ModelAttribute Kommentar kommentar){
        kommentarService.save(kommentar);
        return new ResponseEntity(HttpStatus.OK);
    }
}

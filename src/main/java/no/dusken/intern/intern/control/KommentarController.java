package no.dusken.intern.intern.control;

import no.dusken.intern.intern.model.Kommentar;
import no.dusken.intern.service.KommentarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/kommentar")
public class KommentarController {
    @Autowired
    private KommentarService kommentarService;

    @RequestMapping(value = "/{objekt}", method = RequestMethod.GET)
    public String getForObjekt(@PathVariable String objekt, @RequestParam(required = false, defaultValue = "0") Integer number, @RequestParam(defaultValue = "false", required = false) Boolean useTitle, Model model){
        if (number == 0) {
            model.addAttribute("kommentarer", kommentarService.findByKommentarTilOrderByTimeCreatedDesc(objekt));
        }else {
            Page<Kommentar> all = kommentarService.findByKommentarTilOrderByTimeCreatedDesc(objekt, new PageRequest(0, number));
            model.addAttribute("kommentarer", all.getContent());
        }
        model.addAttribute("count", kommentarService.findCountByKommentarTil(objekt));
        model.addAttribute("objectId", objekt);
        model.addAttribute("useTitle", useTitle);

        return "kommentar/view";
    }

    @RequestMapping(value = "/ny", method = RequestMethod.POST)
    public ResponseEntity ny(@ModelAttribute Kommentar kommentar){
        kommentarService.save(kommentar);
        return new ResponseEntity(HttpStatus.OK);
    }
}

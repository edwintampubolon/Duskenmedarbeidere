package no.dusken.intern.intern.control;

import no.dusken.intern.intern.Galleri;
import no.dusken.intern.service.GalleriService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RequestMapping("/galleri")
@Controller
public class GalleriController {

    @Value("${ dataDir }/galleri")
    private File imagefolder;

    @Autowired
    private GalleriService galleriService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("gallerier", galleriService.findAll());
        return "galleri/index";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String contentList(@RequestParam(required = false, defaultValue = "0") Integer number, Model model){
        if (number == 0) {
            model.addAttribute("gallerier", galleriService.findAll());
        } else {
            Page<Galleri> gallerier = galleriService.findAll(new PageRequest(0, number));
            model.addAttribute("gallerier", gallerier.getContent());
        }
        return "galleri/include/gallerier";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String visGalleri(@PathVariable Long id, Model model){
        model.addAttribute("galleri", galleriService.findOne(id));
        return "galleri/view";
    }

    @RequestMapping(value = "/nytt", method = RequestMethod.GET)
    public String nyttGalleri(){
        return "galleri/new";
    }

    @RequestMapping(value = "/nytt", method = RequestMethod.POST)
    public String uploadImages(@RequestParam String navn, @RequestParam MultipartFile[] files) throws IOException {
        File gallerimappe = new File(imagefolder, navn);
        for(int i = 2; gallerimappe.exists(); i++){
            gallerimappe = new File(imagefolder, navn + i);
        }
        gallerimappe.mkdirs();
        Galleri galleri = new Galleri();
        galleri.setNavn(navn);
        List<String> bilder = galleri.getBilder();
        for (int i = 0, filesLength = files.length; i < filesLength; i++) {
            MultipartFile file = files[i];
            String originalFilename = file.getOriginalFilename();
            String filnavn = i + originalFilename.substring(originalFilename.lastIndexOf("."));
            bilder.add(filnavn);
            IOUtils.copy(file.getInputStream(), new FileOutputStream(new File(gallerimappe, filnavn)));
        }

        galleri = galleriService.save(galleri);
        return "redirect:/galleri/" + galleri.getId();
    }
}

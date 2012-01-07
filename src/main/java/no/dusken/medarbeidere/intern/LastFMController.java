package no.dusken.medarbeidere.intern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.CharacterData;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/lastfm")
public class LastFMController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private String rssUrl = "http://ws.audioscrobbler.com/1.0/user/duskenbaren/recenttracks.rss";
    //Tue, 15 Nov 2011 03:51:02 +0000
    private SimpleDateFormat inputDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
    private SimpleDateFormat outDateFormat = new SimpleDateFormat("EEE HH:mm:ss dd MMM yyyy", Locale.forLanguageTag("no"));

    @RequestMapping
    public String getLastFM(Model model){
        model.addAttribute("items", writeNews());
        return "lastfm";
    }

    private List<no.dusken.medarbeidere.intern.RSSItem> writeNews() {

        List<no.dusken.medarbeidere.intern.RSSItem> items = new LinkedList<no.dusken.medarbeidere.intern.RSSItem>();
        try {

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            URL u = new URL(rssUrl);

            Document doc = builder.parse(u.openStream());

            NodeList nodes = doc.getElementsByTagName("item");

            for(int i=0;i<nodes.getLength();i++) {
                Element element = (Element)nodes.item(i);
                RSSItem rssItem = new RSSItem();
                rssItem.setTitle(getElementValue(element, "title"));
                rssItem.setLink(getElementValue(element,"link"));
                Date time = inputDateFormat.parse(getElementValue(element, "pubDate"));
                rssItem.setTime(outDateFormat.format(time));
                items.add(rssItem);
            }
        }
        catch(Exception ex) {
            log.error("Derp", ex);
        }
        return items;
    }

    private String getCharacterDataFromElement(Element e) {
        try {
            Node child = e.getFirstChild();
            if(child instanceof CharacterData) {
                CharacterData cd = (CharacterData) child;
                return cd.getData();
            }
        }
        catch(Exception ex) {
            log.error("derp", ex);
        }
        return "";
    }

    private String getElementValue(Element parent,String label) {
        return getCharacterDataFromElement((Element)parent.getElementsByTagName(label).item(0));
    }
}

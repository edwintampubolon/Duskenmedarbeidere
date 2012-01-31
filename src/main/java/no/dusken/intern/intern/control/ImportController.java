package no.dusken.intern.intern.control;

import no.dusken.intern.intern.Galleri;
import no.dusken.intern.intern.model.Kommentar;
import no.dusken.intern.service.GalleriService;
import no.dusken.intern.service.KommentarService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import java.io.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping(value = "/import")
public class ImportController {

    private JdbcTemplate jdbcTemplate;
    @Autowired
    private KommentarService kommentarService;

    @Autowired
    private GalleriService galleriService;

    @Value("${dataDir}/galleri")
    private File imagefolder;

    private SimpleDateFormat format = new SimpleDateFormat("EEE dd.MM.yyyy HH:mm");

    @PostConstruct
    public void init(){
        DriverManagerDataSource datasource = new DriverManagerDataSource("jdbc:mysql://pegasus.underdusken.no/db_web", "readonly", "prtz2oktr");
        datasource.setDriverClassName("com.mysql.jdbc.Driver");
        jdbcTemplate = new JdbcTemplate(datasource);
    }

    @RequestMapping(value = "/nyheter", method = RequestMethod.GET)
    public String importNyheter(){

        List<Kommentar> query = jdbcTemplate.query("select * from internnyheter", new RowMapper<Kommentar>() {
            @Override
            public Kommentar mapRow(ResultSet rs, int rowNum) throws SQLException {
                Kommentar kommentar = new Kommentar();
                String bruker = rs.getString("bruker");
                if(bruker == null){
                    bruker = "Ukjent";
                }

                kommentar.setForfatter(bruker);
                kommentar.setTittel(rs.getString("overskrift"));
                kommentar.setKommentar(rs.getString("tekst"));

                Date dato = rs.getDate("dato");
                if (dato != null) {
                    GregorianCalendar gregorianCalendar = new GregorianCalendar();
                    gregorianCalendar.setTimeInMillis(dato.getTime());
                    String tidspunkt = rs.getString("tidspunkt");
                    if (tidspunkt != null) {
                        String[] split = tidspunkt.split(":");
                        gregorianCalendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(split[0]));
                        gregorianCalendar.set(Calendar.MINUTE, Integer.parseInt(split[1]));
                    }
                    kommentar.setTimeCreated(gregorianCalendar);
                }
                return kommentar;
            }
        });
        kommentarService.save(query);
        return "";
    }

    @RequestMapping(value = "/bilder", method = RequestMethod.GET)
    public String importBilder() throws IOException, ParseException {
        File bilder = new File("/home/marv/Desktop/festing");
        FileFilter dirFilter = getDirFilter();
        for(File bildeDir : bilder.listFiles(dirFilter)){
            Galleri galleri = new Galleri();
            String galleriName = bildeDir.getName();
            galleri.setNavn(galleriName);
            File gallerimappe = new File(imagefolder, galleriName);
            gallerimappe.mkdirs();

            List<String> galleribilder = galleri.getBilder();
            File[] listFiles = bildeDir.listFiles(jpgFilter());
            for (int i = 0, listFilesLength = listFiles.length; i < listFilesLength; i++) {
                File f = listFiles[i];
                String name = f.getName();
                int endIndex = name.lastIndexOf(".");

                String filename = i + name.substring(endIndex);
                saveKommentar(bildeDir, name, filename, galleriName);
                galleribilder.add(filename);

                if(i == 0){
                    GregorianCalendar timeCreated = new GregorianCalendar();
                    timeCreated.setTime(new java.util.Date(f.lastModified()));
                    galleri.setTimeCreated(timeCreated);
                }

                FileInputStream input = new FileInputStream(f);
                FileOutputStream output = new FileOutputStream(new File(gallerimappe, filename));

                IOUtils.copy(input, output);

                IOUtils.closeQuietly(input);
                IOUtils.closeQuietly(output);
            }
            galleriService.save(galleri);
        }
        return "";
    }

    private void saveKommentar(File dir, String derpname, String herpName, String galleriName) throws IOException, ParseException {
        String kommentarFileName = "comment_" + derpname + ".txt";
        File kommentarer = new File(dir, kommentarFileName);
        if (kommentarer.exists()) {
            BufferedReader in  = new BufferedReader(new FileReader(kommentarer));
            StringBuilder stringBuilder = new StringBuilder();
            String fileline;
            while ((fileline = in.readLine()) != null){
                stringBuilder.append(fileline.trim());
            }
            for(String line : stringBuilder.toString().split("<p>")){
                if (line.length() > 0) {
                    derp(line, galleriName, herpName);
                }
            }
            in.close();
        }
    }

    public void derp(String line, String galleriName, String herpName) throws ParseException {
        Pattern pattern = Pattern.compile("(.*)<!--.brukernavn:(\\w*).-->.*\\((.*)\\)");
        Matcher matcher = pattern.matcher(line);
        if(matcher.matches()){
            String kommentarString = matcher.group(1);
            String brukernavn = matcher.group(2);
            String dato = matcher.group(3);


            Kommentar kommentar = new Kommentar();
            kommentar.setForfatter(brukernavn);
            kommentar.setKommentar(kommentarString);
            Calendar timeCreated = new GregorianCalendar();
            timeCreated.setTime(format.parse(dato));
            kommentar.setTimeCreated(timeCreated);
            kommentar.setKommentarTil(galleriName + herpName);
        }
    }
    private FilenameFilter jpgFilter() {
        return new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".jpg");
            }
        };
    }

    private FileFilter getDirFilter() {
        return new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        };
    }
}

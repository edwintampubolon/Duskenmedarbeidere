package no.dusken.intern.intern.control;

import no.dusken.intern.intern.model.Kommentar;
import no.dusken.intern.service.KommentarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
@RequestMapping(value = "/import")
public class ImportController {

    private JdbcTemplate jdbcTemplate;
    @Autowired
    private KommentarService kommentarService;

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
}

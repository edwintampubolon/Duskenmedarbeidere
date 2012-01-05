package no.dusken.medarbeidere.intern;

import no.dusken.common.model.DuskenObject;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Kommentar extends DuskenObject{
    @Column(length = 10000)
    private String kommentar;

    private String tittel;

    private String kommentarTil;

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }

    public String getTittel() {
        return tittel;
    }

    public void setTittel(String tittel) {
        this.tittel = tittel;
    }

    public String getKommentarTil() {
        return kommentarTil;
    }

    public void setKommentarTil(String kommentarTil) {
        this.kommentarTil = kommentarTil;
    }
}

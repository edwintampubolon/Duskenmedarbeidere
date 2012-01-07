package no.dusken.intern.intern;

import no.dusken.common.model.DuskenObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Kommentar extends DuskenObject{
    @Column(length = 10000)
    private String kommentar;

    private String tittel;

    private String kommentarTil;

    @NotNull
    private String forfatter;

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

    public String getForfatter() {
        return forfatter;
    }

    public void setForfatter(String forfatter) {
        this.forfatter = forfatter;
    }
}

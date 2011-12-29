package no.dusken.medarbeidere.intern;

import no.dusken.common.model.DuskenObject;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Kommentar extends DuskenObject{
    @Column(length = 10000)
    private String kommentar;

    private DuskenObject kommentarTil;

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }

    public DuskenObject getKommentarTil() {
        return kommentarTil;
    }

    public void setKommentarTil(DuskenObject kommentarTil) {
        this.kommentarTil = kommentarTil;
    }
}

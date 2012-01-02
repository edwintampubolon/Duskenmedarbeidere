package no.dusken.medarbeidere.intern;

import no.dusken.common.model.DuskenObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Kommentar extends DuskenObject{
    @Column(length = 10000)
    private String kommentar;

    @ManyToOne
    private Galleri kommentarTil;

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }

    public DuskenObject getKommentarTil() {
        return kommentarTil;
    }

    public void setKommentarTil(Galleri kommentarTil) {
        this.kommentarTil = kommentarTil;
    }
}

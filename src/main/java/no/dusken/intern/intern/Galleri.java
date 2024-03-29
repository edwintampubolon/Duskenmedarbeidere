package no.dusken.intern.intern;

import no.dusken.common.model.DuskenObject;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Galleri extends DuskenObject {

    public String navn;

    @ElementCollection
    public List<String> bilder = new LinkedList<String>();

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public List<String> getBilder() {
        return bilder;
    }

    public void setBilder(List<String> bilder) {
        this.bilder = bilder;
    }
}

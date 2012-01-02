package no.dusken.medarbeidere.intern;

import no.dusken.common.model.DuskenObject;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Entity
public class Galleri extends DuskenObject {

    public String navn;

    @ElementCollection
    public List<String> bilder;

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

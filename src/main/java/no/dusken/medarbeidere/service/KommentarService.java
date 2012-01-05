package no.dusken.medarbeidere.service;

import no.dusken.common.service.GenericService;
import no.dusken.medarbeidere.intern.Kommentar;

public interface KommentarService extends GenericService<Kommentar> {
    Kommentar findByKommentarTil(String galleri);
}

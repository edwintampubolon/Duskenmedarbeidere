package no.dusken.intern.service;

import no.dusken.common.service.GenericService;
import no.dusken.intern.intern.Kommentar;

import java.util.List;

public interface KommentarService extends GenericService<Kommentar> {
    List<Kommentar> findByKommentarTil(String galleri);

    Long findCountByKommentarTil(String objekt);
}
